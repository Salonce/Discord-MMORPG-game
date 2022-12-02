package org.example;

import java.util.HashMap;

class ManagerItem {
    //maybe items and they put static names

    private static final HashMap<String, Item> itemHashMap = new HashMap<>();
    public static Item get(String string){
        if (itemHashMap.get(string) == null)
            throw new RuntimeException("Incorrect spelling of: " + string);
        return itemHashMap.get(string);
    }
    public static void putInHashMap(Item item){
        itemHashMap.put(item.getName(), item);
    }


    public static Item BEANIE = new Helmet("Beanie", 1, 5, 1);

    public static Item STEEL_HELMET = new Helmet("Steel helmet", 3, 25, 3);
    public static Item STEEL_ARMOR = new Armor("Steel armor", 1, 5, 1);
    public static Item STEEL_TROUSERS = new Trousers("Steel trousers", 10, 35, 7);
    public static Item STEEL_GLOVES = new Gloves("Steel gloves", 3, 10, 3);
    public static Item STEEL_BOOTS = new Boots("Steel boots", 4, 25, 3);
    public static Item STEEL_SHIELD = new Shield("Steel shield", 5, 45, 6);
    public static Item STEEL_SWORD = new Weapon("Steel sword", 3, 45, 4, 7);

    public static Item KNIFE = new Weapon("Knife", 1, 15, 1, 3);
    public static Item GABRIELA_NAIL = new Weapon("Gabriela's nail", 1, 35, 2, 4);
    public static Item PIGEON_MUSCLE = new Weapon("Pigeon's muscle", 1, 15, 1, 5);

    public static Item DOLPHIN_SHIELD = new Shield("Dolphin shield", 3, 155, 16);

    public static Item NO_HELMET = new NoHelmet("NO_HELMET", 0, 0, 0);
    public static Item NO_ARMOR = new NoArmor("NO_ARMOR", 0, 0, 0);
    public static Item NO_TROUSERS = new NoTrousers("NO_TROUSERS", 0, 0, 0);
    public static Item NO_GLOVES = new NoGloves("NO_GLOVES", 0, 0, 0);
    public static Item NO_BOOTS = new NoBoots("NO_BOOTS", 0, 0, 0);
    public static Item NO_SHIELD = new NoShield("NO_SHIELD", 0, 0, 1);
    public static Item NO_WEAPON = new NoWeapon("NO_WEAPON", 0, 0, 1, 2);

    public static Item DOLPHIN_FIN = new CraftingItem("Dolphin's fin", 2, 100);
    public static Item SHEEP_WOOL = new CraftingItem("Sheep's wool", 2, 100);
    public static Item RAT_TAIL = new CraftingItem("Rat's tail", 1, 5);
    public static Item RAT_MEAT = new CraftingItem("Rat's meat", 3, 3);
    public static Item BEAR_MEAT = new CraftingItem("Bear's meat", 5, 15);
    public static Item BEAR_SKIN = new CraftingItem("Bear's skin", 10, 50);
    public static Item BUTTERFLY_WING = new CraftingItem("Butterfly's wing", 0, 10);
    public static Item GABRIELA_TONGUE = new CraftingItem("Gabriela's tongue", 1, 50);
    public static Item FINGERHOOD_STORY = new CraftingItem("Fingerhood's story", 100, 0);
    public static Item FEATHER = new CraftingItem("Feather", 0, 1);

    static {
        /*
        putInHashMap(BEANIE);

        putInHashMap(STEEL_HELMET);
        putInHashMap(STEEL_ARMOR);
        putInHashMap(STEEL_TROUSERS);
        putInHashMap(STEEL_BOOTS);
        putInHashMap(STEEL_GLOVES);
        putInHashMap(STEEL_SHIELD);
        putInHashMap(STEEL_SWORD);

        putInHashMap(KNIFE);
        putInHashMap(GABRIELA_NAIL);
        putInHashMap(PIGEON_MUSCLE);

        putInHashMap(DOLPHIN_SHIELD);

        putInHashMap(NO_HELMET);
        putInHashMap(NO_ARMOR);
        putInHashMap(NO_TROUSERS);
        putInHashMap(NO_BOOTS);
        putInHashMap(NO_GLOVES);
        putInHashMap(NO_SHIELD);
        putInHashMap(NO_WEAPON);

        putInHashMap(DOLPHIN_FIN);
        putInHashMap(SHEEP_WOOL);
        putInHashMap(RAT_TAIL);
        putInHashMap(RAT_MEAT);
        putInHashMap(BEAR_MEAT);
        putInHashMap(BEAR_SKIN);
        putInHashMap(BUTTERFLY_WING);
        putInHashMap(GABRIELA_TONGUE);
        putInHashMap(FINGERHOOD_STORY);
        putInHashMap(FEATHER);
        */
    }



    //MONEY
    public final static Item GOLD = new Money("Gold", 0, 0);


    //IRON SET
    public final static Item IRON_HELMET = new Helmet("Iron helmet", 2, 15, 2);
    public final static Item IRON_ARMOR = new Armor("Iron armor", 5, 35, 3);
    public final static Item IRON_TROUSERS = new Trousers("Iron trousers", 3, 25, 2);
    public final static Item IRON_GLOVES = new Gloves("Iron gloves", 1, 5, 1);
    public final static Item IRON_BOOTS = new Boots("Iron boots", 1, 15, 1);
    public final static Item IRON_SHIELD = new Shield("Iron shield", 2, 25, 4);

    //LEATHER SET
    public final static Item LEATHER_HELMET = new Helmet("Leather helmet", 2, 15, 2);
    public final static Item LEATHER_ARMOR = new Armor("Leather armor", 5, 35, 3);
    public final static Item LEATHER_TROUSERS = new Trousers("Leather trousers", 3, 25, 2);
    public final static Item LEATHER_GLOVES = new Gloves("Leather gloves", 1, 5, 1);
    public final static Item LEATHER_BOOTS = new Boots("Leather boots", 1, 15, 1);
    public final static Item LEATHER_SHIELD = new Shield("Leather shield", 2, 25, 4);


    //VARIOUS CLOTHES
    public final static Item SANTA_HAT = new Helmet("Santa hat", 1, 50, 1);



}
