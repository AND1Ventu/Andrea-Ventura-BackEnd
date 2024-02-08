package it.epicode.w5d1pratica;

import it.epicode.w5d1pratica.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Runner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        private Logger logger = LoggerFactory.getLogger("w5d2p");

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        Table t1 = ctx.getBean("tavolo1", Table.class);

        logger.info("Il tavolo 1 è : " + t1);

        Menu menu = ctx.getBean("menu", Menu.class);
        menu.stampaMenu();

        Order order = new Order(t1, 6);

        List<Pizza> pizze = menu.getPizze();
        List<Drink> drinks = menu.getDrinks();

        order.setPizze(pizze);
        order.setDrinks(drinks);

        logger.info("L'ordine è: " + order);
        logger.info("Il totale dell'ordine è: " + order.totaleOrdine());
    }
}
