package it.epicode.w5d1pratica.bean;

import lombok.Data;

import java.util.List;

@Data
public class Pizza extends Item{
    private List<Topping> toppings;
}
