package com.exercise.gestioneEventi.service;

import com.exercise.gestioneEventi.model.Role;
import com.exercise.gestioneEventi.model.User;
import com.exercise.gestioneEventi.dto.*;
import com.exercise.gestioneEventi.repository.UserRepository;
import com.exercise.gestioneEventi.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;

    public User saveAdmin(UserRequest userRequest) {
        return saveUserWithRole(userRequest, Role.AMMINISTRATORE);
    }

    public User saveManager(UserRequest userRequest) {
        return saveUserWithRole(userRequest, Role.ORGANIZZATORE);
    }

    public User saveUser(UserRequest userRequest) {
        return saveUserWithRole(userRequest, Role.UTENTE);
    }

    private User saveUserWithRole(UserRequest userRequest, Role role) {
        User utente = new User();
        utente.setUsername(userRequest.getUsername());
        utente.setPassword(encoder.encode(userRequest.getPassword()));
        utente.setRole(role);
        return userRepository.save(utente);
    }

    public User getUserById(int id){
        return userRepository.findById(id).orElseThrow(()->new NotFoundException("Utente non trovato"));
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(()->new NotFoundException("Username non trovato"));
    }

    public List<User> getAllUtenti(){
        return userRepository.findAll();
    }

    public User updateUser(String username, UserRequest userRequest){
        User utente =getUserByUsername(username);
        utente.setUsername(userRequest.getUsername());
        utente.setPassword(userRequest.getPassword());

        return userRepository.save(utente);
    }

    public User updateRoleUser(String username, String role) {
        User utente = getUserByUsername(username);
        try {
            Role userRole = Role.valueOf(role);
            utente.setRole(userRole);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role provided");
        }
        return userRepository.save(utente);
    }

    public void deleteUser(String username){
        userRepository.deleteByUsername(username).orElseThrow(()->new NotFoundException("Utente non trovato"));
    }
}
