package com.gx.bootjpa.service.impl;

import com.gx.bootjpa.Locked;
import com.gx.bootjpa.model.CallTaskExecutionState;
import com.gx.bootjpa.service.CallTaskStatesTable;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;

@Service
public class RedisCallTaskStatesTable implements CallTaskStatesTable {
    @Autowired
    private RedissonClient redissonClient;

    @Override
    public void lock(String id, Consumer<CallTaskExecutionState> stateConsumer) {
        lockToUpdate(id,s->{
            stateConsumer.accept(s);
            return null;
        });
    }

    @Override
    public void lockToUpdate(String id, Function<CallTaskExecutionState, CallTaskExecutionState> stateUpdater) {
        try(Locked locked=new Locked("callTaskWithState"+"_"+id, Locked.LockType.eWrite,redissonClient)) {
            RMap<String,CallTaskExecutionState> map=redissonClient.getMap("callTaskWithState");
            CallTaskExecutionState state=map.getOrDefault(id,new CallTaskExecutionState(id));
            CallTaskExecutionState newState=stateUpdater.apply(state);
            if(newState!=null) {
                map.put(id,newState);
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
