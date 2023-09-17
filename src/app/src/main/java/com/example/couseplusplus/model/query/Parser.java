package com.example.couseplusplus.model.query;

/**
 * Parses the following grammar <statement> ::= <expression> | <expression> <condition> <statement>
 * <expression> ::= helpful <operator> <number> | enrol <operator> <enroldate> | posted <operator>
 * <date> | text <fuzzy> <string> | ( <statement> )
 */
public class Parser {
  Tokenizer tokenizer;
  Token currentToken;

  public Parser(Tokenizer tokenizer) {
    this.tokenizer = tokenizer;
    currentToken = tokenizer.getNextToken();
  }

  void take(TokenType tokenType) {
    if (currentToken.tokenType() != tokenType) throw new IllegalSyntaxException();
    currentToken = tokenizer.getNextToken();
  }

  void takeComparable() {
    if (!currentToken.tokenType().isComparable()) throw new IllegalSyntaxException();
    currentToken = tokenizer.getNextToken();
  }

  void takeCondition() {
    TokenType tokenType = currentToken.tokenType;
    if (tokenType == TokenType.And) {
      take(TokenType.And);
      return;
    }
    take(TokenType.Or);
  }

  boolean isCondition(Token token) {
    TokenType tokenType = token.tokenType();
    return tokenType == TokenType.And || tokenType == TokenType.Or;
  }

  public Node statement() {
    Node expression = expression();
    if (!tokenizer.hasNext() || !isCondition(currentToken)) return expression;

    Token token = currentToken;
    takeCondition();
    Node statement = statement();
    return new BinaryOperationNode(expression, token, statement);
  }

  public Node expression() {
    TokenType tokenType = currentToken.tokenType();
    if (tokenType == TokenType.Helpful) {
      Node helpful = id();
      Token token = currentToken;
      takeComparable();
      return new BinaryOperationNode(helpful, token, number());
    }
    if (tokenType == TokenType.Enrol) {
      Node enrol = id();
      Token token = currentToken;
      takeComparable();
      return new BinaryOperationNode(enrol, token, enrolDate());
    }
    if (tokenType == TokenType.Posted) {
      Node posted = id();
      Token token = currentToken;
      takeComparable();
      return new BinaryOperationNode(posted, token, date());
    }
    if (tokenType == TokenType.Text) {
      Node text = id();
      Token token = currentToken;
      take(TokenType.Like);
      return new BinaryOperationNode(text, token, textString());
    }
    take(TokenType.LeftParenthesis);
    Node statement = statement();
    take(TokenType.RightParenthesis);
    return statement;
  }

  public Node id() {
    Token token = currentToken;
    TokenType tokenType = token.tokenType();
    if (tokenType == TokenType.Helpful) take(TokenType.Helpful);
    if (tokenType == TokenType.Enrol) take(TokenType.Enrol);
    if (tokenType == TokenType.Posted) take(TokenType.Posted);
    if (tokenType == TokenType.Text) take(TokenType.Text);
    return new IdNode(token);
  }

  public Node number() {
    Token token = currentToken;
    take(TokenType.Integer);
    return new NumberNode(token);
  }

  public Node enrolDate() {
    Token token = currentToken;
    take(TokenType.EnrolDate);
    return new EnrolDateNode(token);
  }

  public Node date() {
    Token token = currentToken;
    take(TokenType.Date);
    return new DateNode(token);
  }

  public Node textString() {
    Token token = currentToken;
    take(TokenType.TextString);
    return new TextNode(token);
  }

  public ParseTree parse() {
    return new ParseTree(statement());
  }
}
