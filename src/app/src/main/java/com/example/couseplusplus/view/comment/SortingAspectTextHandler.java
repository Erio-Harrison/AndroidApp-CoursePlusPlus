package com.example.couseplusplus.view.comment;

import com.example.couseplusplus.service.comment.SortingAspect;

/**
 * @author Yuki Misumi (u7582380)
 */
public class SortingAspectTextHandler {
  public static String stringify(SortingAspect aspect) {
    switch (aspect) {
      case Helpfulness:
        return "helpful";
      case EnrolDate:
        return "enrol on";
      case PostedDateTime:
        return "post on";
    }
    return "";
  }
}
