package org.example.Presentation;

import java.util.*;

import discord4j.common.util.Snowflake;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;

import org.example.Business.*;
import org.example.Business.WorldManagers.ManagerDungeon;
import org.example.Database.Database;
import org.example.Exceptions.*;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

class CallGive extends MessageProcessor{
    public void process(){
        try{
            String[] content = getContent().split(" ", 3);
            if (content[0].equals(".give")){
                getCharacterManager().createCharAndPutInDb(getId());

                String itemName = content[2];
                Snowflake secondId = Snowflake.of(content[1]);

                Relocator.give(getCharacter(), secondId, itemName);
                Database.updateUserInventory(getId(), getCharacter());
                Database.updateUserInventory(secondId, getCharacterManager().getCharacterById(secondId));
                sendMessage(getUserName() + " gives " + itemName + " to " + getUsernameBySnowflake(secondId));
            }
        } catch (NoSuchCharacterException ex) {
            sendMessage("Make a character first!");
        } catch (NumberFormatException e){
            sendMessage("Wrong ID");
        } catch (InventoryFullException e) {
            sendMessage( "Inventory of that person is currently full.");
        } catch (NullPointerException e){
            sendMessage("Something went wrong with receiver's name");
        } catch (NoSuchItemInInventoryException e) {
            sendMessage("You don't have that item in your inventory!");
        } catch (Exception e){
            sendMessage("Unknown exception");
        }
    }
}

class CallHelp extends MessageProcessor{
    private String getHelp(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("\n\n**General:**")
                .append("\n.help")
                .append("\n.stats")
                .append("\n.hospital")

                .append("\n\n**Inventory:**")
                .append("\n.inv")
                .append("\n.eq")
                .append("\n.equip *itemname*")
                .append("\n.unequip *itemname*")
                .append("\n.drop *itemname*")
                .append("\n.give *userID* *itemname*")

                .append("\n\n**Inventory sorting:**")
                .append("\n.sortval")
                .append("\n.sortname")
                .append("\n.swap num1 num2 - swaps item places")

                .append("\n\n**Dungeons:**")
                .append("\n.cave")
                .append("\n.server")
                .append("\n.forest");

        return stringBuilder.toString();
    }
    public void process(){
        if (getContent().equals(".help")){
            getCharacterManager().createCharAndPutInDb(getId());
            sendPlainMessage(getHelp());
        }
    }
}
class CallCharInfo extends MessageProcessor{
    public void process(){
        if (getContent().equals(".stats") && characterCheck()){
            EmbedCreateSpec embed = EmbedCreateSpec.builder()
                .color(Color.BLUE)
                .addField("Stats", ":hearts: " + getCharacter().getHealth().get() + "/" + getCharacter().getHealth().getMax() + "  :arrow_heading_up: " + getCharacter().getHealth().getRegen() + "p / 1s"

                        + "\n:hiking_boot: " + getCharacter().getActionPoints().getCurrentAP() + "/" + ActionPoints.MAX_AP + "  :arrow_heading_up: 1p / " + getCharacter().getActionPoints().AP_RECOVERY_TIME + "s"

                        //+ "\n:dagger: " + getCharacter().getEquipment().getTotalMinAttack() + "-" + getCharacter().getEquipment().getTotalMaxAttack()
                        //+ "\n:shield: " + getCharacter().getEquipment().getTotalDefence()
                        //+ "\n:athletic_shoe: " + getCharacter().getStats().getSpeed()
                        , false)
                .author(getUserName(), null, getUserAvatarUrl())
                .build();
            sendMessage(embed);
        }
    }
}

class CallTester extends MessageProcessor {
    public void process() {
        try {
            if (getContent().equals(".test")) {
                //if character not created - create it - add it to the hashlist
                getCharacterManager().createCharAndPutInDb(getId());
                sendMessage("Tester applied!");
            }
        } catch (Exception e) {
        }
    }
}



class CallEquip extends MessageProcessor{
    public void process(){
        try{
            String[] content = getContent().split(" ", 2);
            if (content[0].equals(".equip")){
                getCharacterManager().createCharAndPutInDb(getId());

                Relocator.equip(getCharacter(), content[1]);
                Database.updateUserInventory(getId(), getCharacter());
                Database.updateUserEquipment(getId(), getCharacter());
                sendMessage(getUserName() + " equipped " + content[1]);
            }
        } catch (NoSuchItemInInventoryException e) {
            sendMessage("You don't have that item in your inventory!");
        }
        catch (Exception ignored){
        }
    }
}

