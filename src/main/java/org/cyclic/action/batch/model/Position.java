package org.cyclic.action.batch.model;

import lombok.Builder;
import lombok.Data;
import org.cyclic.action.batch.dao.entity.PositionEntity;
import org.cyclic.action.batch.enumeration.Algorithm;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class Position {
    private String name;
    private String store;
    private String manager;
    private Integer carryover;
    private Integer actionCode;
    private Integer storeFormat;
    private String thirdGroup;
    private String actionType;
    private LocalDate actionStartDate;
    private LocalDate actionEndDate;
    private LocalDate beforeActionStartDate;
    private LocalDate beforeActionEndDate;
    private BigDecimal actionAverageSales;
    private BigDecimal beforeActionAverageSales;
    private BigDecimal actualAverageSales;
    private Algorithm algorithm;

    public PositionEntity.PositionId getPositionId() {
        final var positionId = new PositionEntity.PositionId();
        positionId.setStore(store);
        positionId.setActionCode(actionCode);
        positionId.setActionType(actionType);
        positionId.setActionStartDate(actionStartDate);
        positionId.setActionEndDate(actionEndDate);
        return positionId;
    }
}
