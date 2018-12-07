package com.study.zhengql;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * @author zhengql
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,MybatisAutoConfiguration.class})
@EnableScheduling
public class GoldApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoldApplication.class, args);
    }
}
