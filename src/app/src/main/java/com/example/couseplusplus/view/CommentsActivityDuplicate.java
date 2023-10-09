package com.example.couseplusplus.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.couseplusplus.CommentAdapter;
import com.example.couseplusplus.IoCContainer;
import com.example.couseplusplus.R;
import com.example.couseplusplus.model.comment.Comment;
import com.example.couseplusplus.model.comment.NewComment;
import com.example.couseplusplus.model.query.Query;
import com.example.couseplusplus.service.comment.CommentService;
import com.example.couseplusplus.service.comment.SortingAspect;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// FIXME always sort by something
public class CommentsActivityDuplicate extends AppCompatActivity {
  RecyclerView commentRecycleView;
  CommentAdapter commentAdapter;
  List<NewComment> newCommentList = new ArrayList<>();
  List<Comment> commentList = new ArrayList<>();
  CommentService commentService = IoCContainer.commentService();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_comments_duplicate);
    Intent intent = getIntent();
    String courseCodeInfo = intent.getStringExtra("courseCode");
    String courseNameInfo = intent.getStringExtra("courseName");

    // TODO: Make helpfulness clickable
    TextView courseCodeInfoTextView = findViewById(R.id.course_code_info);
    TextView courseNameInfoTextView = findViewById(R.id.course_name_info);
    courseCodeInfoTextView.setText(courseCodeInfo);
    courseNameInfoTextView.setText(courseNameInfo);
    commentRecycleView = findViewById(R.id.comment_rv);
    commentRecycleView.setLayoutManager(new LinearLayoutManager(this));

    Button sortButton = findViewById(R.id.sort_button);
    RadioButton helpfulRadio = findViewById(R.id.helpful_radio_button);
    RadioButton enrolRadio = findViewById(R.id.enrol_radio_button);
    RadioButton postedRadio = findViewById(R.id.posted_radio_button);
    sortButton.setOnClickListener(
        view -> {
          if (sortButton.getText().toString().contains("Down")) sortButton.setText("Sort Up");
          else sortButton.setText("Sort Down");
          sortBy(sortButton, helpfulRadio, enrolRadio);
        });
    helpfulRadio.setOnCheckedChangeListener(
        (view, isChecked) -> {
          if (!isChecked) return;
          sortBy(SortingAspect.Helpfulness, sortButton);
        });
    enrolRadio.setOnCheckedChangeListener(
        (view, isChecked) -> {
          if (!isChecked) return;
          sortBy(SortingAspect.EnrolDate, sortButton);
        });
    postedRadio.setOnCheckedChangeListener(
        (view, isChecked) -> {
          if (!isChecked) return;
          sortBy(SortingAspect.PostedDateTime, sortButton);
        });

    EditText searchInput = findViewById(R.id.comment_search_input);
    Button searchButton = findViewById(R.id.comment_search_button);
    searchButton.setOnClickListener(
        view -> {
          String searchInputString = searchInput.getText().toString();
          Query query = new Query(searchInputString);
          newCommentList =
              query.isBlank()
                  ? commentService.getAll(courseCodeInfo)
                  : commentService.findAll(courseCodeInfo, query);
          newCommentsToComments();
          commentAdapter = new CommentAdapter(commentList);
          commentRecycleView.setAdapter(commentAdapter);
        });

    commentService.listenChange(
        courseCodeInfo,
        comments -> {
          newCommentList = comments;
          newCommentsToComments();
          commentAdapter = new CommentAdapter(commentList);
          commentRecycleView.setAdapter(commentAdapter);
        });
  }

  // FIXME consolidate NewComment and Comment later
  void newCommentsToComments() {
    commentList =
        newCommentList.stream()
            .map(
                c ->
                    new Comment(
                        c.id(),
                        c.courseCode(),
                        c.year(),
                        c.semester(),
                        c.text(),
                        c.helpfulness(),
                        c.postedDateTime().toString()))
            .collect(Collectors.toList());
  }

  void sortBy(Button sortButton, RadioButton helpfulRadio, RadioButton enrolRadio) {
    if (helpfulRadio.isChecked()) sortBy(SortingAspect.Helpfulness, sortButton);
    else if (enrolRadio.isChecked()) sortBy(SortingAspect.EnrolDate, sortButton);
    else sortBy(SortingAspect.PostedDateTime, sortButton);
  }

  void sortBy(SortingAspect aspect, Button sortButton) {
    newCommentList =
        commentService.sort(newCommentList, sortButton.getText().toString().contains("Up"), aspect);
    newCommentsToComments();
    commentAdapter = new CommentAdapter(commentList);
    commentRecycleView.setAdapter(commentAdapter);
  }
}
