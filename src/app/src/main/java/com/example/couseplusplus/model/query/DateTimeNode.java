package com.example.couseplusplus.model.query;

import java.time.LocalDateTime;

public class DateTimeNode extends TerminalNode {
  public DateTimeNode(Token token) {
    super(token);
  }

  public LocalDateTime localDateTime() {
    return (LocalDateTime) value();
  }
}
