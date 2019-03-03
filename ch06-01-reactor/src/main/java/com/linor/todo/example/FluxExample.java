package com.linor.todo.example;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.linor.todo.domain.ToDo;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Configuration
@Slf4j
public class FluxExample {

	@Bean
	public CommandLineRunner runFluxExample() {
		return args -> {
			EmitterProcessor<ToDo> stream =
					EmitterProcessor.create();
			Mono<List<ToDo>> promise = stream
					.filter(s -> s.isCompleted())
					.doOnNext(s -> log.info("FLUX >>> ToDo: {}", s.getDescription()))
					.collectList()
					.subscribeOn(Schedulers.single());
			stream.onNext(new ToDo("Read a Book", true));
			stream.onNext(new ToDo("클래식 음악 듣기"));
			stream.onNext(new ToDo("내 방 정리", true));
			stream.onNext(new ToDo("세차장 가기", true));
			stream.onNext(new ToDo("SP1 2018 is comming", true));
			stream.onComplete();
			promise.block();
		};
	}
}
