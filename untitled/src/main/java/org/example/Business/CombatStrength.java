package org.example.Business;

public class CombatStrength {
    private int health;
    private int minAttack;
    private int maxAttack;
    private int defense;
    private int speed;

    public CombatStrength(int health, int minAttack, int maxAttack, int defense, int speed) {
        this.health = health;
        this.minAttack = minAttack;
        this.maxAttack = maxAttack;
        this.defense = defense;
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }

    public int getMinAttack() {
        return minAttack;
    }

    public int getMaxAttack() {
        return maxAttack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }
}
