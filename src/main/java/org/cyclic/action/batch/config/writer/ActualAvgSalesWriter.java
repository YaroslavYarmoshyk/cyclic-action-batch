package org.cyclic.action.batch.config.writer;

import lombok.NonNull;
import org.cyclic.action.batch.dao.InMemoryStore;
import org.cyclic.action.batch.model.SalesPeriod;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.stereotype.Component;

@Component
public class ActualAvgSalesWriter implements ItemStreamWriter<SalesPeriod> {

    @Override
    public void write(@NonNull final Chunk<? extends SalesPeriod> chunk) {
        for (final SalesPeriod period : chunk.getItems()) {
            if (InMemoryStore.isCyclicActionContainsCode(period.getActionCode())) {
                InMemoryStore.addPeriodToActualAvgSales(period);
            }
        }
    }


    @Override
    public void open(@NonNull final ExecutionContext executionContext) throws ItemStreamException {
        ItemStreamWriter.super.open(executionContext);
    }

    @Override
    public void update(@NonNull final ExecutionContext executionContext) throws ItemStreamException {
        ItemStreamWriter.super.update(executionContext);
    }

    @Override
    public void close() throws ItemStreamException {
        ItemStreamWriter.super.close();
    }
}
