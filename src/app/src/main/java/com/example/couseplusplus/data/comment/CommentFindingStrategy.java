package com.example.couseplusplus.data.comment;

import com.example.couseplusplus.model.comment.Comment;
import com.example.couseplusplus.model.query.TerminalNode;
import com.example.couseplusplus.model.query.TokenType;
import java.util.List;

/**
 * @author Yuki Misumi (u7582380)
 */
public interface CommentFindingStrategy {
  List<Comment> find(TokenType operatorType, TerminalNode terminalNode, CommentCache commentCache);
}
