package com.example.couseplusplus.model.course;

public class Course {
  String code;
  String name;

  public Course(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public String code() {
    return code;
  }

  public String name() {
    return name;
  }
}
