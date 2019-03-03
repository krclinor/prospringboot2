package com.linor.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.linor.client.domain.ToDo;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class ToDoClientApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ToDoClientApplication.class);
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(args);
	}

	@Bean
	public CommandLineRunner process(ToDoRestClient client) {
		return args -> {
			Iterable<ToDo> toDos = client.findAll();
			assert toDos != null;
			toDos.forEach(toDo -> log.info(toDo.toString()));
			
			ToDo newToDo = client.upsert(new ToDo("매일 충분한 물을 마셔라!"));
			assert newToDo != null;
			log.info(newToDo.toString());
			
			ToDo toDo = client.findById(newToDo.getId());
			assert toDos != null;
			log.info(toDo.toString());
			
			ToDo completed = client.setCompleted(newToDo.getId());
			assert completed.isCompleted();
			log.info(completed.toString());
			
			client.delete(newToDo.getId());
			assert client.findById(newToDo.getId()) == null;
		};
	}
}
