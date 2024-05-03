package com.example.myprojectapp;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {
    private DatabaseReference myDatabaseUser;
    private DatabaseReference myDatabaseLastUserId;
    private String USERS = "Users";
    private String LASTUSERID = "LastUserId";
    private LinearLayout layout1;
    private LinearLayout layout_nav1;
    private LinearLayout layout_nav2;
    private LinearLayout layout_nav3;
    private EditText editText1;
    private EditText editText2;
    private TextView textView2;
    private TextView textView3;
    public String userNumber, userPassword;
    public int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        layout1 = findViewById(R.id.layout1);
        layout_nav1 = findViewById(R.id.layout_nav1);
        layout_nav2 = findViewById(R.id.layout_nav2);
        layout_nav3 = findViewById(R.id.layout_nav3);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        Window window = getWindow();
        window.setStatusBarColor(Color.parseColor("#FFFFFFFF"));
        textView2.setText("id: " + id);


        myDatabaseUser = FirebaseDatabase.getInstance().getReference(USERS);
        myDatabaseLastUserId = FirebaseDatabase.getInstance().getReference(LASTUSERID);
        myDatabaseLastUserId.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                id = Integer.parseInt(task.getResult().getValue().toString());
                textView2.setText("Last id: " + id);
            }
        });
        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userNumber = editText1.getText().toString();
                userPassword = editText2.getText().toString();
                myDatabaseLastUserId.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        id = Integer.parseInt(task.getResult().getValue().toString());
                        id++;
                        User user = new User(userNumber, userPassword, id);
                        myDatabaseUser.push().setValue(user);
                        myDatabaseLastUserId.setValue(id);

                        SharedPreferences mSettings = getSharedPreferences("mySettings", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = mSettings.edit();
                        editor.putInt("id", id);
                        editor.apply();

                        Intent intent = new Intent(SignInActivity.this, Account_Activity.class);
                        startActivity(intent);
                    }
                });
            }
        });
        SharedPreferences mSettings = getSharedPreferences("mySettings", Context.MODE_PRIVATE);
        textView3.setText("your id:" + mSettings.getInt("id", 0));
        layout_nav1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, MainActivity4.class);
                startActivity(intent);
            }
        });
        layout_nav2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, FavoriteActivity.class);
                startActivity(intent);
            }
        });
        layout_nav3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, TripsActivity.class);
                startActivity(intent);
            }
        });
    }
}