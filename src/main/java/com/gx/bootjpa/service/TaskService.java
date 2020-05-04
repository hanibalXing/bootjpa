package com.gx.bootjpa.service;

public interface TaskService {
    void start(String taskId);
    void pause(String taskId);
}
