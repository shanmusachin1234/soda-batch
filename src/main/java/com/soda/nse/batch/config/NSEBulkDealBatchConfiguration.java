package com.soda.nse.batch.config;

import com.soda.nse.batch.model.NSEBulkDeal;
import com.soda.nse.batch.util.BeanWrapperFieldSetMapperCustom;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableBatchProcessing

public class NSEBulkDealBatchConfiguration extends DefaultBatchConfigurer {

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
                   ItemReader<NSEBulkDeal> itemReader, ItemProcessor<NSEBulkDeal, NSEBulkDeal> itemProcessor, ItemWriter<NSEBulkDeal> itemWriter) {
        Step step = stepBuilderFactory.get("NSE-BULK-DEAL-FILE-LOAD")
                .<NSEBulkDeal, NSEBulkDeal>chunk(100)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();

        Job job = jobBuilderFactory.get("NSE-BULK-DEAL-ETL")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();

        return job;
    }

    @Bean
    public ItemReader<NSEBulkDeal> itemReader(@Value("${input}") String nseBulkData) {
        Resource[] resources = null;
        ResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        try {
            resources = patternResolver.getResources(nseBulkData + "/**/*.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        MultiResourceItemReader<NSEBulkDeal> reader = new MultiResourceItemReader<>();
        reader.setResources(resources);

        FlatFileItemReader<NSEBulkDeal> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setName("CSV-Reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapper());

        reader.setDelegate(flatFileItemReader);

        return reader;
    }

    @Bean
    public LineMapper<NSEBulkDeal> lineMapper() {
        DefaultLineMapper<NSEBulkDeal> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[] {"id", "date","symbol","securityName","clientName","transactionType","quantityTraded","tradePrice","remark"});

        BeanWrapperFieldSetMapper<NSEBulkDeal> fieldExtractor = new BeanWrapperFieldSetMapperCustom<>();
        fieldExtractor.setTargetType(NSEBulkDeal.class);
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldExtractor);
        return defaultLineMapper;
    }

}
