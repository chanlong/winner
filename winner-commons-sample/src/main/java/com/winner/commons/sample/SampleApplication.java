package com.winner.commons.sample;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * .
 *
 * @Classname SampleApplication
 * @Description winner
 * @Version 1.0.0
 * @Author chanlong
 * @Date 2021/3/5 上午9:38
 */
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class SampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }
}
