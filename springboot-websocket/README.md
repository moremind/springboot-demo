## springboot整合websocket

springboot整合websocket分为两种常见的整合方式：

第一种，使用`@ServerEndpoint`接口来实现。

第二种，使用`@EnableWebSocket`配合WsHandler方法进行。

## ServerEndpoint方式实现

### ServerEndpoint方式配置文件

```java
@Configuration
public class WebSocketConfiguration {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
```

### ServerEndpoint方式的实现

ServerEndpoint的实现主要针对业务领域对websocket消息进行处理，主要使用`@OnOpen`对websocket连接建立时需要实现的逻辑，`@OnClose`对websocket连接关闭时需要实现的逻辑，`@OnMessage`对websocket收到消息时的处理以及`@OnError`对websocket发生错误时的处理方式。

```java
@ServerEndpoint(value = "/websocket")
@Component
public class WebsocketServer {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebsocketServer> webSocketSet = new CopyOnWriteArraySet<WebsocketServer>();

    private static ConcurrentHashMap<Session, WebsocketServer> websocketMap = new ConcurrentHashMap<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    private Session id;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        this.id = session;
        websocketMap.put(session, this);

        try {
            sendMessage("当前有连接进入," + getOnlineCount());
        } catch (IOException e) {
            System.out.println("IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        subOnlineCount();           //在线数减1
        webSocketSet.remove(this);  //从set中删除
        websocketMap.remove(session);
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        try {
            sendMessageToUser(message, session);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }

    public void sendMessageToUser(String message, Session session) throws IOException {
        if (websocketMap.get(session) != null) {
            if(!id.equals(session)) {
                websocketMap.get(session).sendMessage("用户" + session.getId() + "发来消息：" + message);
            } else {
                websocketMap.get(session).sendMessage("用户" + session.getId() + "发来消息：" + message);
            }
        } else {
            //如果用户不在线则返回不在线信息给自己
            sendMessageToUser("当前用户不在线",id);
        }
    }

    /**
     * 群发消息
     * @param message
     */
    public void sendMessageToAll(String message) {
        for (WebsocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 群发自定义消息
     * */
    public static void sendInfo(String message) throws IOException {
        for (WebsocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebsocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebsocketServer.onlineCount--;
    }
}
```

## EnableWebSocket方式实现

### EnableWebSocket方式的配置文件

```java
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
```

### EnableWebSocket方式的实现

`@EnableWebSocket`主要是通过继承`TextWebSocketHandler`来实现对websocket消息的处理。

```java
public class WsHandler extends TextWebSocketHandler {
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 获取接收到的数据
        String payload = message.getPayload();
        System.out.println("收到客户端消息:" + message.getPayload());

        // 向客户端发送数据
        session.sendMessage(new TextMessage("response: " + payload));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("有连接加入");
        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("有连接关闭");
        super.afterConnectionClosed(session, status);
    }
}
```

