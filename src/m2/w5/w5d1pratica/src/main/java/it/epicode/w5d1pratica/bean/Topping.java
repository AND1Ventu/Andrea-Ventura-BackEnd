package it.epicode.w5d1pratica.bean;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import javax.persistence.Entity;
import java.util.List;

@Data
@Entity
public class Topping extends Item{

    @ManyToMany(mappedBy = "toppings")
    private List<Pizza> pizze;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

}
