package com.example.couseplusplus.view;

import android.util.Log;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Objects;

public class SupportActionBarTitleSetter {
  public static void set(String title, AppCompatActivity activity) {
    ActionBar actionBar = activity.getSupportActionBar();
    if (Objects.isNull(actionBar)) {
      Log.i(
          SupportActionBarTitleSetter.class.getSimpleName(), "getSupportActionBar() returned null");
      return;
    }
    actionBar.setTitle(title);
  }
}
