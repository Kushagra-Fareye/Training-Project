package com.fareye.training.controller;

import com.fareye.training.model.Todo;
import com.fareye.training.model.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

@RestController
public class UserController {
    private Integer id = 1;
    private static Map<Integer, User> users = new HashMap<>();

    public User findUser(Integer id) {
        if (users.containsKey(id)) {
            return users.get(id);
        }
        return null;
    }

    private String passwordEncryptor(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(10));
    }

    @GetMapping("/")
    public String hello(@RequestParam String name) {
        return "Hello " + name;
    }

    @GetMapping("/users")
    public Collection<User> getUsers() {
        return users.values();
    }

    @GetMapping("/user")
    public static User getUser(@RequestParam Integer userId) throws Exception {
        if (users.containsKey(userId)) {
            return users.get(userId);
        }
        throw new Exception("User not found.");
    }

    @PostMapping("/user")
    public Collection<User> createUser(@Valid @RequestBody User user) {
        user.setPassword(passwordEncryptor(user.getPassword()));
        user.setId(id);
        user.setCreated(LocalDateTime.now());
        user.setName(user.getFirstName() + ' ' + user.getLastName());
        users.put(id, user);
        id = id + 1;
        return users.values();
    }

    @GetMapping("/exceptionapi2")
    public void exceptionDemo() throws Exception {
        throw new Exception("Demo Exception");
    }

    @DeleteMapping("/delete/user")
    public String deleteUser(@RequestParam Integer userId) {
        if (users.containsKey(userId)) {
            List<Todo> todosFromUser = TodoController.getAllTodosForUser(userId);
            for (Todo todo : todosFromUser) {
                TodoController._deleteTodo(todo.getId());
            }
            users.remove(userId);
            return "Deleted Successfully.";
        }
        return "Not Found.";
    }

    @PutMapping("/update/user")
    public String updateTodo(@RequestParam Integer userId, @RequestBody User user) throws Exception {
        if (users.containsKey(userId)) {
            user.setName(user.getFirstName() + ' ' + user.getLastName());
            users.put(userId, user);
            return "Updated Successfully.";
        }
        throw new Exception("User Not Found.");
    }
}
