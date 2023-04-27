package org.cyclic.action.batch.dao;

import org.cyclic.action.batch.model.Position;
import org.cyclic.action.batch.model.SalesPeriod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InMemoryStore {
    public static List<Position> actionHistory = new ArrayList<>();
    public static List<Position> cyclicAction = new ArrayList<>();
    public static List<SalesPeriod> actualAvgSales = new ArrayList<>();

    public static void addPositionsToHistory(final Collection<? extends Position> positions) {
        actionHistory.addAll(positions);
    }

    public static void addPositionToActualAction(final Position position) {
        cyclicAction.add(position);
    }

    public static void addPeriodToActualAvgSales(final SalesPeriod salesPeriod) {
        actualAvgSales.add(salesPeriod);
    }

    public static boolean isCyclicActionContainsCode(final Integer code) {
        return cyclicAction.stream()
                .map(Position::getActionCode)
                .anyMatch(code::equals);
    }
}
