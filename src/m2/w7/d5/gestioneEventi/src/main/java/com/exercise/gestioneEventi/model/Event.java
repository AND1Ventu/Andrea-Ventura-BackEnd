package com.exercise.gestioneEventi.model;

import lombok.Data;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDate date;
    private String location;
    private Integer availableSeats;

    @ManyToMany(mappedBy = "participants")
    private Set<User> participants = new HashSet<>();

    public Integer getBookedSeats() {
        return participants.size();
    }

    public void setBookedSeats(int bookedSeats) {
        int newAvailableSeats = availableSeats - bookedSeats;

        if (newAvailableSeats >= 0) {
            this.availableSeats = newAvailableSeats;
        } else {
            throw new IllegalArgumentException("Il numero di posti prenotati supera i posti disponibili.");
        }
    }
}


