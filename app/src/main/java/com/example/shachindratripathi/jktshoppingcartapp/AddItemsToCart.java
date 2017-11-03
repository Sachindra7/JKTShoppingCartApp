package com.example.shachindratripathi.jktshoppingcartapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by shachindratripathi on 10/10/17.
 */

public class AddItemsToCart extends AppCompatActivity {

    SqliteHelper sqliteHelper = new SqliteHelper(this);

    EditText codeInput;
    Button btnSubmit;
    String code;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_items_to_cart);
        getUserInput();

    }

    protected void getUserInput() {

        codeInput = (EditText) findViewById(R.id.editText7);
        btnSubmit = (Button) findViewById(R.id.button4);
        code = codeInput.getText().toString();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
                                         public void onClick(View v) {
                                             validation();


                                         }
                                     }
        );
    }

    public void validation() {

        try {
            int intCode = Integer.parseInt(code);
            Cursor cursor = sqliteHelper.search(code);
            if (cursor.moveToNext() == true) {
                addItemToCart();
            } else {
                codeInput.setError("Item does not exist");
            }
        } catch (NumberFormatException e) {
            codeInput.setError("Please enter a valid code.");
        }
    }


    //check if row with given code exists in TABLE
    protected void addItemToCart() {
        boolean status = checkIfRowExistsInCart();
        if (status == false) {
            sqliteHelper.insertCartData(code);
            Toast.makeText(this, "Item added to Cart !", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Required item already exists in Cart.", Toast.LENGTH_LONG).show();
        }
    }

    boolean checkIfRowExistsInCart(){

        Cursor cursor = sqliteHelper.searchCart(code);
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }
}


