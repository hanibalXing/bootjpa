package com.gx.bootjpa.service;

import com.gx.bootjpa.model.CallTaskExecutionState;

import java.util.function.Consumer;
import java.util.function.Function;

public interface CallTaskStatesTable {
    void lock(String id, Consumer<CallTaskExecutionState> stateConsumer);
    void lockToUpdate(String id, Function<CallTaskExecutionState,CallTaskExecutionState> stateUpdater);
}
