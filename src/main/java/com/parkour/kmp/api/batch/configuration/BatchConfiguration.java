package com.parkour.kmp.api.batch.configuration;

import com.parkour.kmp.api.batch.processor.MedCodeProcessor;
import com.parkour.kmp.api.client.payload.response.medcode.MedCodeApiResponse;
import com.parkour.kmp.api.medcode.domain.MedCode;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfiguration {

    private final JobLauncher jobLauncher;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final DataSource dataSource;

    @Bean
    public JsonItemReader<MedCodeApiResponse> reader() {
        return new JsonItemReaderBuilder<MedCodeApiResponse>()
                .name("jsonMedCodeReader")
                .resource(new ClassPathResource("sample-data.json"))
                .jsonObjectReader(new JacksonJsonObjectReader<>(MedCodeApiResponse.class))
                .build();
    }

    @Bean
    public MedCodeProcessor processor() {
        return new MedCodeProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<MedCode> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<MedCode>()
                .sql("INSERT INTO med_codes (item_seq, representative_code, standard_code, product_code, atc_code) VALUES (:itemSeq, :representativeCode, :standardCode, :productCode, :atcCode)")
                .dataSource(dataSource)
                .beanMapped()
                .build();
    }

    @Bean
    public Job importMedCodesJob() {
        return new JobBuilder("importMedCodesJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() {
        return new StepBuilder("step1", jobRepository)
                .<MedCodeApiResponse, MedCode>chunk(10, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer(dataSource))
                .transactionManager(transactionManager)
                .build();
    }
}
