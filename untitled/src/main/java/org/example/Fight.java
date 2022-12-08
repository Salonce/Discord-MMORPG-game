package org.example;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;

import java.util.ArrayList;
import java.util.Random;

class CombatStrength{
    private int health;
    private int minAttack;
    private int maxAttack;
    private int defense;
    private int speed;
    public CombatStrength(int health, int minAttack, int maxAttack, int defense, int speed){
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

interface Fighter{
    CombatStrength getCombatStrength();
    Properties getProperties();
    Weaknesses getWeaknesses();
}



class Properties{
    public Properties(WeaponType weaponType){
        this.weaponType = weaponType;
    }

    private WeaponType weaponType;
    public WeaponType getWeaponType(){ return this.weaponType; }
}

class Weaknesses{
    public Weaknesses(WeaponType weaponType){
        this.weaponType = weaponType;
    }
    private WeaponType weaponType;
    public WeaponType getWeaponType(){ return this.weaponType; }
}

//weaknesses...


//instead of plain HP and attributes showing,
//fight can look like: Saloncey dodged 15 times
//Salonce attacked 19 times, for 150hp in total...
//Salonce got hurt 13 times, for 167 in total...

public class Fight {
    public Fight(Fighter fighterA, Fighter fighterB){
        this.fighterA = fighterA;
        this.fighterB = fighterB;
        this.results = new Results(fighterA.getCombatStrength(), fighterB.getCombatStrength());
    }

    public Results getResults() {
        return results;
    }

    private Results results;
    private Fighter fighterA;
    private Fighter fighterB;

    private int powerA;
    private int powerB;
}

class Results{
    public Results(CombatStrength combatStrengthA, CombatStrength combatStrengthB){
        this.combatStrengthA = combatStrengthA;
        this.combatStrengthB = combatStrengthB;
        this.totalPowerA = calculateRandomTotalPower(combatStrengthA);
        this.totalPowerB = calculateRandomTotalPower(combatStrengthB);

        this.setResults(totalPowerA, totalPowerB);
    }

    public Result getResultA(){
        return resultA;
    }
    public Result getResultB(){
        return resultB;
    }

    private CombatStrength combatStrengthA;
    private CombatStrength combatStrengthB;
    private int totalPowerA;
    private int totalPowerB;
    private Result resultA;
    private Result resultB;

    private int calculateRandomTotalPower(CombatStrength combatStrength){
        int minTotalPower = combatStrength.getHealth() * combatStrength.getMinAttack() * (combatStrength.getDefense() + combatStrength.getSpeed());
        int maxTotalPower = combatStrength.getHealth() * combatStrength.getMaxAttack() * (combatStrength.getDefense() + combatStrength.getSpeed());
        int randomTotalPower = minTotalPower;
        if (maxTotalPower > minTotalPower)
            randomTotalPower += new Random().nextInt(maxTotalPower-minTotalPower);
        return randomTotalPower;
    }

    private void setResults (int totalPowerA, int totalPowerB){
        if (totalPowerA > totalPowerB){
            double newHealth = (double) combatStrengthA.getHealth() - ((( (double) totalPowerB) / (double) totalPowerA) * (double) combatStrengthA.getHealth());
            if (newHealth < 1)
                newHealth = 1;
            resultA = new Result((int) Math.round(newHealth), true);
            resultB = new Result(0, false);
        }
        else if (totalPowerB > totalPowerA){
            double newHealth = (double) combatStrengthB.getHealth() - ((((double) totalPowerA)/totalPowerB) * combatStrengthB.getHealth());
            if (newHealth < 1)
                newHealth = 1;
            resultB = new Result((int) Math.round(newHealth), true);
            resultA = new Result(0, false);
        }
        else if (totalPowerA == totalPowerB){
            int OneOrTwo = new Random().nextInt(2);
            if (OneOrTwo == 0) {
                resultA = new Result(1, true);
                resultB = new Result(0, false);
            }
            else{
                resultA = new Result(0, false);
                resultB = new Result(1, true);
            }
        }
    }
}

class Result{
    Result(int health, boolean victory){
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




/*
System.out.println("totalPowerB: " + totalPowerB);
System.out.println("totalPowerA: " + totalPowerA);
System.out.println("A health: " + combatStrengthA.getHealth());
System.out.println("A new health: " + newHealth);
 */
            /*
System.out.println("totalPowerA: " + totalPowerA);
System.out.println("totalPowerB: " + totalPowerB);
System.out.println("B health: " + combatStrengthB.getHealth());
System.out.println("B new health: " + newHealth);
 */

//weather_type //environment conditions

/*

class Chest implements Lootable{
    public ArrayList<Item> loot(){
        ArrayList<Item> newItems = new ArrayList<>();

        Chancer.chanceItem(newItems, ItemManager.STEEL_SWORD, 60);
        Chancer.chanceItem(newItems, ItemManager.STEEL_HELMET, 20);
        Chancer.chanceItem(newItems, ItemManager.STEEL_ARMOR, 20);
        Chancer.chanceItem(newItems, ItemManager.STEEL_SHIELD, 60);
        Chancer.chanceItem(newItems, ItemManager.DOLPHIN_SHIELD, 60);
        Chancer.chanceItem(newItems, ItemManager.DOLPHIN_FIN, 25);
        Chancer.chanceItem(newItems, ItemManager.SHEEP_WOOL, 25);
        return newItems;
    }
}

class LootingHelper{
    public static ArrayList<String> getItemNamesInArrayList(ArrayList<Item> itemList){
        ArrayList<String> itemNames = new ArrayList<>();
        for (Item item : itemList){
            itemNames.add(item.getName());
        }
        return itemNames;
    }
    public static String getItemNamesInString(ArrayList<Item> itemList){
        ArrayList<String> itemNames = new ArrayList<>();
        for (Item item : itemList){
            itemNames.add(item.getName());
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String name : itemNames) {
            stringBuilder.append(name + ",\n");
        }
        String fullList = stringBuilder.toString();
        return fullList;
    }
}
*/
