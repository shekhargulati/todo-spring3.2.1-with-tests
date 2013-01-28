package com.todo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.todo.controller.TodoController;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = TodoController.class)
public class WebConfig {

}
