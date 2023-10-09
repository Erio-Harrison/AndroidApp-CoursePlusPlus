package com.example.couseplusplus.model.query.parser;

import com.example.couseplusplus.model.query.tokenizer.Token;

/**
 * @author Yuki Misumi (u7582380)
 */
public class TerminalNode extends Node {
  public TerminalNode(Token token) {
    super(token);
  }
}
