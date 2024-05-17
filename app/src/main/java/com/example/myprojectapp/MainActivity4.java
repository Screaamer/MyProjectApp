package com.example.myprojectapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.security.PrivateKey;
import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity {
    private LinearLayout layout_nav2;
    private LinearLayout layout_nav3;
    private LinearLayout layout_nav4;
    private LinearLayout layout_filter;
    private ImageView imageView;
    private TextView textView12;
    private int amount1;
    private RecyclerView recyclerView;
    private DatabaseReference myDatabaseApartments;
    private String APARTMENTS = "Apartments";
    private static StorageReference mStorageReference;
    private static String IMAGES = "Images";
    private ListAdapter listAdapter;
    ArrayList<Apartment> list;
    ArrayList<String> listOfImages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        layout_nav2 = findViewById(R.id.layout_nav2);
        layout_nav3 = findViewById(R.id.layout_nav3);
        layout_nav4 = findViewById(R.id.layout_nav4);
        layout_filter = findViewById(R.id.layout_filter);
        textView12 = findViewById(R.id.textView12);
        imageView = findViewById(R.id.imageView);


        Window window = getWindow();
        window.setStatusBarColor(Color.parseColor("#FFFFFFFF"));

        Intent intent = getIntent();
        amount1 = intent.getIntExtra("amount", 0);
        recyclerView = findViewById(R.id.Recyclerview1);
        myDatabaseApartments = FirebaseDatabase.getInstance().getReference(APARTMENTS);
        mStorageReference = FirebaseStorage.getInstance().getReference(IMAGES);
        String value = mStorageReference.child("1").child("1").getDownloadUrl().toString();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        listAdapter = new ListAdapter(this, list, amount1);
        recyclerView.setAdapter(listAdapter);


        myDatabaseApartments.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!list.isEmpty()){
                    list.clear();
                }
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Apartment apartment = dataSnapshot.getValue(Apartment.class);
                    if (apartment.maxAmount >= amount1){
                        list.add(apartment);
                    }

                }
                listAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        layout_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(MainActivity4.this, MainActivity2.class);
                    startActivity(intent);
            }
        });
        layout_nav2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity4.this, FavoriteActivity.class);
                startActivity(intent);

            }
        });
        layout_nav3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity4.this, TripsActivity.class);
                startActivity(intent);
            }
        });
        layout_nav4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences mSettings = getSharedPreferences("mySettings", Context.MODE_PRIVATE);
                if (mSettings.getInt("id", 0) == 0){
                    Intent intent = new Intent(MainActivity4.this, SignInActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MainActivity4.this, Account_Activity.class);
                    startActivity(intent);
                }
            }
        });
    }
}