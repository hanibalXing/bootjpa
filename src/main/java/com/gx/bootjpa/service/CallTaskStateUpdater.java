package com.gx.bootjpa.service;

import com.gx.bootjpa.model.CallTaskExecutionState;
import com.gx.bootjpa.model.CallTaskState;

public interface CallTaskStateUpdater {
    void updateCallTaskState(String id, CallTaskState state);
    void updateCallTaskStage(String id, CallTaskExecutionState.ExecutionStage newStage);
}
