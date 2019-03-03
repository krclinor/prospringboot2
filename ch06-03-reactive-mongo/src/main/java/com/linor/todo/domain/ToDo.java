package com.linor.todo.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class ToDo {
	@Id
	private String id;
	private String description;
	private LocalDateTime created;
	private LocalDateTime modified;
	private boolean completed;
	
	public ToDo() {
		LocalDateTime date = LocalDateTime.now();
		this.id = UUID.randomUUID().toString();
		this.created = date;
		this.modified = date;
	}
	
	public ToDo(String description) {
		this();
		this.description = description;
	}
	
	public ToDo(String description, boolean complete) {
		this();
		this.description = description;
		this.completed = complete;
	}
}
