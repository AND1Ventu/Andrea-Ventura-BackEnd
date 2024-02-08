package it.epicode.w5d1pratica;

import it.epicode.w5d1pratica.bean.*;
import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AppConfig {

    @Bean("water")
    public Drink water(){
        Drink water = new Drink();
        water.setNome("Water");
        water.setCalorie(0);
        water.setPrezzo(1);
        return water;
    }

    @Bean("cocaCola")
    public Drink cocaCola(){
        Drink cocaCola = new Drink();
        cocaCola.setNome("Coca Cola");
        cocaCola.setCalorie(200);
        cocaCola.setPrezzo(2);
        return cocaCola;
    }

    @Bean("mozzarella")
    public Topping mozzarella(){
        Topping mozzarella = new Topping();
        mozzarella.setNome("mozzarella");
        mozzarella.setCalorie(100);
        mozzarella.setPrezzo(2);
        return mozzarella;
    }

    @Bean("pomodoro")
    public Topping pomodoro(){
        Topping pomodoro = new Topping();
        pomodoro.setNome("pomodoro");
        pomodoro.setCalorie(100);
        pomodoro.setPrezzo(2);
        return pomodoro;
    }

    @Bean("salame")
    public Topping salame(){
        Topping salame = new Topping();
        salame.setNome("salame");
        salame.setCalorie(200);
        salame.setPrezzo(1);
        return salame;
    }

    @Bean("prosciutto")
    public Topping prosciutto(){
        Topping prosciutto = new Topping();
        prosciutto.setNome("prosciutto");
        prosciutto.setCalorie(200);
        prosciutto.setPrezzo(1);
        return prosciutto;
    }

    @Bean("margherita")
    public Pizza margherita(){
        Pizza margherita = new Pizza();
        margherita.setNome("Pizza Margherita");
        margherita.setCalorie(
                mozzarella().getCalorie() + pomodoro().getCalorie()
        );
        margherita.setPrezzo(
                mozzarella().getPrezzo() + pomodoro().getPrezzo()
        );
        margherita.setToppings(
                List.of(pomodoro(), mozzarella())
        );
        return margherita;
    }

    @Bean("margheritaSalame")
    public Pizza margheritaSalame(){
        Pizza margheritaSalame = margherita();
        margheritaSalame.setNome("Pizza Diavola");
        margheritaSalame.setCalorie(
                margheritaSalame.getCalorie() + salame().getCalorie()
        );
        margheritaSalame.setPrezzo(
                margheritaSalame.getPrezzo() + salame().getPrezzo()
        );
        margheritaSalame.getToppings().add(salame());
        return margheritaSalame;
    }

    @Bean("menu")
    public Menu menu(){
        Menu menu = new Menu();
        menu.setToppings(
                List.of(mozzarella(), pomodoro(), prosciutto(), salame())
        );
        menu.setDrinks(
                List.of(water(), cocaCola())
        );
        menu.setPizze(
                List.of(margherita(), margheritaSalame())
        );
        return menu;
    }

    @Bean("tavolo1")
    public Table tavolo1(@Value("${tavolo1.coperto}") String coperto){
        Table tavolo = new Table();
        tavolo.setNumero(1);
        tavolo.setStatoTavolo(TableState.LIBERO);
        tavolo.setNumeroMaxCoperti(6);
        tavolo.setCostoCoperto(Double.parseDouble(coperto));
        return tavolo;
    }

    @Bean("tavolo2")
    public Table tavolo2(@Value("${tavolo2.coperto}") String coperto){
        Table tavolo = new Table();
        tavolo.setNumero(2);
        tavolo.setStatoTavolo(TableState.LIBERO);
        tavolo.setNumeroMaxCoperti(8);
        tavolo.setCostoCoperto(Double.parseDouble(coperto));
        return tavolo;
    }
}
