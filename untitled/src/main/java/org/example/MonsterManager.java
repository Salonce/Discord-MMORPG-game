package org.example;

class MonsterManager{
    public static final Monster RAT = new Monster
            .MonsterBuilder("Rat",15)
            .addLoot(ItemManager.RAT_MEAT, 70)
            .addLoot(ItemManager.RAT_TAIL, 30)
            .addDescription("Little, annoying mammal. Likes to live near people and steal their food.")
            .build();

    public static final Monster GABRIELA = new Monster
            .MonsterBuilder("Gabriela",1)
            .addLoot(ItemManager.GABRIELAS_TONGUE, 40)
            .addDescription("Proud resident of Brazil")
            .build();

    public static final Monster FINGERHOOD = new Monster
            .MonsterBuilder("Fingerhood",50)
            .addLoot(ItemManager.FINGERHOODS_STORY, 100)
            .addDescription("Talking admin")
            .build();

    public static final Monster SQUIRREL = new Monster
            .MonsterBuilder("Squirrel",5)
            .addDescription("Jumps on the trees. Eats (it's) nuts.")
            .build();

    public static final Monster BUTTERFLY = new Monster
            .MonsterBuilder("Butterfly",1)
            .addLoot(ItemManager.BUTTERFLY_WING, 60)
            .addDescription("Flying little thing. Pretty and comes in various colours.")
            .build();

    public static final Monster WOLF = new Monster
            .MonsterBuilder("Wolf", 50)
            .addDescription("An angry, dog-like beast from the forest. Likes to move in packs.")
            .build();

    public static final Monster BEAR = new Monster
            .MonsterBuilder("Bear", 500)
            .addLoot(ItemManager.BEAR_MEAT, 70)
            .addLoot(ItemManager.BEAR_SKIN, 30)
            .addDescription("A wild mammal with a large body and deadly claws.")
            .build();

    public static final Monster DRAGON = new Monster.MonsterBuilder("Dragon", 5000).addDescription("Immense monster covered in thick leather and scales that breathes fire.").build();

    public static final Monster DOLPHIN = new Monster.MonsterBuilder("Dolphin", 200).addDescription("Swimmer.").build();

    public static final Monster CHEST = new Monster.MonsterBuilder("Chest", 0).addDescription("Receptacle with treasuries.")
            .addLoot(ItemManager.STEEL_SWORD, 60)
            .addLoot(ItemManager.STEEL_HELMET, 20)
            .addLoot(ItemManager.DOLPHIN_SHIELD, 60)
            .addLoot(ItemManager.STEEL_SHIELD, 60)
            .addLoot(ItemManager.DOLPHIN_FIN, 25)
            .addLoot(ItemManager.SHEEP_WOOL, 25)
            .build();



    /*
          Chancer.chanceItem(newItems, ItemManager.STEEL_SWORD, 60);
        Chancer.chanceItem(newItems, ItemManager.STEEL_HELMET, 20);
        Chancer.chanceItem(newItems, ItemManager.STEEL_ARMOR, 20);
        Chancer.chanceItem(newItems, ItemManager.STEEL_SHIELD, 60);
        Chancer.chanceItem(newItems, ItemManager.DOLPHIN_SHIELD, 60);
        Chancer.chanceItem(newItems, ItemManager.DOLPHIN_FIN, 25);
        Chancer.chanceItem(newItems, ItemManager.SHEEP_WOOL, 25);
     */
    /*
    public static final Monster HAWK = new Monster("hawk", 50);
    public static final Monster BEAVER = new Monster("beaver", 15);
    public static final Monster HAMSTER = new Monster("hamster", 5);
    public static final Monster TROLL = new Monster("troll", 150);
    public static final Monster RED_WOLF = new Monster("red wolf", 30);
    public static final Monster SNAKE = new Monster("snake", 30);
    public static final Monster DOMESTIC_PIG = new Monster("domestic pig", 30);
    public static final Monster WILD_BOAR = new Monster("wild boar", 30);
    public static final Monster TIGER = new Monster("tiger", 30);
    public static final Monster LION = new Monster("lion", 30);
    public static final Monster PALLAS_CAT = new Monster("pallas cat", 30);
    public static final Monster IMP = new Monster("imp", 200);
    public static final Monster CATFISH = new Monster("catfish", 400);
    public static final Monster CRAZY_FRIDGE = new Monster("crazy fridge", 1000);
    public static final Monster STARFISH = new Monster("starfish", 10);
    public static final Monster SHARK = new Monster("shark", 50);
    public static final Monster COSSACK = new Monster("cossack", 50);
    public static final Monster PIRATE = new Monster("pirate", 50);
    public static final Monster PIGEON = new Monster("pigeon", 50);
    public static final Monster BISON = new Monster("bison", 50);
    public static final Monster BANDIT = new Monster("bandit", 50);
    public static final Monster THIEF = new Monster("thief", 50);
*/
}