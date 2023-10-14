package com.example.couseplusplus.data.comment.findingstrategy;

import static org.junit.Assert.*;

import com.example.couseplusplus.data.comment.CommentCache;
import com.example.couseplusplus.model.comment.Comment;
import com.example.couseplusplus.model.query.parser.TerminalNode;
import com.example.couseplusplus.model.query.parser.TextNode;
import com.example.couseplusplus.model.query.tokenizer.Token;
import com.example.couseplusplus.model.query.tokenizer.TokenType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.Test;

/**
 * @author Yuki Misumi (u7582380)
 */
public class TextBasedTest {
  @Test
  public void testFind() {
    TextBased uut = new TextBased();
    TerminalNode terminalNode = new TextNode(new Token(TokenType.TextValue, "lorem ipsum."));
    CommentCache commentCache =
        new CommentCache(
            List.of(
                new Comment("1", "C", 2000, 1, "lorem", 10, LocalDateTime.MIN),
                new Comment("2", "C", 2000, 1, "ipsum", 10, LocalDateTime.MIN),
                new Comment("3", "C", 2000, 1, "lorem ipsum", 10, LocalDateTime.MIN)));
    List<Comment> result = uut.find(TokenType.Like, terminalNode, commentCache);
    assertEquals(3, result.size());
    assertEquals(
        Set.of("1", "2", "3"), result.stream().map(Comment::id).collect(Collectors.toSet()));
    assertThrows(UnsupportedOperationException.class, () -> uut.find(TokenType.Equal, terminalNode, commentCache));
    assertThrows(UnsupportedOperationException.class, () -> uut.find(TokenType.EqualOrMoreThan, terminalNode, commentCache));
    assertThrows(UnsupportedOperationException.class, () -> uut.find(TokenType.EqualOrLessThan, terminalNode, commentCache));
    assertThrows(UnsupportedOperationException.class, () -> uut.find(TokenType.MoreThan, terminalNode, commentCache));
    assertThrows(UnsupportedOperationException.class, () -> uut.find(TokenType.LessThan, terminalNode, commentCache));
  }
}
