package com.example.shachindratripathi.jktshoppingcartapp;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class DisplayAllItems extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Items> arrayList;


    Button buttonDisplayAllItems;
    SqliteHelper sqliteHelperObj = new SqliteHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_all_items);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerDisplayAllItems);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<>();
        viewAllItems();
        Adapter adapter = new Adapter(arrayList);
        recyclerView.setAdapter(adapter);
        //recyclerView.setAdapter(new Adapter(arrayList));

    }

    public void viewAllItems() {
        buttonDisplayAllItems = (Button) findViewById(R.id.button8);
        buttonDisplayAllItems.setOnClickListener(new View.OnClickListener(){
                   @Override
                    public void onClick(View v){
                       Cursor cursorObj = sqliteHelperObj.displayAllItems();
                       if (cursorObj.getCount() == 0){
                           showMessage("Error" , "Nothing Found");
                           return;
                       }
                       else{
                           Cursor cursor = sqliteHelperObj.displayAllItems();
                           cursor.moveToFirst();
                           do{
                               Items items = new Items(cursor.getString(0),cursor.getString(1),
                                       cursor.getString(2),cursor.getString(3));
                               arrayList.add(items);
                           }
                           while (cursor.moveToNext());
                           sqliteHelperObj.close();
                       }
            }
        }
        );
    }
      public void showMessage(String title,String Message){
          AlertDialog.Builder builder = new AlertDialog.Builder(this);
          builder.setCancelable(true);
          builder.setTitle(title);
          builder.setMessage(Message);
          builder.show();


      }

}
