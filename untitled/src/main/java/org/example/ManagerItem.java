package org.example;

import java.util.HashMap;
import java.util.Optional;

class ManagerItem {
    //maybe items and they put static names

    private static final HashMap<String, Item> itemHashMap = new HashMap<>();
    public static Optional<Item> get(String string){
        return Optional.ofNullable(itemHashMap.get(string));
    }
    public static void putInHashMap(Item item){
        itemHashMap.put(item.getName(), item);
    }


    public static final Item BEANIE = new Item.Builder("Beanie").wearable(Wearable.HEAD).weight(1).value(5).defence(1).build();

    public static final Item STEEL_HELMET = new Item.Builder("Steel helmet").wearable(Wearable.HEAD).weight(3).value(25).defence(3).build();
    public static final Item STEEL_ARMOR = new Item.Builder("Steel armor").wearable(Wearable.TORSO).weight(3).value(25).defence(3).build();
    public static final Item STEEL_TROUSERS = new Item.Builder("Steel trousers").wearable(Wearable.LEGS).weight(10).value(35).defence(7).build();
    public static final Item STEEL_GLOVES = new Item.Builder("Steel gloves").wearable(Wearable.HANDS).weight(3).value(10).defence(3).build();
    public static final Item STEEL_BOOTS = new Item.Builder("Steel boots").wearable(Wearable.FEET).weight(4).value(25).defence(3).build();
    public static final Item STEEL_SHIELD = new Item.Builder("Steel shield").wearable(Wearable.SECONDHAND).weight(5).value(45).defence(6).build();
    public static final Item STEEL_SWORD = new Item.Builder("Steel sword").wearable(Wearable.FIRSTHAND).weight(3).value(45).minAttack(4).maxAttack(7).build();


    public static final Item NO_HELMET = new Item.Builder("NO_HELMET").wearable(Wearable.HEAD).ghostEq(true).build();
    public static Item NO_ARMOR = new Item.Builder("NO_ARMOR").wearable(Wearable.TORSO).ghostEq(true).build();
    public static Item NO_TROUSERS = new Item.Builder("NO_TROUSERS").wearable(Wearable.LEGS).ghostEq(true).build();
    public static Item NO_GLOVES = new Item.Builder("NO_GLOVES").wearable(Wearable.HANDS).ghostEq(true).build();
    public static Item NO_BOOTS = new Item.Builder("NO_BOOTS").wearable(Wearable.FEET).ghostEq(true).build();
    public static Item NO_SHIELD = new Item.Builder("NO_SHIELD").wearable(Wearable.SECONDHAND).ghostEq(true).build();
    public static Item NO_WEAPON = new Item.Builder("NO_WEAPON").wearable(Wearable.FIRSTHAND).ghostEq(true).minAttack(1).maxAttack(2).build();


    public static final Item KNIFE = new Item.Builder("Knife").wearable(Wearable.FIRSTHAND).weight(1).value(15).minAttack(1).maxAttack(3).build();
    public static final Item GABRIELA_NAIL = new Item.Builder("Gabriela's nail").wearable(Wearable.FIRSTHAND).weight(1).value(35).minAttack(2).maxAttack(4).build();
    public static final Item PIGEON_MUSCLE = new Item.Builder("Pigeon's muscle").wearable(Wearable.FIRSTHAND).weight(1).value(15).minAttack(1).maxAttack(5).build();


    public static final Item RAT_TAIL = new Item.Builder("Rat's tail").weight(1).value(15).build();
    public static final Item RAT_MEAT = new Item.Builder("Rat's meat").weight(3).value(3).build();
    public static final Item BEAR_MEAT = new Item.Builder("Bear's meat").weight(5).value(15).build();
    public static final Item BEAR_SKIN = new Item.Builder("Bear's skin").weight(10).value(50).build();
    public static final Item BUTTERFLY_WING = new Item.Builder("Butterfly's wing").value(15).build();
    public static final Item GABRIELA_TONGUE = new Item.Builder("Gabriela's tongue").weight(1).value(50).build();
    public static final Item FINGERHOOD_STORY = new Item.Builder("Fingerhood's story").weight(100).build();
    public static final Item FEATHER = new Item.Builder("Feather").value(1).build();


    ///make maybe a factory producing ARMOR/HELMET/TROUSERS so choosing types according to need or just change the type
    // can add icons to items to print in inventory
    /*



    public static Item RAT_TAIL = new CraftingItem("Rat's tail", 1, 5);
    public static Item RAT_MEAT = new CraftingItem("Rat's meat", 3, 3);
    public static Item BEAR_MEAT = new CraftingItem("Bear's meat", 5, 15);
    public static Item BEAR_SKIN = new CraftingItem("Bear's skin", 10, 50);
    public static Item BUTTERFLY_WING = new CraftingItem("Butterfly's wing", 0, 10);
    public static Item GABRIELA_TONGUE = new CraftingItem("Gabriela's tongue", 1, 50);
    public static Item FINGERHOOD_STORY = new CraftingItem("Fingerhood's story", 100, 0);
    public static Item FEATHER = new CraftingItem("Feather", 0, 1);

    //MONEY
    public final static Item GOLD = new Money("Gold", 0, 0);

    public static Item DOLPHIN_SHIELD = new Shield("Dolphin shield", 3, 155, 16);

    public static Item DOLPHIN_FIN = new CraftingItem("Dolphin's fin", 2, 100);
    public static Item SHEEP_WOOL = new CraftingItem("Sheep's wool", 2, 100);

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
     */
}
