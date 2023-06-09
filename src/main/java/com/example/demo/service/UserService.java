package com.example.demo.service;

import com.example.demo.entitiy.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> findall() {
        return userRepository.findAll();
    }


    public User find(int id) {
        Optional<User> result = userRepository.findById(id);
        if(result.isPresent()) {
           User user = result.get();
            return user;
        }else{
            throw new RuntimeException("User not found :"+id);
        }

    }


    public User save(User user) {
        return userRepository.save(user);
    }


    public User update(int userId,User user){
        Optional<User> user1=userRepository.findById(userId);
        if(user1.isPresent()){
            User user2=user1.get();
            user2.setUserName(user.getUserName());
            user2.setPassword(user.getPassword());
            userRepository.save(user2);
            return user;
        }else{
            throw new RuntimeException("Customer not found :");

        }

    }

    public void deleteAll(){
        userRepository.deleteAll();
    }


    public void delete(int id) {
        userRepository.deleteById(id);
    }

    public User getOneUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
