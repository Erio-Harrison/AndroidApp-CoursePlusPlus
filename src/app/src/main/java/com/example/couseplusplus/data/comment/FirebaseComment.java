package com.example.couseplusplus.data.comment;

/**
 * @author Yuki Misumi (u7582380)
 * @author Min su Park
 *     <p>This is the class which will be used to proccess and ultimately passed to firebase
 *     database
 */
public class FirebaseComment {
  public static String[] DISPLAY_YEARS = {
    "2015", "2016", "2017", "2017", "2018", "2019", "2020", "2021", "2022", "2023"
  };
  public static String[] DISPLAY_SEMESTERS = {"Semester 1", "Semester 2"};

  int year;
  int semester;
  String text;
  int helpfulness;
  String postedDateTime;

  public FirebaseComment() {}

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public int getSemester() {
    return semester;
  }

  public void setSemester(int semester) {
    this.semester = semester;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public int getHelpfulness() {
    return helpfulness;
  }

  public void setHelpfulness(int helpfulness) {
    this.helpfulness = helpfulness;
  }

  public String getPostedDateTime() {
    return postedDateTime;
  }

  public void setPostedDateTime(String postedDateTime) {
    this.postedDateTime = postedDateTime;
  }
}
