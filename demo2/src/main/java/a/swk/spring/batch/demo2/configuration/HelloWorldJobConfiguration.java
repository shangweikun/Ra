package a.swk.spring.batch.demo2.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.support.JdbcTransactionManager;

import static a.swk.spring.batch.demo2.configuration.MyDemo.LOCAL;

@Configuration
@EnableBatchProcessing
@Import(DataSourceConfiguration.class)
public class HelloWorldJobConfiguration {

    @Bean
    public Step step(JobRepository jobRepository, JdbcTransactionManager transactionManager) {
        return new StepBuilder("step", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Hello world!");
                    System.out.println(Thread.currentThread().getName() + " " + LOCAL.get());
                    return RepeatStatus.FINISHED;
                }, transactionManager).build();
    }

    @Bean
    public Job job(JobRepository jobRepository, Step step) {
        return new JobBuilder("job", jobRepository).start(step).build();
    }

    public static void main(String[] args) throws Exception {

        LOCAL.set("Hello World");


        ApplicationContext context = new AnnotationConfigApplicationContext(HelloWorldJobConfiguration.class);
        JobLauncher jobLauncher = context.getBean(JobLauncher.class);
        Job job = context.getBean(Job.class);
        jobLauncher.run(job, new JobParameters());

        new Thread(() -> {
            while (true) {
                System.out.println(Thread.currentThread().getName() + "-"
                        + LOCAL.get());
                Thread.onSpinWait();
            }

        }).start();

        LOCAL.set("Hello World 1");

        new Thread(() -> {
            while (true) {
                System.out.println(Thread.currentThread().getName() + "-"
                        + LOCAL.get());
                Thread.onSpinWait();
            }

        }).start();


    }

}