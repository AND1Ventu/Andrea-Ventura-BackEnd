package com.exercise.gestioneEventi.dto;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Data
public class EventRequest {

    @NotBlank(message = "Il campo 'title' non può essere vuoto")
    @Size(min = 3, max = 100, message = "Il campo 'title' deve essere lungo tra 3 e 100 caratteri")
    private String title;

    @NotBlank(message = "Il campo 'description' non può essere vuoto")
    private String description;

    @NotNull(message = "Il campo 'date' non può essere vuoto")
    private LocalDate date;

    @NotBlank(message = "Il campo 'location' non può essere vuoto")
    private String location;

    @NotNull(message = "Il campo 'availableSeats' non può essere nullo")
    private Integer availableSeats;
}

