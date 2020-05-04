package com.gx.bootjpa.service;

public interface CallTaskExecutingLock {
    boolean tryLock();
}
