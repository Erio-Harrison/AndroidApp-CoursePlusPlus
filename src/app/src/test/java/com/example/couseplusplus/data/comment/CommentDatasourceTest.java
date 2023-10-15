package com.example.couseplusplus.data.comment;

import static org.junit.Assert.*;

import com.example.couseplusplus.model.query.parser.ErrorNode;
import com.example.couseplusplus.model.query.parser.ParseTree;
import com.example.couseplusplus.model.query.tokenizer.Token;
import com.example.couseplusplus.model.query.tokenizer.TokenType;
import java.util.List;
import org.junit.Test;

/**
 * @author Yuki Misumi (u7582380)
 */
public class CommentDatasourceTest {
  @Test
  public void testGetInstance() {
    CommentDatasource uut = CommentDatasource.getInstance();
    assertEquals(CommentDatasource.getInstance(), uut);
  }

  @Test
  public void testGetAll() {
    CommentDatasource uut = new CommentDatasource();
    String courseCode = "COMP6442";
    assertEquals(List.of(), uut.getAll(courseCode));
    uut.cache = new CommentCache(List.of());
    uut.code = courseCode;
    uut.commentFinder = new CommentFinder(uut.cache);
    assertEquals(List.of(), uut.getAll(courseCode));
  }

  @Test
  public void testFindAll() {
    CommentDatasource uut = new CommentDatasource();
    String courseCode = "COMP6442";
    assertEquals(
        List.of(),
        uut.findAll(
            courseCode, new ParseTree(new ErrorNode(new Token(TokenType.Error, null), null))));
    uut.cache = new CommentCache(List.of());
    uut.code = courseCode;
    uut.commentFinder = new CommentFinder(uut.cache);
    assertEquals(
        List.of(),
        uut.findAll(
            courseCode, new ParseTree(new ErrorNode(new Token(TokenType.Error, null), null))));
  }

  @Test
  public void testValidate() {
    CommentDatasource uut = new CommentDatasource();
    String courseCode = "COMP6442";
    assertThrows(IllegalArgumentException.class, () -> uut.validate(courseCode));
    uut.code = courseCode;
    uut.validate(courseCode);
  }

  @Test
  public void testIsNotReady() {
    CommentDatasource uut = new CommentDatasource();
    assertTrue(uut.isNotReady());
    uut.cache = new CommentCache(List.of());
    assertFalse(uut.isNotReady());
  }
}
