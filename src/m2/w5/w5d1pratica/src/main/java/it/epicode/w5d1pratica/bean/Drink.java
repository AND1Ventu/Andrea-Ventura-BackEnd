package it.epicode.w5d1pratica.bean;


import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Drink extends Item {

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

}
