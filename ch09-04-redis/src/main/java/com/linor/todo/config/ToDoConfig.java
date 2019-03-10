package com.linor.todo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.linor.todo.domain.ToDo;
import com.linor.todo.redis.ToDoConsumer;

@Configuration
public class ToDoConfig {
	@Bean
	public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
			MessageListenerAdapter todoListenerAdapter,
			@Value("${todo.redis.topic}") String topic) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(todoListenerAdapter, new PatternTopic(topic));
		return container;
	}
	
	@Bean
	MessageListenerAdapter toDoListenerAdapter(ToDoConsumer consumer) {
		MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(consumer);
		messageListenerAdapter.setSerializer(new Jackson2JsonRedisSerializer<>(ToDo.class));
		return messageListenerAdapter;
	}
	
	@Bean
	RedisTemplate<String, ToDo> redisTemplate(RedisConnectionFactory connectionFactory){
		RedisTemplate<String, ToDo> redistTemplate = new RedisTemplate<>();
		redistTemplate.setConnectionFactory(connectionFactory);
		redistTemplate.setDefaultSerializer(new Jackson2JsonRedisSerializer<>(ToDo.class));
		redistTemplate.afterPropertiesSet();
		return redistTemplate;
	}
}
