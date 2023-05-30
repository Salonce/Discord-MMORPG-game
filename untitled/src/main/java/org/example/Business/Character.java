package org.example.Business;

import discord4j.common.util.Snowflake;
import org.example.*;

import java.util.HashMap;


public class Character implements Fighter {
    public Character(){
        this.inventory = new Inventory();
        this.actionPoints = new ActionPoints();
        this.equipment = new Equipment();
        this.stats = new Stats();
    }

    private Stats stats;
    public Stats getStats(){return stats;}

    private Equipment equipment;
    public Equipment getEquipment() {return equipment;}

    private Inventory inventory;
    public Inventory getInventory(){return inventory;}

    private ActionPoints actionPoints;
    public ActionPoints getActionPoints() {return actionPoints;}

    public CombatStrength getCombatStrength(){
        return new CombatStrength(stats.getHealth().get(), equipment.getTotalMinAttack(), equipment.getTotalMaxAttack(), equipment.getTotalDefence(), stats.getSpeed());
    }

    public Health getHealth(){
        return stats.getHealth();
    }

    @Override
    public Properties getProperties() {
        return null;
    }

    @Override
    public Weaknesses getWeaknesses() {
        return null;
    }
}


class Stats{
    public Stats(){
        this.speed = 1;
        this.health = new Health();
    }

    //attributes
    private int speed;
    private Health health;

    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Health getHealth(){
        return this.health;
    }

}




