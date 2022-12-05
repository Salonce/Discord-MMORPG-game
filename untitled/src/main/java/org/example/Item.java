package org.example;

import java.util.Comparator;

enum Wearable{
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

abstract class Item{
    private final int PRICE_MULTIPLICATOR = 2;
    //public static int MAX_ITEM_NAME_LENGTH = 15;

    public Item(String name, int weight, int value){
        this.name = name;
        ManagerItem.putInHashMap(this);

        this.weight = weight;
        this.value = value;
        this.defence = 0;
        this.minAttack = 0;
        this.maxAttack = 0;
        this.hasDefense = false;
        this.hasAttack = false;
        this.isEmptyEq = false;
        this.isMoney = false;
        this.wearable = Wearable.NOTHING;
    }

    protected String name;
    protected int weight;
    protected int value;
    protected int defence;
    protected int minAttack;
    protected int maxAttack;
    protected boolean hasDefense;
    protected boolean hasAttack;
    protected boolean isEmptyEq;
    protected boolean isMoney;
    protected Wearable wearable;
    //private int price;

    protected String getName(){return name;}
    protected int getWeight(){return weight;}
    protected int getValue(){return value;}
    protected int getPrice(){ return getValue()*PRICE_MULTIPLICATOR; }

    public int getDefence(){
        if (hasDefense())
            return defence;
        else return 0;
    }
    public int getMinAttack(){
        if (hasAttack())
            return minAttack;
        else return 0;
    }
    public int getMaxAttack(){
        if (hasAttack())
            return maxAttack;
        else return 0;
    }


