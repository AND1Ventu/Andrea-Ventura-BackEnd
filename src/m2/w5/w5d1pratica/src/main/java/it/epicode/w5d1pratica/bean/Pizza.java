package it.epicode.w5d1pratica.bean;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class Pizza extends Item{
    @ManyToMany
    @JoinTable(name = "pizze_toppings",
    joinColumns = @JoinColumn(name = "pizza_id"),
    inverseJoinColumns = @JoinColumn(name = "topping_id"))
    private List<Topping> toppings;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;
}
