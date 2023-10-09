package com.example.couseplusplus.model.query;

import com.example.couseplusplus.model.query.parser.ParseTree;
import com.example.couseplusplus.model.query.parser.Parser;
import com.example.couseplusplus.model.query.tokenizer.Tokenizer;

/**
 * @author Yuki Misumi (u7582380)
 */
public class QueryParseTreeCreator {
  public static ParseTree create(Query query) {
    Tokenizer tokenizer = new Tokenizer(query);
    Parser parser = new Parser(tokenizer);
    return parser.parse();
  }
}
