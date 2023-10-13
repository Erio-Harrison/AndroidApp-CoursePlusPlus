package com.example.couseplusplus.view;

import com.example.couseplusplus.service.comment.SortingAspect;

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
