package org.example;

import discord4j.common.util.Snowflake;

import java.sql.*;
import java.time.Instant;
import java.util.HashMap;


class Character implements Fighter{
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

class Health{
    public static int DEFAULT_HP_REGEN = 3;
    public static int DEFAULT_MAX_HEALTH = 100;

    private int health;
    private int regenTime = DEFAULT_HP_REGEN;
    public int getRegen(){
        return regenTime;
    }

    public Health(){
        this.health = DEFAULT_MAX_HEALTH;
        this.nextCd = null;
    }
    private Instant nextCd;

    private int maxHealth = DEFAULT_MAX_HEALTH;
    public int getMax() {
        return maxHealth;
    }

    public int get(){
        System.out.println("get HP");
        cleanAllInstants();
        System.out.println("after get HP");
        return this.health;
    }
    public void set(int health) {
        cleanAllInstants();
        this.health = health;
        maybeSetNewRegenTime();
        clearIfFullHp();
    }

    public void setMax(int maxHealth) {
        this.maxHealth = health;
    }

    public void setRegenTime(int regenTime) {
        this.regenTime = regenTime;
    }

    private void addHp(){
        if (this.health < this.maxHealth)
            this.health += 1;
    }

    private void moveRegenTimeToNext(){
        this.nextCd = nextCd.plusSeconds(regenTime);
    }

    private void maybeSetNewRegenTime(){
        if (health < maxHealth && nextCd == null);
            this.nextCd = Instant.now().plusSeconds(regenTime);
    }
    private void clearIfFullHp(){
        if (health >= maxHealth){
            nextCd = null;
        }
    }

    private boolean regenReady(){
        if (nextCd != null && (Instant.now().compareTo(nextCd)) >= 0)
            return true;
        else
            return false;
    }

    //return true if nextCd is ready for another operation
    private void cleanOneInstant(){
        if (nextCd != null){
            addHp();
            if (health < maxHealth)
                moveRegenTimeToNext();
            else
                nextCd = null;
        }
    }

    private void cleanAllInstants(){
        while (regenReady()){
            //nextCd can't be null
            cleanOneInstant();
        }
    }
}

class CharacterManager {
    private final HashMap<Snowflake, Character> usersCharacters;

    public HashMap<Snowflake, Character> getUsersCharacters() {
        return usersCharacters;
    }

    private void createCharacter(Snowflake id){
        usersCharacters.put(id, new Character());
    }

    public void createCharAndPutInDb(Snowflake id){
        if (!usersCharacters.containsKey(id)){
            createCharacter(id);
            Model.addAllToUserDb(id);
        }
    }

    public CharacterManager(String dbLoad) {
        usersCharacters = new HashMap<>();
    }


    public Character getCharacterById(Snowflake id) {
        return usersCharacters.get(id);
    }
}




