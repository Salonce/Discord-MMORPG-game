package org.example.Business;

import java.time.Instant;

public class Health{
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