class CallUnequip extends MessageProcessor{
    public void process(){
        try{
            String[] content = getContent().split(" ", 2);
            if (content[0].equals(".unequip")) {
                getCharacterManager().createCharAndPutInDb(getId());

                Relocator.takeOff(getCharacter(), content[1]);
                Database.updateUserInventory(getId(), getCharacter());
                Database.updateUserEquipment(getId(), getCharacter());
                sendMessage(getUserName() + " unequipped " + content[1]);
            }
        }
        catch (IndexOutOfBoundsException e){
        } catch (NoSuchItemInEquipmentException e) {
            sendMessage("You don't have that item in your equipment!");
        } catch (Exception e){}
    }
}

class CallDrop extends MessageProcessor{
    public void process(){
        try{
            String[] content = getContent().split(" ", 2);
            if (content[0].equals(".drop")){
                getCharacterManager().createCharAndPutInDb(getId());

                Relocator.drop(getCharacter(), content[1]);
                Database.updateUserInventory(getId(), getCharacter());
                sendMessage(getUserName() + " dropped " + content[1]);
            }
        } catch (NoSuchItemInInventoryException e) {
            sendMessage("You don't have that item in your inventory!");
        }  catch (Exception e){
        }
    }
}

class CallSell extends MessageProcessor{
    public void process(){
        try{
            String[] content = getContent().split(" ", 2);
            if (content[0].equalsIgnoreCase(".sell")){
                getCharacterManager().createCharAndPutInDb(getId());

                int cash = Relocator.sell(getCharacter(), content[1]);
                Database.updateUserInventory(getId(), getCharacter());
                sendMessage(getUserName() + " sold " + content[1] + " for " + cash);
            }
        } catch (NoSuchItemInInventoryException e) {
            sendMessage("You don't have that item in your inventory!");
        }
        catch (Exception e){}
    }
}

class CallShop extends MessageProcessor{
    public void process(){
        if (getContent().equals(".shop")){
            getCharacterManager().createCharAndPutInDb(getId());
            sendMessage(getShop().itemsAvailable());
        }
    }
}

class CallI extends MessageProcessor{
    final static int INV_MAX_CHAR = 15;

    public void process(){
        if (getContent().equals(".inv")){
            getCharacterManager().createCharAndPutInDb(getId());
            EmbedCreateSpec.Builder embedBuilder = EmbedCreateSpec.builder()
                    .color(Color.BROWN)
                    .author(getUserName() + " : Inventory (" + getCharacter().getInventory().getSize() + "/" + Inventory.DEFAULT_MAX_CAPACITY + ")", null, getUserAvatarUrl())
                    //.addField("Total"
                    //        , ":coin: " + getCharacter().getInventory().getMoney()
                    //                + " :scales: " + getCharacter().getInventory().getItemsWeight(), false)
                    .thumbnail("https://openclipart.org/image/800px/330656");

            embedBuilder.addField(emptySpaces(1), emptySpaces(1), false);
            for (Item item : getCharacter().getInventory().getItemList()){
                embedBuilder.addField(addSpaces("__**" + item.getName() + "**__", INV_MAX_CHAR), getItemInfo(item), false);
            }
            sendMessage(embedBuilder.build());
        }
    }
    private String getItemInfo(Item item){
        int empty = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n:coin: " + item.getValue());
        //stringBuilder.append("\n:scales: " + item.getWeight());
        //stringBuilder.append("\n\u2800");
        if (item.hasAttack())
            stringBuilder.append(", :dagger: " + item.getMinAttack() + "-" + item.getMaxAttack() + "  ");
        else
            empty++;
        if (item.hasDefense()){
            stringBuilder.append(", :shield: " + item.getDefence() + "  ");
        } else
            empty++;
        int a = 0;
        while (a < empty){
            stringBuilder.append("\n\u2800");
            a++;
        }
        return stringBuilder.toString();
    }
}

