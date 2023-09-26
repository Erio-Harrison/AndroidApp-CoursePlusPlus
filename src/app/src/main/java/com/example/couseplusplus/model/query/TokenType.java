package com.example.couseplusplus.model.query;

public enum TokenType {
  // keyword
  Helpful,
  Enrol,
  Posted,
  Text,
  // comparator
  MoreThan,
  LessThan,
  EqualOrMoreThan,
  EqualOrLessThan,
  Equal,
  // like
  Like,
  // operand
  Integer,
  DateTime,
  EnrolDate,
  TextString,
  // parenthesis
  LeftParenthesis,
  RightParenthesis,
  // condition
  And,
  Or,
  // end
  End,
  ;

  public boolean isComparable() {
    switch (this) {
      case MoreThan:
      case LessThan:
      case EqualOrMoreThan:
      case EqualOrLessThan:
      case Equal:
        return true;
      default:
        return false;
    }
  }
}
