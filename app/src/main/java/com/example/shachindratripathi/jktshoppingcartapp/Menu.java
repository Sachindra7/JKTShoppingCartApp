package com.example.shachindratripathi.jktshoppingcartapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by shachindratripathi on 11/10/17.
 */


public class Menu extends AppCompatActivity{

    Button displayAllItems;
    Button searchItems;
    Button addItemsToList;
    Button addItemsToCart;
    Button displayCart;
    Button issueItems;
    Button exit;

    AddItemsToList addItemsToListObj;
    DisplayAllItems displayAllObj ;
    IssueItem issueItemObj;
    AddItemsToCart addItemsToCartObj;
    DisplayCart displayCartObj;
    Search searchObj;


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);



    }
    public void displayMenu(){

        displayAllItems = (Button)findViewById(R.id.button8);
        searchItems = (Button)findViewById(R.id.button9);
        addItemsToList = (Button)findViewById(R.id.button10);
        addItemsToCart = (Button)findViewById(R.id.button11);
        displayCart = (Button)findViewById(R.id.button12);
        issueItems = (Button)findViewById(R.id.button13);
        exit = (Button)findViewById(R.id.button14);
        displayAllItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Menu.this,DisplayAllItems.class);
                startActivity(intent1);
              //  displayAllObj.viewAllItems();
            }
        });searchItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Menu.this,Search.class);
                startActivity(intent2);
            //    searchObj.searchItems();
            }
        });addItemsToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(Menu.this,AddItemsToList.class);
                startActivity(intent3);
                addItemsToListObj.addItemsToList();
            }
        });addItemsToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(Menu.this,AddItemsToCart.class);
                startActivity(intent4);
                addItemsToCartObj.getUserInput();
            }
        });displayCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(Menu.this,DisplayCart.class);
                startActivity(intent5);
                displayCartObj.displayCart();
            }
        });issueItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent6 = new Intent(Menu.this,IssueItem.class);
                startActivity(intent6);
           //     issueItemObj.assignValues();
            }
        });exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // exit();
            }
        });
    }
}
