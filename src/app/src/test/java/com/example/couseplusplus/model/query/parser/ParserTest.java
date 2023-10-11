package com.example.couseplusplus.model.query.parser;

import static org.junit.Assert.*;

import com.example.couseplusplus.model.query.Query;
import com.example.couseplusplus.model.query.tokenizer.Tokenizer;
import org.junit.Test;

/**
 * @author Yuki Misumi (u7582380)
 */
public class ParserTest {
  @Test
  public void parsesWithoutException() {
    testParse(
        "helpful > 10",
        "ParseTree{root=ComparisonNode{left=IdNode{token=Token{tokenType=Helpful, value=null}}, right=NumberNode{token=Token{tokenType=Integer, value=10}}, token=Token{tokenType=MoreThan, value=null}}}");
    testParse(
        "enrol > 2023S1",
        "ParseTree{root=ComparisonNode{left=IdNode{token=Token{tokenType=Enrol, value=null}}, right=EnrolDateNode{token=Token{tokenType=EnrolDate, value=2023S1}}, token=Token{tokenType=MoreThan, value=null}}}");
    testParse(
        "posted > 2023-09-20",
        "ParseTree{root=ComparisonNode{left=IdNode{token=Token{tokenType=Posted, value=null}}, right=DateTimeNode{token=Token{tokenType=DateTime, value=2023-09-20T00:00}}, token=Token{tokenType=MoreThan, value=null}}}");
    testParse(
        "text ~ \"hello world\"",
        "ParseTree{root=ComparisonNode{left=IdNode{token=Token{tokenType=Text, value=null}}, right=TextNode{token=Token{tokenType=TextValue, value=\"hello world\"}}, token=Token{tokenType=Like, value=null}}}");
    testParse(
        "helpful > 10 & text ~ \"hello world\"",
        "ParseTree{root=ConditionalNode{left=ComparisonNode{left=IdNode{token=Token{tokenType=Helpful, value=null}}, right=NumberNode{token=Token{tokenType=Integer, value=10}}, token=Token{tokenType=MoreThan, value=null}}, right=ComparisonNode{left=IdNode{token=Token{tokenType=Text, value=null}}, right=TextNode{token=Token{tokenType=TextValue, value=\"hello world\"}}, token=Token{tokenType=Like, value=null}}, token=Token{tokenType=And, value=null}}}");
    testParse(
        "helpful > 10 | text ~ \"hello world\"",
        "ParseTree{root=ConditionalNode{left=ComparisonNode{left=IdNode{token=Token{tokenType=Helpful, value=null}}, right=NumberNode{token=Token{tokenType=Integer, value=10}}, token=Token{tokenType=MoreThan, value=null}}, right=ComparisonNode{left=IdNode{token=Token{tokenType=Text, value=null}}, right=TextNode{token=Token{tokenType=TextValue, value=\"hello world\"}}, token=Token{tokenType=Like, value=null}}, token=Token{tokenType=Or, value=null}}}");
    testParse(
        "(helpful > 10 & posted > 2023-09-20) | text ~ \"hello world\"",
        "ParseTree{root=ConditionalNode{left=ConditionalNode{left=ComparisonNode{left=IdNode{token=Token{tokenType=Helpful, value=null}}, right=NumberNode{token=Token{tokenType=Integer, value=10}}, token=Token{tokenType=MoreThan, value=null}}, right=ComparisonNode{left=IdNode{token=Token{tokenType=Posted, value=null}}, right=DateTimeNode{token=Token{tokenType=DateTime, value=2023-09-20T00:00}}, token=Token{tokenType=MoreThan, value=null}}, token=Token{tokenType=And, value=null}}, right=ComparisonNode{left=IdNode{token=Token{tokenType=Text, value=null}}, right=TextNode{token=Token{tokenType=TextValue, value=\"hello world\"}}, token=Token{tokenType=Like, value=null}}, token=Token{tokenType=Or, value=null}}}");
    testParse(
        "helpful > 10 | (text ~ \"hello world\" & posted > 2023-09-20)",
        "ParseTree{root=ConditionalNode{left=ComparisonNode{left=IdNode{token=Token{tokenType=Helpful, value=null}}, right=NumberNode{token=Token{tokenType=Integer, value=10}}, token=Token{tokenType=MoreThan, value=null}}, right=ConditionalNode{left=ComparisonNode{left=IdNode{token=Token{tokenType=Text, value=null}}, right=TextNode{token=Token{tokenType=TextValue, value=\"hello world\"}}, token=Token{tokenType=Like, value=null}}, right=ComparisonNode{left=IdNode{token=Token{tokenType=Posted, value=null}}, right=DateTimeNode{token=Token{tokenType=DateTime, value=2023-09-20T00:00}}, token=Token{tokenType=MoreThan, value=null}}, token=Token{tokenType=And, value=null}}, token=Token{tokenType=Or, value=null}}}");
    testParse(
        "(helpful > 10)",
        "ParseTree{root=ComparisonNode{left=IdNode{token=Token{tokenType=Helpful, value=null}}, right=NumberNode{token=Token{tokenType=Integer, value=10}}, token=Token{tokenType=MoreThan, value=null}}}");
    testParse(
        "((helpful > 10))",
        "ParseTree{root=ComparisonNode{left=IdNode{token=Token{tokenType=Helpful, value=null}}, right=NumberNode{token=Token{tokenType=Integer, value=10}}, token=Token{tokenType=MoreThan, value=null}}}");
  }

  void testParse(String queryString, String expected) {
    Tokenizer tokenizer = new Tokenizer(new Query(queryString));
    Parser uut = new Parser(tokenizer);
    ParseTree result = uut.parse();
    assertNotNull(result);
    assertEquals(expected, result.toString());
  }
}
