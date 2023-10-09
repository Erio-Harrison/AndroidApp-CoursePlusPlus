package com.example.couseplusplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.couseplusplus.model.comment.Comment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AddComment extends AppCompatActivity {
    public DatabaseReference mDatabase;
    public EditText commentSpace;
    public Button postButton;
    String courseCodeInfo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        commentSpace = findViewById(R.id.comment_space);
        postButton = findViewById(R.id.post_comment);
        LocalDateTime currentTime = LocalDateTime.now();

        postButton.setOnClickListener(view -> {
            String userComment = commentSpace.getText().toString();
            Intent intent = getIntent();
            courseCodeInfo = intent.getStringExtra("courseCode");
            int year = 2023;
            int helpfulness = 0;
            String postedDateTime = currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS"));
            int semester = 2;

//            Comment newComment = new Comment(
//                    null,
//                    null,
//                    year,
//                    semester,
//                    userComment,
//                    helpfulness,
//                    null
//            );
            Map<String, Object> newComment = new HashMap<>();
            newComment.put("text", userComment);
            newComment.put("semester", semester);
            newComment.put("year", year);
            newComment.put("helpfulness", helpfulness);
            newComment.put("postedDateTime", postedDateTime);


            mDatabase.child("comment")
                    .child(courseCodeInfo)
                    .push()
                    .setValue(newComment)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(AddComment.this, "Comment added successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e("AddComment", "Failed to add comment to Firebase", task.getException());
                            Toast.makeText(AddComment.this, "Failed to add comment", Toast.LENGTH_SHORT).show();
                        }
                    });


        });

    }

}