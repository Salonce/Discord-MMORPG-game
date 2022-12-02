package org.example;

import discord4j.common.util.Snowflake;

import static org.example.MessageProcessor.getCharacterManager;

class Relocator{
    public static void takeOff(Character character, String itemName) throws InventoryFullException, NoSuchItemInEquipmentException {
        Item item = character.getEquipment().get(itemName);
        if (item.isEquipment()) {
            character.getInventory().add(item);
            character.getEquipment().remove(itemName);
        }
    }

    public static void equip(Character character, String itemName) throws NotWearableItemException, InventoryFullException, NoSuchItemInInventoryException {
        Item itemOn = character.getInventory().get(itemName);
        Item itemOff = character.getEquipment().get(itemOn.getWearable());

        character.getEquipment().equip(itemOn);
        character.getInventory().remove(itemName);
        character.getInventory().add(itemOff);
    }

    public static void drop(Character character, String itemName) throws NoSuchItemInInventoryException {
        character.getInventory().remove(itemName);
    }

    public static int sell(Character character, String itemName) throws NoSuchItemInInventoryException {
        Item itemToSell = character.getInventory().get(itemName);
        character.getInventory().remove(itemName);
        character.getInventory().addMoney(itemToSell.getValue());
        return itemToSell.getValue();
    }

    public static void give(Character character, Snowflake receiver, String itemName) throws NoSuchCharacterException, InventoryFullException, NoSuchItemInInventoryException {
        Item item = character.getInventory().get(itemName);
        getCharacterManager().getCharacterById(receiver).getInventory().add(item);
        character.getInventory().removeItem(item);
    }
    /// public static int give(Character character, String userId, String itemName)
}
