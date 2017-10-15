package com.example.model;

import com.example.util.ShoppingGroup;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class ItemForSale implements Serializable {

    @Id
    public String name;

    public ShoppingGroup shoppingGroup;

    public Integer price;

    //Tom konstruktor behövs för att annars får man 500 om man postar från frontend
    public ItemForSale() {

    }

    @JsonCreator
    public ItemForSale(@JsonProperty("shoppingGroup") ShoppingGroup shoppingGroup,@JsonProperty("name") String name, @JsonProperty("price") Integer price) {
        this.shoppingGroup = shoppingGroup;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ShoppingGroup getShoppingGroup() {
        return shoppingGroup;
    }

    public void setShoppingGroup(ShoppingGroup shoppingGroup) {
        this.shoppingGroup = shoppingGroup;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemForSale that = (ItemForSale) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
