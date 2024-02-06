package it.epicode.w5d1.bean;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Persona {
    private String nome;
    private String cognome;
    private LocalDate dataNascita;


}