class CallEquipmentInfo extends MessageProcessor{
    private final static int EMPTY_SPACE_COUNT = 13;
    public void process(){
        if (getContent().equals(".eq")){
            getCharacterManager().createCharAndPutInDb(getId());
            EmbedCreateSpec embed = EmbedCreateSpec.builder()
                .color(Color.BLUE)
                .addField(addSpaces(emptySpaces(EMPTY_SPACE_COUNT), EQ_MAX_CHAR), getEmbedStats(":billed_cap:", getCharacter().getEquipment().getHeadEquipment()), true)
                .addField(addSpaces(emptySpaces(EMPTY_SPACE_COUNT), EQ_MAX_CHAR), getEmbedStats(":shirt:", getCharacter().getEquipment().getTorsoEquipment()), true)
                .addField(addSpaces(emptySpaces(EMPTY_SPACE_COUNT), EQ_MAX_CHAR), getEmbedStats(":jeans:", getCharacter().getEquipment().getLegsEquipment()), true)
                .addField(addSpaces(emptySpaces(EMPTY_SPACE_COUNT), EQ_MAX_CHAR), getEmbedStats(":boot:", getCharacter().getEquipment().getFeetEquipment()), true)
                .addField(addSpaces(emptySpaces(EMPTY_SPACE_COUNT), EQ_MAX_CHAR), getEmbedStats(":gloves:", getCharacter().getEquipment().getHandsEquipment()), true)
                .addField(addSpaces(emptySpaces(EMPTY_SPACE_COUNT), EQ_MAX_CHAR), getEmbedStats(":leftwards_hand:",getCharacter().getEquipment().getFirstHandEquipment()), true)
                .addField(addSpaces(emptySpaces(EMPTY_SPACE_COUNT), EQ_MAX_CHAR), getEmbedStats(":rightwards_hand:",getCharacter().getEquipment().getSecondHandEquipment()), true)
                .addField(emptySpaces(EMPTY_SPACE_COUNT), emptySpaces(EMPTY_SPACE_COUNT), true)
                //.addField("Total", ":shield: " + getCharacter().getEquipment().getTotalDefence()
                //        + "\n:dagger: " + getCharacter().getEquipment().getTotalMinAttack() + "-" + getCharacter().getEquipment().getTotalMaxAttack()
                //        + "\n:scales: " + getCharacter().getEquipment().getTotalWeight(), true)
                .author(getUserName() + " - Equipment", null, getUserAvatarUrl())
                .build();
            sendMessage(embed);
        }
    }
    final static int EQ_MAX_CHAR = 13;
    private String getEmbedStats(String place, Item item){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(place);
        if (!item.isEmptyEq) {
            stringBuilder.append(" __**" + item.getName() + "**__");
            //stringBuilder.append("\n:coin: " + item.getValue());
            stringBuilder.append("\n:scales: " + item.getWeight() + "  ");
            if (item.hasAttack()) {
                stringBuilder.append(", :dagger: " + item.getMinAttack() + "-" + item.getMaxAttack() + "  ");
            }
            if (item.hasDefense()) {
                stringBuilder.append(", :shield: " + item.getDefence() + "  ");
            }

        }
        else
        //stringBuilder.append(" __**(empty)**__");
        stringBuilder.append(" (empty)");
        return stringBuilder.toString();
    }
}
//":crossed_swords: " + getCharacter().getCombatPower()
//":crossed_swords: " + newMonster.getCombatPower()
class CallRat extends MessageProcessor{
    private final static int AP_FOR_QUEST = 1;
    public void process(){
        Dungeon dungeon = getDungeon(getContent());
        try {
            if (dungeon != null) {
                getCharacterManager().createCharAndPutInDb(getId());
                getCharacter().getActionPoints().addCooldown(AP_FOR_QUEST);
                Monster newMonster = dungeon.getMonster();
                Fight fight = new Fight(getCharacter(), newMonster);
                ArrayList<Item> randomLoot = newMonster.getRandomLoot();
                EmbedCreateSpec.Builder embedBuilder = EmbedCreateSpec.builder()
                    .color(Color.BLUE)
                    .author(getUserName() + " uses " + AP_FOR_QUEST + " action points", null, getUserAvatarUrl())
                    .addField(newMonster.getName(), newMonster.getDescription(), false)
                    .addField(getUserName(),
                            "\n:hearts: " + getCharacter().getCombatStrength().getHealth() + "/" + getCharacter().getHealth().getMax()
                            + "\n:dagger: " + getCharacter().getCombatStrength().getMinAttack() + "-" + getCharacter().getCombatStrength().getMaxAttack()
                            + "\n:shield: " + getCharacter().getCombatStrength().getDefense()
                            //+ "\n:hiking_boot: " + getCharacter().getCombatStrength().getSpeed()
                            + "\n\u2800", true)
                    .addField(newMonster.getName(),

                             "\n:hearts: " + newMonster.getCombatStrength().getHealth() + "/" + newMonster.getCombatStrength().getHealth()
                            + "\n:dagger: " + newMonster.getCombatStrength().getMinAttack() + "-" + newMonster.getCombatStrength().getMaxAttack()
                            + "\n:shield: " + newMonster.getCombatStrength().getDefense()
                            //+ "\n:hiking_boot: " + newMonster.getCombatStrength().getSpeed()
                            , true)
                    .addField(":clipboard:", printFightResult(fight, newMonster), false);
                Database.updateUserHealth(getId(), getCharacter());
                if (fight.getResults().getResultA().isVictory()) {
                    int lootNumber = getCharacter().getInventory().addItems(randomLoot);
                    embedBuilder.addField(":palm_down_hand:", printLoot(randomLoot, lootNumber), false);
                    Database.updateUserInventory(getId(), getCharacter());
                }

                sendMessage(embedBuilder.build());
            }
        } catch (InventoryFullException e){
            sendMessage("Not all looted");
        } catch (NoSuchMonsterException e) {
            sendMessage("You haven't found find anything!");
        } catch (NotEnoughActionPointsException e) {
            sendMessage("You need 2 action points to do that!");
        }

    }
    private String printFightResult(Fight fight, Monster monster){
        StringBuilder stringBuilder = new StringBuilder();
        if (fight.getResults().getResultA().isVictory()){
            stringBuilder.append(monster.getName() + " :skull:");
            //stringBuilder.append("\n" + getUserName() + " :broken_heart:" + (getCharacter().getCombatStrength().getHealth() - fight.getResults().getResultA().getHealth()));
            stringBuilder.append("\n" + getUserName() + " :hearts:" + fight.getResults().getResultA().getHealth() + " (:broken_heart:" + (getCharacter().getCombatStrength().getHealth() - fight.getResults().getResultA().getHealth()) + ")");
            //stringBuilder.append(":hearts:" + fight.getResults().getResultA().getHealth() + " left.");
            }
        else {
            stringBuilder.append(getUserName() + " :skull:");
            stringBuilder.append("\n" + monster.getName() + " :hearts:" + fight.getResults().getResultB().getHealth() + " left. " + "(:broken_heart:" + (monster.getCombatStrength().getHealth() - fight.getResults().getResultB().getHealth()) + ")");

            //stringBuilder.append(getUserName() + " dies.");
            //stringBuilder.append("\n" + monster.getName() + " has :hearts:" + fight.getResults().getResultB().getHealth() + " left.");
        }
        getCharacter().getHealth().set(fight.getResults().getResultA().getHealth());
        return stringBuilder.toString();
    }

