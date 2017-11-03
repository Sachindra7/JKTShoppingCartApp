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

public class Search extends AppCompatActivity{

    SqliteHelper sqliteHelper = new SqliteHelper(this);
    EditText codeInput;
    Button btnSubmit;
    String code;

        public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        getInput();

        }

    protected void  getInput(){

           SqliteHelper sqliteHelperObj = new SqliteHelper(this);

          /*  String columns[] = {"code", "name", "rate", "quantity"};
            Cursor cursor = dbReadableObj.query("Items",columns,null,null,null,null,null);
            cursor.moveToFirst();
            System.out.println("name is" + cursor.getString(1));  */
           codeInput = (EditText) findViewById(R.id.editText8);
           btnSubmit = (Button) findViewById(R.id.button5);
           code = codeInput.getText().toString();
           btnSubmit.setOnClickListener(new View.OnClickListener() {
               public void onClick(View v) {

                   validation();

                  // String result = buffer.toString();
               }}
           );
       }

      protected void validation(){

          try {
              int intCode = Integer.parseInt(code);
              searchItem();
          } catch (NumberFormatException e) {
              codeInput.setError("Please enter a valid number code");
          }

       }
        void searchItem(){
                final StringBuffer buffer = new StringBuffer();
                Cursor cursor =  sqliteHelper.searchItems(code);
            if(cursor.getCount()>0){
                String resultCode =  cursor.getString(0) ;
                String resultName=  cursor.getString(1) ;
                String resultRate =  cursor.getString(2) ;
                String resultQuantity=  cursor.getString(3)  ;
                buffer.append(resultCode +" " + resultName + " " + resultRate +" " + resultQuantity);
                Toast.makeText(this,buffer, Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "Required item does not exists.", Toast.LENGTH_LONG).show();
            }

            }

        }


