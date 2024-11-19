package com.example.TaskManagement.Controller;

import com.example.TaskManagement.Entity.Task;
import com.example.TaskManagement.Pojo.AddTaskPojo;
import com.example.TaskManagement.RequestDto.AddTaskDto;
import com.example.TaskManagement.Service.ServiceInterface.TaskServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskServiceInterface taskSerivce;

    private static final ModelMapper modelMapper = new ModelMapper();


    @GetMapping("/home")
    public String home(){
        return "Welcom3";
    }
    @PostMapping
    public ResponseEntity addTask(@RequestBody AddTaskPojo addTaskReq){


     AddTaskDto addTaskDto= modelMapper.map(addTaskReq, AddTaskDto.class);  //convert pojo to dto

        String res= null;
        try {
            res = taskSerivce.addTask(addTaskDto);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_GATEWAY);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity getTask(@PathVariable int id) {
        try {
          Task task = taskSerivce.getTask(id);
          return new ResponseEntity<>(task, HttpStatus.OK);
        }
        catch (Exception e){
          return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping
    public ResponseEntity getTaskAll() {

            List<Task> tasks = taskSerivce.getTaskAll();
            return new ResponseEntity<>(tasks, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletTask(@PathVariable int id) {
        try {
            String res = taskSerivce.deletTask(id);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


    @PatchMapping("/{id}/complete")
    public ResponseEntity completeTask(@PathVariable int id) {
        try {
            Task task= taskSerivce.completeTask(id);
            return new ResponseEntity<>(task, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity updateTask(@PathVariable int id ,@RequestBody AddTaskDto addTaskReq){

        try {
            Task task= taskSerivce.updateTask(id,addTaskReq);
            return new ResponseEntity<>(task, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }

}
