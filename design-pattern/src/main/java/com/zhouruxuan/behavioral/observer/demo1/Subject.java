package com.zhouruxuan.behavioral.observer.demo1;

public interface Subject {
  void registerObserver(Observer observer);
  void removeObserver(Observer observer);
  void notifyObservers(Message message);
}






