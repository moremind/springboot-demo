package cn.moremind.spring.springbootwebsocket.wshandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableWebSocket
public class WsHandlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WsHandlerApplication.class, args);
    }

}
