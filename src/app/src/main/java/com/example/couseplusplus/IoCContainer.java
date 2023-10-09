package com.example.couseplusplus;

import com.example.couseplusplus.data.comment.CommentDatasource;
import com.example.couseplusplus.data.course.CourseDatasource;
import com.example.couseplusplus.data.user.UserDatasource;
import com.example.couseplusplus.model.comment.CommentRepository;
import com.example.couseplusplus.model.course.CourseRepository;
import com.example.couseplusplus.model.user.UserRepository;
import com.example.couseplusplus.service.comment.CommentService;
import com.example.couseplusplus.service.course.CourseService;
import com.example.couseplusplus.service.user.UserService;

/**
 * @author Yuki Misumi (u7582380)
 */
public class IoCContainer {
  private static final UserRepository userRepository = new UserDatasource();
  private static final UserService userService = new UserService(userRepository);

  private static final CourseRepository courseRepository = new CourseDatasource();
  private static final CourseService courseService = new CourseService(courseRepository);

  private static final CommentRepository commentRepository = new CommentDatasource();
  private static final CommentService commentService = new CommentService(commentRepository);

  public static UserService userService() {
    return userService;
  }

  public static CourseService courseService() {
    return courseService;
  }

  public static CommentService commentService() {
    return commentService;
  }
}
