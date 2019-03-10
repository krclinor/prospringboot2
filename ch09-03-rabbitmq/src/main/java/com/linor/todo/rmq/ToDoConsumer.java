package com.linor.todo.rmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
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
	
	@RabbitListener(queues="${todo.amqp.queue}")
	public void processToDo(ToDo toDo) {
		log.info("Consumer --> " + toDo);
		log.info("ToDo 생성됨 --> " + this.repository.save(toDo));
	}
}
