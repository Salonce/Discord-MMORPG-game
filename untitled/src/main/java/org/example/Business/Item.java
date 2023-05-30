package org.example.Business;

import org.example.Business.WorldManagers.ManagerItem;



public class Item {
    private final int PRICE_MULTIPLICATOR = 2;
    //public static int MAX_ITEM_NAME_LENGTH = 15;

    public Item(String name, int weight, int value) {
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
    public boolean isEmptyEq;
    protected boolean isMoney;
    protected Wearable wearable;
    //private int price;

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    protected int getPrice() {
        return getValue() * PRICE_MULTIPLICATOR;
    }

    public int getDefence() {
        if (hasDefense())
            return defence;
        else return 0;
    }

    public int getMinAttack() {
        if (hasAttack())
            return minAttack;
        else return 0;
    }

    public int getMaxAttack() {
        if (hasAttack())
            return maxAttack;
        else return 0;
    }

    public boolean isReal() {
        return !this.isEmptyEq;
    }

    public boolean hasAttack() {
        return this.hasAttack;
    }

    public boolean hasDefense() {
        return this.hasDefense;
    }

    public Wearable getWearable() {
        return this.wearable;
    }

    public boolean isMoney() {
        return this.isMoney;
    }


    Item(Builder builder){
        this.name = builder.name;
        ManagerItem.putInHashMap(this);

        this.weight = builder.weight;
        this.value = builder.value;
        this.defence = builder.defence;
        this.minAttack = builder.minAttack;
        this.maxAttack = builder.maxAttack;
        this.hasDefense = builder.hasDefense;
        this.hasAttack = builder.hasAttack;
        this.isEmptyEq = builder.isEmptyEq;
        this.isMoney = builder.isMoney;
        this.wearable = builder.wearable;
    }

    public static class Builder{
        public Builder(String name){
            this.name = name;
        }

        private String name;
        private int weight = 0;
        private int value = 0;
        private int defence = 0;
        private int minAttack = 0;
        private int maxAttack = 0;
        private boolean hasDefense = false;
        private boolean hasAttack = false;
        private boolean isEmptyEq = false;
        private boolean isMoney = false;
        private Wearable wearable = Wearable.NOTHING;

        public Builder weight(int weight) {
            this.weight = weight;
            return this;
        }

        public Builder value(int value) {
            this.value = value;
            return this;
        }

        public Builder defence(int defence) {
            this.defence = defence;
            this.hasDefense = true;
            return this;
        }

        public Builder minAttack(int minAttack) {
            this.minAttack = minAttack;
            this.hasAttack = true;
            return this;
        }

        public Builder maxAttack(int maxAttack) {
            this.maxAttack = maxAttack;
            this.hasAttack = true;
            return this;
        }

        public Builder ghostEq(boolean emptyEq) {
            isEmptyEq = emptyEq;
            return this;
        }

        public Builder money(boolean money) {
            isMoney = money;
            return this;
        }

        public Builder wearable(Wearable wearable) {
            this.wearable = wearable;
            return this;
        }

        public Item build(){
            return new Item(this);
        }
    }
}

abstract class DefensiveItem extends Item {
    public DefensiveItem(String name, int weight, int value, int defence) {
        super(name, weight, value);
        this.defence = defence;
        this.hasDefense = true;
    }
}

//class ItemFactory{
//    static Item getHelmet(String name, int weight, int value){
//        return new Item() {
//        };
//    }
//}

abstract class SecondHandEquipment extends DefensiveItem{
    public SecondHandEquipment(String name, int weight, int value, int defence){
        super(name, weight, value, defence);
    }
}
final class Shield extends SecondHandEquipment{
    public Shield(String name, int weight, int value, int defence){
        super(name, weight, value, defence);
        this.wearable = Wearable.SECONDHAND;
    }
}

final class CraftingItem extends Item{
    public CraftingItem(String name, int weight, int value){
        super(name, weight, value);
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