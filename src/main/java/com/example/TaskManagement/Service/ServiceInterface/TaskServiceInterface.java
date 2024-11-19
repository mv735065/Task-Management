package com.example.TaskManagement.Service.ServiceInterface;

import com.example.TaskManagement.Entity.Task;
import com.example.TaskManagement.RequestDto.AddTaskDto;

import java.util.List;

public interface TaskServiceInterface {
    String addTask(AddTaskDto addTaskReq) throws Exception;
    Task getTask(int id) throws Exception;
    List<Task> getTaskAll();
    String deletTask(int id) throws Exception;
    Task completeTask(int id) throws Exception;
    Task updateTask(int id, AddTaskDto addTaskReq) throws Exception;
}