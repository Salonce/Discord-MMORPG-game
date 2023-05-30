package org.example.Business;

import java.util.Random;

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
