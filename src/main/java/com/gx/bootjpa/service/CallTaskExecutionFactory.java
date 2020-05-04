package com.gx.bootjpa.service;

import com.gx.bootjpa.thread.TaskThread;

public interface CallTaskExecutionFactory {
    TaskThread newExecution(String id);
}
