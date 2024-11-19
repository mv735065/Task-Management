package com.example.TaskManagement.Service.ServiceImp;


import com.example.TaskManagement.CustomException.DueDateInvalidException;
import com.example.TaskManagement.CustomException.InvalidTaskException;
import com.example.TaskManagement.CustomException.TitleCannotbeEmptyException;
import com.example.TaskManagement.Entity.Task;
import com.example.TaskManagement.Enums.Status;
import com.example.TaskManagement.Repository.TaskRepository;
import com.example.TaskManagement.RequestDto.AddTaskDto;
import com.example.TaskManagement.Service.ServiceInterface.TaskServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskSerivceImp implements TaskServiceInterface{

    @Autowired
    TaskRepository taskRepository;
    public String addTask(AddTaskDto addTaskReq) throws Exception{

        if (addTaskReq.getTitle() == null || addTaskReq.getTitle().isEmpty()) {
            throw new TitleCannotbeEmptyException("Title cannot be null or empty");
        }

         if (addTaskReq.getDue_date() != null && addTaskReq.getDue_date().isBefore(LocalDate.now())) {
            throw new DueDateInvalidException("Due date cannot be in the past or empty");
        }


        Status status = addTaskReq.getStatus();
        if (status == null) status=Status.PENDING;

        LocalDateTime  created=LocalDateTime.now();
        LocalDateTime  updated=LocalDateTime.now();



        Task task=Task.builder()
             .title(addTaskReq.getTitle())
             .description(addTaskReq.getDescription())
             .created_at(created)
             .due_date(addTaskReq.getDue_date())
             .updated_at(updated)
             .status(status)
             .build();

     task=taskRepository.save(task);

        return "Task added succesfully with id: "+task.getId();
    }

    public Task getTask(int id) throws Exception{
        Optional<Task> data=taskRepository.findById(id);
        if(!data.isPresent())  throw new InvalidTaskException("With given Id , No task is Exist");
        Task task=data.get();

        return task;
    }

    public List<Task> getTaskAll() {

        List<Task> tasks=taskRepository.findAll();

        return tasks;

    }

    public String deletTask(int id) throws Exception{
        Optional<Task> data=taskRepository.findById(id);
        if(!data.isPresent())  throw new InvalidTaskException("With given Id , No task is Exist");
        taskRepository.deleteById(id);
       return "SuccessFully Task is deleted of id "+id;
    }

    public Task completeTask(int id) throws Exception{
        Optional<Task> data=taskRepository.findById(id);
        if(data.isEmpty()) throw new InvalidTaskException("With given Id , No task is Exist");
        Task task=data.get();
        task.setStatus(Status.COMPLETED);

        task=taskRepository.save(task);
        return task;
    }

    public Task updateTask(int id,AddTaskDto addTaskReq) throws Exception{
        Optional<Task> data=taskRepository.findById(id);
        if(data.isEmpty()) throw new InvalidTaskException("With given Id , No task is Exist");

        Task task=data.get();
        task.setTitle(addTaskReq.getTitle());
        task.setStatus(addTaskReq.getStatus());
        task.setUpdated_at(LocalDateTime.now());
        task.setDescription(addTaskReq.getDescription());
        task.setDue_date(addTaskReq.getDue_date());
        task=taskRepository.save(task);

        return task;
    }
}
