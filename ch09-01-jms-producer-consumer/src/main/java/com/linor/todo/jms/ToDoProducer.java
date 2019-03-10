package com.linor.todo.jms;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.linor.todo.domain.ToDo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ToDoProducer {
	private JmsTemplate jmsTemplate;
	
	public ToDoProducer(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
	public void sendTo(String destination, ToDo toDo) {
		this.jmsTemplate.convertAndSend(destination, toDo);
		log.info("Producer --> Message Sent");
	}
}
