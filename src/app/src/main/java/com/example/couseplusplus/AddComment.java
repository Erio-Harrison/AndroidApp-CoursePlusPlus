package com.example.couseplusplus;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Min su Park
 *
 * AddComment is used as the main Comment-adding activity. This class supersedes CommentsActivity
 */
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


    postButton.setOnClickListener(
        view -> {
            LocalDateTime currentTime = LocalDateTime.now();
          String userComment = commentSpace.getText().toString();
          Intent intent = getIntent();
          courseCodeInfo = intent.getStringExtra("courseCode");
          String postedDateTime =
                    currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS"));
          int year = Integer.parseInt(postedDateTime.substring(0,4));
          int month = Integer.parseInt(postedDateTime.substring(5,7));
          int helpfulness = 0;
          int semester = (month > 7) ? 2 : 1;

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

          mDatabase
              .child("comment")
              .child(courseCodeInfo)
              .push()
              .setValue(newComment)
              .addOnCompleteListener(
                  task -> {
                    if (task.isSuccessful()) {
                      Toast.makeText(
                              AddComment.this, "Comment added successfully", Toast.LENGTH_SHORT)
                          .show();
                    } else {
                      Log.e("AddComment", "Failed to add comment to Firebase", task.getException());
                      Toast.makeText(AddComment.this, "Failed to add comment", Toast.LENGTH_SHORT)
                          .show();
                    }
                  });
        });
  }
}
