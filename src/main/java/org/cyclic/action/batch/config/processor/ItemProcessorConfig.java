package org.cyclic.action.batch.config.processor;

import lombok.RequiredArgsConstructor;
import org.cyclic.action.batch.config.annotations.ActionHistoryProcessor;
import org.cyclic.action.batch.dao.InMemoryStore;
import org.cyclic.action.batch.model.Position;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static org.cyclic.action.batch.util.CyclicUtil.isAnalyzedPosition;

@Component
@RequiredArgsConstructor
public class ItemProcessorConfig {
    @Value("${cyclic-action.start-date}")
    private LocalDate actionStartDate;
    @Value("${cyclic-action.end-date}")
    private LocalDate actionEndDate;

    @Bean
    @Profile("!dev")
    @StepScope
    @ActionHistoryProcessor
    public ItemProcessor<Position, Position> actionHistoryDbProcessor() {
        return position -> position;
    }

    @Bean
    @Profile("dev")
    @StepScope
    @ActionHistoryProcessor
    public ItemProcessor<Position, Position> actionHistoryProcessor() {
        return position -> {
            if (isAnalyzedPosition(position, actionStartDate, actionEndDate)) {
                InMemoryStore.addPositionToActualAction(position);
                return null;
            }
            return position;
        };
    }
}
