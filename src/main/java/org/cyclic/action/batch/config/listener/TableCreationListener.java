package org.cyclic.action.batch.config.listener;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class TableCreationListener implements JobExecutionListener {
    private final DataSource dataSource;

    @Override
    public void beforeJob(@NonNull final JobExecution jobExecution) {
        try (final Connection databaseConnection = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(databaseConnection, new ClassPathResource("db/createTables.sql"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public void afterJob(@NonNull final JobExecution jobExecution) {
//        try (final Connection databaseConnection = dataSource.getConnection()) {
//            ScriptUtils.executeSqlScript(databaseConnection, new ClassPathResource("db/deleteTables.sql"));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
