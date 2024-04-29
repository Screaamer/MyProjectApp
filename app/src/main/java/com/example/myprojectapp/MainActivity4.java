package com.example.myprojectapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.security.PrivateKey;

public class MainActivity4 extends AppCompatActivity {
    private int amount1;
    private int amount2;
    private RecyclerView List1;
    private ListAdapter listAdapter;
    private int a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
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


    }
    public interface MyIntentInterface{
        void MyIntent();
    }
}