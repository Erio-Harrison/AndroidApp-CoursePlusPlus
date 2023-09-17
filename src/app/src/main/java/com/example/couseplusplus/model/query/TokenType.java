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
  Like,
  // operand
  Integer,
  Date,
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
}
