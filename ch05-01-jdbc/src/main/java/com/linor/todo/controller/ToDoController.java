package com.linor.todo.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.linor.todo.domain.ToDo;
import com.linor.todo.domain.ToDoBuilder;
import com.linor.todo.repository.CommonRepository;
import com.linor.todo.validation.ToDoValidationError;
import com.linor.todo.validation.ToDoValidationErrorBuilder;

@RestController
@RequestMapping("/api")
public class ToDoController {
	private CommonRepository<ToDo> repository;
	
	public ToDoController(CommonRepository<ToDo> repository) {
		this.repository = repository;
	}
	
	@GetMapping("/todo")
	public ResponseEntity<Iterable<ToDo>> getToDos(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/todo/{id}")
	public ResponseEntity<ToDo> getToDoById(@PathVariable String id){
		return ResponseEntity.ok(repository.findById(id));
	}
	
	@PatchMapping("/todo/{id}")
	public ResponseEntity<ToDo> setCompleted(@PathVariable String id){
		ToDo result = repository.findById(id);
		result.setCompleted(true);
		repository.save(result);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.buildAndExpand(result.getId()).toUri();
		return ResponseEntity.ok().header("Location", location.toString())
				.build();
	}
	
	@RequestMapping(value="/todo", method= {RequestMethod.POST, RequestMethod.PUT})
	public ResponseEntity<?> createToDo(@Valid @RequestBody ToDo toDo, Errors errors){
		if(errors.hasErrors()) {
			return ResponseEntity.badRequest()
					.body(ToDoValidationErrorBuilder.fromBindingErrors(errors));
		}
		
		ToDo resulToDo = repository.save(toDo);
		URI locationUri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(resulToDo.getId())
				.toUri();
		return ResponseEntity.created(locationUri)
				.build();
	}
	
	@DeleteMapping("/todo/{id}")
	public ResponseEntity<ToDo> deleteToDo(@PathVariable String id){
		repository.delete(ToDoBuilder.create().withId(id).build());
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/todo")
	public ResponseEntity<ToDo> deleteToDo(@RequestBody ToDo toDo){
		repository.delete(toDo);
		return ResponseEntity.noContent().build();
	}
	
	@ExceptionHandler
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public ToDoValidationError handleException(Exception exception) {
		return new ToDoValidationError(exception.getMessage());
	}
}
