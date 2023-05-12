package org.cyclic.action.batch.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "position")
public class PositionEntity {

    @EmbeddedId
    private PositionId id;

    @Column(name = "name")
    private String name;

    @Column(name = "manager")
    private String manager;

    @Column(name = "carryover")
    private Integer carryover;

    @Column(name = "store_format")
    private Integer storeFormat;

    @Column(name = "third_group")
    private String thirdGroup;

    @Column(name = "action_average_sales")
    private BigDecimal actionAverageSales;

    @Column(name = "before_action_average_sales")
    private BigDecimal beforeActionAverageSales;

    @Column(name = "actual_average_sales")
    private BigDecimal actualAverageSales;

    @Column(name = "algorithm")
    private Integer algorithm;

    @Data
    @Embeddable
    public static class PositionId implements Serializable {
        @Column(name = "store")
        private String store;

        @Column(name = "action_code")
        private Integer actionCode;

        @Column(name = "action_type")
        private String actionType;

        @Column(name = "action_start_date")
        private LocalDate actionStartDate;

        @Column(name = "action_end_date")
        private LocalDate actionEndDate;
    }
}
