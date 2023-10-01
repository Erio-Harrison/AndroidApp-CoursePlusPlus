package com.example.couseplusplus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.couseplusplus.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddComment extends AppCompatActivity {
    public DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

}