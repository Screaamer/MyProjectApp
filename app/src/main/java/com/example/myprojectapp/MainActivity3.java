package com.example.myprojectapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {
    private DatabaseReference myDatabaseApartments;
    private DatabaseReference myDatabaseLastApatmentId;
    public String landlord, address, city, description;
    public int id, maxAmount;
    private int nOfIm, userId;
    private EditText EditTextText;
    private EditText EditTextText1;
    private EditText EditTextText_description;
    private LinearLayout layoutc1;
    private LinearLayout layoutc2;
    private LinearLayout layout_button;
    private TextView Text3;
    private TextView textView_err1, textView_err2, textView_err3;
    private TextView TextView2;
    private ImageView imageViewa1;
    private ImageView imageViewa2;
    private ImageView imageViewa3;
    private ImageView imageViewa4;
    private ImageView imageViewa5;
    private boolean a1;
    private boolean a2;
    private boolean a3;
    private boolean a4;
    private boolean a5;
    private String APARTMENTS = "Apartments";
    private String LASTAPARTMENTID = "lastApartmentId";
    private String IMAGES = "Images";
    private Uri uploadUri;
    private StorageReference mStorageReference;
    private ArrayList<String> listOfImages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        EditTextText = findViewById(R.id.editTextText);
        EditTextText1 = findViewById(R.id.editTextText1);
        EditTextText_description = findViewById(R.id.editTextText_description);
        layoutc1 = findViewById(R.id.layoutc1);
        layoutc2 = findViewById(R.id.layoutc2);
        layout_button = findViewById(R.id.layout_button);
        TextView2 = findViewById(R.id.textView2);
        Text3 = findViewById(R.id.text3);
        textView_err1 = findViewById(R.id.textView_err1);
        textView_err2 = findViewById(R.id.textView_err2);
        textView_err3 = findViewById(R.id.textView_err3);
        imageViewa1 = findViewById(R.id.imageViewa1);
        imageViewa2 = findViewById(R.id.imageViewa2);
        imageViewa3 = findViewById(R.id.imageViewa3);
        imageViewa4 = findViewById(R.id.imageViewa4);
        imageViewa5 = findViewById(R.id.imageViewa5);

        listOfImages = new ArrayList<>();

        mStorageReference = FirebaseStorage.getInstance().getReference(IMAGES);

        Window window = getWindow();
        window.setStatusBarColor(Color.parseColor("#FFFFFFFF"));

        SharedPreferences mSettings = getSharedPreferences("mySettings", Context.MODE_PRIVATE);
        userId = mSettings.getInt("id", 0);

        a1 = false;
        a2 = false;
        a3 = false;
        a4 = false;
        a5 = false;

        int color1 = ContextCompat.getColor(this, R.color.gray);
        int color2 = ContextCompat.getColor(this, R.color.text);
        id = 0;

        nOfIm = 0;

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
        layout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                address = EditTextText1.getText().toString();
                city = EditTextText.getText().toString();
                description = EditTextText_description.getText().toString();
                if (city.isEmpty()){
                    textView_err1.setVisibility(View.VISIBLE);
                }else {
                    textView_err1.setVisibility(View.GONE);
                }
                if (address.isEmpty()) {
                    textView_err2.setVisibility(View.VISIBLE);
                }else {
                    textView_err2.setVisibility(View.GONE);
                }
                if (description.isEmpty()){
                    textView_err3.setVisibility(View.VISIBLE);
                }else {
                    textView_err3.setVisibility(View.GONE);
                }
                if (!address.isEmpty() && !city.isEmpty() && !description.isEmpty()){
                    myDatabaseLastApatmentId.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            id = Integer.parseInt(task.getResult().getValue().toString());
                            id++;
                            uploadImage(id);
                            Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        });
        imageViewa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                getImage(1);
            }
        });
        imageViewa2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                getImage(2);
            }
        });
        imageViewa3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                getImage(3);
            }
        });
        imageViewa4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                getImage(4);
            }
        });
        imageViewa5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                getImage(5);
            }
        });
    }
    private void saveUser(int id){
        Log.d("My Log", "Image URI   "  +listOfImages.size());
        address = EditTextText1.getText().toString();
        city = EditTextText.getText().toString();
        description = EditTextText_description.getText().toString();
        Apartment apartment = new Apartment(id, userId, city, address, maxAmount, listOfImages, description);
        myDatabaseApartments.child(id + "").setValue(apartment);
        myDatabaseLastApatmentId.setValue(id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data != null && data.getData() != null){
            if (resultCode == RESULT_OK){
                Log.d("My Log", "Image1 URI" + data.getData());
                imageViewa1.setImageURI(data.getData());
                a1 = true;
            }
        }
        if (requestCode == 2 && data != null && data.getData() != null){
            if (resultCode == RESULT_OK){
                Log.d("My Log", "Image2 URI" + data.getData());
                imageViewa2.setImageURI(data.getData());
                a2 = true;
            }
        }
        if (requestCode == 3 && data != null && data.getData() != null){
            if (resultCode == RESULT_OK){
                Log.d("My Log", "Image3 URI" + data.getData());
                imageViewa3.setImageURI(data.getData());
                a3 = true;
            }
        }
        if (requestCode == 4 && data != null && data.getData() != null){
            if (resultCode == RESULT_OK){
                Log.d("My Log", "Image4 URI" + data.getData());
                imageViewa4.setImageURI(data.getData());
                a4 = true;
            }
        }
        if (requestCode == 5 && data != null && data.getData() != null){
            if (resultCode == RESULT_OK){
                Log.d("My Log", "Image5 URI" + data.getData());
                imageViewa5.setImageURI(data.getData());
                a5 = true;
            }
        }
    }
    private void uploadImage(int id){
        if (!listOfImages.isEmpty()){
            listOfImages.clear();
        }
        if (a1) {
            Bitmap bitmap = ((BitmapDrawable) imageViewa1.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] byteArray = baos.toByteArray();
            nOfIm++;
            StorageReference mReference = mStorageReference.child(id + "").child(nOfIm + "");
            UploadTask up = mReference.putBytes(byteArray);
            Task<Uri> task = up.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    return mReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    uploadUri = task.getResult();
                    listOfImages.add(uploadUri.toString());
                    Log.d("My Log", "Image URIIIIII   " + listOfImages.size());
                    saveUser(id);
                }
            });
        }

        if (a2) {

            Bitmap bitmap = ((BitmapDrawable) imageViewa2.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] byteArray = baos.toByteArray();
            nOfIm++;
            StorageReference mReference = mStorageReference.child(id + "").child(nOfIm + "");
            UploadTask up = mReference.putBytes(byteArray);
            Task<Uri> task = up.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    return mReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    uploadUri = task.getResult();
                    listOfImages.add(uploadUri.toString());
                    saveUser(id);
                }
            });
        }
        if (a3) {
            Bitmap bitmap = ((BitmapDrawable) imageViewa3.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] byteArray = baos.toByteArray();
            nOfIm++;
            StorageReference mReference = mStorageReference.child(id + "").child(nOfIm + "");
            UploadTask up = mReference.putBytes(byteArray);
            Task<Uri> task = up.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    return mReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    uploadUri = task.getResult();
                    listOfImages.add(uploadUri.toString());
                    saveUser(id);
                }
            });
        }
        if (a4) {
            Bitmap bitmap = ((BitmapDrawable) imageViewa4.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] byteArray = baos.toByteArray();
            nOfIm++;
            StorageReference mReference = mStorageReference.child(id + "").child(nOfIm + "");
            UploadTask up = mReference.putBytes(byteArray);
            Task<Uri> task = up.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    return mReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    uploadUri = task.getResult();
                    listOfImages.add(uploadUri.toString());
                    saveUser(id);
                }
            });
        }
        if (a5) {
            Bitmap bitmap = ((BitmapDrawable) imageViewa5.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] byteArray = baos.toByteArray();
            nOfIm++;
            StorageReference mReference = mStorageReference.child(id + "").child(nOfIm + "");
            UploadTask up = mReference.putBytes(byteArray);
            Task<Uri> task = up.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    return mReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    uploadUri = task.getResult();
                    listOfImages.add(uploadUri.toString());
                    saveUser(id);
                }
            });
        }
    }

    private void getImage(int i){
        Intent intentChooser = new Intent();
        intentChooser.setType("image/*");
        intentChooser.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentChooser, i);
    }
}