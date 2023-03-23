package com.zhouruxuan.behavioral.iterator.demo1.iterators;

import com.zhouruxuan.behavioral.iterator.demo1.profile.Profile;

public interface ProfileIterator {
    boolean hasNext();

    Profile getNext();

    void reset();
}