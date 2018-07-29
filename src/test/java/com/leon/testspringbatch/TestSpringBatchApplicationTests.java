package com.leon.testspringbatch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSpringBatchApplicationTests {

    @Qualifier("readJdbcTemplate")
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Qualifier("writeJdbcTemplate")
    @Autowired
    private JdbcTemplate writeDBJdbcTemplate;

    @Test
    public void contextLoads() {
        jdbcTemplate.execute("select 1 from dual");
    }

    @Test
    public void testWriteDBJdbcTemplate() {
        writeDBJdbcTemplate.execute("select 1 from dual");
    }

}
