package com.example.couseplusplus.data.comment.findingstrategy;

import static org.junit.Assert.*;

import com.example.couseplusplus.data.comment.CommentCache;
import com.example.couseplusplus.model.comment.Comment;
import com.example.couseplusplus.model.query.parser.DateTimeNode;
import com.example.couseplusplus.model.query.parser.NumberNode;
import com.example.couseplusplus.model.query.parser.TerminalNode;
import com.example.couseplusplus.model.query.tokenizer.Token;
import com.example.couseplusplus.model.query.tokenizer.TokenType;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Yuki Misumi (u7582380)
 */
public class PostedBasedTest {
    @Test
    public void testFind() {
        PostedBased uut = new PostedBased();
        TerminalNode terminalNode = new DateTimeNode(new Token(TokenType.DateTime, LocalDateTime.parse("2000-01-01T01:01:01")));
        CommentCache commentCache =
                new CommentCache(
                        List.of(
                                new Comment("1", "C", 2000, 1, "lorem", 10, LocalDateTime.parse("2000-01-01T01:01:00")),
                                new Comment("2", "C", 2000, 1, "lorem", 10, LocalDateTime.parse("2000-01-01T01:01:01")),
                                new Comment("3", "C", 2000, 1, "lorem", 10, LocalDateTime.parse("2000-01-01T01:01:02"))));
        assertEquals(1, uut.find(TokenType.Equal, terminalNode, commentCache).size());
        assertEquals(2, uut.find(TokenType.EqualOrMoreThan, terminalNode, commentCache).size());
        assertEquals(2, uut.find(TokenType.EqualOrLessThan, terminalNode, commentCache).size());
        assertEquals(1, uut.find(TokenType.MoreThan, terminalNode, commentCache).size());
        assertEquals(1, uut.find(TokenType.LessThan, terminalNode, commentCache).size());
        assertThrows(UnsupportedOperationException.class, () -> uut.find(TokenType.Like, terminalNode, commentCache));
    }
}