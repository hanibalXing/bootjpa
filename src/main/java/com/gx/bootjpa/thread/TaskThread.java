package com.gx.bootjpa.thread;

import com.gx.bootjpa.model.CallTaskExecutionState;
import com.gx.bootjpa.model.CallTaskState;
import com.gx.bootjpa.service.CallTaskStateUpdater;
import com.gx.bootjpa.service.CallTaskStatesTable;
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
    @Autowired
    private CallTaskStateUpdater callTaskStateUpdater;
    @Autowired
    CallTaskStatesTable callTaskStatesTable;

    @Override
    public void run() {
        RLock threadlock = redissonClient.getReadWriteLock("threadlock").writeLock();
        boolean b = threadlock.tryLock();
        if (!b){
            logger.info("unable to acquire task lock,not start");
        }
        try {
            setStateTo(CallTaskState.IN_PROGRESS);
            CallTaskExecutionState state=callTaskStatesTable.getTaskState(id).orElseThrow(()->new IllegalArgumentException("xxx"));
            logger.info("call task excution starts from {} stage [calltaskId={}]",state);
            switch (state.getExecutionStage()) {
                case INITIAL:
                case PARSING_CSV:
                    setStageTo(CallTaskExecutionState.ExecutionStage.PARSING_CSV);
                    TimeUnit.SECONDS.sleep(100);
                case HANDING:
                    setStageTo(CallTaskExecutionState.ExecutionStage.HANDING);
                    TimeUnit.SECONDS.sleep(100);
                default:
            }
            logger.info("completed");
            setStateTo(CallTaskState.COMPLETED);

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

    private void setStateTo(CallTaskState newState){
        callTaskStateUpdater.updateCallTaskState(id,newState);
    }

    private void setStageTo(CallTaskExecutionState.ExecutionStage newStage){
        callTaskStateUpdater.updateCallTaskStage(id,newStage);
    }
}
