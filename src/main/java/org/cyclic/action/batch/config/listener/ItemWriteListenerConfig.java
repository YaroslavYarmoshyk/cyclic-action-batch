package org.cyclic.action.batch.config.listener;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.cyclic.action.batch.model.Position;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
@Slf4j
public class ItemWriteListenerConfig{
    private Instant start;

    @Bean
    @StepScope
    public ItemWriteListener<Position> actionWriteListener() {

    }

    @Override
    public void beforeWrite(@NonNull final Chunk<? extends Position> items) {
        start = Instant.now();
    }

    @Override
    public void afterWrite(@NonNull final Chunk<? extends Position> items) {
        log.info(
                "Writing {} positions to action history took {} milliseconds",
                items.size(),
                Duration.between(start, Instant.now()).toMillis()
        );
    }
}
