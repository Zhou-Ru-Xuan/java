package com.zhouruxuan.behavioral.observer.demo2.imp1.imp11;

import com.zhouruxuan.behavioral.observer.demo2.imp2.PromotionService;
import com.zhouruxuan.behavioral.observer.demo2.UserService;

/**
 * 假设我们在开发一个P2P投资理财系统，用户注册成功之后，我们会给用户发放投资体验金
 */
public class UserController {
  private UserService userService; // 依赖注入
  private PromotionService promotionService; // 依赖注入

  public Long register(String telephone, String password) {
    //省略输入参数的校验代码
    //省略userService.register()异常的try-catch代码
    long userId = userService.register(telephone, password);
    promotionService.issueNewUserExperienceCash(userId);
    return userId;
  }
}