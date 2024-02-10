package teste.pagamento.batch.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import teste.pagamento.models.records.Pagamento;

import javax.sql.DataSource;
@Configuration
@EnableBatchProcessing
public class SaveDataStep {
    @Value("${filesPath}")
    String filePath;
    @Autowired
    DataSource dataSource;


    @Bean
    @StepScope
    public FlatFileItemReader<Pagamento> SaveDataReader(@Value("#{jobParameters['fileName']}") String fileName){
        System.out.println(filePath + fileName);
        return new FlatFileItemReaderBuilder<Pagamento>()
                .name("saveDataReader")
                .resource(new PathResource(filePath + "/" + fileName))
                .delimited()
                .names("nome", "cargo", "unidade", "salario")
                .targetType(Pagamento.class)
                .build();
    }
    @Bean
    public JdbcBatchItemWriter<Pagamento> saveDataWriter(DataSource dataSource){
        return new JdbcBatchItemWriterBuilder<Pagamento>()
                .sql("INSERT INTO pagamentos (nome, cargo, unidade, salario) VALUES (:nome, :cargo, :unidade, :salario)")
                .dataSource(dataSource)
                .beanMapped()
                .build();
    }

    @Bean
    public Step saveDataStepConfig(DataSourceTransactionManager transactionManager,
                                   JobRepository jobRepository,
                                   FlatFileItemReader<Pagamento> reader,
                                    JdbcBatchItemWriter<Pagamento> writer){
        return new StepBuilder("saveDataStep", jobRepository)
                .<Pagamento, Pagamento> chunk(10, transactionManager)
                .reader(reader)
                .writer(writer)
                .build();
    }
}
