package a.swk.spring.batch.demo2.configuration;

import a.swk.spring.batch.demo2.MyItemProcessor;
import a.swk.spring.batch.demo2.MyItemReader;
import a.swk.spring.batch.demo2.MyItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

public class MyConfiguration {

    @Bean
    public MyItemProcessor myItemProcessor() {
        return new MyItemProcessor();
    }

    @Bean
    public MyItemWriter writer() {
        return new MyItemWriter();
    }

    @Bean
    public MyItemReader reader() {
        return new MyItemReader();
    }

    @Bean
    public Job myJob(JobRepository jobRepository, Step step1) {
        return new JobBuilder("MyJob", jobRepository)
                .start(step1)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
                      MyItemReader reader, MyItemProcessor processor, MyItemWriter writer) {
        return new StepBuilder("step1", jobRepository)
                .<String, String>chunk(1, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
