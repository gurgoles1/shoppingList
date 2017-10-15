package com.example.repo;

import com.example.model.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ShoppingListRepo extends JpaRepository<ShoppingList,String> {

    public ShoppingList findByName(String name);

}
