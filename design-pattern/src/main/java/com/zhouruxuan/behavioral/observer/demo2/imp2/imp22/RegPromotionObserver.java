package com.zhouruxuan.behavioral.observer.demo2.imp2.imp22;

import com.zhouruxuan.behavioral.observer.demo2.imp2.PromotionService;
import com.zhouruxuan.behavioral.observer.demo2.RegObserver;

public class RegPromotionObserver implements RegObserver {
  private PromotionService promotionService; // 依赖注入

  @Override
  public void handleRegSuccess(long userId) {
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        promotionService.issueNewUserExperienceCash(userId);
      }
    });
    thread.start();
  }
}