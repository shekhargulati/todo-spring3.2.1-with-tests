package com.todo.repository;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.todo.config.DevConfig;
import com.todo.domain.Todo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=DevConfig.class)
@ActiveProfiles("dev")
@Transactional
public class TodoRepositoryTest {

	@Autowired
	private TodoRepository todoRepository;
	
	@Test
	public void todoRepositoryShouldNotBeNull() {
		assertNotNull(todoRepository);
	}

	@Test
	public void shouldSaveTodoToDatabase(){
		Todo todo = new Todo("Learn OpenShift",Arrays.asList("cloud","paas"));
		todo = todoRepository.save(todo);
		assertNotNull(todo.getId());
	}
	
	@Test
	public void shouldReadTodoById(){
		Todo todo = new Todo("Learn OpenShift",Arrays.asList("cloud","paas"));
		todo = todoRepository.save(todo);
		Todo persitedTodo = todoRepository.findOne(todo.getId());
		assertNotNull(persitedTodo);
		
	}
}