    public boolean isEquipment(){
        return !this.isEmptyEq;
    };
    public boolean hasAttack(){
        return this.hasAttack;
    }
    public boolean hasDefense(){
        return this.hasDefense;
    }
    public Wearable getWearable(){
        return this.wearable;
    }
    public boolean isMoney(){
        return this.isMoney;
    }

}



abstract class DefensiveItem extends Item{
    public DefensiveItem(String name, int weight, int value, int defence){
        super(name, weight, value);
        this.defence = defence;
        this.hasDefense = true;
    }
}

abstract class HeadEquipment extends DefensiveItem{
    public HeadEquipment(String name, int weight, int value, int defence){
        super(name, weight, value, defence);
    }
}

abstract class TorsoEquipment extends DefensiveItem{
    public TorsoEquipment(String name, int weight, int value, int defence){
        super(name, weight, value, defence);
    }
}

abstract class LegsEquipment extends DefensiveItem{
    public LegsEquipment(String name, int weight, int value, int defence){
        super(name, weight, value, defence);
    }
}

abstract class BootsEquipment extends DefensiveItem{
    public BootsEquipment(String name, int weight, int value, int defence){
        super(name, weight, value, defence);
    }
}

abstract class HandsEquipment extends DefensiveItem{
    public HandsEquipment(String name, int weight, int value, int defence){
        super(name, weight, value, defence);
    }
}

abstract class SecondHandEquipment extends DefensiveItem{
    public SecondHandEquipment(String name, int weight, int value, int defence){
        super(name, weight, value, defence);
    }
}

abstract class OffensiveItem extends Item{
    public OffensiveItem(String name, int weight, int value, int minAttack, int maxAttack){
        super(name, weight, value);
        this.minAttack = minAttack;
        this.maxAttack = maxAttack;
        this.hasAttack = true;
    }
}

abstract class FirstHandEquipment extends OffensiveItem{
    public FirstHandEquipment(String name, int weight, int value, int minAttack, int maxAttack){
        super(name, weight, value, minAttack, maxAttack);
    }
}

class NoHelmet extends HeadEquipment{
    public NoHelmet(String name, int weight, int value, int defence){
        super(name, weight, value, defence);
        this.isEmptyEq = true;
        this.wearable = Wearable.HEAD;
    }
}
final class NoArmor extends TorsoEquipment{
    public NoArmor(String name, int weight, int value, int defence){
        super(name, weight, value, defence);
        this.isEmptyEq = true;
        this.wearable = Wearable.TORSO;
    }
}
final class NoTrousers extends LegsEquipment{
    public NoTrousers(String name, int weight, int value, int defence){
        super(name, weight, value, defence);
        this.isEmptyEq = true;
        this.wearable = Wearable.LEGS;
    };
}
final class NoGloves extends HandsEquipment{
    public NoGloves(String name, int weight, int value, int defence){
        super(name, weight, value, defence);
        this.isEmptyEq = true;
        this.wearable = Wearable.HANDS;
    }

}
final class NoBoots extends BootsEquipment{
    public NoBoots(String name, int weight, int value, int defence){
        super(name, weight, value, defence);
        this.isEmptyEq = true;
        this.wearable = Wearable.FEET;
    }
}
final class NoShield extends SecondHandEquipment{
    public NoShield(String name, int weight, int value, int defence){
        super(name, weight, value, defence);
        this.isEmptyEq = true;
        this.wearable = Wearable.SECONDHAND;
    }
}
final class NoWeapon extends FirstHandEquipment{
    public NoWeapon(String name, int weight, int value, int minAttack, int maxAttack){
        super(name, weight, value, minAttack, maxAttack);
        this.isEmptyEq = true;
        this.wearable = Wearable.FIRSTHAND;
    }
}
class Helmet extends HeadEquipment{
    public Helmet(String name, int weight, int value, int defence){
        super(name, weight, value, defence);
        this.wearable = Wearable.HEAD;
    }
}
final class Armor extends TorsoEquipment{
    public Armor(String name, int weight, int value, int defence){
        super(name, weight, value, defence);
        this.wearable = Wearable.TORSO;
    }
}
final class Trousers extends LegsEquipment{
    public Trousers(String name, int weight, int value, int defence){
        super(name, weight, value, defence);
        this.wearable = Wearable.LEGS;
    }
}
final class Gloves extends HandsEquipment{
    public Gloves(String name, int weight, int value, int defence){
        super(name, weight, value, defence);
        this.wearable = Wearable.HANDS;
    }
}
final class Boots extends BootsEquipment{
    public Boots(String name, int weight, int value, int defence){
        super(name, weight, value, defence);
        this.wearable = Wearable.FEET;
    }
}
final class Shield extends SecondHandEquipment{
    public Shield(String name, int weight, int value, int defence){
        super(name, weight, value, defence);
        this.wearable = Wearable.SECONDHAND;
    }
}
final class Weapon extends FirstHandEquipment{
    public Weapon(String name, int weight, int value, int minAttack, int maxAttack){
        super(name, weight, value, minAttack, maxAttack);
        this.wearable = Wearable.FIRSTHAND;
    }
}

final class CraftingItem extends Item{
    public CraftingItem(String name, int weight, int value){
        super(name, weight, value);
    }
}

final class Money extends Item{
    public Money(String name, int weight, int value){
        super(name, weight, value);
        this.isMoney = true;
    }
}

//
enum WeaponType{
    CUTTING,
    BASHING,
    PIERCING
    //FIRE
    //WATER
    //POISON
}

/*
///Item builder gives more optional values to pick and make the coupling with ENUM system looser, i think

enum Itemtype{
    HELMET (false, false, true, false, Wearable.HEAD),
    ARMOR (false, false, true, false, Wearable.TORSO),
    TROUSERS (false, false, true, false, Wearable.LEGS),
    BOOTS (false, false, true, false, Wearable.FEET),
    GLOVES (false, false, true, false, Wearable.HANDS),
    ONEHWEAPON (false, true, false, false, Wearable.FIRSTHAND),
    SHIELD (false, false, true, false, Wearable.SECONDHAND),

    NOHELMET (false, false, true, true, Wearable.HEAD),
    NOARMOR (false, false, true, true, Wearable.TORSO),
    NOTROUSERS (false, false, true, true, Wearable.LEGS),
    NOBOOTS (false, false, true, true, Wearable.FEET),
    NOGLOVES (false, false, true, true, Wearable.HANDS),
    NOONEHWEAPON (false, true, false, true, Wearable.FIRSTHAND),
    NOSHIELD (false, false, true, true, Wearable.SECONDHAND),

    CRAFTINGITEM (false, false, false, false, Wearable.NOTHING),
    MONEY (true, false, false, false, Wearable.NOTHING);

    private final boolean isMoney;
    private final boolean hasattack;
    private final boolean hasdefence;
    private final boolean isEmptyEquipment;
    private final Wearable wearable;
    Itemtype(boolean isMoney, boolean hasAttack, boolean hasDefense, boolean isEmptyEquipment, Wearable wearable) {
        this.isMoney = isMoney;
        this.hasattack = hasAttack;
        this.hasdefence = hasDefense;
        this.isEmptyEquipment = isEmptyEquipment;
        this.wearable = wearable;
    }
}
 */




/*

    //TESTING
    static Comparator<Item> getComparatorByName(){
        return Comparator.comparing(Item::getName);
    }
    static Comparator<Item> getComparatorByValue(){
        return Comparator.<Item, Integer>comparing(item -> item.getValue());
    }
    //END OF TESTING
 */