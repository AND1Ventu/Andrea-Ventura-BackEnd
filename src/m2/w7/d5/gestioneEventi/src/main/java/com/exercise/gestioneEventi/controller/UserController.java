package com.exercise.gestioneEventi.controller;

import com.exercise.gestioneEventi.dto.UserRequest;
import com.exercise.gestioneEventi.exception.BadRequestException;
import com.exercise.gestioneEventi.model.User;
import com.exercise.gestioneEventi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/utenti")
    public List<User> getAll(){
        return userService.getAllUtenti();
    }

    @GetMapping("/utenti/{username}")
    public User getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

    @PutMapping("/utenti/{username}")
    public User updateUser(@PathVariable String username, @RequestBody @Validated UserRequest userRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return userService.updateUser(username, userRequest);

    }
    @PostMapping("/utenti")
    public User save(@RequestBody @Validated UserRequest userRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return userService.saveUser(userRequest);
    }
    @DeleteMapping("/utenti/{username}")
    public void deleteUser(@PathVariable String username){

        userService.deleteUser(username);
    }
    @PatchMapping("/utenti/{username}")
    public User changeRole(@PathVariable String username, @RequestBody String role){
        return userService.updateRoleUser(username, role);

    }
}
