package com.gx.bootjpa.service.impl;

import com.gx.bootjpa.model.CallTaskExecutionState;
import com.gx.bootjpa.model.CallTaskState;
import com.gx.bootjpa.service.CallTaskStateUpdater;
import com.gx.bootjpa.service.CallTaskStatesTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CallTaskStateUpdaterImpl implements CallTaskStateUpdater {
    private final static Logger logger = LoggerFactory.getLogger(CallTaskStateUpdaterImpl.class);
    @Autowired
    CallTaskStatesTable callTaskStatesTable;
    @Override
    public void updateCallTaskState(String id, CallTaskState newState) {
        Function<CallTaskExecutionState,CallTaskExecutionState> stateUpdater=state ->{
            CallTaskState previousState=state.getCallTaskState();
            logger.info("updating call task state:{} -> {}",previousState,newState);
            if (newState!=previousState) {
                switch (newState) {
                    case IN_PROGRESS:
                        state.setExecutingDialerId("gx");
                        break;
                    case PAUSED:
                        state.setExecutingDialerId(null);
                        break;
                    case CANCELED:
                    case COMPLETED:
                    case ERROR:
                        state.setExecutingDialerId(null);
                        state.setExecutionStage(CallTaskExecutionState.ExecutionStage.INITIAL);
                        break;
                    default:
                }
                state.setCallTaskState(newState);
            }
            return state;
        };
        callTaskStatesTable.lockToUpdate(id,stateUpdater);
    }

    @Override
    public void updateCallTaskStage(String id, CallTaskExecutionState.ExecutionStage newStage) {
        callTaskStatesTable.lockToUpdate(id,state ->{
            logger.info("updating call task execution stage:{}->{}",state.getExecutionStage(),newStage);
            state.setExecutionStage(newStage);
            return state;
        });
    }
}
