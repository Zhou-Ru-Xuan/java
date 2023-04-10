package com.zhouruxuan.jvm.method.symbolicreference.nonIface;

import com.zhouruxuan.jvm.method.symbolicreference.iface.RentChild;

public class Host implements RentChild {

  @Override
  public void say() {
    System.out.println("说两句话");
  }

  @Override
  public void rent() {
    System.out.println("child rent");
  }
}
