package com.example.couseplusplus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.couseplusplus.data.comment.FirebaseComment;
import com.example.couseplusplus.service.comment.FireBaseCommentService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Author: Min su Park
 *
 * <p>AddComment is used as the main Comment-adding activity. This class supersedes CommentsActivity
 */
public class AddComment extends AppCompatActivity {
  public TextView title;
  public EditText commentSpace;
  public Button postButton;
  public String courseCodeInfo;
  public Spinner selectSemester;
  FireBaseCommentService fireBaseCommentService;

  @SuppressLint({"SetTextI18n", "CutPasteId"})
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_comment);
    title = findViewById(R.id.addCommentTitle);
    commentSpace = findViewById(R.id.comment_space);
    postButton = findViewById(R.id.post_comment);
    selectSemester = findViewById(R.id.selectSemester);
    fireBaseCommentService = IoCContainer.fireBaseCommentService();
    Intent intent = getIntent();
    courseCodeInfo = intent.getStringExtra("courseCode");

    title.setText("Please write a comment for the course: " + courseCodeInfo);

    ArrayAdapter<String> yearAdapter =
        new ArrayAdapter<>(
            this, android.R.layout.simple_spinner_item, FirebaseComment.DISPLAY_YEARS);
    yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    ArrayAdapter<String> semesterAdapter =
        new ArrayAdapter<>(
            this, android.R.layout.simple_spinner_item, FirebaseComment.DISPLAY_SEMESTERS);
    semesterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    Spinner yearsSpinner = findViewById(R.id.selectYear);
    yearsSpinner.setAdapter(yearAdapter);

    Spinner semesterSpinner = findViewById(R.id.selectSemester);
    semesterSpinner.setAdapter(semesterAdapter);

    postButton.setOnClickListener(
        view -> {
          LocalDateTime currentTime = LocalDateTime.now();
          String userComment = commentSpace.getText().toString().trim();
          if (TextUtils.isEmpty(userComment)) {
            Toast.makeText(this, "Empty reviews are not allowed", Toast.LENGTH_SHORT).show();
            return;
          }
          String postedDateTime =
              currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS"));
          int year = Integer.parseInt(yearsSpinner.getSelectedItem().toString());
          int helpfulness = 0;
          int semester = selectSemester.getSelectedItem().equals("Semester 1") ? 1 : 2;

          FirebaseComment newComment = new FirebaseComment();
          newComment.setText(userComment);
          newComment.setSemester(semester);
          newComment.setYear(year);
          newComment.setHelpfulness(helpfulness);
          newComment.setPostedDateTime(postedDateTime);

          fireBaseCommentService.addComment(
              courseCodeInfo,
              newComment,
              isSuccessful -> {
                if (isSuccessful) {
                  Toast.makeText(this, "Comment added successfully", Toast.LENGTH_SHORT).show();
                } else {
                  Toast.makeText(this, "Failed to add comment", Toast.LENGTH_SHORT).show();
                }
              });
        });
  }
}
