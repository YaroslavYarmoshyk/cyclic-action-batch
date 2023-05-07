package org.cyclic.action.batch.config.reader;

import org.cyclic.action.batch.dao.InMemoryStore;
import org.cyclic.action.batch.model.Position;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListItemReaderConfig {

    @Bean
    @StepScope
    public ListItemReader<Position> cyclicActionListReader() {
        return new ListItemReader<>(InMemoryStore.cyclicAction);
    }
}
