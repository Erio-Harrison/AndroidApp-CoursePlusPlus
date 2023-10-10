package com.example.couseplusplus;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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
 * <p>AddComment is used as the main Comment-adding activity. This class supersedes CommentsActivity
 */
public class AddComment extends AppCompatActivity {
  public TextView title;
  public DatabaseReference mDatabase;
  public EditText commentSpace;
  public Button postButton;
  String courseCodeInfo;
  public Spinner selectYear;
  public Spinner selectSemester;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_comment);
    title = findViewById(R.id.addCommentTitle);
    mDatabase = FirebaseDatabase.getInstance().getReference();
    commentSpace = findViewById(R.id.comment_space);
    postButton = findViewById(R.id.post_comment);
    selectYear = findViewById(R.id.selectYear);
    selectSemester = findViewById(R.id.selectSemester);
    Intent intent = getIntent();
    courseCodeInfo = intent.getStringExtra("courseCode");

    title.setText("Please write a comment for the course: " + courseCodeInfo);

    String[] years = {
      "2015", "2016", "2017", "2017", "2018", "2019", "2020", "2021", "2022", "2023"
    };
    String[] semesters = {"Semester 1", "Semester 2"};

    ArrayAdapter<String> yearAdapter =
        new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, years);
    yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    ArrayAdapter<String> semesterAdapter =
        new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, semesters);
    semesterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    Spinner yearsSpinner = findViewById(R.id.selectYear);
    yearsSpinner.setAdapter(yearAdapter);

    Spinner semesterSpinner = findViewById(R.id.selectSemester);
    semesterSpinner.setAdapter(semesterAdapter);

    postButton.setOnClickListener(
        view -> {
          LocalDateTime currentTime = LocalDateTime.now();
          String userComment = commentSpace.getText().toString();
          String postedDateTime =
              currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS"));
          int year = Integer.parseInt(yearsSpinner.getSelectedItem().toString());
          int helpfulness = 0;
          int semester = selectSemester.getSelectedItem().equals("Semester 1") ? 1 : 2;

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

          Log.d("FINAL STRING", newComment.toString());

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
