package org.cyclic.action.batch;

import com.etake.parent.annotation.EnableParentBatch;
import com.excel.custom.library.annotation.EnableExcelLibrary;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableExcelLibrary
@EnableParentBatch
public class CyclicActionBatch {
    public static void main(String[] args) {
        SpringApplication.run(CyclicActionBatch.class, args);
    }

    @Bean
    ApplicationRunner runner(JobLauncher jobLauncher, Job job) {
        return args -> jobLauncher.run(job, new JobParameters());
    }
}