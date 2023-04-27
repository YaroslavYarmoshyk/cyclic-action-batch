package org.cyclic.action.batch.util;

import org.apache.logging.log4j.util.Strings;
import org.cyclic.action.batch.model.Position;
import org.springframework.batch.extensions.excel.support.rowset.RowSet;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.function.Function;

import static org.cyclic.action.batch.util.Constants.*;

public final class CyclicUtil {

    public static Map<String, Integer> createReportColumnIndexes() {
        final Map<String, Integer> indexesMap = new HashMap<>();
        indexesMap.put(MANAGER, 0);
        indexesMap.put(STORE, 1);
        indexesMap.put(STORE_FORMAT, 2);
        indexesMap.put(CARRYOVER, 3);
        indexesMap.put(THIRD_GROUP, 4);
        indexesMap.put(ACTION_CODE, 5);
        indexesMap.put(NAME, 6);
        indexesMap.put(ALGORITHM, 7);
        indexesMap.put(BEFORE_ACTION_AVERAGE_SALES, 8);
        indexesMap.put(ACTION_AVERAGE_SALES, 9);
        indexesMap.put(ACTUAL_AVERAGE_SALES_RESULT, 10);
        indexesMap.put(DYNAMIC_COEFFICIENT, 11);
        indexesMap.put(FORECAST_ACTION_AVERAGE_SALES, 12);
        indexesMap.put(FORECAST_SALES, 13);
        return indexesMap;
    }

    public static Map<String, Function<Position, Object>> createPositionSuppliers() {
        Map<String, Function<Position, Object>> supplierMap = new HashMap<>();
        supplierMap.put(MANAGER, Position::getManager);
        supplierMap.put(STORE, Position::getStore);
        supplierMap.put(STORE_FORMAT, Position::getStoreFormat);
        supplierMap.put(CARRYOVER, Position::getCarryover);
        supplierMap.put(THIRD_GROUP, Position::getThirdGroup);
        supplierMap.put(ACTION_CODE, Position::getActionCode);
        supplierMap.put(NAME, Position::getName);
        supplierMap.put(ALGORITHM, position -> position.getAlgorithm().getNumber());
        supplierMap.put(BEFORE_ACTION_AVERAGE_SALES, Position::getBeforeActionAverageSales);
        supplierMap.put(ACTION_AVERAGE_SALES, Position::getActionAverageSales);
        supplierMap.put(ACTUAL_AVERAGE_SALES_RESULT, Position::getActualAverageSales);
        return supplierMap;
    }

    public static boolean isAnalyzedPosition(final Position position,
                                             final LocalDate actionStartDate,
                                             final LocalDate actionEndDate) {
        return Objects.equals(position.getActionStartDate(), actionStartDate)
                && Objects.equals(position.getActionEndDate(), actionEndDate);
    }

    public static boolean isForMapping(final RowSet rowSet, final String key) {
        try {
            final Properties properties = rowSet.getProperties();
            return Strings.isNotBlank(properties.get(key).toString());
        } catch (Exception e) {
            return false;
        }
    }
}
