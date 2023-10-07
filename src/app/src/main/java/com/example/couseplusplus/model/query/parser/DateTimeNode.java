package com.example.couseplusplus.model.query.parser;

import com.example.couseplusplus.model.query.tokenizer.Token;
import java.time.LocalDateTime;

/**
 * @author Yuki Misumi (u7582380)
 */
public class DateTimeNode extends TerminalNode {
  public DateTimeNode(Token token) {
    super(token);
  }

  public LocalDateTime localDateTime() {
    return (LocalDateTime) value();
  }
}