    private String printLoot(ArrayList<Item> lootList, int lootNumber){
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<Item> itemIterator = lootList.iterator();
        int looted = 0;
        while (itemIterator.hasNext()) {
            if (looted < lootNumber){
                stringBuilder.append(" :white_check_mark:");
                looted++;
            }
            else
                stringBuilder.append(" :x:");
            stringBuilder.append(itemIterator.next().getName());

            if (itemIterator.hasNext())
                stringBuilder.append(", ");
        }
        if (stringBuilder.length() == 0)
            stringBuilder.append("-");
        return stringBuilder.toString();
    }

    private Dungeon getDungeon(String command){
        switch (command){
            case ".forest":
                return ManagerDungeon.FOREST;
            case ".lake":
                return ManagerDungeon.LAKE;
            case ".chest":
                return ManagerDungeon.CHESTPLACE;
            case ".server":
                return ManagerDungeon.SERVER;
            case ".cave":
                return ManagerDungeon.CHICKEN_CAVE;
            default:
                return null;
        }
    }
}

////NEW SORTING
class CallSortByName extends MessageProcessor{
    public void process(){
        if (getContent().equals(".sortname")){
            getCharacterManager().createCharAndPutInDb(getId());
            getCharacter().getInventory().sortByName();
            Database.updateUserInventory(getId(), getCharacter());
            sendMessage("Inventory sorted by name.");
        }
    }
}

class CallSortByValue extends MessageProcessor{
    public void process(){
        if (getContent().equals(".sortval")){
            getCharacterManager().createCharAndPutInDb(getId());
            getCharacter().getInventory().sortByValueReversed();
            Database.updateUserInventory(getId(), getCharacter());
            sendMessage("Inventory sorted by value.");
        }
    }
}

class CallHospital extends MessageProcessor{
    public void process(){
        if (getContent().equals(".hospital")){
            getCharacterManager().createCharAndPutInDb(getId());
            getCharacter().getHealth().set(getCharacter().getHealth().getMax());
            sendMessage("You are healed.");
        }
    }
}

class CallSwap extends MessageProcessor{
    public void process(){
        try{
            String[] content = getContent().split(" ", 3);
            if (content[0].equalsIgnoreCase(".swap")){
                getCharacterManager().createCharAndPutInDb(getId());
                int one = Integer.parseInt(content[1]);
                int two = Integer.parseInt(content[2]);
                getCharacter().getInventory().swap(one-1, two-1);
                Database.updateUserInventory(getId(), getCharacter());
                sendMessage("Inventory: *" + getCharacter().getInventory().getItemList().get(two-1).getName() + "* and *" + getCharacter().getInventory().getItemList().get(one-1).getName() + "* have been swapped.");

            }
        } catch (NumberFormatException  e){
            sendMessage("Wrong number!");
        } catch (ArrayIndexOutOfBoundsException e){
            sendMessage("You don't have these items.");
        } catch (IndexOutOfBoundsException e){
            sendMessage("You don't have these items.");
        }
        catch (Exception e){}
    }
}
