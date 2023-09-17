package com.example.couseplusplus.model.query;

public class TextNode extends TerminalNode {
  public TextNode(Token token) {
    super(token);
  }

  public String text() {
    return ((String) value()).replaceAll("\"", "");
  }
}
