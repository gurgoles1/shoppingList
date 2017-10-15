package com.example.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class ShoppingItem implements Serializable{

    @Id
    public ItemForSale itemForSale;

    public Integer quantity;

    public ShoppingItem() {

    }

    @JsonCreator
    public ShoppingItem(@JsonProperty("itemForSale") ItemForSale itemForSale, @JsonProperty("quantity") Integer quantity) {

        this.itemForSale = itemForSale;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShoppingItem that = (ShoppingItem) o;

        return itemForSale.equals(that.itemForSale);
    }

    @Override
    public int hashCode() {
        int result = itemForSale != null ? itemForSale.hashCode() : 0;
        result = 31 * result + quantity;
        return result;
    }

    public ItemForSale getItemForSale() {

        return itemForSale;
    }

    public void setItemForSale(ItemForSale itemForSale) {
        this.itemForSale = itemForSale;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
