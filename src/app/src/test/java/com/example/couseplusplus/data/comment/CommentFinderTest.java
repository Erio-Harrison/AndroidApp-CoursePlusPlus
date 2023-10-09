package com.example.couseplusplus.data.comment;

import static org.junit.Assert.*;

import com.example.couseplusplus.model.comment.NewComment;
import com.example.couseplusplus.model.query.Query;
import com.example.couseplusplus.model.query.QueryParseTreeCreator;
import com.example.couseplusplus.model.query.parser.ParseTree;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.Test;

/**
 * @author Yuki Misumi (u7582380)
 */
public class CommentFinderTest {
  @Test
  public void walks() {
    List<NewComment> comments =
        List.of(
            new NewComment(
                "1", "COMP6442", 2021, 1, "hello", 0, LocalDateTime.parse("2021-03-10T11:11:11")),
            new NewComment(
                "2", "COMP6442", 2021, 1, "world", 1, LocalDateTime.parse("2021-03-10T11:11:11")),
            new NewComment(
                "3", "COMP6442", 2021, 2, "hello", 11, LocalDateTime.parse("2021-09-10T11:11:11")),
            new NewComment(
                "4", "COMP6442", 2022, 1, "world", 0, LocalDateTime.parse("2022-03-10T11:11:11")),
            new NewComment(
                "5", "COMP6442", 2022, 1, "hello", 1, LocalDateTime.parse("2022-03-10T11:11:11")),
            new NewComment(
                "6", "COMP6442", 2022, 2, "world", 11, LocalDateTime.parse("2022-09-10T11:11:11")),
            new NewComment(
                "7", "COMP6442", 2023, 1, "hello", 0, LocalDateTime.parse("2023-03-10T11:11:11")),
            new NewComment(
                "8", "COMP6442", 2023, 1, "world", 1, LocalDateTime.parse("2023-03-10T11:11:11")),
            new NewComment(
                "9", "COMP6442", 2023, 2, "hello", 11, LocalDateTime.parse("2023-09-10T11:11:11")),
            new NewComment(
                "0", "COMP6442", 2023, 2, "world", -1, LocalDateTime.parse("2023-09-10T11:11:11")));
    CommentCache commentCache = new CommentCache(comments);
    CommentFinder uut = new CommentFinder(commentCache);

    ParseTree parseTree1 = parse("helpful > 10");
    List<NewComment> result1 = uut.walk(parseTree1);
    assertEquals(3, result1.size());

    ParseTree parseTree2 = parse("enrol > 2023S1");
    List<NewComment> result2 = uut.walk(parseTree2);
    assertEquals(2, result2.size());

    ParseTree parseTree3 = parse("posted > 2023-09-01");
    List<NewComment> result3 = uut.walk(parseTree3);
    assertEquals(2, result3.size());

    ParseTree parseTree4 = parse("text ~ \"world\"");
    List<NewComment> result4 = uut.walk(parseTree4);
    assertEquals(5, result4.size());

    ParseTree parseTree5 = parse("helpful > 10 & text ~ \"hell\"");
    List<NewComment> result5 = uut.walk(parseTree5);
    assertEquals(2, result5.size());

    ParseTree parseTree6 = parse("helpful > 10 | text ~ \"hell\"");
    List<NewComment> result6 = uut.walk(parseTree6);
    assertEquals(6, result6.size());

    ParseTree parseTree7 = parse("enrol > 2023S1 & helpful > 10 & text ~ \"hell\"");
    List<NewComment> result7 = uut.walk(parseTree7);
    assertEquals(1, result7.size());

    ParseTree parseTree8 = parse("enrol > 2023S1 | (helpful > 10 & text ~ \"hell\")");
    List<NewComment> result8 = uut.walk(parseTree8);
    assertEquals(3, result8.size());
  }

  ParseTree parse(String queryString) {
    return QueryParseTreeCreator.create(new Query(queryString));
  }
}
