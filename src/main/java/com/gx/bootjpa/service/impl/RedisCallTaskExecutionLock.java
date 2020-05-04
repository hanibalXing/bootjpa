package com.gx.bootjpa.service.impl;

import com.gx.bootjpa.service.CallTaskExecutingLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

public class RedisCallTaskExecutionLock implements CallTaskExecutingLock {
    private final RedissonClient redissonClient;
    private final String name;

    @Override
    public boolean tryLock() {
        return getRedisLock().tryLock();
    }

     RedisCallTaskExecutionLock(RedissonClient redissonClient, String name) {
        this.redissonClient = redissonClient;
        this.name = name;
    }

    private RLock getRedisLock(){
        return redissonClient.getReadWriteLock(name).writeLock();
    }
}
