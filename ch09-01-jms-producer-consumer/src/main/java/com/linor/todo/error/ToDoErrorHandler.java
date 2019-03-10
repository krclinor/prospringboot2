package com.linor.todo.error;

import org.springframework.util.ErrorHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ToDoErrorHandler implements ErrorHandler {

	@Override
	public void handleError(Throwable t) {
		log.warn("ToDo error...");
		log.error(t.getCause().getMessage());
	}

}
