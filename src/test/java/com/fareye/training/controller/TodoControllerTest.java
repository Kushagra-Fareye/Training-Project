package com.fareye.training.controller;

import com.fareye.training.model.Todo;
import com.fareye.training.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class TodoControllerTest {

    private static Integer createdUserId;
    @BeforeAll
    public static void createUser(){
        RestTemplate restTemplate = new RestTemplate();
        User user = new User();
        user.setFirstName("Kushagra");
        user.setLastName("Singh");
        user.setEmail("temp@gmail.com");
        user.setPassword("password");
        user.setCompany("FarEye");
        user.setAge(20);
        user.setUserName("Kushagra-Fareye");
        User createdTodo = restTemplate.postForObject("http://localhost:8081/user", user, User.class);
        createdUserId = createdTodo.getId();
    }

    @AfterAll
    public static void deleteCreatedUser(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete("http://localhost:8081/delete/user?userId=" + createdUserId);
    }

    @Test
    void getTodo() {
        RestTemplate restTemplate = new RestTemplate();
        Todo todo = new Todo();
        todo.setTitle("Testing");
        todo.setBody("Writing tests for todo controller.");
        todo.setUserId(createdUserId);
        Todo createdTodo = restTemplate.postForObject("http://localhost:8081/todo",todo,Todo.class);
        Todo fetchedTodo = restTemplate.getForObject("http://localhost:8081/todo?todoId="+ createdTodo.getId(),Todo.class);
        assertEquals(createdTodo.getBody(), fetchedTodo.getBody());
        assertEquals(createdTodo.getTitle(), fetchedTodo.getTitle());
        assertEquals(createdTodo.getUserId(), fetchedTodo.getUserId());
        restTemplate.delete("http://localhost:8081/delete/todo?todoId=" + createdTodo.getId());
    }

    @Test
    void getTodos() {
        RestTemplate restTemplate = new RestTemplate();
        Todo todo = new Todo();
        todo.setTitle("Testing");
        todo.setBody("Writing tests for todo controller.");
        todo.setUserId(createdUserId);

        Todo createdTodo1 = restTemplate.postForObject("http://localhost:8081/todo",todo,Todo.class);

        todo.setTitle("Testing part 2");
        todo.setBody("Writing tests for todo controller again.");
        Todo createdTodo2 = restTemplate.postForObject("http://localhost:8081/todo",todo,Todo.class);

        Todo allTodos[] = restTemplate.getForObject("http://localhost:8081/todos",Todo[].class);
        assertEquals(2,allTodos.length);
        assertEquals(createdTodo1.getTitle(),allTodos[0].getTitle());
        assertEquals(createdTodo2.getTitle(), allTodos[1].getTitle());
        restTemplate.delete("http://localhost:8081/delete/todo?todoId=" + createdTodo1.getId());
        restTemplate.delete("http://localhost:8081/delete/todo?todoId=" + createdTodo2.getId());
    }

    @Test
    void createTodo() {
        RestTemplate restTemplate = new RestTemplate();
        Todo todo = new Todo();
        todo.setTitle("Testing");
        todo.setBody("Writing tests for todo controller.");
        todo.setUserId(createdUserId);
        Todo createdTodo = restTemplate.postForObject("http://localhost:8081/todo",todo,Todo.class);
        assertEquals(todo.getBody(), createdTodo.getBody());
        assertEquals(todo.getTitle(), createdTodo.getTitle());
        assertEquals(todo.getUserId(), createdTodo.getUserId());
        restTemplate.delete("http://localhost:8081/delete/todo?todoId=" + createdTodo.getId());
    }

    @Test
    void deleteTodo() {
        RestTemplate restTemplate = new RestTemplate();
        Todo todo = new Todo();
        todo.setTitle("Testing");
        todo.setBody("Writing tests for todo controller.");
        todo.setUserId(createdUserId);
        Todo createdTodo = restTemplate.postForObject("http://localhost:8081/todo",todo,Todo.class);
        restTemplate.delete("http://localhost:8081/delete/todo?todoId=" + createdTodo.getId());
        Todo fetchedTodo = restTemplate.getForObject("http://localhost:8081/todo?todoId="+ createdTodo.getId(),Todo.class);
        assertEquals(null, fetchedTodo.getBody());
        assertEquals(null, fetchedTodo.getTitle());
        assertEquals(null, fetchedTodo.getUserId());
    }

    @Test
    void updateTodo() {
        RestTemplate restTemplate = new RestTemplate();
        Todo todo = new Todo();
        todo.setTitle("Testing");
        todo.setBody("Writing tests for todo controller.");
        todo.setUserId(createdUserId);
        Todo createdTodo = restTemplate.postForObject("http://localhost:8081/todo",todo,Todo.class);
        createdTodo.setBody("Testing part 2");
        System.out.println(createdTodo.getId());
        restTemplate.put("http://localhost:8081/update/todo?todoId="+createdTodo.getId(),createdTodo);
        Todo updatedTodo = restTemplate.getForObject("http://localhost:8081/todo?todoId="+createdTodo.getId(),Todo.class);
        assertEquals(createdTodo.getBody(), updatedTodo.getBody());
        restTemplate.delete("http://localhost:8081/delete/todo?todoId=" + createdTodo.getId());
    }
}