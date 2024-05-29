package com.example.myprojectapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {
    private LinearLayout layout_nav1, layout_nav4;
    private RecyclerView recyclerView;
    private TextView textView3, textView_empty;
    private DatabaseReference myDatabaseApartments;
    private String APARTMENTS = "Apartments";
    private ListAdapter listAdapter;
    private DatabaseReference myDatabaseUsers;
    private String USERS = "Users";
    ArrayList<Apartment> list;
    ArrayList<Integer> favorites;
    private int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        layout_nav1 = findViewById(R.id.layout_nav1);
        layout_nav4 = findViewById(R.id.layout_nav4);
        textView3 = findViewById(R.id.textView3);
        textView_empty = findViewById(R.id.textView_empty);

        Window window = getWindow();
        window.setStatusBarColor(Color.parseColor("#FFFFFFFF"));

        SharedPreferences mSettings = getSharedPreferences("mySettings", Context.MODE_PRIVATE);
        userId = mSettings.getInt("id", 0);


        recyclerView = findViewById(R.id.Recyclerview);
        myDatabaseApartments = FirebaseDatabase.getInstance().getReference(APARTMENTS);
        myDatabaseUsers = FirebaseDatabase.getInstance().getReference(USERS);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        favorites = new ArrayList<>();
        listAdapter = new ListAdapter(this, list, favorites);
        recyclerView.setAdapter(listAdapter);

        if (userId != 0){
            myDatabaseUsers.child(userId + "").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User user = snapshot.getValue(User.class);
                    favorites.addAll(user.favorites);
                    listAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        myDatabaseApartments.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!list.isEmpty()){
                    list.clear();
                }
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Apartment apartment = dataSnapshot.getValue(Apartment.class);
                    if (favorites.contains(apartment.apartmentId)){
                        list.add(apartment);
                    }
                }
                listAdapter.notifyDataSetChanged();
                if (!list.isEmpty()){
                    textView3.setLayoutParams(textView_empty.getLayoutParams());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        layout_nav1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FavoriteActivity.this, MainActivity4.class);
                startActivity(intent);

            }
        });
        layout_nav4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences mSettings = getSharedPreferences("mySettings", Context.MODE_PRIVATE);
                if (mSettings.getInt("id", 0) == 0){
                    Intent intent = new Intent(FavoriteActivity.this, SignInActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(FavoriteActivity.this, Account_Activity.class);
                    startActivity(intent);
                }
            }
        });
    }
}