package org.example.Business;

import discord4j.common.util.Snowflake;
import org.example.Exceptions.*;
import org.example.Presentation.MessageProcessor;

import java.util.Optional;

public class Relocator{
    public static void takeOff(Character character, String itemName) throws InventoryFullException, NoSuchItemInEquipmentException {
        //Optional<Item> item2 = ManagerItem.get(itemName);
        //if (item2.isPresent() && item2.get().isEquipment())

        Optional<Item> item = character.getEquipment().get(itemName);
        if (item.isPresent() && item.get().isReal()) {
            character.getInventory().add(item.get());
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
        MessageProcessor.getCharacterManager().getCharacterById(receiver).getInventory().add(item);
        character.getInventory().removeItem(item);
    }
    /// public static int give(Character character, String userId, String itemName)
}
