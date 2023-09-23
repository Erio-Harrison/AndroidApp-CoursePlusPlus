package com.example.couseplusplus.model.user;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;

public interface UserRepository {
  void login(User user, OnCompleteListener<AuthResult> onCompleteListener);
  void logout(User user);
  void register(User user, OnCompleteListener<AuthResult> onCompleteListener);
  User getCurrentUser();



}
