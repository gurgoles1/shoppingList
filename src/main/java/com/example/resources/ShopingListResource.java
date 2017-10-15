package com.example.resources;

import com.example.model.ItemForSale;
import com.example.model.ShoppingItem;
import com.example.model.ShoppingList;
import com.example.services.RestService;
import com.example.util.ShoppingGroup;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ListIterator;


@RestController
@RequestMapping(("/rest"))
public class ShopingListResource {

    @Autowired
    private RestService service;


    //Kan behöva begränsningar för hur man kan och ska posta (Så att man måste posta quantity och inte kan posta flera parametrar)
    @PostMapping("/shoppingList")
    public String shopping(@RequestBody ShoppingList myList){


        service.createShoppingList(myList);

        System.out.println(myList);

        return "hej";

    }

    //KASS DESIGN SOM KNAPPT FUNKAR. GÖR KLART FUNKTIONALITETEN SOM DU TYP VILL HA INNAN RÖR DENNA SKIT.
    @PostMapping("/shoppingItem/{name}")
    public ResponseEntity shoppingItem(@RequestBody ShoppingItem shoppingItem, @RequestBody String name) throws JsonProcessingException {

                if(service.getShoppingList(name) == null) {
                    ShoppingList kalle = new ShoppingList(name, new ArrayList<>(Arrays.asList(shoppingItem)));
                    service.createShoppingList(kalle);
                } else {
                    ShoppingList kalle = service.getShoppingList(name);
                    ArrayList<ShoppingItem> stuffToBuyAndNumberOfThem = kalle.getStuffToBuyAndNumberOfThem();
                    stuffToBuyAndNumberOfThem.add(shoppingItem);

                    ShoppingList newList = new ShoppingList(name, stuffToBuyAndNumberOfThem);
                    service.createShoppingList(newList);
                }



        return new ResponseEntity(HttpStatus.OK);

    }

    @GetMapping("/shoppingList/{name}")
    public ShoppingList getSpecificShoppingList(@PathVariable String name) {


        return service.getShoppingList(name);

    }

}
