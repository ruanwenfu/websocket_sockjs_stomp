package com.ruanwenfu.websocketsockjs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // 定时器功能
public class WebsocketSockjsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsocketSockjsApplication.class, args);
    }

}
