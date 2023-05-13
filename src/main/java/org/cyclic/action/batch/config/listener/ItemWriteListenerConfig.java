package org.cyclic.action.batch.config.listener;

import lombok.extern.slf4j.Slf4j;
import org.cyclic.action.batch.model.Position;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.annotation.AfterWrite;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@Component
public class ItemWriteListenerConfig {

    @Bean
    @StepScope
    public ItemWriteListener<Position> actionWriteListener() {
        return new ItemWriteListener<>() {
            private Instant start;

            @BeforeWrite
            public void beforeWrite(final Chunk<? extends Position> items) {
                start = Instant.now();
            }

            @AfterWrite
            public void afterWrite(final Chunk<? extends Position> items) {
                log.info(
                        "Writing {} positions to action history took {} milliseconds",
                        items.size(),
                        Duration.between(start, Instant.now()).toMillis()
                );
            }
        };
    }
}
