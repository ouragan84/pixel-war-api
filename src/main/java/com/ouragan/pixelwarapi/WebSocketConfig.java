package com.ouragan.pixelwarapi;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final List<String> allowedAdressesList = Arrays.asList("http://localhost:3000", "http://localhost:3001", "http://localhost:3002", "http://127.0.0.1:5500"); //, "https://pixel-war.netlify.app" "http://localhost:3000", "http://localhost:3001", "http://localhost:3002"

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        System.out.println("Called configureMessageBroker");

        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        System.out.println("Called registerStompEndpoints");

        String[] allowedAdressesArray = new String[allowedAdressesList.size()];
        allowedAdressesArray = allowedAdressesList.toArray(allowedAdressesArray);

        registry.addEndpoint("/connect").setAllowedOrigins(allowedAdressesArray).withSockJS();
    }
}