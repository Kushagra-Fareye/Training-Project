package com.fareye.training.service;

import com.fareye.training.model.User;
import com.fareye.training.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void addUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(Integer userId){
        userRepository.deleteById(userId);
    }

    public List<User> findUserByUserId(Integer id){
        return userRepository.findAllById(Collections.singleton(id));
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User updateUser(User user,Integer userId){
        User userDetails= userRepository.findById(userId).get();
        userDetails.setUserName(user.getUserName());
        userDetails.setLastName(user.getLastName());
        userDetails.setBloodGroup((user.getBloodGroup()));
        userDetails.setPhoneNumber(user.getPhoneNumber());
        userRepository.save(userDetails);
        return userDetails;
    }
}
