package com.example.demo.contoroller;

import com.example.demo.entitiy.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/findAll")
    public List<User>findAll(){
        return userService.findall();
    }

    @GetMapping("/find/{id}")
    public User findById(@PathVariable int id){
        return userService.find(id);
    }

    @PostMapping("/save")
    public User save(@RequestBody User user){
        return userService.save(user);
    }

    @PutMapping("/update")
    public User update(@PathVariable int id,@RequestBody User user){
       return userService.update(id,user);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id ){
        userService.delete(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll(){
        userService.deleteAll();
    }



}
