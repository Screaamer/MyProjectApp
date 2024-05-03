package com.example.myprojectapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import java.security.PrivateKey;

public class MainActivity4 extends AppCompatActivity {
    private LinearLayout layout_nav2;
    private LinearLayout layout_nav3;
    private LinearLayout layout_nav4;
    private int amount1;
    private int amount2;
    private RecyclerView List1;
    private ListAdapter listAdapter;
    private int a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        layout_nav2 = findViewById(R.id.layout_nav2);
        layout_nav3 = findViewById(R.id.layout_nav3);
        layout_nav4 = findViewById(R.id.layout_nav4);
        Window window = getWindow();
        window.setStatusBarColor(Color.parseColor("#FFFFFFFF"));


        Intent intent = getIntent();
        amount1 = intent.getIntExtra("amount1", 0);
        amount2 = intent.getIntExtra("amount2", 0);

        List1 = findViewById(R.id.Recyclerview1);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        List1.setLayoutManager(layoutManager);

        List1.setHasFixedSize(true);

        MyIntentInterface myInt = new MyIntentInterface() {
            @Override
            public void MyIntent() {
                Intent intent = new Intent(MainActivity4.this, MainActivity5.class);
                startActivity(intent);
            }
        };
        listAdapter = new ListAdapter(100, this , amount1, amount2, myInt);
        List1.setAdapter(listAdapter);
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
    public interface MyIntentInterface{
        void MyIntent();
    }
}