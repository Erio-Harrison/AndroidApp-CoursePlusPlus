package com.example.couseplusplus.model.query.parser;

import com.example.couseplusplus.model.query.tokenizer.Token;

/**
 * @author Yuki Misumi (u7582380)
 */
public class IdNode extends TerminalNode {
  public IdNode(Token token) {
    super(token);
  }
}
