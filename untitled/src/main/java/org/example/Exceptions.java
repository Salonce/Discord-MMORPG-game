package org.example;

class NoSuchMonsterException extends Exception{
    public NoSuchMonsterException(){
        super();
    }
}

class InvalidUserException extends Exception{
    public InvalidUserException(String message){
        super(message);
    }
}

class InventoryFullException extends Exception{
    public InventoryFullException(String message){
        super(message);
    };
}

class NoSuchItemInInventoryException extends Exception{
    public NoSuchItemInInventoryException(){
        super();
    }
}

class NoSuchItemInEquipmentException extends Exception{
    public NoSuchItemInEquipmentException(){
        super();
    }
}

class NoSuchCharacterException extends Exception{
    public NoSuchCharacterException(){
        super();
    }
}

class NotEnoughActionPointsException extends Exception{
    public NotEnoughActionPointsException(String message){
        super(message);
    }
}

class NotWearableItemException extends Exception{
    public NotWearableItemException(){
        super();
    }
}

class GhostItemException extends Exception{
    public GhostItemException(){
        super();
    }
}