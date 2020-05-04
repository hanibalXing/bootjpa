package com.gx.bootjpa.service.impl;

import com.gx.bootjpa.service.CallTaskExecutionFactory;
import com.gx.bootjpa.thread.TaskThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class CallTaskExecutionFactoryImpl implements CallTaskExecutionFactory {
    @Autowired
    ApplicationContext applicationContext;
    @Override
    public TaskThread newExecution(String id) {
        TaskThread taskThread=applicationContext.getBean(TaskThread.class);
        return taskThread.init(id);
    }
}
