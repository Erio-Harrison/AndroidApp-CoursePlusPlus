package com.example.couseplusplus.model.query;

public class EnrolDateNode extends TerminalNode {
  Integer year;
  Integer semester;

  public EnrolDateNode(Token token) {
    super(token);
    String valueString = value().toString();
    String[] yearSemester = valueString.split("S");
    year = Integer.parseInt(yearSemester[0]);
    semester = Integer.parseInt(yearSemester[1]);
  }

  public Integer year() {
    return year;
  }

  public Integer semester() {
    return semester;
  }
}
