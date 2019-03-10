package com.linor.todo.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(ToDoProperties.class)
@Configuration
public class ToDoConfig {

}
