package com.zhouruxuan.behavioral.iterator.demo1.socialnetworks;

import com.zhouruxuan.behavioral.iterator.demo1.iterators.ProfileIterator;

public interface SocialNetwork {
    ProfileIterator createFriendsIterator(String profileEmail);

    ProfileIterator createCoworkersIterator(String profileEmail);
}