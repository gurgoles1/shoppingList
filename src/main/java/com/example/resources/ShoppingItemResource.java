package com.example.resources;

import com.example.model.ItemForSale;
import com.example.model.ShoppingList;
import com.example.services.RestService;
import com.example.util.ShoppingGroup;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest")
public class ShoppingItemResource {

    @Autowired
    private RestService service;

    @GetMapping("/shoppingItems")
    public List<ItemForSale> getAllShoppingItems() throws InterruptedException {

        return service.getAllShoppingItems();

    }

    @GetMapping("/shoppingItems/{price}")
    public List<ItemForSale> getAllShoppingItemsUnderPrice(@PathVariable ("price") Integer pris) throws InterruptedException {


        return getAllShoppingItems().stream().filter(i -> i.getPrice() > pris).collect(Collectors.toList());

    }

    @GetMapping("/shoppingItems/{shoppingGroup}")
    public List<ItemForSale> getAllShoppingItemsInSameShoppingGroup(@PathVariable ("shoppingGroup") ShoppingGroup shoppingGroup) throws InterruptedException {


        return getAllShoppingItems().stream().filter(i -> i.getShoppingGroup() == shoppingGroup).collect(Collectors.toList());

    }

    @GetMapping("/shoppingItems/{name}")
    public ItemForSale getSingleShoppingItem(@PathVariable ("name") String name) {

        return service.getSingleShoppingItem(name);
    }

    @PostMapping("/shoppingItems")
    public ResponseEntity createItemForSale(@RequestBody ItemForSale shoppingItem) {

        service.createShoppingItem(shoppingItem);

        if(shoppingItem != null)
            return new ResponseEntity(HttpStatus.OK);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }



}
