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

public class TripsActivity extends AppCompatActivity {
    private LinearLayout layout_nav1;
    private LinearLayout layout_nav2;
    private LinearLayout layout_nav4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);
        layout_nav1 = findViewById(R.id.layout_nav1);
        layout_nav2 = findViewById(R.id.layout_nav2);
        layout_nav4 = findViewById(R.id.layout_nav4);
        Window window = getWindow();
        window.setStatusBarColor(Color.parseColor("#FFFFFFFF"));
        layout_nav1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TripsActivity.this, MainActivity4.class);
                startActivity(intent);

            }
        });
        layout_nav2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TripsActivity.this, FavoriteActivity.class);
                startActivity(intent);
            }
        });
        layout_nav4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences mSettings = getSharedPreferences("mySettings", Context.MODE_PRIVATE);
                if (mSettings.getInt("id", 0) == 0){
                    Intent intent = new Intent(TripsActivity.this, SignInActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(TripsActivity.this, Account_Activity.class);
                    startActivity(intent);
                }
            }
        });
    }
}