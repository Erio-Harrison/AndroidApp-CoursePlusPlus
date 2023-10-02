package com.example.couseplusplus;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.couseplusplus.model.comment.Comment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CommentsActivity extends AppCompatActivity {
    DatabaseReference mDatabase;
    RecyclerView commentRecycleView;
    CommentAdapter commentAdapter;
    List<Comment> commentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        Intent intent = getIntent();
        String courseCodeInfo = intent.getStringExtra("courseCode");

        // TODO: Show all necessary data in comments
        // TODO: Show course name
        TextView courseCodeInfoTextView = findViewById(R.id.course_code_info);
        courseCodeInfoTextView.setText(courseCodeInfo);
        commentRecycleView = (RecyclerView) findViewById(R.id.comment_rv);
        commentRecycleView.setLayoutManager(new LinearLayoutManager(this));

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (snapshot.getKey().equals("comment")) {
                        for (DataSnapshot courseSnapshot : snapshot.getChildren()) {
                            if (courseSnapshot.getKey().equals(courseCodeInfo)) {
                                for (DataSnapshot commentSnapshot : courseSnapshot.getChildren()) {
                                    Comment comment = commentSnapshot.getValue(Comment.class);
                                    commentList.add(comment);
                                }
                            }
                        }
                    }
                }
                commentAdapter = new CommentAdapter(commentList);
                commentRecycleView.setAdapter(commentAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {  // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}