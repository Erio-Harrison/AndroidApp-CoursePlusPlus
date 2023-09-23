package com.example.couseplusplus.data.user;

import com.example.couseplusplus.model.user.User;
import com.example.couseplusplus.model.user.UserRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.Objects;

public class UserDatasource implements UserRepository {

  @Override
  public void login(User user, OnCompleteListener<AuthResult> onCompleteListener) {
    FirebaseAuth mAuth;
    mAuth = FirebaseAuth.getInstance();
    mAuth
        .signInWithEmailAndPassword(user.userName(), user.password())
        .addOnCompleteListener(onCompleteListener);
  }

  @Override
  public void logout(User user) {
    FirebaseAuth.getInstance().signOut();
  }

  @Override
  public void register(User user, OnCompleteListener<AuthResult> onCompleteListener) {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    mAuth
        .createUserWithEmailAndPassword(user.userName(), user.password())
        .addOnCompleteListener(onCompleteListener);
  }

  @Override
  public User getCurrentUser() {
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    if (Objects.isNull(firebaseUser)) return null;
    return new User(firebaseUser.getEmail(), null);
  }
}
