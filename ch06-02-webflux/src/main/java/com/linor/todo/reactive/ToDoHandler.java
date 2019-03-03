package com.linor.todo.reactive;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.linor.todo.domain.ToDo;
import com.linor.todo.repository.ToDoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ToDoHandler {
	private ToDoRepository repository;
	public ToDoHandler(ToDoRepository repository) {
		this.repository = repository;
	}
	
	public Mono<ServerResponse> getToDo(ServerRequest request){
		String toDoId = request.pathVariable("id");
		Mono<ServerResponse> notFound = 
				ServerResponse.notFound().build();
		Mono<ToDo> toDo = this.repository.findById(toDoId);
		return toDo.flatMap(t -> ServerResponse
					.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(BodyInserters.fromObject(t)))
				.switchIfEmpty(notFound);
	}
	public Mono<ServerResponse> getToDos(ServerRequest request){
		Flux<ToDo> toDos = this.repository.findAll();
		return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(toDos, ToDo.class);
	}
}
