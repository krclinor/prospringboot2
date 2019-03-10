package com.linor.todo.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.linor.todo.domain.ToDo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ToDoProducer {
	private RedisTemplate redisTemplate;
	
	public ToDoProducer(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	public void sendTo(String topic, ToDo toDo) {
		log.info("Producer --> Todo 전송");
		this.redisTemplate.convertAndSend(topic, toDo);
	}
}
