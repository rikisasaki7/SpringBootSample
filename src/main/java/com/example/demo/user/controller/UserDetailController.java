package com.example.demo.user.controller;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.user.domain.model.MUser;
import com.example.demo.user.domain.service.UserService;
import com.example.demo.user.form.UserDetailForm;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user/")
@RequiredArgsConstructor
public class UserDetailController {

  private final UserService userService;
  private final ModelMapper modelMapper;

  @GetMapping("detail/{userId}")
  public String getUser(
      UserDetailForm form,
      Model model,
      @PathVariable("userId") String userId) {

    // ユーザーを１件取得
    MUser user = userService.getUser(userId);
    user.setPassword(null);

    // MUserをformに変換
    form = modelMapper.map(user, UserDetailForm.class);

    // Modelに登録
    model.addAttribute("userDetailForm", form);

    return "user/detail";
  }

  @PostMapping(value = "detail", params = "update")
  public String updateUser(UserDetailForm form,
      Model model) {
    userService.updateUserOne(form.getUserId(), form.getPassword(), form.getUserName());
    return "redirect:/user/list";
  }

  @PostMapping(value = "detail", params = "delete")
  public String deleteUser(UserDetailForm form,
      Model model) {
    userService.deleteUserOne(form.getUserId());
    return "redirect:/user/list";
  }
}
