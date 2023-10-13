package com.example.couseplusplus.view;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.res.ResourcesCompat;
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
import com.example.couseplusplus.service.user.UserService;
import com.google.android.material.button.MaterialButton;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// FIXME always sort by something
public class CommentsActivity extends AppCompatActivity
    implements PopupMenu.OnMenuItemClickListener {
  RecyclerView commentRecycleView;
  CommentAdapter commentAdapter;
  List<Comment> commentList = new ArrayList<>();
  CommentService commentService = IoCContainer.commentService();
  UserService userService = IoCContainer.userService();
  boolean usesRawQuery = false;
  SortingAspect sortingAspect = SortingAspect.EnrolDate;
  SortingDirection sortingDirection = SortingDirection.Descending;
  MaterialButton filterButton;
  MaterialButton sortButton;

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.comments_menu, menu);
    setListeners(menu);
    return true;
  }

  void setListeners(Menu menu) {
    Intent intent = getIntent();
    String courseCodeInfo = intent.getStringExtra("courseCode");

    MenuItem searchItem = menu.findItem(R.id.search);
    SearchView searchView = (SearchView) Objects.requireNonNull(searchItem.getActionView());
    searchView.setOnQueryTextListener(
        new SearchView.OnQueryTextListener() {
          @Override
          public boolean onQueryTextSubmit(String text) {
            Query query = new Query(text);
            commentList = commentService.findAll(courseCodeInfo, query);
            commentAdapter = new CommentAdapter(commentList);
            commentRecycleView.setAdapter(commentAdapter);
            sortBy(sortingAspect);
            return true;
          }

          @Override
          public boolean onQueryTextChange(String newText) {
            if (!newText.isBlank()) return false;
            commentList = commentService.getAll(courseCodeInfo);
            commentAdapter = new CommentAdapter(commentList);
            commentRecycleView.setAdapter(commentAdapter);
            sortBy(sortingAspect);
            return true;
          }
        });

    menu.findItem(R.id.add_comment)
        .setOnMenuItemClickListener(
            item -> {
              Intent addCommentIntent =
                  new Intent(getApplicationContext(), AddComment.class)
                      .putExtra("courseCode", courseCodeInfo);
              startActivity(addCommentIntent);
              return true;
            });

    menu.findItem(R.id.enable_raw_query)
        .setOnMenuItemClickListener(
            item -> {
              usesRawQuery = !item.isChecked();
              item.setChecked(usesRawQuery);
              searchItem.setIcon(
                  usesRawQuery ? R.drawable.baseline_code_24 : R.drawable.baseline_search_24);
              filterButton.setEnabled(!usesRawQuery);
              return true;
            });

    menu.findItem(R.id.logout)
        .setOnMenuItemClickListener(
            item -> {
              userService.logout();
              Intent logoutIntent = new Intent(getApplicationContext(), Login.class);
              startActivity(logoutIntent);
              finish();
              return true;
            });
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTitle();
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

    sortButton = findViewById(R.id.sort_button);
    sortButton.setText(SortingAspectTextHandler.stringify(sortingAspect));
    PopupMenu popupMenu = new PopupMenu(this, sortButton);
    popupMenu.setOnMenuItemClickListener(this);
    popupMenu.inflate(R.menu.sort_menu);
    sortButton.setOnClickListener(view -> popupMenu.show());
    sortButton.setOnLongClickListener(
        view -> {
          sortingDirection = sortingDirection.next();
          Drawable drawable =
              ResourcesCompat.getDrawable(
                  getResources(), sortingDirection.drawableId(), getTheme());
          sortButton.setIcon(drawable);
          sortBy(sortingAspect);
          return true;
        });

    filterButton = findViewById(R.id.filter_button);
    LayoutInflater inflater = LayoutInflater.from(this);
    View popupView = inflater.inflate(R.layout.filter, null);
    filterButton.setOnClickListener(
        view -> {
          int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;
          PopupWindow popupWindow = new PopupWindow(popupView, wrapContent, wrapContent, true);
          popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
          popupView.setOnTouchListener(
              (v, event) -> {
                popupWindow.dismiss();
                v.performClick();
                return true;
              });
        });

    commentService.listenChange(
        courseCodeInfo,
        comments -> {
          commentList = comments;
          commentAdapter = new CommentAdapter(commentList);
          commentRecycleView.setAdapter(commentAdapter);
        });
  }

  void setTitle() {
    ActionBar actionBar = getSupportActionBar();
    if (Objects.isNull(actionBar)) {
      Log.i(getClass().getSimpleName(), "getSupportActionBar() returned null");
      return;
    }
    actionBar.setTitle("Comment");
  }

  void sortBy(SortingAspect aspect) {
    if (sortingDirection == SortingDirection.Nowhere) return;
    commentList = commentService.sort(commentList, sortingDirection.isAscending(), aspect);
    commentAdapter = new CommentAdapter(commentList);
    commentRecycleView.setAdapter(commentAdapter);
  }

  @Override
  public boolean onMenuItemClick(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.sort_by_enrol:
        sortAndCheck(item, SortingAspect.EnrolDate);
        break;
      case R.id.sort_by_helpful:
        sortAndCheck(item, SortingAspect.Helpfulness);
        break;
      case R.id.sort_by_posted:
        sortAndCheck(item, SortingAspect.PostedDateTime);
        break;
      default:
        throw new IllegalArgumentException(String.format("%s is not supported", item.getTitle()));
    }
    return true;
  }

  void sortAndCheck(MenuItem item, SortingAspect aspect) {
    sortButton.setText(SortingAspectTextHandler.stringify(aspect));
    sortBy(aspect);
    item.setChecked(true);
    sortingAspect = aspect;
  }
}
