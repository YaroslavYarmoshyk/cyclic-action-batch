//package org.cyclic.action.batch.config.processor;
//
//import lombok.NonNull;
//import org.cyclic.action.batch.dao.InMemoryStore;
//import org.cyclic.action.batch.model.Position;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDate;
//
//import static org.cyclic.action.batch.util.CyclicUtil.isAnalyzedPosition;
//
//@Configuration
//public class ActionHistoryItemProcessor implements ItemProcessor<Position, Position> {
//    @Value("${cyclic-action.start-date}")
//    private LocalDate actionStartDate;
//    @Value("${cyclic-action.end-date}")
//    private LocalDate actionEndDate;
//
//    @Override
//    public Position process(@NonNull final Position position) {
//        if (isAnalyzedPosition(position, actionStartDate, actionEndDate)) {
//            InMemoryStore.addPositionToActualAction(position);
//            return null;
//        }
//        return position;
//    }
//}
