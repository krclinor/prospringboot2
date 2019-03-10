package com.linor.todo.repository;

import org.springframework.data.repository.CrudRepository;

import com.linor.todo.domain.ToDo;

public interface ToDoRepository extends CrudRepository<ToDo	, String> {

}
