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

public class IssueItem extends AppCompatActivity {

    Button issueItemBtn;
    EditText codeEditText;
    EditText numberEditText;
    SqliteHelper sqliteHelperObj;
    String code;
    String number;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.issue);
        getUserValues();

    }

    protected void getUserValues() {

        issueItemBtn = (Button) findViewById(R.id.button3);
        codeEditText = (EditText) findViewById(R.id.editText5);
        numberEditText = (EditText) findViewById(R.id.editText6);
        code = codeEditText.getText().toString();
        number = codeEditText.getText().toString();
        issueItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              validation();
            }
        });
    }
    void validation (){

         int sum = 0;

        try {
            int intCode = Integer.parseInt(code);
            sum = sum + 1;
        } catch (NumberFormatException e) {
            codeEditText.setError("Please enter a valid number code");
        }
        try {
            int intQuantity = Integer.parseInt(number);
            sum = sum + 1;
        } catch (NumberFormatException e) {
            numberEditText.setError("Please enter a valid number code");
        }
        if (sum == 2){
            issueItem();
        }
    }

     protected void issueItem(){


         Cursor cursor = sqliteHelperObj.searchCart(code);
         boolean condition1 = checkIfRowExists();
         boolean condition2 = checkIfRowExistsInCart();

         if(condition1 == false){
             Toast.makeText(this, "Required item not in record.",
                     Toast.LENGTH_LONG).show();
         }


         else if(condition1 == true && condition2 == true){

             String resultQuantity=  cursor.getString(3);
             int intNumber = Integer.parseInt(number);
             int intResultQuantity = Integer.parseInt(resultQuantity);
             int intUpdatedQuantity = intResultQuantity - intNumber;
             String updatedQuantity = Integer.toString(intUpdatedQuantity);


             if(intNumber < intResultQuantity){
                 int status1 = sqliteHelperObj.updateData(code,updatedQuantity);
                 int status2 = sqliteHelperObj.updateCartData(code,updatedQuantity);
                 if(status1 >= 1 && status2 >= 1) {
                     Toast.makeText(this, "Sucess!", Toast.LENGTH_LONG).show();
                 }
                 else{
                     Toast.makeText(this,"Operation failed" ,Toast.LENGTH_LONG ).show();
                 }
             }


             if(intNumber > intResultQuantity){
                 Toast.makeText(this,"Required quantity not in stock" ,Toast.LENGTH_LONG ).show();
             }

             if(intNumber == intResultQuantity){
                 boolean condition = sqliteHelperObj.delete(code);
                 if(condition == true) {
                     Toast.makeText(this, "Sucess!", Toast.LENGTH_LONG).show();
                 }
                 else{
                     Toast.makeText(this,"Operation failed" ,Toast.LENGTH_LONG ).show();
                 }

             }

         }

         else {
                 Toast.makeText(this, "Required item not in Cart. Please add to cart first.",
                         Toast.LENGTH_LONG).show();
             }
         }


     boolean checkIfRowExists(){
         Cursor cursor = sqliteHelperObj.search(code);
         if(cursor.getCount()>0){
            return true;
         }else{
            return false;
         }
     }

     boolean checkIfRowExistsInCart(){

         Cursor cursor = sqliteHelperObj.searchCart(code);
         if(cursor.getCount()>0){
             return true;
         }else{
             return false;
         }


     }
}