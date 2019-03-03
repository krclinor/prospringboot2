package com.linor.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class SpringBootConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootConfigApplication.class, args);
	}

	@Value("${myapp.server-ip}")
	String serverIp;
	
	@Autowired
	MyAppProperties props;
	
	@Bean
	CommandLineRunner values() {
		return args -> {
			log.info("> 서버 IP: " + serverIp);
			log.info("> App Name: " + props.getName());
			log.info("> App Info: " + props.getDescription());
		};
	}
	
	@Component
	@ConfigurationProperties(prefix="myapp")
	@Data
	public static class MyAppProperties{
		private String name;
		private String description;
		private String serverIp;
		
	}
}
