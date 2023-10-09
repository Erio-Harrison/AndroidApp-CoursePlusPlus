package com.example.couseplusplus.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.couseplusplus.CommentAdapter;
import com.example.couseplusplus.IoCContainer;
import com.example.couseplusplus.R;
import com.example.couseplusplus.model.comment.Comment;
import com.example.couseplusplus.service.comment.CommentService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommentsActivityDuplicate extends AppCompatActivity {
  RecyclerView commentRecycleView;
  CommentAdapter commentAdapter;
  List<Comment> commentList = new ArrayList<>();
  CommentService commentService = IoCContainer.commentService();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_comments);
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

    commentService.listenChange(
        courseCodeInfo,
        comments -> {
          // FIXME consolidate NewComment and Comment later
          commentList =
              comments.stream()
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
          commentAdapter = new CommentAdapter(commentList);
          commentRecycleView.setAdapter(commentAdapter);
        });
  }
}
