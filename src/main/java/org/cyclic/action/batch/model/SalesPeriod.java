package org.cyclic.action.batch.model;

import java.math.BigDecimal;

public record SalesPeriod(
        String store,
        Integer actionCode,
        BigDecimal actualAverageSales
) {}