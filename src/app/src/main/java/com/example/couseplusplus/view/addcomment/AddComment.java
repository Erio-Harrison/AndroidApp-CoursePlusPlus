package com.example.couseplusplus.view.addcomment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.couseplusplus.IoCContainer;
import com.example.couseplusplus.R;
import com.example.couseplusplus.model.comment.Comment;
import com.example.couseplusplus.service.comment.CommentService;
import com.example.couseplusplus.service.user.UserService;
import com.example.couseplusplus.view.activityhandler.LogoutConfigHandler;
import com.example.couseplusplus.view.activityhandler.SupportActionBarTitleSetter;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;

/**
 * AddComment is used as the main Comment-adding activity. This class supersedes CommentsActivity
 *
 * @author Min su Park
 * @author Yuki Misumi (u7582380) - polished layout
 */
public class AddComment extends AppCompatActivity {
  public EditText commentSpace;
  public Button postButton;
  public String courseCodeInfo;
  public Spinner selectSemester;
  CommentService CommentService;

  UserService userService = IoCContainer.userService();

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.add_comment_menu, menu);
    setListeners(menu);
    return true;
  }

  void setListeners(Menu menu) {
    var handler = new LogoutConfigHandler(R.id.logout_in_add_comment, menu, userService, this);
    handler.configure();
  }

  @SuppressLint({"SetTextI18n", "CutPasteId"})
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_comment);
    commentSpace = findViewById(R.id.comment_space);
    postButton = findViewById(R.id.post_comment);
    selectSemester = findViewById(R.id.selectSemester);
    CommentService = IoCContainer.commentService();
    Intent intent = getIntent();
    courseCodeInfo = intent.getStringExtra("courseCode");
    SupportActionBarTitleSetter.set(String.format("Add Comment To %s", courseCodeInfo), this);
    List<String> DISPLAY_YEARS = YearGenerator.generateAsString(Year.of(2015), Year.now());
    String[] DISPLAY_SEMESTERS = {"Semester 1", "Semester 2"};

    ArrayAdapter<String> yearAdapter =
        new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, DISPLAY_YEARS);
    yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    ArrayAdapter<String> semesterAdapter =
        new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, DISPLAY_SEMESTERS);
    semesterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    Spinner yearsSpinner = findViewById(R.id.selectYear);
    yearsSpinner.setAdapter(yearAdapter);
    yearsSpinner.setSelection(DISPLAY_YEARS.size() - 1);

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
          int year = Integer.parseInt(yearsSpinner.getSelectedItem().toString());
          int helpfulness = 0;
          int semester = selectSemester.getSelectedItem().equals("Semester 1") ? 1 : 2;

          Comment newComment =
              new Comment(
                  null, courseCodeInfo, year, semester, userComment, helpfulness, currentTime);

          CommentService.addComment(
              courseCodeInfo,
              newComment,
              isSuccessful -> {
                if (isSuccessful) {
                  Toast.makeText(this, "Comment added successfully", Toast.LENGTH_SHORT).show();
                  finish();
                } else {
                  Toast.makeText(this, "Failed to add comment", Toast.LENGTH_SHORT).show();
                }
              });
        });
  }
}
