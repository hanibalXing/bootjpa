package com.gx.bootjpa.service.impl;

import com.gx.bootjpa.service.CallTaskExecutingLock;
import com.gx.bootjpa.service.DialerStorageService;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DialerStorageServiceImpl implements DialerStorageService {
    private static final String PERFIX="dm-calltaskexec:";
    @Autowired
    RedissonClient redissonClient;

    @Override
    public CallTaskExecutingLock getCallTaskExecutionLock(String id) {
        String name=PERFIX+id;
        return new RedisCallTaskExecutionLock(redissonClient,name);
    }
}
