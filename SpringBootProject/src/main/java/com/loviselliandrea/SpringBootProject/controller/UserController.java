package com.loviselliandrea.SpringBootProject.controller;

import com.loviselliandrea.SpringBootProject.model.User;
import com.loviselliandrea.SpringBootProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable("id") int id){
        return userService.getUserById(id);
    }

    @GetMapping("/")
    public Iterable<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/search/username/{partialUsername}")
    public List<User> searchUserByUsername(@PathVariable("partialUsername") String partialUsername){
        return userService.findByUsernameContains(partialUsername);
    }

    @GetMapping("/search/username/{partialUsername}/mail/{partialMail}")
    public List<User> searchUserByUsernameAndEmail(@PathVariable("partialUsername") String partialUsername,
                                                   @PathVariable("partialMail") String partialMail) {
        return userService.findByUsernameContainsAndEmailContains(partialUsername, partialMail);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id){
        return userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable("id") int id, @RequestBody User user){
        return userService.updateUser(id,user);
    }

    @PostMapping("/registration/")
    public String registration(@RequestBody User user){
        return userService.registerUser(user);
    }

    /* PostMan example ->
            {
                "username" : "utente",
                "password" : "123456"
            }
    */
    @PostMapping("/login/")
    public String login(@RequestBody User user){
        return userService.login(user.getUsername(), user.getPassword());
    }

}
