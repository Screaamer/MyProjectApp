package com.example.myprojectapp;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    ArrayList<Integer> favorites;


    public ListAdapter(Context context, ArrayList<Apartment> listOfApartments, ArrayList<Integer> favorites) {
        this.context = context;
        this.listOfApartments = listOfApartments;
        this.favorites = favorites;
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
        holder.textView_city.setText("" + apartment.city);
        holder.textView_adress.setText("" + apartment.address);
        Picasso.get().load(apartment.listOfImages.get(0)).into(holder.imageView1);
        ViewGroup.LayoutParams params = holder.imageView1.getLayoutParams();
        params.height = 700;
        if (favorites.contains(apartment.apartmentId)){
            holder.imageView_favorite.setImageResource(R.drawable.baseline_favorite_24);
            holder.flag = true;
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ApartmentActivity.class);
                intent.putExtra("apartment", apartment.apartmentId);
                intent.putExtra("landlordId", apartment.landlordId);
                intent.putExtra("images", apartment.listOfImages);
                intent.putExtra("flag", holder.flag);
                intent.putExtra("description", apartment.description);
                intent.putExtra("MaxAmount", apartment.maxAmount);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfApartments.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder{
        TextView textView_city, textView_adress;
        ImageView imageView1, imageView_favorite;
        boolean flag;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_city = itemView.findViewById(R.id.textView_city);
            textView_adress = itemView.findViewById(R.id.textView_adress);
            imageView1 = itemView.findViewById(R.id.imageView1);
            imageView_favorite = itemView.findViewById(R.id.imageView_favorite);
            flag = false;
        }
    }
}
