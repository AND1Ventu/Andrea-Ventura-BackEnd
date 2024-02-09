package it.epicode.w5d1pratica.service;

import it.epicode.w5d1pratica.bean.Drink;
import it.epicode.w5d1pratica.repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrinkService {
    @Autowired
    private DrinkRepository drinkRepository;

    public void salvaDrink(Drink drink) {
        drinkRepository.save(drink);
    }

    public void aggiornaDrink(Drink drink) {
        Drink d = drinkRepository.findById(drink.getId()).orElse(null);
        if (d != null) {
            d.setNome(drink.getNome());
            d.setCalorie(drink.getCalorie());
            d.setPrezzo(drink.getPrezzo());

            drinkRepository.save(d);
        }
    }

    public Drink cercaDrinkPerId(Long id) {
        return drinkRepository.findById(id).orElse(null);
    }

    public List<Drink> getAllDrinks() {
        return drinkRepository.findAll();
    }

    public void cancellaDrink(Long id) {
        drinkRepository.deleteById(id);
    }
}
