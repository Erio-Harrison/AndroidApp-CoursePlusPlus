package com.example.couseplusplus.simulation;

import android.util.Log;
import com.example.couseplusplus.model.comment.Comment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.List;

public class ReadCommentsHandler implements Observer {
    @Override
    public void on(ActionType actionType, List<String> arguments) {
        if (actionType != ActionType.ReadComments) return;

        String courseID = arguments.get(0);
        Log.i("Simulation", "Initiating read for comments for course: " + courseID);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference commentsRef = mDatabase.child("comment").child(courseID);

        commentsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot commentSnapshot : dataSnapshot.getChildren()) {
                    Comment comment = commentSnapshot.getValue(Comment.class);
                    if (comment != null) {
                        Log.i("Simulation", comment.getId()+":"+comment.getText()+comment.getYear()
                        +comment.getSemester()+" "+comment.getPostedDateTime()+" "+"helpfulness: "+comment.getHelpfulness());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("Simulation", "Failed to read comments.", error.toException());
            }
        });
    }
}