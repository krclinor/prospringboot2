package com.linor.todo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.linor.todo.domain.ToDo;

public interface ToDoRepository extends ReactiveMongoRepository<ToDo, String> {

}
