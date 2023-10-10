package com.example.couseplusplus.data.course;

import com.example.couseplusplus.data.avl.AVLTree;
import com.example.couseplusplus.model.course.NewCourse;
import java.util.List;

/**
 * @author Yuki Misumi (u7582380)
 */
public class CourseCache {
  List<NewCourse> courses;
  AVLTree<String, NewCourse> codeTree;
  AVLTree<String, NewCourse> nameTree;

  public CourseCache(List<NewCourse> courses) {
    this.courses = courses;
    codeTree = new AVLTree<>();
    nameTree = new AVLTree<>();

    courses.forEach(
        course -> {
          codeTree.insert(course.courseCode(), course);
          nameTree.insert(course.courseName(), course);
        });
  }

  public List<NewCourse> courses() {
    return courses;
  }

  public AVLTree<String, NewCourse> codeTree() {
    return codeTree;
  }

  public AVLTree<String, NewCourse> nameTree() {
    return nameTree;
  }
}
