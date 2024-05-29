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

import java.util.ArrayList;

public class SignInActivity extends AppCompatActivity {
    private DatabaseReference myDatabaseUser;
    private DatabaseReference myDatabaseLastUserId;
    private String USERS = "Users";
    private String LASTUSERID = "LastUserId";
    private LinearLayout layout1;
    private LinearLayout layout_nav1;
    private LinearLayout layout_nav2;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private TextView textView_err1;
    private TextView textView_err2;
    private TextView textView_err3;
    private TextView textView_err4;
    public String userPhone, userPassword, userName, userTg;
    public int userId;
    private ArrayList<Integer> favorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        layout1 = findViewById(R.id.layout1);
        layout_nav1 = findViewById(R.id.layout_nav1);
        layout_nav2 = findViewById(R.id.layout_nav2);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        textView_err1 = findViewById(R.id.textView_err1);
        textView_err2 = findViewById(R.id.textView_err2);
        textView_err3 = findViewById(R.id.textView_err3);
        textView_err4 = findViewById(R.id.textView_err4);
        favorites = new ArrayList<>();
        favorites.add(0);

        Window window = getWindow();
        window.setStatusBarColor(Color.parseColor("#FFFFFFFF"));

        myDatabaseUser = FirebaseDatabase.getInstance().getReference(USERS);
        myDatabaseLastUserId = FirebaseDatabase.getInstance().getReference(LASTUSERID);

        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userPhone = editText1.getText().toString();
                userPassword = editText2.getText().toString();
                userName = editText3.getText().toString();
                userTg = editText4.getText().toString();
                if (userPhone.isEmpty()){
                    textView_err1.setVisibility(View.VISIBLE);
                }else {
                    textView_err1.setVisibility(View.GONE);
                }
                if (userPassword.length() < 8) {
                    textView_err2.setVisibility(View.VISIBLE);
                }else {
                    textView_err2.setVisibility(View.GONE);
                }
                if (userName.isEmpty()){
                    textView_err3.setVisibility(View.VISIBLE);
                }else {
                    textView_err3.setVisibility(View.GONE);
                }
                if (userTg.isEmpty()){
                    textView_err4.setVisibility(View.VISIBLE);
                }else {
                    textView_err4.setVisibility(View.GONE);
                }
                if (!userPhone.isEmpty() && userPassword.length() > 7 && !userName.isEmpty() && !userTg.isEmpty()){
                    myDatabaseLastUserId.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            userId = Integer.parseInt(task.getResult().getValue().toString());
                            userId++;
                            User user = new User(userPhone, userPassword, userName, userTg, userId, favorites);
                            myDatabaseUser.child(userId + "").setValue(user);
                            myDatabaseLastUserId.setValue(userId);

                            SharedPreferences mSettings = getSharedPreferences("mySettings", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = mSettings.edit();
                            editor.putInt("id", userId);
                            editor.apply();

                            Intent intent = new Intent(SignInActivity.this, Account_Activity.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        });
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
    }
}