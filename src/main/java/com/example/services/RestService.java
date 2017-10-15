package com.example.services;

import com.example.model.ItemForSale;
import com.example.model.ShoppingList;
import com.example.repo.ShoppingListRepo;
import com.example.repo.ShoppingRepo;
import com.example.util.ShoppingGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestService {


    public ShoppingRepo shoppingRepo;

    public ShoppingListRepo shoppingListRepo;

    @Bean
    public ItemForSale itemForSale() {
        return new ItemForSale(ShoppingGroup.BREAD, "hej", 5);
    }

    @Autowired
    public RestService(ShoppingRepo shoppingRepo, ShoppingListRepo shoppingListRepo) {
        this.shoppingRepo = shoppingRepo;
        this.shoppingListRepo = shoppingListRepo;
    }

    public List<ItemForSale> getAllShoppingItems() {

        return shoppingRepo.findAll();

    }

    public ItemForSale getSingleShoppingItem(String item) {

        return shoppingRepo.findByName(item);
    }

    public ShoppingList getShoppingList(String name) {

        return shoppingListRepo.findByName(name);
    }

    public void createShoppingItem(ItemForSale item) {

        shoppingRepo.save(item);

    }

    public void createShoppingList(ShoppingList shoppingList) {

        shoppingListRepo.save(shoppingList);
    }

}
