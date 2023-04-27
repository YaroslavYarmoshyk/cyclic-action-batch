package org.cyclic.action.batch.config;

import lombok.RequiredArgsConstructor;
import org.cyclic.action.batch.config.processor.ActionHistoryItemProcessor;
import org.cyclic.action.batch.config.processor.CyclicActionItemProcessor;
import org.cyclic.action.batch.config.reader.ForecastItemReader;
import org.cyclic.action.batch.config.writer.ExcelPoiItemWriter;
import org.cyclic.action.batch.model.Position;
import org.cyclic.action.batch.model.SalesPeriod;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class JobConfiguration {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final ItemStreamReader<Position> actionHistoryReader;
    private final ItemStreamReader<SalesPeriod> actualAvgSalesReader;
    private final ForecastItemReader forecastItemReader;
    private final ItemStreamWriter<Position> actionHistoryWriter;
    private final ItemStreamWriter<SalesPeriod> actualAvgSalesWriter;
    private final ExcelPoiItemWriter cyclicActionItemWriter;
    private final CyclicActionItemProcessor cyclicActionItemProcessor;
    private final ActionHistoryItemProcessor actionHistoryItemProcessor;

    @Bean
    public Job job() {
        return new JobBuilder("firstJob", jobRepository)
                .start(firstStep())
                .next(secondStep())
                .next(thirdStep())
                .build();
    }

    @Bean
    public Step firstStep() {
        return new StepBuilder("actionHistoryStep", jobRepository)
                .<Position, Position>chunk(500, transactionManager)
                .reader(actionHistoryReader)
                .processor(actionHistoryItemProcessor)
                .writer(actionHistoryWriter)
                .build();
    }

    @Bean
    public Step secondStep() {
        return new StepBuilder("actualSalesStep", jobRepository)
                .<SalesPeriod, SalesPeriod>chunk(10, transactionManager)
                .reader(actualAvgSalesReader)
                .writer(actualAvgSalesWriter)
                .build();
    }

    @Bean
    public Step thirdStep() {
        return new StepBuilder("forecastStep", jobRepository)
                .<Position, Position>chunk(500, transactionManager)
                .reader(forecastItemReader)
                .processor(cyclicActionItemProcessor)
                .writer(cyclicActionItemWriter)
                .build();
    }

    @Bean
    public Tasklet tasklet() {
        return (contribution, chunkContext) -> {
            System.out.println("It's finally working");
            return RepeatStatus.FINISHED;
        };
    }
}
