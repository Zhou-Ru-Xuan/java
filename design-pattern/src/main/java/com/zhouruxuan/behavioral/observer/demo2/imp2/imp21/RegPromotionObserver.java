package com.zhouruxuan.behavioral.observer.demo2.imp2.imp21;

import com.zhouruxuan.behavioral.observer.demo2.RegObserver;
import com.zhouruxuan.behavioral.observer.demo2.imp2.PromotionService;

public class RegPromotionObserver implements RegObserver {
  private PromotionService promotionService; // 依赖注入

  @Override
  public void handleRegSuccess(long userId) {
    promotionService.issueNewUserExperienceCash(userId);
  }
}