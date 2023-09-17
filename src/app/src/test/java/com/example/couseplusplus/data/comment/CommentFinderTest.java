package com.example.couseplusplus.data.comment;

import static org.junit.Assert.*;

import com.example.couseplusplus.model.comment.Comment;
import com.example.couseplusplus.model.query.ParseTree;
import com.example.couseplusplus.model.query.Parser;
import com.example.couseplusplus.model.query.Query;
import com.example.couseplusplus.model.query.Tokenizer;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.Test;

public class CommentFinderTest {
  @Test
  public void walks() {
    CommentFinder uut =
        new CommentFinder(
            List.of(
                new Comment(
                    "1",
                    "COMP6442",
                    2021,
                    1,
                    "hello",
                    0,
                    LocalDateTime.parse("2021-03-10T11:11:11")),
                new Comment(
                    "2",
                    "COMP6442",
                    2021,
                    1,
                    "world",
                    1,
                    LocalDateTime.parse("2021-03-10T11:11:11")),
                new Comment(
                    "3",
                    "COMP6442",
                    2021,
                    2,
                    "hello",
                    11,
                    LocalDateTime.parse("2021-09-10T11:11:11")),
                new Comment(
                    "4",
                    "COMP6442",
                    2022,
                    1,
                    "world",
                    0,
                    LocalDateTime.parse("2022-03-10T11:11:11")),
                new Comment(
                    "5",
                    "COMP6442",
                    2022,
                    1,
                    "hello",
                    1,
                    LocalDateTime.parse("2022-03-10T11:11:11")),
                new Comment(
                    "6",
                    "COMP6442",
                    2022,
                    2,
                    "world",
                    11,
                    LocalDateTime.parse("2022-09-10T11:11:11")),
                new Comment(
                    "7",
                    "COMP6442",
                    2023,
                    1,
                    "hello",
                    0,
                    LocalDateTime.parse("2023-03-10T11:11:11")),
                new Comment(
                    "8",
                    "COMP6442",
                    2023,
                    1,
                    "world",
                    1,
                    LocalDateTime.parse("2023-03-10T11:11:11")),
                new Comment(
                    "9",
                    "COMP6442",
                    2023,
                    2,
                    "hello",
                    11,
                    LocalDateTime.parse("2023-09-10T11:11:11")),
                new Comment(
                    "0",
                    "COMP6442",
                    2023,
                    2,
                    "world",
                    -1,
                    LocalDateTime.parse("2023-09-10T11:11:11"))));

    ParseTree parseTree1 = parse("helpful > 10");
    List<Comment> result1 = uut.walk(parseTree1);
    assertEquals(3, result1.size());

    ParseTree parseTree2 = parse("enrol > 2023S1");
    List<Comment> result2 = uut.walk(parseTree2);
    assertEquals(2, result2.size());

    ParseTree parseTree3 = parse("posted > 2023-09-01");
    List<Comment> result3 = uut.walk(parseTree3);
    assertEquals(2, result3.size());

    ParseTree parseTree4 = parse("text ~ \"world\"");
    List<Comment> result4 = uut.walk(parseTree4);
    assertEquals(5, result4.size());

    ParseTree parseTree5 = parse("helpful > 10 & text ~ \"hell\"");
    List<Comment> result5 = uut.walk(parseTree5);
    assertEquals(2, result5.size());

    ParseTree parseTree6 = parse("helpful > 10 | text ~ \"hell\"");
    List<Comment> result6 = uut.walk(parseTree6);
    assertEquals(6, result6.size());
  }

  ParseTree parse(String queryString) {
    Tokenizer tokenizer = new Tokenizer(new Query(queryString));
    Parser uut = new Parser(tokenizer);
    return uut.parse();
  }
}
