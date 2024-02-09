package it.epicode.w5d1pratica.service;

import it.epicode.w5d1pratica.bean.Item;
import it.epicode.w5d1pratica.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public void salvaItem(Item item){
        itemRepository.save(item);
    }

    public  cercaItemPerId(int id){
        return itemRepository.findById(id).get();
    }

    public List<Item> cercaTuttiGliItem(){
        return itemRepository.findAll();
    }

    public void calcellaItemPerId(int id){
        itemRepository.deleteById(id);
    }
}
