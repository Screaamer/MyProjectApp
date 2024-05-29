 package com.example.myprojectapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

 public class Account_Activity extends AppCompatActivity {
    private LinearLayout layout_nav1;
    private LinearLayout layout_nav2;
    private LinearLayout layout_sell;
    private LinearLayout layout_exit;
    private RecyclerView recyclerView;
    private TextView textView_name;
    private TextView textView18;
    private TextView textView20;
    private TextView textView_empty;
    private int userId;
    String userName;
     private DatabaseReference myDatabaseUsers;
     private String USERS = "Users";
     private DatabaseReference myDatabaseApartments;
     private String APARTMENTS = "Apartments";
     private static StorageReference mStorageReference;
     private static String IMAGES = "Images";
     private ListAdapter listAdapter;
     ArrayList<Apartment> list;
     ArrayList<Integer> favorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        layout_nav1 = findViewById(R.id.layout_nav1);
        layout_nav2 = findViewById(R.id.layout_nav2);
        layout_sell = findViewById(R.id.layout_sell);
        textView_name = findViewById(R.id.textView_name);
        textView18 = findViewById(R.id.textView18);
        textView20 = findViewById(R.id.textView20);
        textView_empty = findViewById(R.id.textView_empty);
        layout_exit = findViewById(R.id.layout_exit);
        recyclerView = findViewById(R.id.Recyclerview);

        Window window = getWindow();
        window.setStatusBarColor(Color.parseColor("#FFFFFFFF"));

        SharedPreferences mSettings = getSharedPreferences("mySettings", Context.MODE_PRIVATE);
        userId = mSettings.getInt("id", 0);

        myDatabaseApartments = FirebaseDatabase.getInstance().getReference(APARTMENTS);
        mStorageReference = FirebaseStorage.getInstance().getReference(IMAGES);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        favorites = new ArrayList<>();
        listAdapter = new ListAdapter(this, list, favorites);
        recyclerView.setAdapter(listAdapter);

        myDatabaseApartments.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!list.isEmpty()){
                    list.clear();
                }
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Apartment apartment = dataSnapshot.getValue(Apartment.class);
                    if (apartment.landlordId == userId){
                        list.add(apartment);
                    }
                }
                listAdapter.notifyDataSetChanged();
                if (!list.isEmpty()){
                    Log.d("My Log", "WWWWWWWWWWWWWWWW");
                    textView20.setLayoutParams(textView_empty.getLayoutParams());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myDatabaseUsers = FirebaseDatabase.getInstance().getReference(USERS);
        myDatabaseUsers.child(userId + "").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                User user = task.getResult().getValue(User.class);
                userName = user.userName;
                textView_name.setText(userName);
            }
        });

        layout_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Account_Activity.this, MainActivity3.class);
                startActivity(intent);
            }
        });
        layout_nav1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Account_Activity.this, MainActivity4.class);
                startActivity(intent);

            }
        });
        layout_nav2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Account_Activity.this, FavoriteActivity.class);
                startActivity(intent);
            }
        });
        layout_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences mSettings = getSharedPreferences("mySettings", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mSettings.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(Account_Activity.this, MainActivity4.class);
                startActivity(intent);
            }
        });
    }
}