package com.linor.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class SpringBootSimpleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSimpleApplication.class, args);
	}

	@Bean
	String Info() {
		return "단순한 문자열 빈";
	}
	
	@Autowired
	String info;
	
	@Bean
	CommandLineRunner myMethod() {
		return args -> {
			log.info("## > CommandLine Runner 실행...");
			log.info("info 빈 인출: " + info);
			for(String arg:args)
				log.info(arg);
		};
	}
}

