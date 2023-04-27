package org.cyclic.action.batch.config.reader;

import com.etake.parent.config.reader.ExcelItemReader;
import lombok.RequiredArgsConstructor;
import org.cyclic.action.batch.config.mapper.PositionRowMapper;
import org.cyclic.action.batch.config.mapper.SalesPeriodRowMapper;
import org.cyclic.action.batch.config.properties.ExcelManagementProperties;
import org.cyclic.action.batch.model.Position;
import org.cyclic.action.batch.model.SalesPeriod;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class ExcelItemReaderConfig {
    private final ExcelManagementProperties excelProperties;
    private final PositionRowMapper positionRowMapper;
    private final SalesPeriodRowMapper salesPeriodRowMapper;
    private final ExcelItemReader excelItemReader;

    @Bean
    public ItemStreamReader<Position> actionHistoryReader() {
        final String actionHistoryFilePath = excelProperties.getActionHistoryFilePath();
        final int actionSkipLines = excelProperties.getActionHistorySkipLines();
        final int actionHistoryHeaderRowIndex = excelProperties.getActionHistoryHeaderRowIndex();
        return excelItemReader.getReader(actionHistoryFilePath, positionRowMapper, actionSkipLines, actionHistoryHeaderRowIndex);
    }

    @Bean
    public ItemStreamReader<SalesPeriod> actualAvgSalesReader() {
        final String avgSalesFilePath = excelProperties.getAvgSalesFilePath();
        final int avgSalesSkipLines = excelProperties.getAvgSalesSkipLines();
        final int avgSalesHeaderRowIndex = excelProperties.getAvgSalesHeaderRowIndex();
        return excelItemReader.getReader(avgSalesFilePath, salesPeriodRowMapper, avgSalesSkipLines, avgSalesHeaderRowIndex);
    }
}
