package it.epicode.w5d1pratica.service;

import it.epicode.w5d1pratica.bean.Item;
import it.epicode.w5d1pratica.bean.Menu;
import it.epicode.w5d1pratica.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public void salvaMenu(Menu menu){
        menuRepository.save(menu);
    }

    public cercaMenuPerId(int id){
        return menuRepository.findById(id).get();
    }

    public List<Menu> cercaTuttiGliMenu(){
        return menuRepository.findAll();
    }

    public void calcellaMenuPerId(int id){
        menuRepository.deleteById(id);
    }

}
