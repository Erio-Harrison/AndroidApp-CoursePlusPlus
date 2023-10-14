package com.example.couseplusplus.view.comment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.couseplusplus.R;
import com.example.couseplusplus.model.comment.Comment;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
  private List<Comment> commentList;

  public CommentAdapter(List<Comment> commentList) {
    this.commentList = commentList;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext())
            .inflate(R.layout.comment_recycle_view, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Comment commentItem = commentList.get(position);
    holder.commentCodeTextView.setText(commentItem.enrolKey());
    holder.commentNameTextView.setText(commentItem.text());
    holder.commentHelpfulnessTextView.setText(Integer.toString(commentItem.helpfulness()));
    holder.commentDateTextView.setText(commentItem.formattedDateString());
  }

  @Override
  public int getItemCount() {
    return commentList.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    TextView commentCodeTextView;
    TextView commentNameTextView;
    TextView commentHelpfulnessTextView;
    TextView commentDateTextView;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      commentCodeTextView = itemView.findViewById(R.id.sem);
      commentNameTextView = itemView.findViewById(R.id.text);
      commentHelpfulnessTextView = itemView.findViewById(R.id.helpfulness);
      commentDateTextView = itemView.findViewById(R.id.date);
    }
  }
}
