package com.example.myprojectapp;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder>{
    private static int viewHolderCount;
    private int ListItems;
    private int amount1;
    private int amount2;
    private Context parent;
    private MainActivity4.MyIntentInterface myInt;
    private DatabaseReference myDatabaseApartment;

    public ListAdapter(int ListOfItems, Context parent , int amount1, int amount2, MainActivity4.MyIntentInterface myInt) {
        ListItems = ListOfItems;
        this.amount1 = amount1;
        this.amount2 = amount2;
        viewHolderCount = 0;
        this.parent = parent;
        this.myInt = myInt;
    }
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int list_item = R.layout.list_item;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(list_item, parent, false);
        ListViewHolder viewHolder = new ListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return ListItems;
    }


    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;
        TextView viewHolderIndex;
        public ListViewHolder(View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView1);
            viewHolderIndex = itemView.findViewById(R.id.viewHolderIndex);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myInt.MyIntent();
                }
            });
        }
        void bind(int listIndex){
            textView1.setText(String.valueOf(listIndex) + amount1 + amount2);
        }
    }
}
