package com.example.demo.user.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.user.domain.model.MUser;

public interface UserService {
  /** ユーザー登録 */
  public void signup(MUser user);

  /** ユーザー取得 */
  public Page<MUser> getUsers(MUser user, Pageable pageable);

  /** ユーザー取得（１件） */
  public MUser getUser(String userId);

  /** ユーザー更新（１件） */
  public void updateUserOne(String userId, String password, String userName);

  /** ユーザー削除（１件） */
  public void deleteUserOne(String userId);
}
