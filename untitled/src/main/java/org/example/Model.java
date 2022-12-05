package org.example;

import discord4j.common.util.Snowflake;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Model {
    private static final String connectionUrl = "jdbc:mysql://localhost:3306/";
    private static String inApostrophes(String string){
        return ("'" + string + "'");
    }
    private static String doubleTheApostrophe(String string){
        return string.replace("'", "''");
    }


    private static String selectAll(String tableName){
        return "SELECT * FROM rpg4j." + tableName;
    }

    public static void loadAllIds(CharacterManager characterManager){
        try (Connection conn = DriverManager.getConnection(connectionUrl, "root", "password1");

        PreparedStatement selectAllUsersInventory = conn.prepareStatement(selectAll("inventory"));
        ResultSet allUsersInventory = selectAllUsersInventory.executeQuery();

        PreparedStatement selectAllUsersEquipment = conn.prepareStatement(selectAll("equipment"));
        ResultSet allUsersEquipment = selectAllUsersEquipment.executeQuery();

        PreparedStatement selectAllUsersIds = conn.prepareStatement(selectAll("ids"));
        ResultSet allUsersIds = selectAllUsersIds.executeQuery();

        PreparedStatement selectAllUsersHealth = conn.prepareStatement(selectAll("health"));
        ResultSet allUsersHealth = selectAllUsersHealth.executeQuery();
        ) {
            loadIdResults(allUsersIds, characterManager);
            loadHealthResults(allUsersHealth, characterManager);
            loadInventoryResults(allUsersInventory, characterManager);
            loadEquipmentResults(allUsersEquipment, characterManager);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (InventoryFullException e) {
            throw new RuntimeException(e);
        } catch (NotWearableItemException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadIdResults(ResultSet rs, CharacterManager characterManager) throws SQLException{
        while (rs.next()) {
            long id = rs.getLong("Snowflake");
            characterManager.getUsersCharacters().put(Snowflake.of(id), new Character());
        }
    }

    private static void loadHealthResults(ResultSet rs, CharacterManager characterManager) throws SQLException{
        while (rs.next()) {
            long id = rs.getLong("Snowflake");
            int maxHealth = rs.getInt("max_health");
            int currentHealth = rs.getInt("current_health");
            int healthRegen = rs.getInt("health_regen");
            Character character = characterManager.getCharacterById(Snowflake.of(id));
            character.getHealth().setMax(maxHealth);
            character.getHealth().set(currentHealth);
            character.getHealth().setRegenTime(healthRegen);
        }
    }

    private static void loadInventoryResults(ResultSet rs, CharacterManager characterManager) throws SQLException, InventoryFullException {
        while (rs.next()) {
            ArrayList<Item> itemArrayList = new ArrayList<>();
            long id = rs.getLong("Snowflake");
            int max_capacity = rs.getInt("max_capacity");
            fillItems(itemArrayList, rs);
            //itemStrings[0] = ManagerItem.get(rs.getString("i0"));
            //itemStrings[1] = ManagerItem.get(rs.getString("i1"));
            Character character = characterManager.getCharacterById(Snowflake.of(id));
            character.getInventory().setMaxCapacity(max_capacity);
            character.getInventory().addItems(itemArrayList);
        }
    }

    private static void fillItems(ArrayList<Item> itemArrayList, ResultSet rs) throws SQLException {
        for (int i = 0; i < 15; i++){
            if (rs.getString("i" + i) != null) {
                Item newItem = ManagerItem.get(rs.getString("i" + i));
                //if (newItem != null)
                itemArrayList.add(newItem);
            }
        }
    }

    private static void loadEquipmentResults(ResultSet rs, CharacterManager characterManager) throws SQLException, NotWearableItemException {
        while (rs.next()) {
            ArrayList<Item> itemArrayList = new ArrayList<>();
            long id = rs.getLong("Snowflake");
            Item head = ManagerItem.get(rs.getString("head"));
            Item torso = ManagerItem.get(rs.getString("torso"));
            Item legs = ManagerItem.get(rs.getString("legs"));
            Item feet = ManagerItem.get(rs.getString("feet"));
            Item hands = ManagerItem.get(rs.getString("hands"));
            Item firstHand = ManagerItem.get(rs.getString("firsthand"));
            Item secondHand = ManagerItem.get(rs.getString("secondhand"));
            Character character = characterManager.getCharacterById(Snowflake.of(id));
            character.getEquipment().equip(head);
            character.getEquipment().equip(torso);
            character.getEquipment().equip(legs);
            character.getEquipment().equip(feet);
            character.getEquipment().equip(hands);
            character.getEquipment().equip(firstHand);
            character.getEquipment().equip(secondHand);
        }
    }

    private static String selectSingleSnowflake(String tableName, Snowflake snowflake){
        return "SELECT * FROM rpg4j." + tableName + " WHERE Snowflake = '" + snowflake.asLong() + "'";
    }

    private static String addEquipmentString(Snowflake snowflake){
        return "INSERT INTO rpg4j.equipment (Snowflake, head, torso, legs, feet, hands, firsthand, secondhand) VALUES "
                + "(" + snowflake.asLong() + ", "
                + "'NO_HELMET', "
                + "'NO_ARMOR', "
                + "'NO_TROUSERS', "
                + "'NO_BOOTS', "
                + "'NO_GLOVES', "
                + "'NO_WEAPON', "
                + "'NO_SHIELD'"
                + ")";
    }

    private static String addInventoryString(Snowflake snowflake){
        return "INSERT INTO rpg4j.inventory (Snowflake, max_capacity) VALUES "
                + "(" + snowflake.asLong() + ", "
                + Inventory.DEFAULT_MAX_CAPACITY
                + ")";
    }

    private static String addHealthString(Snowflake snowflake){
        return "INSERT INTO rpg4j.health (Snowflake, max_health, current_health, health_regen) VALUES "
                + "(" + snowflake.asLong() + ", "
                + Health.DEFAULT_MAX_HEALTH + ", "
                + Health.DEFAULT_MAX_HEALTH + ", "
                + Health.DEFAULT_HP_REGEN
                + ")";
    }

    private static String addIdString(Snowflake snowflake){
        return "INSERT INTO rpg4j.ids (Snowflake) VALUES " + "(" + snowflake.asLong() + ")";
    }

    public static void addHealthToDb(Snowflake snowflake){
        addToDb(selectSingleSnowflake("health", snowflake), addHealthString(snowflake));
    }

    public static void addEquipmentToDb(Snowflake snowflake){
        addToDb(selectSingleSnowflake("equipment", snowflake), addEquipmentString(snowflake));
    }

    public static void addInventoryToDb(Snowflake snowflake){
        addToDb(selectSingleSnowflake("inventory", snowflake), addInventoryString(snowflake));
    }

    public static void addIdToDb(Snowflake snowflake){
        addToDb(selectSingleSnowflake("ids", snowflake), addIdString(snowflake));
    }

    public static void addToDb(String stringCheck, String stringExecute){
        try (Connection conn = DriverManager.getConnection(connectionUrl, "root", "password1");
             PreparedStatement idExistsStatement = conn.prepareStatement(stringCheck);
             PreparedStatement insertValuesStatement = conn.prepareStatement(stringExecute);
             ResultSet resultSetId = idExistsStatement.executeQuery()){

            if(resultSetId.next()){
                System.out.println("user in IDs");
            }
            else{
                insertValuesStatement.execute();
                System.out.println("User not in IDs. Saving user.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addAllToUsersDb(){
        String getIds = "SELECT * FROM rpg4j.ids";
        try (Connection conn = DriverManager.getConnection(connectionUrl, "root", "password1");
             PreparedStatement getAllIdsStatement = conn.prepareStatement(getIds);
             ResultSet resultSetId = getAllIdsStatement.executeQuery()){

            while(resultSetId.next()){
                Snowflake snowflake = Snowflake.of(resultSetId.getLong("Snowflake"));
                addAllToUserDb(snowflake);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addAllToUserDb(Snowflake snowflake){
        addIdToDb(snowflake);
        addInventoryToDb(snowflake);
        addEquipmentToDb(snowflake);
        addHealthToDb(snowflake);
    }

    public static void updateUserHealth(Snowflake snowflake, Character character){
        updateByString(updateHealthString(snowflake, character));
    }
    public static void updateUserInventory(Snowflake snowflake, Character character){
        updateByString(updateInventoryString(snowflake, character));
    }
    public static void updateUserEquipment(Snowflake snowflake, Character character){
        updateByString(updateEquipmentString(snowflake, character));
    }

    public static void updateByString(String updateString){
        try (Connection connection = DriverManager.getConnection(connectionUrl, "root", "password1");
             PreparedStatement statement = connection.prepareStatement(updateString);
        ) {
            statement.execute();
        } catch (SQLTimeoutException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String updateInventoryString(Snowflake snowflake, Character character){
        long newId = snowflake.asLong();
        String updateInventory = "UPDATE rpg4j.inventory "
                + "SET max_capacity = '" + character.getInventory().getMaxCapacity() + "'"
                + inventoryListSaver(character)
                + " WHERE Snowflake = " + "'" + newId + "';";
        //System.out.println(updateInventory);
        return updateInventory;
    }

    private static String inventoryListSaver(Character character){
        ArrayList<Item> itemList = character.getInventory().getItemList();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < itemList.size(); i++){
            stringBuilder.append(", i" + i + " = '" + doubleTheApostrophe(character.getInventory().getItemList().get(i).getName()) + "'");
        }
        for (int i = itemList.size(); i < 15; i++){
            stringBuilder.append(", i" + i + " = NULL");
        }
        return stringBuilder.toString();
    }

    private static String updateHealthString(Snowflake snowflake, Character character){
        long newId = snowflake.asLong();
        String updateHealth = "UPDATE rpg4j.health "
                + "SET max_health = '" + character.getHealth().getMax() + "', "
                + "current_health =" + "'" + character.getHealth().get() + "', "
                + "health_regen =" + "'" + character.getHealth().getRegen() + "' "
                + "WHERE Snowflake = " + "'" + newId + "';";
        System.out.println(updateHealth);
        return updateHealth;
    }

    private static String updateEquipmentString(Snowflake snowflake, Character character){
        long newId = snowflake.asLong();
        String updateEquipment = "UPDATE rpg4j.equipment "
                + "SET head = '" + character.getEquipment().getHeadEquipment().getName() + "'"
                + ", torso = '" + character.getEquipment().getTorsoEquipment().getName() + "'"
                + ", legs = '" + character.getEquipment().getLegsEquipment().getName() + "'"
                + ", feet = '" + character.getEquipment().getFeetEquipment().getName() + "'"
                + ", hands = '" + character.getEquipment().getHandsEquipment().getName() + "'"
                + ", firstHand = '" + character.getEquipment().getFirstHandEquipment().getName() + "'"
                + ", secondHand = '" + character.getEquipment().getSecondHandEquipment().getName() + "'"
                + " WHERE Snowflake = " + "'" + newId + "';";
        System.out.println(updateEquipment);
        return updateEquipment;
    }
}


    /*
            try (Connection conn = DriverManager.getConnection(connectionUrl, "root", "password1");
        PreparedStatement ps = conn.prepareStatement(selectAll("ids"));
        ResultSet rs = ps.executeQuery()) {
            loadIdResults(rs, characterManager);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
     */

    /*
      try (Connection conn = DriverManager.getConnection(connectionUrl, "root", "password1");
        PreparedStatement ps = conn.prepareStatement(selectAll("equipment"));
        ResultSet rs = ps.executeQuery()) {
            loadEquipmentResults(rs, characterManager);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NotWearableItemException e) {
            throw new RuntimeException(e);
        }
    */

    /*
     try (Connection conn = DriverManager.getConnection(connectionUrl, "root", "password1");
        PreparedStatement ps = conn.prepareStatement(selectAll("health"));
        ResultSet rs = ps.executeQuery()) {
            loadHealthResults(rs, characterManager);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
     */
