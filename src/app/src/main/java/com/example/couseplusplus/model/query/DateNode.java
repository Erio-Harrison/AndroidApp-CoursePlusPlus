package com.example.couseplusplus.model.query;

import java.time.LocalDate;

public class DateNode extends TerminalNode {
  public DateNode(Token token) {
    super(token);
  }

  public LocalDate localDate() {
    return (LocalDate) value();
  }
}
