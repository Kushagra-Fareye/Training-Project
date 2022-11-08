package com.fareye.training.service;

import com.fareye.training.model.Todo;
import com.fareye.training.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TodoService {
    @Autowired
    TodoRepository todoRepository;

    public void addTodo(Todo todo) {
        todoRepository.saveAndFlush(todo);
    }

    public void deleteTodo(Integer todoId) {
        todoRepository.deleteById(todoId);
    }

    public List<Todo> findTodoByTodoId(Integer todoId) {
        return todoRepository.findAllById(Collections.singleton(todoId));
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo updateTodo(Todo newTodo, Integer todoId) {
        Todo todo = todoRepository.findById(todoId).get();
        todo.setBody(newTodo.getBody());
        todo.setTitle(newTodo.getTitle());
        todo.setStatus(newTodo.getStatus());
        todoRepository.save(todo);
        return todo;
    }
}