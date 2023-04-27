package org.cyclic.action.batch.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class SalesPeriod {
    private String store;
    private Integer actionCode;
    private BigDecimal actionAverageSales;
}
