package org.example.Business;

public class Result {
    Result(int health, boolean victory) {
        this.health = health;
        this.victory = victory;
    }

    private int health;

    public int getHealth() {
        return health;
    }

    private boolean victory;

    public boolean isVictory() {
        return victory;
    }
}
