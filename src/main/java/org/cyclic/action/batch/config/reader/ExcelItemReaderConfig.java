package org.cyclic.action.batch.config.reader;

import com.etake.parent.config.reader.ExcelItemReader;
import lombok.RequiredArgsConstructor;
import org.cyclic.action.batch.config.annotations.ActionHistoryReader;
import org.cyclic.action.batch.config.annotations.ActualAvgSalesReader;
import org.cyclic.action.batch.config.mapper.PositionRowMapper;
import org.cyclic.action.batch.config.mapper.SalesPeriodRowMapper;
import org.cyclic.action.batch.config.properties.ExcelManagementProperties;
import org.cyclic.action.batch.model.Position;
import org.cyclic.action.batch.model.SalesPeriod;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.extensions.excel.poi.PoiItemReader;
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
    @StepScope
    @ActionHistoryReader
    public PoiItemReader<Position> actionHistoryReader() {
        final String actionHistoryFilePath = excelProperties.getActionHistoryFilePath();
        final int actionSkipLines = excelProperties.getActionHistorySkipLines();
        final int actionHistoryHeaderRowIndex = excelProperties.getActionHistoryHeaderRowIndex();
        return excelItemReader.getReader(actionHistoryFilePath, positionRowMapper, actionSkipLines, actionHistoryHeaderRowIndex);
    }

    @Bean
    @StepScope
    @ActualAvgSalesReader
    public PoiItemReader<SalesPeriod> actualAvgSalesReader() {
        final String avgSalesFilePath = excelProperties.getAvgSalesFilePath();
        final int avgSalesSkipLines = excelProperties.getAvgSalesSkipLines();
        final int avgSalesHeaderRowIndex = excelProperties.getAvgSalesHeaderRowIndex();
        return excelItemReader.getReader(avgSalesFilePath, salesPeriodRowMapper, avgSalesSkipLines, avgSalesHeaderRowIndex);
    }
}
