package com.gx.bootjpa.service.impl;

import com.gx.bootjpa.model.CallTaskExecutionState;
import com.gx.bootjpa.service.CallTaskExecutionFactory;
import com.gx.bootjpa.service.CallTaskStatesTable;
import com.gx.bootjpa.service.TaskService;
import com.gx.bootjpa.thread.TaskThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class TaskServiceImpl implements TaskService {
    private static final Logger logger= LoggerFactory.getLogger(TaskServiceImpl.class);
    private final Map<String,CallTaskExecFuture> callTaskExcutions = new ConcurrentHashMap<>();
    private final ExecutorService callTaskExecutorPoll= Executors.newCachedThreadPool();
    @Autowired
    CallTaskStatesTable callTaskStatesTable;
    @Autowired
    CallTaskExecutionFactory callTaskExecutionFactory;
    static class CallTaskExecFuture {
        private final TaskThread taskThread;
        private final Future<?> future;

        public CallTaskExecFuture(TaskThread taskThread, Future<?> future) {
            this.taskThread = taskThread;
            this.future = future;
        }

        public TaskThread getTaskThread() {
            return taskThread;
        }

        public Future<?> getFuture() {
            return future;
        }
    }

    @Override
    public void start(String taskId) {
        callTaskStatesTable.lock(taskId,state->runTask(taskId,state));
    }

    @Override
    public void pause(String taskId) {
        callTaskStatesTable.lock(taskId,state -> interruptTask(taskId,state));
    }

    private void runTask(String id, CallTaskExecutionState state) {
        logger.info("Trying to start call task in state:{}",state);
        if(state.isExecuting()) {
            logger.error("Unable to start call task {} because it is excuting.",id);
        }
        callTaskExcutions.compute(id,(taskId,taskFuture)->{
           if (taskFuture==null || taskFuture.getFuture().isDone()) {
               TaskThread taskThread = callTaskExecutionFactory.newExecution(id);
               Future<?> future=callTaskExecutorPoll.submit(taskThread);
               return new CallTaskExecFuture(taskThread,future);
           } else {
               logger.warn("call task with id{} has already been started",id);
               return taskFuture;
           }
        });
    }

    private void interruptTask(String callTaskId,CallTaskExecutionState state)
    {
        logger.info("trying to interrupt call task in state:{}",state);
        if(!state.isExecuting()){
            logger.error("Unable to interrupt call task{} because it is NOT excuting",callTaskId);
            return;
        }
        callTaskExcutions.computeIfPresent(callTaskId,(taskId,taskFuture)->{
            if(!taskFuture.getFuture().isDone()) {
                taskFuture.getFuture().cancel(true);
                logger.info("call task execution{} cancelled",taskId);
            } else {
                logger.warn("call task with id{} is NOT executing",taskId);
            }
            return taskFuture;
        });

        callTaskExcutions.remove(callTaskId);
    }
}
