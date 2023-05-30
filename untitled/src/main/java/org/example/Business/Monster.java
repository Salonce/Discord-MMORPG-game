package org.example.Business;

import java.util.ArrayList;
import java.util.Random;


public class Monster implements Fighter{
    public Monster(MonsterBuilder monsterBuilder){
        this.name = monsterBuilder.name;
        this.description = monsterBuilder.description;
        this.maybeItemList = monsterBuilder.maybeItemList;
        this.health = monsterBuilder.health;
        this.minAttack = monsterBuilder.minAttack;
        this.maxAttack = monsterBuilder.maxAttack;
        this.defense = monsterBuilder.defense;
        this.speed = monsterBuilder.speed;
    }

    public CombatStrength getCombatStrength(){
        return new CombatStrength(health, minAttack, maxAttack, defense, speed);
    }

    private int health;
    private int minAttack;
    private int maxAttack;
    private int defense;
    private int speed;

    private ArrayList<MaybeItem> maybeItemList;

    public ArrayList<Item> getRandomLoot(){
        ArrayList<Item> loot = new ArrayList<>();
        for (MaybeItem maybeItem : maybeItemList) {
            Item item = maybeItem.maybeGet();
            if (item != null)
                loot.add(item);
        }
        return loot;
    }

    private String name;
    public String getName(){return this.name;}

    private String description;
    public String getDescription() {
        return description;
    }

    public Properties getProperties(){return null;}
    public Weaknesses getWeaknesses(){return null;}


    public static class MonsterBuilder{
        public MonsterBuilder(String name, int health, int minAttack, int maxAttack, int defense, int speed){
            this.name = name;
            this.description = "empty";
            this.maybeItemList = new ArrayList<>();
            this.health = health;
            this.minAttack = minAttack;
            this.maxAttack = maxAttack;
            this.defense = defense;
            this.speed = speed;
        }
        private int health;
        private int minAttack;
        private int maxAttack;
        private int defense;
        private int speed;

        private String name;

        private String description;
        public MonsterBuilder addDescription(String description){
            this.description = description;
            return this;
        }

        private ArrayList<MaybeItem> maybeItemList;
        public MonsterBuilder addLoot(Item item, int probability){
            maybeItemList.add(new MaybeItem(item, probability));
            return this;
        }

        public Monster build(){
            return new Monster(this);
        }
    }
    //public int vulnerabilityFire;
    //public int vulnerabilityIce;
    //public int vulnerabilitySlash;
    //public int vulnerabilityPierce;
    //public int vulnerabilityBash;
}

abstract class Maybe<T>{
    private static final Random random = new Random();

    public Maybe(T object, int probability){
        this.object = object;
        this.probability = probability;
    }
    private final T object;
    private final int probability;

    public T maybeGet(){
        if (Maybe.random.nextInt(100) < this.probability)
            return this.object;
        else
            return null;
    }
}

class MaybeItem extends Maybe<Item>{
    public MaybeItem(Item item, int probability) {
        super(item, probability);
    }
}


