package cn.moremind.spring.springbootwebsocket.wshandler.config;

import cn.moremind.spring.springbootwebsocket.wshandler.handler.WsHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(webSocketHandler(), "websocket")
                .setAllowedOrigins("*");

    }

    public WebSocketHandler webSocketHandler() {
        return new WsHandler();
    }
}
