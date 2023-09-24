package com.example.couseplusplus.data.course;

import com.example.couseplusplus.data.avl.AVLTree;
import com.example.couseplusplus.model.course.Course;
import java.util.Comparator;
import java.util.List;

public class CourseCache {
  List<Course> courses;
  AVLTree<Course> codeTree;
  AVLTree<Course> nameTree;

  public CourseCache(List<Course> courses) {
    this.courses = courses;
    codeTree = new AVLTree<>(Comparator.comparing(Course::code));
    nameTree = new AVLTree<>(Comparator.comparing(Course::name));

    courses.forEach(
        course -> {
          codeTree.insert(course);
          nameTree.insert(course);
        });
  }
}
