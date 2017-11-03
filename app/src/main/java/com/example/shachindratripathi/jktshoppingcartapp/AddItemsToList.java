package com.example.shachindratripathi.jktshoppingcartapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by shachindratripathi on 10/10/17.
 */

public class AddItemsToList extends AppCompatActivity {



    EditText codeInput,nameInput,rateInput,quantityInput;
    String code, name,rate,quantity;
    Button btnSubmit;

    SqliteHelper sqliteHelper = new SqliteHelper(this);
    SQLiteDatabase dbWriteableObj = sqliteHelper.getWritableDatabase();


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_items_to_list);
        getUserData();
    }

    protected void getUserData(){

        codeInput = (EditText)findViewById(R.id.editText);
        nameInput = (EditText)findViewById(R.id.editText2);
        rateInput = (EditText)findViewById(R.id.editText3);
        quantityInput = (EditText)findViewById(R.id.editText4);
        btnSubmit = (Button)findViewById(R.id.button2);
        code = codeInput.getText().toString();
        name = nameInput.getText().toString();
        rate = rateInput.getText().toString();
        quantity = quantityInput.getText().toString();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                validation();

            }}
        );
    }

    public void validation(){


        boolean  condition2 = false;
        boolean  condition3 = false;
        int sum = 0;


        try {
            int intCode = Integer.parseInt(code);
            sum = sum + 1;
        } catch (NumberFormatException e) {
            codeInput.setError("Please enter a valid number code");
        }


        if (!name.matches("[a-zA-Z_]+")) {
            nameInput.setError("Please enter a valid name.");
        }
        else {
            condition2 = true;
        }
         if( name.length() > 20 )
        {
            nameInput.setError("Name is too long. Please enter a name of suitable length.");
        }
        else {
             condition3 = true;
         }


        try {
            float floatRate = Float.parseFloat(rate);
            sum = sum + 1;
        } catch (NumberFormatException e) {
            rateInput.setError("Please enter a valid rate");
        }


        try {
            int intQuantity = Integer.parseInt(quantity);
            sum = sum + 1;

        } catch (NumberFormatException e) {
            quantityInput.setError("Please enter a valid quantity");
        }
       if (sum == 3 && condition2 == true && condition3 == true){
           addItemsToList();
       }

    }


    public void addItemsToList(){


        boolean status = checkIfRowExists();
        if(status == false){
            boolean condition = sqliteHelper.insertData(code,name,rate,quantity);
            if (condition == true){
                Toast.makeText(this,"Item added to List" ,Toast.LENGTH_LONG ).show();
            }else{
                Toast.makeText(this,"Operation Failed" ,Toast.LENGTH_LONG ).show();
            }
        }
        else {
            Toast.makeText(this, "Required item already exists.", Toast.LENGTH_LONG).show();
        }

    }
    boolean checkIfRowExists(){

        Cursor cursor = sqliteHelper.searchCart(code);
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

}


