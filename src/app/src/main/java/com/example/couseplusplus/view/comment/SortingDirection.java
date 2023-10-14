package com.example.couseplusplus.view.comment;

import com.example.couseplusplus.R;

/**
 * @author Yuki Misumi (u7582380)
 */
public enum SortingDirection {
  Ascending(true, R.drawable.baseline_sort_24_rev),
  Descending(false, R.drawable.baseline_sort_24),
  Nowhere(null, R.drawable.baseline_unfold_more_24),
  ;

  final Boolean isAscending;
  final int drawableId;

  SortingDirection(Boolean isAscending, int drawableId) {
    this.isAscending = isAscending;
    this.drawableId = drawableId;
  }

  public SortingDirection next() {
    switch (this) {
      case Ascending:
        return Descending;
      case Descending:
        return Ascending;
      case Nowhere:
        return Descending;
      default:
        throw new IllegalArgumentException("unsupported direction");
    }
  }

  public Boolean isAscending() {
    return isAscending;
  }

  public int drawableId() {
    return drawableId;
  }
}
