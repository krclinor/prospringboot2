package com.linor.todo.repository;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.linor.todo.domain.ToDo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ToDoRepository {

	private Flux<ToDo> toDoFlux = 
			Flux.fromIterable(Arrays.asList(
					new ToDo("숙제하기"),
					new ToDo("아침마다 운동하기"),
					new ToDo("오늘 저녁식사 차리기"),
					new ToDo("스튜디오 청소하기", true)
					));
	public Mono<ToDo> findById(String id){
		return Mono.from(toDoFlux.filter(todo -> todo.getId().equals(id)));
	}
	
	public Flux<ToDo> findAll(){
		return toDoFlux;
	}
}
