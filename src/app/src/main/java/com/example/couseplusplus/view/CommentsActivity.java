package com.example.couseplusplus.view;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
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
import com.example.couseplusplus.view.query.Pair;
import com.example.couseplusplus.view.query.QueryElements;
import com.google.android.material.button.MaterialButton;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// FIXME always sort by something
public class CommentsActivity extends AppCompatActivity
    implements PopupMenu.OnMenuItemClickListener {
  String courseCode;
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
  Query query = new Query("");
  QueryElements queryElements =
      new QueryElements(
          new Pair<>(null, null),
          new Pair<>(null, null),
          new Pair<>(null, null),
          new Pair<>(null, null),
          null);

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.comments_menu, menu);
    setListeners(menu);
    return true;
  }

  void setListeners(Menu menu) {
    Intent intent = getIntent();
    courseCode = intent.getStringExtra("courseCode");

    MenuItem searchItem = menu.findItem(R.id.search);
    SearchView searchView = (SearchView) Objects.requireNonNull(searchItem.getActionView());
    searchView.setOnQueryTextListener(
        new SearchView.OnQueryTextListener() {
          @Override
          public boolean onQueryTextSubmit(String text) {
            doSearch(text);
            return true;
          }

          @Override
          public boolean onQueryTextChange(String text) {
            if (!text.isBlank()) return false;
            doSearch(text);
            return true;
          }

          void doSearch(String text) {
            if (usesRawQuery) query = new Query(text);
            else queryElements = queryElements.updateTextHint(text);
            search();
          }
        });

    menu.findItem(R.id.add_comment)
        .setOnMenuItemClickListener(
            item -> {
              Intent addCommentIntent =
                  new Intent(getApplicationContext(), AddComment.class)
                      .putExtra("courseCode", courseCode);
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
              queryElements.updateTextHint("");
              query = new Query("");
              search();
              return true;
            });

    LogoutLabeler.label(R.id.logout, menu, userService, this);

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
    SupportActionBarTitleSetter.set("Comment", this);
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
          sortByThenReflect(sortingAspect);
          return true;
        });

    filterButton = findViewById(R.id.filter_button);
    LayoutInflater inflater = LayoutInflater.from(this);
    View popupView = inflater.inflate(R.layout.filter, null);
    Spinner helpfulOptions = popupView.findViewById(R.id.helpful_filter_options);
    helpfulOptions.setAdapter(
        new ArrayAdapter<>(
            getApplicationContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            HelpfulFilterOption.titles()));
    helpfulOptions.setOnItemSelectedListener(
        new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            Object item = adapterView.getItemAtPosition(i);
            HelpfulFilterOption option = HelpfulFilterOption.getBy(item.toString());
            queryElements = queryElements.updateHelpful(option.minMax());
          }

          @Override
          public void onNothingSelected(AdapterView<?> adapterView) {}
        });

    Spinner enrolOptions = popupView.findViewById(R.id.enrol_filter_options);
    enrolOptions.setAdapter(
        new ArrayAdapter<>(
            getApplicationContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            EnrolDateFilterOption.titles()));
    enrolOptions.setOnItemSelectedListener(
        new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            Object item = adapterView.getItemAtPosition(i);
            EnrolDateFilterOption option = EnrolDateFilterOption.getBy(item.toString());
            queryElements = queryElements.updateEnrol(option.yearMinMax(), option.semesterMinMax());
          }

          @Override
          public void onNothingSelected(AdapterView<?> adapterView) {}
        });

    Spinner postedOptions = popupView.findViewById(R.id.posted_filter_options);
    postedOptions.setAdapter(
        new ArrayAdapter<>(
            getApplicationContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            PostedDateFilterOption.titles()));
    postedOptions.setOnItemSelectedListener(
        new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            Object item = adapterView.getItemAtPosition(i);
            PostedDateFilterOption option = PostedDateFilterOption.getBy(item.toString());
            queryElements = queryElements.updatePosted(option.datetimeMinMax());
          }

          @Override
          public void onNothingSelected(AdapterView<?> adapterView) {}
        });

    filterButton.setOnClickListener(
        view -> {
          PopupWindow popupWindow =
              new PopupWindow(
                  popupView,
                  LinearLayout.LayoutParams.MATCH_PARENT,
                  LinearLayout.LayoutParams.WRAP_CONTENT,
                  true);
          popupWindow.setOnDismissListener(this::search);
          popupWindow.setElevation(12);
          popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        });

    commentService.listenChange(
        courseCodeInfo,
        comments -> {
          commentList = comments;
          sortBy(sortingAspect);
          reflectSearchAndSort();
        });
  }

  void sortByThenReflect(SortingAspect aspect) {
    sortBy(aspect);
    reflectSearchAndSort();
  }

  void sortBy(SortingAspect aspect) {
    if (sortingDirection == SortingDirection.Nowhere) return;
    commentList = commentService.sort(commentList, sortingDirection.isAscending(), aspect);
  }

  void reflectSearchAndSort() {
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
    sortByThenReflect(aspect);
    item.setChecked(true);
    sortingAspect = aspect;
  }

  void search() {
    Query query = usesRawQuery ? this.query : queryElements.toQuery();
    commentList =
        query.isBlank()
            ? commentService.getAll(courseCode)
            : commentService.findAll(courseCode, query);
    sortBy(sortingAspect);
    reflectSearchAndSort();
  }
}
