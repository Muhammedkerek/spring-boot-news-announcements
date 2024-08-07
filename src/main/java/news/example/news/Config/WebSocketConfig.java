package news.example.news.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketConfig.class);

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        logger.info("Registering WebSocket handler for /ws/announcements");

        // Register the WebSocket handler and configure allowed origins
        registry.addHandler(new AnnouncementWebSocketHandler(), "/ws/announcements")
                .setAllowedOrigins("*")
                .addInterceptors(new HttpSessionHandshakeInterceptor()); // Optional: Adds session support
    }
}
