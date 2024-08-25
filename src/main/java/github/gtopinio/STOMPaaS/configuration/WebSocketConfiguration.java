package github.gtopinio.STOMPaaS.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Application: messages that are bound for @MessageMapping-annotated methods in controllers
        // Broker: messages that are brokered to one or more subscribing clients
        // /topic: messages that are broadcast to all subscribing clients
        // /queue: messages that are sent to a specific client
        registry.setApplicationDestinationPrefixes("/app"); // for application destinations
        registry.enableSimpleBroker("/topic", "/queue"); // for broker destinations
    }
}
