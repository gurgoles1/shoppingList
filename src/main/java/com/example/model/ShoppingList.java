package com.example.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
public class ShoppingList implements Serializable {

    @Id
    public String name;

    @Column(length = 60000)
    public ArrayList<ShoppingItem> stuffToBuyAndNumberOfThem;

    //Behövs för att annars får man 500 om man postar från frontend
    public ShoppingList() {

    }

    @JsonCreator
    public ShoppingList(@JsonProperty("name") String name, @JsonProperty("stuffToBuyAndNumberOfThem") ArrayList<ShoppingItem> stuffToBuyAndNumberOfThem) {
        this.name = name;
        this.stuffToBuyAndNumberOfThem = stuffToBuyAndNumberOfThem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ShoppingItem> getStuffToBuyAndNumberOfThem() {
        return stuffToBuyAndNumberOfThem;
    }

    public void setStuffToBuyAndNumberOfThem(ArrayList<ShoppingItem> stuffToBuyAndNumberOfThem) {
        this.stuffToBuyAndNumberOfThem = stuffToBuyAndNumberOfThem;
    }

    public void addStuffToBuy(ShoppingItem shoppingItem) {

        stuffToBuyAndNumberOfThem.add(shoppingItem);
    }
}
