 package com.example.myprojectapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Account_Activity extends AppCompatActivity {
    private LinearLayout layout_nav1;
    private LinearLayout layout_nav2;
    private LinearLayout layout_nav3;
    private LinearLayout layout_sell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        layout_nav1 = findViewById(R.id.layout_nav1);
        layout_nav2 = findViewById(R.id.layout_nav2);
        layout_nav3 = findViewById(R.id.layout_nav3);
        layout_sell = findViewById(R.id.layout_sell);
        Window window = getWindow();
        window.setStatusBarColor(Color.parseColor("#FFFFFFFF"));
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
        layout_nav3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Account_Activity.this, TripsActivity.class);
                startActivity(intent);
            }
        });
    }
}