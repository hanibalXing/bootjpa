package com.gx.bootjpa.model;

public enum CallTaskState {
    NOT_START(0),
    IN_PROGRESS(1),
    COMPLETED(2),
    CANCELED(3),
    ERROR(4),
    PAUSED(5);
    private int code;
    CallTaskState(int i) {
        this.code=i;
    }
}
