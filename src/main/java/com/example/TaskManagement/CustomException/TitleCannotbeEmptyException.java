package com.example.TaskManagement.CustomException;

public class TitleCannotbeEmptyException extends Exception{
    public TitleCannotbeEmptyException(String messsage){
        super(messsage);
    }
}
