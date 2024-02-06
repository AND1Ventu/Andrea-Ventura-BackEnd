package it.epicode.w5d1pratica.bean;

import lombok.Data;

import java.util.List;

@Data
public class Menu {
    private List<Drink> drinks;
    private List<Topping> toppings;
    private List<Pizza> pizze;

    public void stampaMenu(){
        System.out.println("PIZZE");
        pizze.stream().forEach(pizza ->
                System.out.println(pizza.getNome() +
                        ", calorie: " + pizza.getCalorie() +
                        ", prezzo: " + pizza.getPrezzo()));
        System.out.println();
        System.out.println("TOPPINGS");
        toppings.stream().forEach(topping ->
                System.out.println(topping.getNome() +
                        ", calorie: " + topping.getCalorie() +
                        ", prezzo: " + topping.getPrezzo()));
        System.out.println();
        System.out.println("DRINKS");
        drinks.stream().forEach(drink ->
                System.out.println(drink.getNome() +
                        ", calorie: " + drink.getCalorie() +
                        ", prezzo: " + drink.getPrezzo()));
    }
}
