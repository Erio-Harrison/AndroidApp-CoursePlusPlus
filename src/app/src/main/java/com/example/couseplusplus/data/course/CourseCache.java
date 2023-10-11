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
          codeTree.insert(course.courseCode(), course);
          nameTree.insert(course.courseName(), course);
        });
  }

  public List<Course> courses() {
    return courses;
  }

  public AVLTree<String, Course> codeTree() {
    return codeTree;
  }

  public AVLTree<String, Course> nameTree() {
    return nameTree;
  }
}
