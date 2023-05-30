package org.example.Business.WorldManagers;

import org.example.Business.Dungeon;

public class ManagerDungeon {
    public static final Dungeon CAVE = new Dungeon.DungeonBuilder().addMonster(ManagerMonster.ENORMOUS_RAT, 50).build();

    public static final Dungeon CHICKEN_CAVE = new Dungeon.DungeonBuilder()
            .addMonster(ManagerMonster.CHICK, 60)
            .addMonster(ManagerMonster.SMALL_ROOSTER, 25)
            .addMonster(ManagerMonster.EGG_MONSTER, 10)
            .addMonster(ManagerMonster.CHICK_LORD, 5)
            .build();

    /*
    public static final Dungeon LONELY_SWAMP = new Dungeon.DungeonBuilder()
            .addMonster(MonsterManager.FROG_WIFE, 60)
            .addMonster(MonsterManager.SNAKE, 25)
            .build();
     */

    public static final Dungeon FOREST = new Dungeon.DungeonBuilder()
            .addMonster(ManagerMonster.ENORMOUS_RAT, 30)
            .addMonster(ManagerMonster.BUTTERFLY, 30)
            .addMonster(ManagerMonster.SQUIRREL, 10)
            .addMonster(ManagerMonster.WOLF, 10)
            .addMonster(ManagerMonster.BEAR, 14)
            .addMonster(ManagerMonster.DRAGON, 5)
            .build();


    public static final Dungeon SERVER = new Dungeon.DungeonBuilder()
            .addMonster(ManagerMonster.FINGERHOOD, 30)
            .addMonster(ManagerMonster.GABRIELA, 30)
            .addMonster(ManagerMonster.PIGEON, 30)
            .build();


    public static final Dungeon CHESTPLACE = new Dungeon.DungeonBuilder().addMonster(ManagerMonster.CHEST, 100).build();

    public static final Dungeon SHIP = new Dungeon.DungeonBuilder().addMonster(ManagerMonster.SQUIRREL, 100).build();

    public static final Dungeon LAKE = new Dungeon.DungeonBuilder().addMonster(ManagerMonster.DOLPHIN, 1).build();


}
