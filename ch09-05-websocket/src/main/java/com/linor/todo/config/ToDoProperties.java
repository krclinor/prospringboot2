package com.linor.todo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix="todo.ws")
public class ToDoProperties {
	private String app = "/todo-api-ws";
	private String broker = "/todo";
	private String endpoint = "/stomp";
}
