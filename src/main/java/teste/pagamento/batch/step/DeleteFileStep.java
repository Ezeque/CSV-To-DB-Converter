package teste.pagamento.batch.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.builder.TaskletStepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.io.File;

@Configuration
@EnableBatchProcessing
public class DeleteFileStep {
    @Value("${FILES_PATH}")
    String filesPath;

    @Bean
    public Step DeleteFileStep(DataSourceTransactionManager dataSourceTransactionManager, JobRepository jobRepository, Tasklet DeleteFileTasklet){
        return new StepBuilder("DeleteFileStep", jobRepository)
                .tasklet(DeleteFileTasklet, dataSourceTransactionManager)
                .build();
    }

    @Bean
    @StepScope
    public Tasklet DeleteFileTasklet(@Value("#{jobParameters['fileName']}") String fileName){
        return (contribution, chunkContext) -> {
            File fileToDelete = new File(filesPath, fileName);
            boolean deleted = fileToDelete.delete();
            if(deleted){
                System.out.println("Arquivo " + fileName + " deletado com sucesso");
            }
            else{
                System.out.println("Não foi possível, deletar o arquivo " + fileName + ".");
            }
            return null;
        };
    }
}
