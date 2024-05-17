package com.example.myprojectapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ApartmentActivity extends AppCompatActivity {
    TextView textView1;
    TextView textView2;
    ImageView imageViewa0;
    ImageView imageViewa1;
    ImageView imageViewa2;
    ImageView imageViewa3;
    ImageView imageViewa4;
    ImageView imageViewa5;
    int id;
    int amountOfImages;
    String landlord;
    ArrayList<String> listOfImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartment);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        imageViewa0 = findViewById(R.id.imageViewa0);
        imageViewa1 = findViewById(R.id.imageViewa1);
        imageViewa2 = findViewById(R.id.imageViewa2);
        imageViewa3 = findViewById(R.id.imageViewa3);
        imageViewa4 = findViewById(R.id.imageViewa4);
        imageViewa5 = findViewById(R.id.imageViewa5);
        listOfImages = new ArrayList<>();

        Window window = getWindow();
        window.setStatusBarColor(Color.parseColor("#FFFFFFFF"));

        id = getIntent().getIntExtra("apartment", 2);
        landlord = getIntent().getStringExtra("landlord");
        listOfImages = getIntent().getStringArrayListExtra("images");

        ViewGroup.LayoutParams params = imageViewa0.getLayoutParams();
        if (listOfImages.size() == 1){
            Picasso.get().load(listOfImages.get(0)).into(imageViewa1);
            imageViewa2.setLayoutParams(params);
            imageViewa3.setLayoutParams(params);
            imageViewa4.setLayoutParams(params);
            imageViewa5.setLayoutParams(params);
        }
        if (listOfImages.size() == 2){
            Picasso.get().load(listOfImages.get(0)).into(imageViewa1);
            Picasso.get().load(listOfImages.get(1)).into(imageViewa2);
            imageViewa3.setLayoutParams(params);
            imageViewa4.setLayoutParams(params);
            imageViewa5.setLayoutParams(params);
        }
        if (listOfImages.size() == 3){
            Picasso.get().load(listOfImages.get(0)).into(imageViewa1);
            Picasso.get().load(listOfImages.get(1)).into(imageViewa2);
            Picasso.get().load(listOfImages.get(2)).into(imageViewa3);
            imageViewa4.setLayoutParams(params);
            imageViewa5.setLayoutParams(params);
        }
        if (listOfImages.size() == 14){
            Picasso.get().load(listOfImages.get(0)).into(imageViewa1);
            Picasso.get().load(listOfImages.get(1)).into(imageViewa2);
            Picasso.get().load(listOfImages.get(2)).into(imageViewa3);
            Picasso.get().load(listOfImages.get(3)).into(imageViewa4);
            imageViewa5.setLayoutParams(params);
        }
        if (listOfImages.size() == 5){
            Picasso.get().load(listOfImages.get(0)).into(imageViewa1);
            Picasso.get().load(listOfImages.get(1)).into(imageViewa2);
            Picasso.get().load(listOfImages.get(2)).into(imageViewa3);
            Picasso.get().load(listOfImages.get(3)).into(imageViewa4);
            Picasso.get().load(listOfImages.get(4)).into(imageViewa5);
            imageViewa1.setLayoutParams(params);
            imageViewa2.setLayoutParams(params);
            imageViewa3.setLayoutParams(params);
            imageViewa4.setLayoutParams(params);
            imageViewa5.setLayoutParams(params);
        }

        textView2.setText(landlord);
    }
}