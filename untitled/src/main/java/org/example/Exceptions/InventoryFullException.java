package org.example.Exceptions;

public class InventoryFullException extends Exception{
    public InventoryFullException(String message){
        super(message);
    };
}
