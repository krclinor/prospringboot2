package com.linor.todo.example;

import java.time.Duration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.linor.todo.domain.ToDo;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoProcessor;
import reactor.core.scheduler.Schedulers;

@Configuration
@Slf4j
public class MonoExample {

	@Bean
	public CommandLineRunner runMonoExample() {
		return args -> {
			MonoProcessor<ToDo> promise = MonoProcessor.create();
			Mono<ToDo> result = promise
					.doOnSuccess(p -> log.info("MONO >> ToDo: {}", p.getDescription()))
					.doOnTerminate(() -> log.info("MONO >> Done"))
					.doOnError(t -> log.error(t.getMessage(), t))
					.subscribeOn(Schedulers.single());
			
			promise.onNext(new ToDo("Buy my ticket for SpringOne Platform 2018"));
			result.block(Duration.ofMillis(1000));
		};
	}
}
