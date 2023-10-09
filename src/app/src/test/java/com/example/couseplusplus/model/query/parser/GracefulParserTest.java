package com.example.couseplusplus.model.query.parser;

import static org.junit.Assert.*;

import com.example.couseplusplus.model.query.Query;
import com.example.couseplusplus.model.query.tokenizer.Tokenizer;
import org.junit.Test;

/**
 * @author Yuki Misumi (u7582380)
 */
public class GracefulParserTest {
  @Test
  public void parsesWithoutException() {
    testParse(
        "<",
        "ParseTree{root=ErrorNode{exception=IllegalSyntaxException, token=Token{tokenType=LessThan, value=null}}}");
    testParse(
        "helpful >",
        "ParseTree{root=ErrorNode{exception=IllegalSyntaxException, token=Token{tokenType=End, value=null}}}");
    testParse(
        "posted > 2023:09:20",
        "ParseTree{root=ErrorNode{exception=NoTransitionException, token=Token{tokenType=MoreThan, value=null}}}");
    testParse(
        "helpful > 0 & 2023-09-20",
        "ParseTree{root=ConditionalNode{left=ComparisonNode{left=IdNode{token=Token{tokenType=Helpful, value=null}}, right=NumberNode{token=Token{tokenType=Integer, value=0}}, token=Token{tokenType=MoreThan, value=null}}, right=ErrorNode{exception=IllegalSyntaxException, token=Token{tokenType=DateTime, value=2023-09-20T00:00}}, token=Token{tokenType=And, value=null}}}");
    testParse(
        "helpful > 0 && posted > 2023-09-20",
        "ParseTree{root=ConditionalNode{left=ComparisonNode{left=IdNode{token=Token{tokenType=Helpful, value=null}}, right=NumberNode{token=Token{tokenType=Integer, value=0}}, token=Token{tokenType=MoreThan, value=null}}, right=ConditionalNode{left=ErrorNode{exception=IllegalSyntaxException, token=Token{tokenType=And, value=null}}, right=ComparisonNode{left=IdNode{token=Token{tokenType=Posted, value=null}}, right=DateTimeNode{token=Token{tokenType=DateTime, value=2023-09-20T00:00}}, token=Token{tokenType=MoreThan, value=null}}, token=Token{tokenType=And, value=null}}, token=Token{tokenType=And, value=null}}}");
    testParse(
        "(helpful > 10",
        "ParseTree{root=ErrorNode{exception=IllegalSyntaxException, token=Token{tokenType=End, value=null}}}");
  }

  void testParse(String queryString, String expected) {
    Tokenizer tokenizer = new Tokenizer(new Query(queryString));
    Parser uut = new GracefulParser(tokenizer);
    ParseTree result = uut.parse();
    assertNotNull(result);
    assertEquals(expected, result.toString());
  }
}
