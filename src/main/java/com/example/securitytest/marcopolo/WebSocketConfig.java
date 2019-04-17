package com.example.securitytest.marcopolo;

import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Created by hx on 2019-04-17.
 */
@EnableWebSocket// 配置消息处理器类,使用左边注解，实现WebSocketConfigurer接口
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(marcoHandler(),"/marco");// 将MarcoHandler映射到"/marco"
    }
    @Bean
    public MarcoHandler marcoHandler() {// 声明MarcoHandler bean
        return new MarcoHandler();
    }
}
