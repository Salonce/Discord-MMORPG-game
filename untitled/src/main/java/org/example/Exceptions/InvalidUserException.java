package org.example.Exceptions;

class InvalidUserException extends Exception{
    public InvalidUserException(String message){
        super(message);
    }
}