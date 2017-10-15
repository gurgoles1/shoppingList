package com.example.repo;

import com.example.model.ItemForSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShoppingRepo extends JpaRepository<ItemForSale, String> {

    public ItemForSale findByName(String shoppingItemName);


}
