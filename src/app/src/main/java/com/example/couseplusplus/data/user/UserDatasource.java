package com.example.couseplusplus.data.user;

import com.example.couseplusplus.model.user.AuthenticationResult;
import com.example.couseplusplus.model.user.User;
import com.example.couseplusplus.model.user.UserRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.Objects;
import java.util.function.Consumer;

public class UserDatasource implements UserRepository {

  @Override
  public void login(User user, Consumer<AuthenticationResult> onCompleteListener) {
    FirebaseAuth mAuth;
    mAuth = FirebaseAuth.getInstance();
    mAuth
        .signInWithEmailAndPassword(user.userName(), user.password())
        .addOnCompleteListener(
            task -> {
              var result = new AuthenticationResult(task.isSuccessful(), user);
              onCompleteListener.accept(result);
            });
  }

  @Override
  public void logout() {
    FirebaseAuth.getInstance().signOut();
  }

  @Override
  public void register(User user, Consumer<AuthenticationResult> onCompleteListener) {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    mAuth
        .createUserWithEmailAndPassword(user.userName(), user.password())
        .addOnCompleteListener(
            task -> {
              var result = new AuthenticationResult(task.isSuccessful(), user);
              onCompleteListener.accept(result);
            });
  }

  @Override
  public User getCurrentUser() {
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    if (Objects.isNull(firebaseUser)) return null;
    return new User(firebaseUser.getEmail(), null);
  }
}
