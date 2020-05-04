package com.gx.bootjpa;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;

public class Locked implements Closeable {
    private static final Logger logger= LoggerFactory.getLogger(Locked.class);
    public enum LockType {
        eReed,
        eWrite
    }
    final String lockName;
    final RLock lock;
    final RedissonClient redisson;

    public Locked(String lockName, LockType lockType, RedissonClient redisson) {
        this.lockName = lockName;
        this.redisson = redisson;
        switch (lockType) {
            case eWrite:
                lock=redisson.getReadWriteLock(lockName).writeLock();
                break;
            default:
                lock=redisson.getReadWriteLock(lockName).writeLock();
                break;
        }
        logger.info("lock start,key:{},Thread:{};",lockName,Thread.currentThread().getId());
        lock.lock();
        logger.info("lock end,key:{},Thread:{};",lockName,Thread.currentThread().getId());
    }

    @Override
    public void close() throws IOException {
        logger.info("unlock start,key:{},Thread:{};",lockName,Thread.currentThread().getId());
        lock.unlock();
        logger.info("unlock end,key:{},Thread:{};",lockName,Thread.currentThread().getId());

    }
}
