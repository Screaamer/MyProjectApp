package com.example.myprojectapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity3 extends AppCompatActivity {
    private DatabaseReference myDatabaseApartments;
    private DatabaseReference myDatabaseLastApatmentId;
    public String landlord, address;
    public int id, maxAmount;
    private EditText EditTextText;
    private LinearLayout layoutc1;
    private LinearLayout layoutc2;
    private TextView Text3;
    private TextView TextView2;
    private Button button1;
    private String APARTMENTS = "Apartments";
    private String LASTAPARTMENTID = "lastApartmentId";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        EditTextText = findViewById(R.id.editTextText);
        layoutc1 = findViewById(R.id.layoutc1);
        layoutc2 = findViewById(R.id.layoutc2);
        TextView2 = findViewById(R.id.textView2);
        Text3 = findViewById(R.id.text3);
        button1 = findViewById(R.id.button1);

        int color1 = ContextCompat.getColor(this, R.color.gray);
        int color2 = ContextCompat.getColor(this, R.color.text);
        id = 0;

        myDatabaseApartments = FirebaseDatabase.getInstance().getReference(APARTMENTS);
        myDatabaseLastApatmentId = FirebaseDatabase.getInstance().getReference(LASTAPARTMENTID);
        layoutc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (maxAmount > 0){
                    maxAmount -= 1;
                    TextView2.setText(maxAmount + "");
                }
                if (maxAmount == 0){
                    layoutc1.setBackground(getResources().getDrawable(R.drawable.button_background5));
                    Text3.setTextColor(color1);
                }
            }
        });
        layoutc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maxAmount += 1;
                layoutc1.setBackground(getResources().getDrawable(R.drawable.button_background4));
                Text3.setTextColor(color2);
                TextView2.setText(maxAmount + "");
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                address = EditTextText.getText().toString();
                id = 2;
                Apartment apartment = new Apartment(id, landlord, address, maxAmount);
                myDatabaseApartments.push().setValue(apartment);
                myDatabaseLastApatmentId.push().setValue(id);
                myDatabaseLastApatmentId.setValue(id);

            }
        });


    }
}