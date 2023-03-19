package com.zhouruxuan.behavioral.observer.demo2.imp2;

import com.zhouruxuan.behavioral.observer.demo2.RegObserver;

public class RegNotificationObserver implements RegObserver {
  private NotificationService notificationService;

  @Override
  public void handleRegSuccess(long userId) {
    notificationService.sendInboxMessage(userId, "Welcome...");
  }
}