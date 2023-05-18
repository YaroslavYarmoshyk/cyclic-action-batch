package org.cyclic.action.batch.config.processor;

import jakarta.annotation.PostConstruct;
import org.cyclic.action.batch.dao.InMemoryStore;
import org.cyclic.action.batch.model.SalesPeriod;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@StepScope
public class ActualAvgSalesProcessor implements ItemProcessor<SalesPeriod, SalesPeriod> {
    private Set<Integer> actionCodes;

    @PostConstruct
    private void setActionCodes() {
        actionCodes = InMemoryStore.getActionCodes();
    }

    @Override
    public SalesPeriod process(final SalesPeriod item) {
        if (actionCodes.contains(item.actionCode())) {
            return item;
        }
        return null;
    }
}
