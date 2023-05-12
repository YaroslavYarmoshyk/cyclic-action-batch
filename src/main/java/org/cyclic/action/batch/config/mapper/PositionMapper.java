package org.cyclic.action.batch.config.mapper;

import org.cyclic.action.batch.dao.entity.PositionEntity;
import org.cyclic.action.batch.enumeration.Algorithm;
import org.cyclic.action.batch.model.Position;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.Objects.isNull;

@Component
public class PositionMapper {

    public PositionEntity mapToEntity(final Position position) {
        final PositionEntity positionEntity = new PositionEntity();
        final PositionEntity.PositionId positionId = new PositionEntity.PositionId();
        positionId.setStore(position.getStore());
        positionId.setActionCode(position.getActionCode());
        positionId.setActionType(position.getActionType());
        positionId.setActionStartDate(position.getActionStartDate());
        positionId.setActionEndDate(position.getActionEndDate());
        positionEntity.setId(positionId);
        positionEntity.setCarryover(position.getCarryover());
        positionEntity.setManager(position.getManager());
        positionEntity.setThirdGroup(position.getThirdGroup());
        positionEntity.setName(position.getName());
        positionEntity.setStoreFormat(position.getStoreFormat());
        positionEntity.setAlgorithm(getAlgorithmNumber(position));
        positionEntity.setActionAverageSales(position.getActionAverageSales());
        positionEntity.setBeforeActionAverageSales(position.getBeforeActionAverageSales());
        positionEntity.setActualAverageSales(position.getActualAverageSales());
        return positionEntity;
    }

    private static Integer getAlgorithmNumber(final Position position) {
        return Optional.ofNullable(position)
                .map(Position::getAlgorithm)
                .map(Algorithm::getNumber)
                .orElse(null);
    }
}
