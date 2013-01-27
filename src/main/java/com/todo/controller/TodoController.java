package com.todo.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.todo.domain.Todo;
import com.todo.repository.TodoRepository;

@Controller
@RequestMapping("/todos")
public class TodoController {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private TodoRepository todoRepository;

	@RequestMapping(method = RequestMethod.POST, value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> create(@RequestBody Todo todo) {
		logger.info("Creating Todo ...");
		todo = todoRepository.save(todo);
		logger.info("Persisted Todo : " + todo);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{todoId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Todo> view(@PathVariable("todoId") Long todoId) {
		logger.info("Read Todo with id : " + todoId);
		return new ResponseEntity<Todo>(todoRepository.findOne(todoId),
				HttpStatus.FOUND);
	}
}
