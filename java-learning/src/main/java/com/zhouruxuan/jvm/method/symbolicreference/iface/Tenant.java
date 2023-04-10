package com.zhouruxuan.jvm.method.symbolicreference.iface;

import com.zhouruxuan.jvm.method.symbolicreference.nonIface.Host;

public class Tenant {

    public void rent(RentChild rent) {
        rent.rent();
        System.out.println("花了我一大笔钱");
        System.out.println(rent.toString());
    }

    public static void main(String[] args) {
        Tenant tenant = new Tenant();
        RentChild rentChild = new Host();
        tenant.rent(rentChild);
    }
}