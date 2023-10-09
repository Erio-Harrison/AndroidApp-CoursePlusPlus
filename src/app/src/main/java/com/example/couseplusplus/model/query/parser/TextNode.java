package com.example.couseplusplus.model.query.parser;

import com.example.couseplusplus.model.query.tokenizer.Token;

/**
 * @author Yuki Misumi (u7582380)
 */
public class TextNode extends TerminalNode {
  public TextNode(Token token) {
    super(token);
  }

  public String text() {
    return ((String) value()).replaceAll("\"", "");
  }
}
