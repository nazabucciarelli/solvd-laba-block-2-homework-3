package com.solvd.animals_mvc.model.exception;

public class NegativeValueException extends Exception{

    public NegativeValueException(String msg){
        super(msg);
    }
}
