package com.example.couseplusplus.data.course;

import com.example.couseplusplus.data.avl.AVLTree;
import com.example.couseplusplus.model.course.Course;
import java.util.List;

/**
 * @author Yuki Misumi (u7582380)
 */
public class CourseCache {
  List<Course> courses;
  AVLTree<String, Course> codeTree;
  AVLTree<String, Course> nameTree;

  public CourseCache(List<Course> courses) {
    this.courses = courses;
    codeTree = new AVLTree<>();
    nameTree = new AVLTree<>();

    courses.forEach(
        course -> {
          codeTree.insert(course.getCourseCode(), course);
          nameTree.insert(course.getCourseName(), course);
        });
  }
}
