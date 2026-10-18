package com.example.demo.user.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.user.domain.model.LoginUser;
import com.example.demo.user.domain.model.MUser;
import com.example.demo.user.domain.service.UserService;
import com.example.demo.user.form.UserListForm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
@SessionAttributes(types = UserListForm.class)
@Slf4j
public class UserListController {

  // セッションに登録
  @ModelAttribute("userListForm")
  private UserListForm setUserListForm() {
    return new UserListForm();
  }

  private final UserService userService;
  private final ModelMapper modelMapper;

  /** ユーザー一覧画面を表示 */
  @GetMapping("/list")
  public String getUserList(
      Model model,
      @ModelAttribute UserListForm userListForm,
      @PageableDefault(page = 0, size = 2) Pageable pageable,
      @AuthenticationPrincipal LoginUser loginUser) {

    log.info("ユーザーID: {}, ユーザー名: {}", loginUser.getUsername(), loginUser.getDisplayUserName());

    // formをMUserクラスに変換
    MUser user = modelMapper.map(userListForm, MUser.class);
    // ユーザー一覧取得
    Page<MUser> userPage = userService.getUsers(user, pageable);
    model.addAttribute("userList", userPage.getContent());
    model.addAttribute("page", userPage);

    // ユーザー一覧画面へ遷移
    return "user/list";
  }
}
