package com.example.couseplusplus.model.query;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;
import org.junit.Test;

public class TokenizerTest {

  @Test(timeout = 1000)
  public void testGetNextToken() {
    testAllTokens(
        new Tokenizer(new Query("helpful enrol posted text")),
        List.of(
            new Token(TokenType.Helpful, null),
            new Token(TokenType.Enrol, null),
            new Token(TokenType.Posted, null),
            new Token(TokenType.Text, null)));
    testAllTokens(
        new Tokenizer(new Query("&|")),
        List.of(new Token(TokenType.And, null), new Token(TokenType.Or, null)));
    testAllTokens(
        new Tokenizer(new Query("()")),
        List.of(
            new Token(TokenType.LeftParenthesis, null),
            new Token(TokenType.RightParenthesis, null)));
    testAllTokens(
        new Tokenizer(new Query("<><=>==")),
        List.of(
            new Token(TokenType.LessThan, null),
            new Token(TokenType.MoreThan, null),
            new Token(TokenType.EqualOrLessThan, null),
            new Token(TokenType.EqualOrMoreThan, null),
            new Token(TokenType.Equal, null)));
    testAllTokens(new Tokenizer(new Query("~")), List.of(new Token(TokenType.Like, null)));
    testAllTokens(
        new Tokenizer(new Query("2023-09-20")),
        List.of(new Token(TokenType.DateTime, LocalDate.parse("2023-09-20").atStartOfDay())));
    testAllTokens(
        new Tokenizer(new Query("2023S1")), List.of(new Token(TokenType.EnrolDate, "2023S1")));
    testAllTokens(
        new Tokenizer(new Query("12 123 1234 12345")),
        List.of(
            new Token(TokenType.Integer, 12),
            new Token(TokenType.Integer, 123),
            new Token(TokenType.Integer, 1234),
            new Token(TokenType.Integer, 12345)));
    testAllTokens(
        new Tokenizer(new Query("\"hello world\" \"1\"")),
        List.of(
            new Token(TokenType.TextValue, "\"hello world\""),
            new Token(TokenType.TextValue, "\"1\"")));
  }

  void testAllTokens(Tokenizer tokenizer, List<Token> expectedTokens) {
    int index = 0;
    Token token = tokenizer.getNextToken();
    while (token.tokenType() != TokenType.End) {
      assertEquals(expectedTokens.get(index).tokenType(), token.tokenType());
      assertEquals(expectedTokens.get(index).value(), token.value());
      index++;
      token = tokenizer.getNextToken();
    }
  }
}
