package org.cyclic.action.batch.config.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
@Profile("!dev")
public class TableCreationListener implements JobExecutionListener {
    private final DataSource dataSource;

    @Override
    public void beforeJob(final JobExecution jobExecution) {
        try (final Connection databaseConnection = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(databaseConnection, new ClassPathResource("db/createTables.sql"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
