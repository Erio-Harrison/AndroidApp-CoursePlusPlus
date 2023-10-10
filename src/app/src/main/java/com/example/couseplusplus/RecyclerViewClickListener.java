package com.example.couseplusplus;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewClickListener implements RecyclerView.OnItemTouchListener {
  private OnItemClickListener mListener;
  private GestureDetector mGestureDetector;

  public interface OnItemClickListener {
    void onItemClick(View view, int position);

    void onLongItemClick(View view, int position);
  }

  public RecyclerViewClickListener(
      Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
    mListener = listener;
    mGestureDetector =
        new GestureDetector(
            context,
            new GestureDetector.SimpleOnGestureListener() {
              @Override
              public boolean onSingleTapUp(MotionEvent e) {
                return true;
              }

              @Override
              public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && mListener != null) {
                  mListener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child));
                }
              }
            });
  }

  @Override
  public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
    View child = rv.findChildViewUnder(e.getX(), e.getY());
    if (child != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
      mListener.onItemClick(child, rv.getChildAdapterPosition(child));
      return true;
    }
    return false;
  }

  @Override
  public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {}

  @Override
  public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {}
}
