package com.example.couseplusplus.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.couseplusplus.AddComment;
import com.example.couseplusplus.CommentAdapter;
import com.example.couseplusplus.IoCContainer;
import com.example.couseplusplus.R;
import com.example.couseplusplus.model.comment.Comment;
import com.example.couseplusplus.model.query.Query;
import com.example.couseplusplus.service.comment.CommentService;
import com.example.couseplusplus.service.comment.SortingAspect;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

// FIXME always sort by something
public class CommentsActivity extends AppCompatActivity {
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

    FloatingActionButton sortButton = findViewById(R.id.sort_comment_button);
    sortButton.setOnClickListener(
        view -> {
          sortBy(SortingAspect.Helpfulness);
        });

    FloatingActionButton filterButton = findViewById(R.id.filter_comment_button);

    SearchView searchBar = findViewById(R.id.search_bar);
    searchBar.setOnQueryTextListener(
        new SearchView.OnQueryTextListener() {
          @Override
          public boolean onQueryTextSubmit(String s) {
            Query query = new Query(s);
            commentList = commentService.findAll(courseCodeInfo, query);
            commentAdapter = new CommentAdapter(commentList);
            commentRecycleView.setAdapter(commentAdapter);
            return true;
          }

          @Override
          public boolean onQueryTextChange(String s) {
            if (!s.isBlank()) return false;
            commentList = commentService.getAll(courseCodeInfo);
            commentAdapter = new CommentAdapter(commentList);
            commentRecycleView.setAdapter(commentAdapter);
            return true;
          }
        });

    commentService.listenChange(
        courseCodeInfo,
        comments -> {
          commentList = comments;
          commentAdapter = new CommentAdapter(commentList);
          commentRecycleView.setAdapter(commentAdapter);
        });

    findViewById(R.id.add_comment_button)
        .setOnClickListener(
            view -> {
              startActivity(
                  new Intent(getApplicationContext(), AddComment.class)
                      .putExtra("courseCode", courseCodeInfo));
            });
  }

  void sortBy(SortingAspect aspect) {
    commentList = commentService.sort(commentList, true, aspect);
    commentAdapter = new CommentAdapter(commentList);
    commentRecycleView.setAdapter(commentAdapter);
  }
}
