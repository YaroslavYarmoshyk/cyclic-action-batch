package org.cyclic.action.batch.config.writer;

import lombok.RequiredArgsConstructor;
import org.cyclic.action.batch.config.annotations.ActionHistoryDbWriter;
import org.cyclic.action.batch.config.annotations.ActionHistoryWriter;
import org.cyclic.action.batch.config.writer.sql.PositionSQL;
import org.cyclic.action.batch.dao.InMemoryStore;
import org.cyclic.action.batch.model.Position;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class ItemWriterConfig {
    private final DataSource dataSource;

    @Bean
    @Profile(value = "!dev")
    @StepScope
//    @ActionHistoryDbWriter
    @ActionHistoryWriter
    public ItemWriter<Position> actionHistoryDbWriter() {
        return new JdbcBatchItemWriterBuilder<Position>()
                .dataSource(dataSource)
                .sql(PositionSQL.POSITION_QUERY)
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .build();
    }

    @Bean
    @Profile(value = "dev")
    @StepScope
    @ActionHistoryWriter
    public ItemWriter<Position> actionHistoryWriter() {
        return (ItemStreamWriter<Position>) chunk -> InMemoryStore.addPositionsToHistory(chunk.getItems());
    }
}
