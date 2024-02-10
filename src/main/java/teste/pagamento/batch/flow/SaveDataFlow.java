package teste.pagamento.batch.flow;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaveDataFlow {
    @Autowired
    private Step saveDataStepConfig;

    @Bean
    public Flow saveDataFlowConfig(){
        return new FlowBuilder<SimpleFlow>("saveDataFlow")
                .start(saveDataStepConfig)
                .build();
    }
}
