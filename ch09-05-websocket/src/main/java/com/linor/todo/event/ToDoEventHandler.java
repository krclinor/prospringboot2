package com.linor.todo.event;

import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.linor.todo.config.ToDoProperties;
import com.linor.todo.domain.ToDo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RepositoryEventHandler(ToDo.class)
public class ToDoEventHandler {
	private SimpMessagingTemplate simpMessagingTemplate;
	private ToDoProperties toDoProperties;
	
	public ToDoEventHandler(SimpMessagingTemplate simpMessagingTemplate,
			ToDoProperties toDoProperties) {
		this.simpMessagingTemplate = simpMessagingTemplate;
		this.toDoProperties = toDoProperties;
	}
	
	@HandleAfterCreate
	public void handleToDoSave(ToDo toDo) {
		this.simpMessagingTemplate.convertAndSend(this.toDoProperties.getBroker() + "/new", toDo);
		log.info(">> WS로 메시지 전송: ws//todo/new - " + toDo);
	}
}
