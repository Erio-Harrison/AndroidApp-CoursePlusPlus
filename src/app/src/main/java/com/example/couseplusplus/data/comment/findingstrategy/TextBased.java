package com.example.couseplusplus.data.comment.findingstrategy;

import com.example.couseplusplus.data.comment.CommentCache;
import com.example.couseplusplus.model.comment.NewComment;
import com.example.couseplusplus.model.query.parser.TerminalNode;
import com.example.couseplusplus.model.query.parser.TextNode;
import com.example.couseplusplus.model.query.tokenizer.TokenType;
import java.util.List;

/**
 * @author Yuki Misumi (u7582380)
 */
public class TextBased implements CommentFindingStrategy {
  @Override
  public List<NewComment> find(
      TokenType operatorType, TerminalNode terminalNode, CommentCache commentCache) {
    TextNode textNode = (TextNode) terminalNode;
    String key = textNode.text();
    if (operatorType == TokenType.Like)
      return commentCache
          .textTree()
          .collectEqualOrMoreThan(
              key,
              (key1, key2) -> {
                if (key1.equals(key2)) return 0;
                return key2.contains(key1) ? -1 : 1;
              });
    throw new UnsupportedOperationException(operatorType);
  }
}
