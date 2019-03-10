package com.linor.todo.rmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.linor.todo.domain.ToDo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ToDoProducer {
	private RabbitTemplate template;
	
	public ToDoProducer(RabbitTemplate template) {
		this.template = template;
	}
	
	public void sendTo(String queue, ToDo toDo) {
		this.template.convertAndSend(queue, toDo);
		log.info("Producer --> 메시지 전송됨");
	}
}
