package com.ubi.websocket2.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Random;

@Component
public class StockPriceWebSocketHandler implements WebSocketHandler {

    private final Random random = new Random();

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        Flux<String> stockPriceStream = Flux.interval(Duration.ofSeconds(1)) // Generate random stock prices every second
                .map(i -> {
                    double price = 100 + random.nextDouble() * 50; // Generate random price between 100 and 150
                    return "Client: " + session.getId() + ", Stock Price: $" + String.format("%.2f", price);
                });

        return session.send(stockPriceStream.map(session::textMessage));
    }
}
