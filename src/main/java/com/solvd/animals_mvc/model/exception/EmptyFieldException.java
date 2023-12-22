package com.solvd.animals_mvc.model.exception;

public class EmptyFieldException extends RuntimeException{

    public EmptyFieldException(String msg){
        super(msg);
    }

}
