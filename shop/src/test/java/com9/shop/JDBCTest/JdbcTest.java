package com9.shop.JDBCTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class JdbcTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;


}
