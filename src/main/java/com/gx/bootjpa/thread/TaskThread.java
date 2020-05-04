package com.gx.bootjpa.thread;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Scope("prototype")
public class TaskThread implements Runnable {
    private static final Logger logger= LoggerFactory.getLogger(TaskThread.class);
    private String id;
    @Autowired
    private RedissonClient redissonClient;

    @Override
    public void run() {
        logger.info("thread start");
        RLock threadlock = redissonClient.getReadWriteLock("threadlock").writeLock();
        boolean b = threadlock.tryLock();
        if (!b){
            logger.info("unable to acquire task lock,not start");
        }
        try {
            logger.info("running");
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.info("",e);
        } catch (Exception e) {
            logger.error("error");
        } finally {
            threadlock.unlock();
            logger.info("success release task lock");
        }
    }

    public TaskThread init(String id) {
        this.id=id;
        return this;
    }
}
