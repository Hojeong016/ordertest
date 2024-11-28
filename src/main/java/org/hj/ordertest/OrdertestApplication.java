package org.hj.ordertest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync // 비동기 활성화
@SpringBootApplication
public class OrdertestApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdertestApplication.class, args);
    }

}
