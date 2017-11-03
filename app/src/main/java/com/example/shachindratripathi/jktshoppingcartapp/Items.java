package com.example.shachindratripathi.jktshoppingcartapp;

import java.util.ArrayList;

/**
 * Created by shachindratripathi on 11/10/17.
 */

public class Items {

    String code;
    String name;
    String price;
    String quantity;

   // ArrayList<>
    Items(String code, String name,String price,String quantity){
       this.code = code;
       this.name = name;
       this.price = price;
       this.quantity = quantity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
