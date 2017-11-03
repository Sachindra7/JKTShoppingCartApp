package com.example.shachindratripathi.jktshoppingcartapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by shachindratripathi on 10/10/17.
 */


public class DisplayCart extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Items> cartList;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_cart);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerCartItems);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartList = new ArrayList<>();
        displayCart();
        Adapter adapter = new Adapter(cartList);
        recyclerView.setAdapter(adapter);
        //recyclerView.setAdapter(new Adapter(arrayList));

    }

    SqliteHelper sqliteHelperObj;

    public void displayCart() {

        Cursor cursorObj = sqliteHelperObj.displayCartItems();

        if (cursorObj.getCount() == 0){
            showMessage("Error" , "Nothing Found");
            return;
        }
        //  Pending : Implement recycler view to show cart data
        else{
            Cursor cursor = sqliteHelperObj.displayCartItems();
            cursor.moveToFirst();
            do{
                Items items = new Items(cursor.getString(0),cursor.getString(1),
                        cursor.getString(2),cursor.getString(3));
                cartList.add(items);
            }
            while (cursor.moveToNext());
            sqliteHelperObj.close();
        }
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
        
    }
}
