package org.cyclic.action.batch.dao;

import org.cyclic.action.batch.dao.entity.PositionEntity;
import org.cyclic.action.batch.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PositionRepository extends JpaRepository<PositionEntity, PositionEntity.PositionId> {

//    @Query()
//    boolean isAlreadyExist();
//
//    @Override
//    boolean existsById(Position position);
}
