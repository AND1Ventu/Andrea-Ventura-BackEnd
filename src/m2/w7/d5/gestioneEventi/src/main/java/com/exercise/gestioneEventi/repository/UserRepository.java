package com.exercise.gestioneEventi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.exercise.gestioneEventi.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>, PagingAndSortingRepository<User, Integer> {

    public Optional<User> findByUsername(String username);

    public Optional<User> deleteByUsername(String username);
}
