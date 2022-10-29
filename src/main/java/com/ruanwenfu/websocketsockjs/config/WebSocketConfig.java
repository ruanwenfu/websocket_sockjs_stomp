package com.ruanwenfu.websocketsockjs.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig  implements WebSocketMessageBrokerConfigurer {

    // 注册一个EndPoint节点,供客户端连接
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/socket")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 配置Broker ,类似于配置Kafka Broker，参数为topic prefix 前缀
        // 实际上我认为不需要配置 /message,确实不需要
        // registry.enableSimpleBroker("/server","/user","/message");  // 目的地server,user开头的都转到 SimpleBroker
        registry.enableSimpleBroker("/server","/user");
    }
}
