package com.example.couseplusplus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CommentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        Intent intent = getIntent();
        String courseCodeInfo = intent.getStringExtra("courseCode");

        TextView courseCodeInfoTextView = findViewById(R.id.course_code_info);
        courseCodeInfoTextView.setText(courseCodeInfo);
        ListView commentsListView = findViewById(R.id.commentsListView);

        // Create an ArrayAdapter or RecyclerViewAdapter to display comments.
        // Set the adapter to the ListView.
        // Example: commentsListView.setAdapter(adapter);
    }
}