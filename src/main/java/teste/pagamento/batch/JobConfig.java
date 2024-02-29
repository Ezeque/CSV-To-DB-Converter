package teste.pagamento.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import teste.pagamento.batch.step.DeleteFileStep;

import javax.sql.DataSource;

@Configuration
public class JobConfig {
    @Autowired
    private Flow saveDataFlowConfig;
    @Autowired
    private Step DeleteFileStep;

    @Bean
     Job saveDataJob(JobRepository jobRepository){
        return new JobBuilder("saveDataJob", jobRepository)
                .start(saveDataFlowConfig)
                .next(DeleteFileStep)
                .build().build();
    }
}
