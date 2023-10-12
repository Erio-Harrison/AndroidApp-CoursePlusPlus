package com.example.couseplusplus;

import com.example.couseplusplus.data.comment.CommentDatasource;
import com.example.couseplusplus.data.course.CourseDatasource;
import com.example.couseplusplus.data.user.UserDatasource;
import com.example.couseplusplus.model.comment.CommentRepository;
import com.example.couseplusplus.model.course.CourseRepository;
import com.example.couseplusplus.model.query.QueryParseTreeCreator;
import com.example.couseplusplus.model.query.parser.GracefulParser;
import com.example.couseplusplus.model.query.tokenizer.GracefulTokenizer;
import com.example.couseplusplus.model.user.UserRepository;
import com.example.couseplusplus.service.comment.CommentService;
import com.example.couseplusplus.service.comment.FireBaseCommentService;
import com.example.couseplusplus.service.course.CourseService;
import com.example.couseplusplus.service.user.UserService;

/**
 * @author Yuki Misumi (u7582380)
 */
public class IoCContainer {
  private static final UserRepository userRepository = UserDatasource.getInstance();
  private static final UserService userService = new UserService(userRepository);

  private static final CourseRepository courseRepository = CourseDatasource.getInstance();
  private static final CourseService courseService = new CourseService(courseRepository);

  private static final CommentRepository commentRepository = CommentDatasource.getInstance();
  private static final FireBaseCommentService fireBaseCommentService = new FireBaseCommentService(commentRepository);
  private static final QueryParseTreeCreator queryParserTreeCreator =
      new QueryParseTreeCreator(GracefulTokenizer::new, GracefulParser::new);
  private static final CommentService commentService =
      new CommentService(commentRepository, queryParserTreeCreator);

  public static UserService userService() {
    return userService;
  }

  public static CourseService courseService() {
    return courseService;
  }

  public static CommentService commentService() {
    return commentService;
  }
  public static FireBaseCommentService fireBaseCommentService() { return fireBaseCommentService;}
}
