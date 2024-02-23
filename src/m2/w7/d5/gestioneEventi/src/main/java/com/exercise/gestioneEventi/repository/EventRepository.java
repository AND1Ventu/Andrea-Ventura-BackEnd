package com.exercise.gestioneEventi.repository;

import com.exercise.gestioneEventi.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EventRepository extends JpaRepository<Event, Integer>, PagingAndSortingRepository<Event, Integer> {
}
