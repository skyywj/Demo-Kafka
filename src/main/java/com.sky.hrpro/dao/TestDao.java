package com.sky.hrpro.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @Author: YanWenjie
 * @Date: 2018/9/27 上午10:29
 */
@Repository
public class TestDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addTest(){
        String sql = "insert into test (id,name,age) values(2,'ywj',23)";
        int rows = jdbcTemplate.update(sql);
        assert rows == 1;
    }



}
