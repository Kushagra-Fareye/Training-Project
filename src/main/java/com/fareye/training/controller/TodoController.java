package com.fareye.training.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fareye.training.model.Todo;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

@RestController
public class TodoController {

    private Integer id = 1;
    private static Map<Integer, Todo> todos = new HashMap<>();
    public static List<String> titles = new ArrayList<>();

    public static void _deleteTodo(Integer todoId, String title) {
        titles.remove(title);
        todos.remove(todoId);
    }

    public static List<Todo> getAllTodosForUser(Integer userId) {
        List<Todo> todosFromUser = new ArrayList<>();
        for (Todo todo : todos.values()) {
            if (todo.getUserId() == userId) {
                todosFromUser.add(todo);
            }
        }
        return todosFromUser;
    }

    @GetMapping("/todo")
    public Todo getTodo(@RequestParam Integer todoId) {
        if (todos.containsKey(todoId))
            return todos.get(todoId);
        return new Todo();
    }

    @GetMapping("/todos")
    public Collection<Todo> getTodos() {
        return todos.values();
    }

    @PostMapping("/todo")
    public Todo createTodo(@Valid @RequestBody Todo todo) throws Exception {
        todo.setUser(UserController.getUser(todo.getUserId()));
        todo.setCreatedDate(LocalDateTime.now());
        todo.setId(id);
        todos.put(id, todo);
        id = id + 1;
        return todo;
    }

    @DeleteMapping("/delete/todo")
    public String deleteTodo(@RequestParam Integer todoId) {
        if (todos.containsKey(todoId)) {
            Todo todo = this.getTodo(todoId);
            _deleteTodo(todo.getId(), todo.getTitle());
            return "Deleted Successfully.";
        }
        return "Not Found.";
    }

    @PutMapping("/update/todo")
    public String updateTodo(@RequestParam Integer todoId, @RequestBody Todo todo) throws Exception {
        if (todos.containsKey(todoId)) {
            Todo oldTodo = todos.get(todoId);
            titles.remove(oldTodo.getTitle());
            titles.add(todo.getTitle());
            todos.remove(todoId);
            todos.put(todoId, todo);
            return "Updated Successfully.";
        }
        throw new Exception("Todo Not Found.");
    }
}
