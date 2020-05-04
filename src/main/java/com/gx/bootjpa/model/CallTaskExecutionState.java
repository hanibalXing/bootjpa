package com.gx.bootjpa.model;

public class CallTaskExecutionState {

    public enum ExecutionStage {
        INITIAL,
        PARSING_CSV,
        HANDING;
    }
    private String id;
    private ExecutionStage executionStage=ExecutionStage.INITIAL;
    private String executingDialerId;

    public CallTaskExecutionState(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ExecutionStage getExecutionStage() {
        return executionStage;
    }

    public void setExecutionStage(ExecutionStage executionStage) {
        this.executionStage = executionStage;
    }

    public String getExecutingDialerId() {
        return executingDialerId;
    }

    public void setExecutingDialerId(String executingDialerId) {
        this.executingDialerId = executingDialerId;
    }

    public boolean isExecuting(){
        return executingDialerId!=null;
    }
}
