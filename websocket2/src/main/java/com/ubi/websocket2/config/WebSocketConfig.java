package com.ubi.websocket2.config;


import java.util.HashMap;
import java.util.Map;

import com.ubi.websocket2.handler.StockPriceWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;

@Configuration
public class WebSocketConfig {
    @Bean
    public HandlerMapping webSocketHandlerMapping(StockPriceWebSocketHandler stockHandler) {
        Map<String, WebSocketHandler> map = new HashMap<>();
        map.put("/ws/stocks", stockHandler); // Maps the URL "/ws/stocks" to the StockPriceWebSocketHandler

        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setOrder(1);
        mapping.setUrlMap(map);
        return mapping;
    }

    @Bean
    public WebSocketHandlerAdapter webSocketHandlerAdapter() {
        return new WebSocketHandlerAdapter();
    }
}
