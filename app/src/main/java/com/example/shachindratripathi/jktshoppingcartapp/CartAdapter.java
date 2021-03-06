package com.example.shachindratripathi.jktshoppingcartapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by shachindratripathi on 12/10/17.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.RecyclerViewHolder> {

    ArrayList<Items> cartList = new ArrayList<>();
    private Context context;



    public CartAdapter(ArrayList<Items> arrayList ){

        this.cartList = arrayList;
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cartlist_item_layout,parent,false);
        //  RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Items items = cartList.get(position);
        holder.Code.setText(items.getCode());
        holder.Name.setText(items.getName());
        holder.Rate.setText(items.getPrice());
        holder.Quantity.setText(items.getQuantity());
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }



    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView Code,Name,Rate,Quantity;

        RecyclerViewHolder(View view){
            super(view);
            Code = (TextView)view.findViewById(R.id.textView29);
            Name = (TextView)view.findViewById(R.id.textView30);
            Rate = (TextView)view.findViewById(R.id.textView31);
            Quantity= (TextView)view.findViewById(R.id.textView20 );
        }
    }
}
