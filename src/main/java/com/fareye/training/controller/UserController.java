package com.fareye.training.controller;

import com.fareye.training.model.User;
import com.fareye.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    UserService userService;
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

    @GetMapping("/users")
    public Collection<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user")
    public List<User> getUser(@RequestParam Integer userId) throws Exception {
        return userService.findUserByUserId(userId);

    }

    @PostMapping("/user")
    public User createUser(@Valid @RequestBody User user) {
        user.setPassword(passwordEncryptor(user.getPassword()));
        user.setId(id);
        user.setCreated(LocalDateTime.now());
        user.setName(user.getFirstName() + ' ' + user.getLastName());
        user.setAvatar_url(this.getUserAvatar(user.getUserName()));
        users.put(id, user);
        id = id + 1;
        userService.addUser(user);
        return user;
    }

    @GetMapping("/exceptionapi2")
    public void exceptionDemo() throws Exception {
        throw new Exception("Demo Exception");
    }

    @DeleteMapping("/delete/user")
    public String deleteUser(@RequestParam Integer userId) {
        userService.deleteUser(userId);
        return "Successful";
    }

    @PutMapping("/update/user")
    public User updateTodo(@RequestParam Integer userId, @RequestBody User user) throws Exception {

        return userService.updateUser(user, userId);
    }

    @GetMapping("/user/avatar")
    public String getUserAvatar(@RequestParam String userName) {
        String url = "https://api.github.com/users/" + userName;
        return makeAPIcall(url);
    }

    public String makeAPIcall(String url) {
        RestTemplate restTemplate = new RestTemplate();
        User userDetails = restTemplate.getForObject(url, User.class);
        return userDetails.getAvatar_url();
    }

    public long addTwoNumber(long a, long b) {
        return a + b;
    }
}
