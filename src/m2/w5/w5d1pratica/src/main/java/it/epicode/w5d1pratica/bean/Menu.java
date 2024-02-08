package it.epicode.w5d1pratica.bean;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Data
public class Menu {
    private List<Drink> drinks;
    private List<Topping> toppings;
    private List<Pizza> pizze;
    private Logger logger = LoggerFactory.getLogger("w5d2p");

    public void stampaMenu(){
        logger.info("PIZZE");
        pizze.stream().forEach(pizza ->
                logger.info(pizza.getNome() +
                        ", calorie: " + pizza.getCalorie() +
                        ", prezzo: " + pizza.getPrezzo()));

        logger.info("TOPPINGS");
        toppings.stream().forEach(topping ->
                logger.info(topping.getNome() +
                        ", calorie: " + topping.getCalorie() +
                        ", prezzo: " + topping.getPrezzo()));

        logger.info("DRINKS");
        drinks.stream().forEach(drink ->
                logger.info(drink.getNome() +
                        ", calorie: " + drink.getCalorie() +
                        ", prezzo: " + drink.getPrezzo()));
    }
}
