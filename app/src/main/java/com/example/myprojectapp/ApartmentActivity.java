package com.example.myprojectapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ApartmentActivity extends AppCompatActivity {
    TextView textView1;
    TextView textView2, textView4, textView6;
    ImageView imageViewa0;
    ImageView imageViewa1;
    ImageView imageViewa2;
    ImageView imageViewa3;
    ImageView imageViewa4;
    ImageView imageViewa5;
    ImageView imageView_favorite, imageView_edit;
    private LinearLayout layout_tg;
    int id;
    int amountOfImages;
    private boolean favorite_flag = false;
    private int landlordId, userId, maxAmount;
    ArrayList<String> listOfImages;
    private DatabaseReference myDatabaseUsers;
    private String USERS = "Users";
    private String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartment);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView4 = findViewById(R.id.textView4);
        textView6 = findViewById(R.id.textView6);
        imageViewa0 = findViewById(R.id.imageViewa0);
        imageViewa1 = findViewById(R.id.imageViewa1);
        imageViewa2 = findViewById(R.id.imageViewa2);
        imageViewa3 = findViewById(R.id.imageViewa3);
        imageViewa4 = findViewById(R.id.imageViewa4);
        imageViewa5 = findViewById(R.id.imageViewa5);
        layout_tg = findViewById(R.id.layout_tg);
        imageView_favorite = findViewById(R.id.imageView_favorite);
        imageView_edit = findViewById(R.id.imageView_edit);
        listOfImages = new ArrayList<>();

        Window window = getWindow();
        window.setStatusBarColor(Color.parseColor("#FFFFFFFF"));

        SharedPreferences mSettings = getSharedPreferences("mySettings", Context.MODE_PRIVATE);
        userId = mSettings.getInt("id", 0);

        id = getIntent().getIntExtra("apartment", 2);
        landlordId = getIntent().getIntExtra("landlordId", 0);
        listOfImages = getIntent().getStringArrayListExtra("images");
        favorite_flag = getIntent().getBooleanExtra("flag", false);
        maxAmount = getIntent().getIntExtra("MaxAmount", 0);
        description = getIntent().getStringExtra("description");

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
        if (listOfImages.size() == 4){
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

        if (!(landlordId == userId)){
            imageView_edit.setLayoutParams(params);
        }

        if (favorite_flag){
            imageView_favorite.setImageResource(R.drawable.baseline_favorite_24);

        }else {
            imageView_favorite.setImageResource(R.drawable.nav_favorite_border_24);

        }

        imageView_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (favorite_flag){
                    imageView_favorite.setImageResource(R.drawable.nav_favorite_border_24);
                    favorite_flag = false;
                    myDatabaseUsers.child(userId + "").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            User user = task.getResult().getValue(User.class);
                            int i = user.favorites.indexOf(id);
                            user.favorites.remove(i);
                            myDatabaseUsers.child(userId + "").setValue(user);
                        }
                    });
                }else {
                    imageView_favorite.setImageResource(R.drawable.baseline_favorite_24);
                    favorite_flag = true;
                    myDatabaseUsers.child(userId + "").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            User user = task.getResult().getValue(User.class);
                            user.favorites.add(id);
                            myDatabaseUsers.child(userId + "").setValue(user);
                        }
                    });
                }
            }
        });

        myDatabaseUsers = FirebaseDatabase.getInstance().getReference(USERS);
        myDatabaseUsers.child(landlordId + "").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                User user = task.getResult().getValue(User.class);
                textView2.setText(user.userName);
            }
        });
        textView4.setText(maxAmount + "");
        textView6.setText("Описание: " + description);

        layout_tg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDatabaseUsers.child(landlordId + "").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        User user = task.getResult().getValue(User.class);
                        openTg(user.userTg);
                    }
                });
            }
        });
    }
    private void openTg(String tgId){
        Uri webpage = Uri.parse("https://t.me/" + tgId);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);

    }
}