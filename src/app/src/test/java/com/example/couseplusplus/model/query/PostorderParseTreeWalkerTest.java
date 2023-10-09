package com.example.couseplusplus.model.query;

import static org.junit.Assert.*;

import com.example.couseplusplus.model.query.parser.IdNode;
import com.example.couseplusplus.model.query.parser.Parser;
import com.example.couseplusplus.model.query.parser.TerminalNode;
import com.example.couseplusplus.model.query.tokenizer.Token;
import com.example.couseplusplus.model.query.tokenizer.Tokenizer;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class PostorderParseTreeWalkerTest {
  PostorderParseTreeWalker<List<String>> uut;

  @Test
  public void walks() {
    Tokenizer tokenizer = new Tokenizer(new Query("helpful > 10 & text ~ \"t\""));
    Parser parser = new Parser(tokenizer);
    List<String> result = uut.walk(parser.parse());
    assertEquals(1, result.size());
    assertEquals("[Helpful MoreThan 10] And [Text Like \"t\"]", result.get(0));
  }

  @Before
  public void before() {
    uut =
        new PostorderParseTreeWalker<>() {
          @Override
          public List<String> processComparison(IdNode left, Token operator, TerminalNode right) {
            return List.of(
                String.format(
                    "%s %s %s",
                    left.token().tokenType(), operator.tokenType(), right.token().value()));
          }

          @Override
          public List<String> processConditional(
              List<String> left, Token operator, List<String> right) {
            return List.of(String.format("%s %s %s", left, operator.tokenType(), right));
          }
        };
  }
}
