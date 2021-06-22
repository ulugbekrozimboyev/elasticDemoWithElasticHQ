package uz.elasticdemo.with.elastic.hq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import uz.elasticdemo.with.elastic.hq.document.bootstrap.SampleDataSet;

@SpringBootApplication
@EnableElasticsearchRepositories
public class ElasticDemoWithElasticHqApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticDemoWithElasticHqApplication.class, args);
    }

    @Bean
    @ConditionalOnProperty("initial-import.enabled")
    public SampleDataSet dataSet() {
        return new SampleDataSet();
    }
}
