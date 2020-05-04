package com.gx.bootjpa.service;

public interface DialerStorageService {
    CallTaskExecutingLock getCallTaskExecutionLock(String id);
}
