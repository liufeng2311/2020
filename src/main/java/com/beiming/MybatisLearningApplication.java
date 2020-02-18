package com.beiming;

import org.apache.ibatis.binding.MapperProxyFactory;
import org.apache.ibatis.transaction.TransactionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MybatisLearningApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisLearningApplication.class, args);
    }

}
