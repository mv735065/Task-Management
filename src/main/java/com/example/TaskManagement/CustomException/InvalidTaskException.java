package com.example.TaskManagement.CustomException;

public class InvalidTaskException extends Exception{
    public InvalidTaskException(String message){
        super(message);
    }
}
