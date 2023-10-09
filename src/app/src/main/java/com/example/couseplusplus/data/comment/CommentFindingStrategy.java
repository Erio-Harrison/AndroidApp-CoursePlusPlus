package com.example.couseplusplus.data.comment;

import com.example.couseplusplus.model.comment.NewComment;
import com.example.couseplusplus.model.query.parser.TerminalNode;
import com.example.couseplusplus.model.query.tokenizer.TokenType;
import java.util.List;

/**
 * @author Yuki Misumi (u7582380)
 */
public interface CommentFindingStrategy {
  List<NewComment> find(
      TokenType operatorType, TerminalNode terminalNode, CommentCache commentCache);
}
