package com.linor.todo.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@EnableConfigurationProperties(ToDoProperties.class)
public class ToDoConfig implements WebSocketMessageBrokerConfigurer{
	private ToDoProperties properties;
	
	public ToDoConfig(ToDoProperties properties) {
		this.properties = properties;
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint(properties.getEndpoint())
			.setAllowedOrigins("*")
			.withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker(properties.getBroker());
		registry.setApplicationDestinationPrefixes(properties.getApp());
	}
	
	
}
