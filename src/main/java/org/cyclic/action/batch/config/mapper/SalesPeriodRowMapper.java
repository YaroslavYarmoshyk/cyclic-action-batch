package org.cyclic.action.batch.config.mapper;

import org.cyclic.action.batch.model.SalesPeriod;
import org.springframework.batch.extensions.excel.RowMapper;
import org.springframework.batch.extensions.excel.support.rowset.RowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Properties;

import static com.excel.custom.library.util.MappingUtils.castToType;
import static org.cyclic.action.batch.util.Constants.*;
import static org.cyclic.action.batch.util.CyclicUtil.isForMapping;

@Component
public class SalesPeriodRowMapper implements RowMapper<SalesPeriod> {

    @Override
    public SalesPeriod mapRow(final RowSet rowSet) {
        if (isForMapping(rowSet, ACTUAL_SALES_CODE)) {
            return map(rowSet);
        }
        return null;
    }

    private SalesPeriod map(final RowSet rowSet) {
        final Properties properties = rowSet.getProperties();
        return SalesPeriod.builder()
                .store(getValidStoreName(properties))
                .actionCode(castToType(properties.get(ACTUAL_SALES_CODE), Integer.class))
                .actionAverageSales(castToType(properties.get(ACTUAL_AVERAGE_SALES), BigDecimal.class))
                .build();
    }

    private static String getValidStoreName(final Properties properties) {
        return Optional.ofNullable(castToType(properties.get(ACTUAL_SALES_STORE), String.class))
                .map(stockName -> stockName.replaceAll(REDUNDANT_CHARACTERS_IN_STORE_NAME, ""))
                .orElse(null);
    }
}
