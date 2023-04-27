package org.cyclic.action.batch.config.processor;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.cyclic.action.batch.dao.InMemoryStore;
import org.cyclic.action.batch.enumeration.Algorithm;
import org.cyclic.action.batch.model.Position;
import org.cyclic.action.batch.model.SalesPeriod;
import org.cyclic.action.batch.service.AlgorithmService;
import org.cyclic.action.batch.service.AverageSalesService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static org.cyclic.action.batch.dao.InMemoryStore.actualAvgSales;
import static org.cyclic.action.batch.util.Constants.DEFAULT_AVERAGE_SALES_SCALE;
import static org.cyclic.action.batch.util.Constants.DEFAULT_ROUNDING_MODE;

@Component
@RequiredArgsConstructor
public class CyclicActionItemProcessor implements ItemProcessor<Position, Position> {
    private final AlgorithmService algorithmService;
    private final AverageSalesService averageSalesService;

    @Override
    public Position process(@NonNull final Position position) {
        final Map<Algorithm, List<Position>> algorithmMap = algorithmService.defineAlgorithmMap(position, InMemoryStore.actionHistory);
        final Algorithm algorithm = algorithmService.getAlgorithm(algorithmMap);

        final BigDecimal beforeActionAvgSales = defineAverageSales(algorithm, algorithmMap.get(algorithm), Position::getBeforeActionAverageSales);
        final BigDecimal actionAvgSales = defineAverageSales(algorithm, algorithmMap.get(algorithm), Position::getActionAverageSales);
        final BigDecimal actualAverageSales = getActualAverageSales(position, actualAvgSales);

        position.setAlgorithm(algorithm);
        position.setBeforeActionAverageSales(beforeActionAvgSales);
        position.setActionAverageSales(actionAvgSales);
        position.setActualAverageSales(actualAverageSales);

        return position;
    }

    private BigDecimal defineAverageSales(final Algorithm algorithm,
                                          final List<Position> positions,
                                          final Function<Position, BigDecimal> avgSalesFunc) {
        final boolean exactAlgorithm = Algorithm.isAlgorithmExact(algorithm);
        final boolean exactSales = salesExistBeforeAndDuringAction(positions);
        return exactAlgorithm && exactSales ?
                averageSalesService.getExactAverageSales(positions, avgSalesFunc) :
                averageSalesService.getAverageSales(positions, avgSalesFunc);
    }

    private BigDecimal getActualAverageSales(final Position position, final List<SalesPeriod> actualSales) {
        return actualSales.stream()
                .filter(actual -> Objects.equals(actual.getStore(), position.getStore())
                        && Objects.equals(actual.getActionCode(), position.getActionCode()))
                .findFirst()
                .map(SalesPeriod::getActionAverageSales)
                .map(bigDecimal -> bigDecimal.setScale(DEFAULT_AVERAGE_SALES_SCALE, DEFAULT_ROUNDING_MODE))
                .orElse(BigDecimal.ZERO);
    }

    private boolean salesExistBeforeAndDuringAction(final List<Position> positions) {
        return positions.stream()
                .anyMatch(pos -> Objects.nonNull(pos.getBeforeActionAverageSales())
                        && Objects.nonNull(pos.getActionAverageSales()));
    }
}
