package com.example.couseplusplus.data.comment;

import com.example.couseplusplus.model.comment.Comment;
import com.example.couseplusplus.model.query.TerminalNode;
import com.example.couseplusplus.model.query.TextNode;
import com.example.couseplusplus.model.query.TokenType;
import java.util.List;

public class TextBased implements CommentFindingStrategy {
  @Override
  public List<Comment> find(
      TokenType operatorType, TerminalNode terminalNode, CommentCache commentCache) {
    TextNode textNode = (TextNode) terminalNode;
    String key = textNode.text();
    if (operatorType == TokenType.Like)
      return commentCache.textTree.collectEqualOrMoreThan(
          key,
          (key1, key2) -> {
            if (key1.equals(key2)) return 0;
            return key2.contains(key1) ? -1 : 1;
          });
    throw new UnsupportedOperationException(operatorType);
  }
}
