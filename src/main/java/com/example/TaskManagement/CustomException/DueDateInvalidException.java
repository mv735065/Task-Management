package com.example.TaskManagement.CustomException;

public class DueDateInvalidException extends Exception{
    public  DueDateInvalidException(String message){
        super(message);
    }
}
