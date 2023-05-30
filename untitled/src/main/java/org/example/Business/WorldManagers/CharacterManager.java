package org.example.Business.WorldManagers;

import discord4j.common.util.Snowflake;
import org.example.Business.Character;
import org.example.Database.Database;

import java.util.HashMap;

public class CharacterManager {
    private final HashMap<Snowflake, Character> usersCharacters;

    public HashMap<Snowflake, Character> getUsersCharacters() {
        return usersCharacters;
    }

    private void createCharacter(Snowflake id) {
        usersCharacters.put(id, new Character());
    }

    public void createCharAndPutInDb(Snowflake id) {
        if (!usersCharacters.containsKey(id)) {
            createCharacter(id);
            Database.addAllToUserDb(id);
        }
    }

    public CharacterManager(String dbLoad) {
        usersCharacters = new HashMap<>();
    }


    public Character getCharacterById(Snowflake id) {
        return usersCharacters.get(id);
    }
}
