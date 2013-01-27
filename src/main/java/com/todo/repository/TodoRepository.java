package com.todo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.todo.domain.Todo;

public interface TodoRepository extends PagingAndSortingRepository<Todo, Long> {

}
