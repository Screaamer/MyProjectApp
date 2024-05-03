package com.example.myprojectapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileFragment extends Fragment {
    private DatabaseReference myDatabaseUser;
    private DatabaseReference myDatabaseLastUserId;
    private String USERS = "Users";
    private String LASTUSERID = "LastUserId";
    private LinearLayout layout1;
    private EditText editText1;
    private EditText editText2;
    private Button button4;
    public TextView textView2;
    private TextView textView3;
    public String userNumber, userPassword;
    public int id;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDatabaseUser = FirebaseDatabase.getInstance().getReference(USERS);
        myDatabaseLastUserId = FirebaseDatabase.getInstance().getReference(LASTUSERID);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        button4 = view.findViewById(R.id.button4);
        layout1 = view.findViewById(R.id.layout1);
        editText1 = view.findViewById(R.id.editText1);
        editText2 = view.findViewById(R.id.editText2);
        textView2 = view.findViewById(R.id.textView2);
        textView3 = view.findViewById(R.id.textView3);
        return view;
    }



}
