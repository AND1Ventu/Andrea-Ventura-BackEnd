package com.exercise.gestioneEventi.controller;

import com.exercise.gestioneEventi.dto.UserRequest;
import com.exercise.gestioneEventi.exception.BadRequestException;
import com.exercise.gestioneEventi.exception.LoginFaultException;
import com.exercise.gestioneEventi.model.User;
import com.exercise.gestioneEventi.security.JwtTools;
import com.exercise.gestioneEventi.service.UserService;
import com.exercise.gestioneEventi.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTools jwtTools;
    @Autowired
    private PasswordEncoder encoder;
    @PostMapping("/auth/register")
    public User register(
            @RequestBody @Validated UserRequest userRequest,
            BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return userService.saveUserWithRole(userRequest, userRequest.getRole());
    }

    @PostMapping("/auth/login")
    public String login(@RequestBody @Validated LoginRequest loginRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        User user = userService.getUserByUsername(loginRequest.getUsername());

        if(encoder.matches(loginRequest.getPassword(), user.getPassword())){
            return jwtTools.createToken(user);
        }
        else{
            throw new LoginFaultException("username/password errate");
        }

    }
}
