package com.linor.todo.jms;

import javax.validation.Valid;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.linor.todo.domain.ToDo;
import com.linor.todo.repository.ToDoRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ToDoConsumer {
	private ToDoRepository repository;
	public ToDoConsumer(ToDoRepository repository) {
		this.repository = repository;
	}
	
	@JmsListener(destination="${todo.jms.destination}", containerFactory="jmsFactory")
	public void processToDo(@Valid ToDo toDo) {
		log.info("Consumer --> " + toDo);
		log.info("ToDo created --> " + this.repository.save(toDo));
	}
}
