package org.example.Business;

public enum Wearable{
    HEAD ("HEAD"),
    TORSO ("TORSO"),
    LEGS ("LEGS"),
    FEET ("FEET"),
    HANDS ("HANDS"),
    FIRSTHAND ("FIRSTHAND"),
    SECONDHAND ("SECONDHAND"),
    NOTHING ("NOTHING");

    private final String where;
    Wearable(String where){
        this.where = where;
    }

    public boolean isEq(){
        return false;
    }
    public boolean isHeadEq(){
        return false;
    }
    public boolean isTorsoEq(){
        return false;
    }
    public boolean isLegsEq(){
        return false;
    }
    public boolean isFeetEq(){
        return false;
    }
    public boolean isHandsEq(){
        return false;
    }
    public boolean isFirstHandEq(){
        return false;
    }
    public boolean isSecondHandEq(){
        return false;
    }
}