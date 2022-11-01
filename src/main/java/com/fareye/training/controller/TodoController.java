package com.fareye.training.controller;

import com.fareye.training.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    TodoService todoService;
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
            if (todo.getUser().getId() == userId) {
                todosFromUser.add(todo);
            }
        }
        return todosFromUser;
    }

    @GetMapping("/todo")
    public List<Todo> getTodo(@RequestParam Integer todoId) {
        return todoService.findTodoByTodoId(todoId);
    }

    @GetMapping("/todos")
    public Collection<Todo> getTodos() {
        return todoService.getAllTodos();
    }

    @PostMapping("/todo")
    public Todo createTodo(@Valid @RequestBody Todo todo) throws Exception {
        todo.setId(id);
        todo.setCreatedDate(LocalDateTime.now());
        todos.put(id, todo);
        todoService.addTodo(todo);
        id = id + 1;
        return todo;
    }

    @DeleteMapping("/delete/todo")
    public String deleteTodo(@RequestParam Integer todoId) {
        todoService.deleteTodo(todoId);
        return "Successful";
    }

    @PutMapping("/update/todo")
    public Todo updateTodo(@RequestParam Integer todoId, @RequestBody Todo todo) throws Exception {
        return todoService.updateTodo(todo, todoId);
    }
}
