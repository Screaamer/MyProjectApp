package com.example.myprojectapp;

import android.content.Intent;
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
    private EditText editText1;
    private EditText editText2;
    private Button button4;
    private TextView textView2;
    private TextView textView3;


    public String userNumber, userPassword;
    public int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        layout1 = findViewById(R.id.layout1);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        button4 = findViewById(R.id.button4);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        Window window = getWindow();
        window.setStatusBarColor(Color.parseColor("#FFFFFFFF"));
        textView2.setText("id: " + id);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        myDatabaseUser = FirebaseDatabase.getInstance().getReference(USERS);
        myDatabaseLastUserId = FirebaseDatabase.getInstance().getReference(LASTUSERID);
        myDatabaseLastUserId.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                id = Integer.parseInt(task.getResult().getValue().toString());
                textView2.setText("Last id: " + id);
            }
        });
        textView3.setText("your id: " + id);

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
                        textView2.setText("Last id: " + id);
                        textView3.setText("your id: " + id);
                    }
                });




            }
        });

    }
}