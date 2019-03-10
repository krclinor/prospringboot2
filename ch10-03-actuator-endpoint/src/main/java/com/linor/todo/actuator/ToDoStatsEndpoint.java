package com.linor.todo.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import com.linor.todo.domain.ToDo;
import com.linor.todo.repository.TodoRepository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Component
@Endpoint(id="todo-stats")
public class ToDoStatsEndpoint {
	private TodoRepository repository;
	public ToDoStatsEndpoint(TodoRepository repository) {
		this.repository = repository;
	}
	
	@ReadOperation
	public Stats stats() {
		return new Stats(this.repository.count(), this.repository.countByCompleted(true));
	}
	
	@ReadOperation
	public ToDo getToDo(@Selector String id) {
		return this.repository.findById(id).orElse(null);
	}
	
	@WriteOperation
	public Operation completeToDo(@Selector String id) {
		ToDo toDo = this.repository.findById(id).orElse(null);
		if(null != toDo) {
			toDo.setCompleted(true);
			this.repository.save(toDo);
			return new Operation("COMPLETED", true);
		}
		return new Operation("COMPLETED", false);
	}
	
	@AllArgsConstructor
	@Data
	public class Stats{
		private long count;
		private long completed;
	}
	
	@AllArgsConstructor
	@Data
	public class Operation{
		private String name;
		private boolean successful;
	}
}
