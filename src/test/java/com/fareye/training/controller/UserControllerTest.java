package com.fareye.training.controller;

import com.fareye.training.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserControllerTest {

    @Spy
    UserController userController;

    @Test
    public void twoPlusTwoIsFour() {
        UserController userController = new UserController();
        assertEquals(4, userController.addTwoNumber(2, 2));
    }

    @Test
    public void twoPlusTenIsTwelve() {
        UserController userController = new UserController();
        assertEquals(12, userController.addTwoNumber(10, 2));
    }

    @Test
    public void createUser() {
        RestTemplate restTemplate = new RestTemplate();
        User user = new User();
        user.setFirstName("Kushagra");
        user.setLastName("Singh");
        user.setEmail("temp@gmail.com");
        user.setPassword("password");
        user.setAge(20);
        user.setUserName("Kushagra-Fareye");
        Mockito.when(userController.makeAPIcall("https://api.github.com/users/Kushagra-Fareye"))
                .thenReturn("SONYBHUNJKM");
        User addedUser = restTemplate.postForObject("http://localhost:8081/user", user, User.class);

        assertEquals(user.getLastName(), addedUser.getLastName());
        assertEquals(user.getEmail(), addedUser.getEmail());
        assertEquals(user.getFirstName(), addedUser.getFirstName());
        // restTemplate.delete("http://localhost:8081/delete/user?userId=" +
        // addedUser.getId());
    }

    @Test
    public void getUserById() {
        RestTemplate restTemplate = new RestTemplate();
        User user = new User();
        user.setFirstName("Kushagra");
        user.setLastName("Singh");
        user.setEmail("temp@gmail.com");
        user.setPassword("password");
        user.setUserName("Kushagra-Fareye");
        User addedUser = restTemplate.postForObject("http://localhost:8081/user", user, User.class);
        User fetchedUser = restTemplate.getForObject("http://localhost:8081/user?userId=" + addedUser.getId(),
                User.class);
        assertEquals(addedUser.getFirstName(), fetchedUser.getFirstName());
        assertEquals(addedUser.getEmail(), fetchedUser.getEmail());
        assertEquals(addedUser.getFirstName(), fetchedUser.getFirstName());
        restTemplate.delete("http://localhost:8081/delete/user?userId=" + addedUser.getId());

    }

    @Test
    public void deleteUserById() {
        RestTemplate restTemplate = new RestTemplate();
        User user = new User();
        user.setFirstName("Kushagra");
        user.setLastName("Singh");
        user.setEmail("temp@gmail.com");
        user.setPassword("password");
        user.setAge(20);
        user.setUserName("Kushagra-Fareye");
        User addedUser = restTemplate.postForObject("http://localhost:8081/user", user, User.class);
        restTemplate.delete("http://localhost:8081/delete/user?userId=" + addedUser.getId());
        User fetchedUser = restTemplate.getForObject("http://localhost:8081/user?userId=" + addedUser.getId(),
                User.class);
        assertEquals(null, fetchedUser.getFirstName());
        assertEquals(null, fetchedUser.getEmail());
        assertEquals(null, fetchedUser.getFirstName());
    }

    @Test
    public void getAllUserDetails() {
        RestTemplate restTemplate = new RestTemplate();
        User user = new User();
        user.setFirstName("Kushagra");
        user.setLastName("Singh");
        user.setEmail("temp@gmail.com");
        user.setPassword("password");
        user.setUserName("Kushagra-Fareye");
        User addedUser1 = restTemplate.postForObject("http://localhost:8081/user", user, User.class);
        user.setEmail("new@gmail.com");
        user.setFirstName("new");
        user.setLastName("user");
        User addedUser2 = restTemplate.postForObject("http://localhost:8081/user", user, User.class);
        User allUsers[] = restTemplate.getForObject("http://localhost:8081/users", User[].class);
        assertEquals(2, allUsers.length);
        assertEquals(addedUser1.getFirstName(), allUsers[0].getFirstName());
        assertEquals(addedUser2.getFirstName(), allUsers[1].getFirstName());
        restTemplate.delete("http://localhost:8081/delete/user?userId=" + addedUser1.getId());
        restTemplate.delete("http://localhost:8081/delete/user?userId=" + addedUser2.getId());
    }

    @Test
    public void updateUserDetails() {
        RestTemplate restTemplate = new RestTemplate();
        User user = new User();
        user.setFirstName("Kushagra");
        user.setLastName("Singh");
        user.setEmail("temp@gmail.com");
        user.setPassword("password");
        user.setAge(20);
        user.setUserName("Kushagra-Fareye");
        User addedUser = restTemplate.postForObject("http://localhost:8081/user", user, User.class);
        addedUser.setFirstName("updated");
        addedUser.setEmail("updated@gmail.com");
        restTemplate.put("http://localhost:8081/update/user?userId=" + addedUser.getId(), addedUser);
        User fetchedUser = restTemplate.getForObject("http://localhost:8081/user?userId=" + addedUser.getId(),
                User.class);
        assertEquals(addedUser.getFirstName(), fetchedUser.getFirstName());
        assertEquals(addedUser.getEmail(), fetchedUser.getEmail());
        restTemplate.delete("http://localhost:8081/delete/user?userId=" + addedUser.getId());
    }
}
