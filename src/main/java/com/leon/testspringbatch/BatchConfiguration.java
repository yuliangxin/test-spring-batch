package com.leon.testspringbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@EnableBatchProcessing
@Configuration
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job(Step step) {
        return jobBuilderFactory.get("leon-job").start(step).build();
    }

    @Bean
    public Step simpleStep(ItemReader<Student> itemReader, @Qualifier("studentToPeopleItemProcessor") ItemProcessor itemProcessor, ItemWriter<People> itemWriter) {
        return stepBuilderFactory.get("simple-step").chunk(10).reader(itemReader)
                .processor(itemProcessor).writer(itemWriter).build();
    }

    @Bean
    public ItemReader<Student> itemReader(@Qualifier("readDataSource") DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Student>().name("studentItemReader")
                .dataSource(dataSource).sql("select * from leon_student")
                .rowMapper(new StudentRowMapper()).build();
    }

    @Bean
    public ItemWriter<People> itemWriter(@Qualifier("writeDataSource") DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder().dataSource(dataSource)
                .itemPreparedStatementSetter((ItemPreparedStatementSetter<People>) (people, ps) -> {
                    ps.setInt(1, people.getId());
                    ps.setString(2, people.getName());
                    ps.setInt(3, people.getAge());
                    ps.setString(4, people.getProfession());
                }).sql("insert into leon_people values(?, ?, ?, ?)").build();
    }


}
