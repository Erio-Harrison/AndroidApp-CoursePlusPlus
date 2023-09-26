package com.example.couseplusplus.model.query;

public class Tokenizer {
  Query query;
  int index;

  public Tokenizer(Query query) {
    this.query = query;
    index = 0;
  }

  public boolean hasNext() {
    return !query.isOutOfRange(index);
  }

  public Token getNextToken() {
    while (query.find(index).isPresent()) {
      if (query.isSpace(index)) {
        while (query.isSpace(index)) index++;
        continue;
      }
      if (query.isDigit(index)) {
        int start = index;
        while (query.isDigit(index)) index++;
        if (index - start == 4 && query.isHyphen(index) && query.isDigit(index + 1)) {
          index++;
          int monthStart = index;
          while (query.isDigit(index)) index++;
          if (index - monthStart == 2 && query.isHyphen(index) && query.isDigit(index + 1)) {
            index++;
            int dayStart = index;
            while (query.isDigit(index)) index++;
            if (index - dayStart == 2)
              return new Token(TokenType.DateTime, query.getDateTime(start, index));
            throw new IllegalTokenException();
          }
          throw new IllegalTokenException();
        }
        if (index - start == 4 && query.isS(index)) {
          index++;
          if (query.is(index, '1') || query.is(index, '2')) {
            index++;
            return new Token(TokenType.EnrolDate, query.getSubstring(start, index));
          }
          throw new IllegalTokenException();
        }
        return new Token(TokenType.Integer, query.getInteger(start, index));
      }
      if (query.is(index, '"')) {
        int start = index;
        index++;
        while (!query.is(index, '"')) {
          if (query.isOutOfRange(index)) throw new IllegalTokenException();
          index++;
        }
        index++;
        return new Token(TokenType.TextString, query.getSubstring(start, index));
      }
      if (query.is(index, '&')) {
        index++;
        return new Token(TokenType.And, null);
      }
      if (query.is(index, '|')) {
        index++;
        return new Token(TokenType.Or, null);
      }
      if (query.is(index, '<')) {
        index++;
        if (query.is(index, '=')) {
          index++;
          return new Token(TokenType.EqualOrLessThan, null);
        }
        return new Token(TokenType.LessThan, null);
      }
      if (query.is(index, '>')) {
        index++;
        if (query.is(index, '=')) {
          index++;
          return new Token(TokenType.EqualOrMoreThan, null);
        }
        return new Token(TokenType.MoreThan, null);
      }
      if (query.is(index, '=')) {
        index++;
        return new Token(TokenType.Equal, null);
      }
      if (query.is(index, '~')) {
        index++;
        return new Token(TokenType.Like, null);
      }
      if (query.is(index, 'h')) {
        for (char ch : "helpful".toCharArray()) {
          if (!query.is(index, ch)) throw new IllegalTokenException();
          index++;
        }
        return new Token(TokenType.Helpful, null);
      }
      if (query.is(index, 'e')) {
        for (char ch : "enrol".toCharArray()) {
          if (!query.is(index, ch)) throw new IllegalTokenException();
          index++;
        }
        return new Token(TokenType.Enrol, null);
      }
      if (query.is(index, 'p')) {
        for (char ch : "posted".toCharArray()) {
          if (!query.is(index, ch)) throw new IllegalTokenException();
          index++;
        }
        return new Token(TokenType.Posted, null);
      }
      if (query.is(index, 'p')) {
        for (char ch : "posted".toCharArray()) {
          if (!query.is(index, ch)) throw new IllegalTokenException();
          index++;
        }
        return new Token(TokenType.Posted, null);
      }
      if (query.is(index, 't')) {
        for (char ch : "text".toCharArray()) {
          if (!query.is(index, ch)) throw new IllegalTokenException();
          index++;
        }
        return new Token(TokenType.Text, null);
      }
      if (query.is(index, '(')) {
        index++;
        return new Token(TokenType.LeftParenthesis, null);
      }
      if (query.is(index, ')')) {
        index++;
        return new Token(TokenType.RightParenthesis, null);
      }
    }

    return new Token(TokenType.End, null);
  }
}
