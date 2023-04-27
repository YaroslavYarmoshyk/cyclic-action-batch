package org.cyclic.action.batch.config.reader;

import org.apache.commons.collections4.iterators.ArrayIterator;
import org.cyclic.action.batch.dao.InMemoryStore;
import org.cyclic.action.batch.model.Position;
import org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class ForecastItemReader extends AbstractItemCountingItemStreamItemReader<Position> {
    private Iterator<Position> cyclicAction;

    public ForecastItemReader() {
        setName("forecastItemReader");
    }

    @Override
    protected Position doRead() {
        if (cyclicAction.hasNext()) {
            return cyclicAction.next();
        } else {
            return null;
        }
    }

    @Override
    protected void doOpen() {
        cyclicAction = new ArrayIterator<>(InMemoryStore.cyclicAction.toArray());
    }

    @Override
    protected void doClose() {
    }
}
