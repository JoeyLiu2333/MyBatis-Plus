package com.joey.mybatisplusdemo1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.joey.mybatisplusdemo1.mapper")
public class MyBatisPlusDemo1Application {

    public static void main(String[] args) {
        SpringApplication.run(MyBatisPlusDemo1Application.class, args);
    }

}
