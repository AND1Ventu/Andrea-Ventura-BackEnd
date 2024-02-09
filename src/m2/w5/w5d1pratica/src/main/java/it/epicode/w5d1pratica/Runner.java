package it.epicode.w5d1pratica;

import it.epicode.w5d1pratica.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Runner implements CommandLineRunner {

    @Autowired








    @Override
    public void run(String... args) throws Exception {
        private Logger logger = LoggerFactory.getLogger("w5d2p");

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
//
//        Table t1 = ctx.getBean("tavolo1", Table.class);
//
//        logger.info("Il tavolo 1 è : " + t1);
//
//        Menu menu = ctx.getBean("menu", Menu.class);
//        menu.stampaMenu();
//
//        Order order = new Order(t1, 6);
//
//        List<Pizza> pizze = menu.getPizze();
//        List<Drink> drinks = menu.getDrinks();
//
//        order.setPizze(pizze);
//        order.setDrinks(drinks);
//
//        logger.info("L'ordine è: " + order);
//        logger.info("Il totale dell'ordine è: " + order.totaleOrdine());

        Menu menu = ctx.getBean("menu", Menu.class);
        menuService.salvaMenu(menu);

        Drink water = ctx.getBean("water", Drink.class);
        water.setMenu(menu);
        itemService.salvaItem(water);

        Drink cocaCola = ctx.getBean("cocaCola", Drink.class);
        itemService.salvaItem(cocaCola);

        Topping mozzarella = ctx.getBean("mozzarella", Topping.class);
        Topping pomodoro = ctx.getBean("pomodoro", Topping.class);
        Topping salame = ctx.getBean("salame", Topping.class);
        Topping prosciutto = ctx.getBean("prosciutto", Topping.class);

        itemService.salvaItem(mozzarella);
        itemService.salvaItem(pomodoro);
        itemService.salvaItem(salame);
        itemService.salvaItem(prosciutto);


        Pizza margherita = ctx.getBean("margherita", Pizza.class);
        Pizza margheritaSalame = ctx.getBean("margheritaSalame", Pizza.class);

        itemService.salvaItem(margherita);
        itemService.salvaItem(margheritaSalame);


    }
}
