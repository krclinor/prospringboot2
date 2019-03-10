package com.linor.todo.redis;

import org.springframework.stereotype.Component;

import com.linor.todo.domain.ToDo;
import com.linor.todo.repository.TodoRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ToDoConsumer {
	private TodoRepository repository;
	
	public ToDoConsumer(TodoRepository repository) {
		this.repository = repository;
	}
	
	public void handleMessage(ToDo toDo) {
		log.info("Consumer --> " + toDo);
		log.info("ToDo 생성 --> " + this.repository.save(toDo));
	}
}
