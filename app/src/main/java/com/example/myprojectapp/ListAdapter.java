package com.example.myprojectapp;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder>{
    Context context;
    ArrayList<Apartment> listOfApartments;
    int amount1;

    public ListAdapter(Context context, ArrayList<Apartment> listOfApartments, int amount1) {
        this.context = context;
        this.listOfApartments = listOfApartments;
        this.amount1 = amount1;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new ListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Apartment apartment = listOfApartments.get(position);
        holder.textView1.setText("" + apartment.id);
        holder.viewHolderIndex.setText("" + apartment.maxAmount);
        Picasso.get().load(apartment.listOfImages.get(0)).into(holder.imageView1);
        ViewGroup.LayoutParams params = holder.imageView1.getLayoutParams();
        params.height = 700;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ApartmentActivity.class);
                intent.putExtra("apartment", apartment.id);
                intent.putExtra("landlord", apartment.landlord);
                intent.putExtra("images", apartment.listOfImages);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listOfApartments.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder{
        TextView textView1, viewHolderIndex;
        ImageView imageView1;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.textView1);
            viewHolderIndex = itemView.findViewById(R.id.viewHolderIndex);
            imageView1 = itemView.findViewById(R.id.imageView1);

        }
    }
}
