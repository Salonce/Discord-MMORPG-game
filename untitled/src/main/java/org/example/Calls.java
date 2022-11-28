package org.example;

import discord4j.common.util.Snowflake;
import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;
import java.time.Instant;
import java.util.*;
import discord4j.core.DiscordClient;
import discord4j.core.object.entity.channel.MessageChannel;
import java.util.NoSuchElementException;

abstract class MessageProcessor{
    public static void setMessage(Message message) {
        try {
            MessageProcessor.id = message.getAuthor().get().getId();
            MessageProcessor.content = message.getContent();
            MessageProcessor.channel = message.getChannel().block();
            MessageProcessor.userName = message.getAuthor().get().getUsername();
            MessageProcessor.userNameId = "<@" + id.asString() + ">";
            MessageProcessor.userAvatarUrl = message.getAuthor().get().getAvatarUrl();
            MessageProcessor.character = characterManager.getCharacterById(id);
        }
        catch(NoSuchElementException noSuchElementException){
           ///
        } catch (NoSuchCharacterException noSuchCharacterException) {
            MessageProcessor.character = null;
        }
    }

    public abstract void process();

    private static MessageChannel channel;

    private static String content;
    protected static String getContent(){return content;}

    private static Snowflake id;
    protected static Snowflake getId(){return id;}

    private static Character character;
    protected static Character getCharacter(){return character;}

    private static CharacterManager characterManager;
    protected static CharacterManager getCharacterManager(){return characterManager;}

    private static String userName;
    protected static String getUserName(){return userName;}

    private static String userNameId;
    protected static String getUserNameId(){return userNameId;}

    private static String userAvatarUrl;
    protected static String getUserAvatarUrl(){return userAvatarUrl;}

    private static DiscordClient discordClient;
    public static void setDiscordClient(DiscordClient discordClient) {
        MessageProcessor.discordClient = discordClient;
    }

    private static Shop shop;
    public static void setShops(Shop shop) {
        MessageProcessor.shop = shop;
    }
    public static Shop getShop(){return MessageProcessor.shop;}


    public String getUsernameBySnowflake(Snowflake id){
        final StringBuilder stringBuilder = new StringBuilder();
        try{
            stringBuilder.append(discordClient.getUserById(id).getData().block().username());
            //discordClient.getUserById(id).getData().subscribe(data -> {stringBuilder.append(data.username()); System.out.println(data.username());});
            return stringBuilder.toString();
        } catch (Exception e){
            System.out.println("exception when catching user");
        }
        return null;
    }

    public static void setCharacterManager(CharacterManager characterManager) {
        MessageProcessor.characterManager = characterManager;
    }

    protected static boolean characterCheck(){
        if (character == null){
            sendMessage("You need to create a character to use that command!");
            return false;
        }
        else return true;
    }
    protected static boolean characterNegativeCheck(){
        if (character != null){
            sendMessage("You can't use that command if you already have a character!");
            return false;
        }
        else return true;
    }
    protected static void sendMessage(EmbedCreateSpec embedMessage){
        channel.createMessage(embedMessage).block();
    }
    protected static void sendMessage(String message){
        EmbedCreateSpec embed = EmbedCreateSpec.builder()
                .author(getUserName(), null, getUserAvatarUrl())
                .title(message)
                .build();
        sendMessage(embed);
    }
    protected static void sendPlainMessage(String message){
        EmbedCreateSpec embed = EmbedCreateSpec.builder()
                .addField("\u2800", message, false)
                .build();
        sendMessage(embed);
    }

    protected static String addSpaces(String string, int maxWidth){
        StringBuilder stringBuilder = new StringBuilder(string);
        while(stringBuilder.length() < maxWidth){
            stringBuilder.append("\u2800");
        }
        return stringBuilder.toString();
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

class CallGive extends MessageProcessor{
    public void process(){
        try{
            String[] content = getContent().split(" ", 3);
            if (content[0].equals(".give") && characterCheck()){
                String itemName = content[2];
                Snowflake secondId = Snowflake.of(content[1]);

                Relocator.give(getCharacter(), secondId, itemName);
                sendMessage(getUserName() + " gives " + itemName + " to " + getUsernameBySnowflake(secondId));
            }
        } catch (NoSuchCharacterException ex) {
            sendMessage("Make a character first!");
        } catch (NumberFormatException e){
            sendMessage("Wrong ID");
        } catch (InventoryFullException e) {
            sendMessage( "Sorry! Can not do! Inventory of receiver is full.");
        } catch (NullPointerException e){
            sendMessage("Sorry! Something went wrong with receiver's name");
        } catch (NoSuchItemException e){
            sendMessage("Sorry! You don't have that item!");
        } catch (Exception e){
            sendMessage("Unknown exception");
        }
    }
}


class CallCharTester extends MessageProcessor{
    public void process(){
        try {
            if (getContent().equals(".tester") && characterNegativeCheck()){
                 getCharacterManager().createNewCharacter(getId());
                 getCharacterManager().getCharacterById(getId()).getInventory().add(ManagerItem.STEEL_SHIELD);
                 getCharacterManager().getCharacterById(getId()).getInventory().add(ManagerItem.STEEL_ARMOR);
                 getCharacterManager().getCharacterById(getId()).getInventory().add(ManagerItem.STEEL_SWORD);


                 sendMessage("Character created!");
            }
        } catch(Exception e){
        }
    }
}

class CallHelp extends MessageProcessor{
    private String getHelp(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("\n\n**Character creation:**")
                .append("\n.tester")
                .append("\n.create")

                .append("\n\n**General:**")
                .append("\n.info")
                .append("\n.ap")
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

                .append("\n\n**Dungeons:**")
                .append("\n.cave")
                .append("\n.server")
                .append("\n.forest");

        return stringBuilder.toString();
    }
    public void process(){
        if (getContent().equals(".help")){
            sendPlainMessage(getHelp());
        }
    }
}

class CallCharInfo extends MessageProcessor{
    public void process(){
        if (getContent().equals(".stats") && characterCheck()){
            EmbedCreateSpec embed = EmbedCreateSpec.builder()
                .color(Color.BLUE)
                .addField("Stats", ":hearts: " + getCharacter().getHealth().get() + "/" + getCharacter().getHealth().getMax()
                        + "\n:hiking_boot: " + getCharacter().getActionPoints().getCurrentAP() + "/" + ActionPoints.MAX_AP
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

class CallCreateCharacter extends MessageProcessor {
    public void process() {
        try {
            if (getContent().equals(".create") && characterNegativeCheck()) {
                getCharacterManager().createNewCharacter(getId());
                Model.insertOneId(getId());
                sendMessage("Character created!");
            }
        } catch (Exception e) {
        }
    }
}

class CallTester extends MessageProcessor {
    public void process() {
        try {
            if (getContent().equals(".test")) {
                Model.insertOneId(getId());
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
            if (content[0].equals(".equip") && characterCheck()){

                Relocator.equip(getCharacter(), content[1]);
                sendMessage(getUserName() + " equipped " + content[1]);
            }
        } catch (NoSuchElementException e){
            sendMessage("You don't have this item in your inventory!");
        }
        catch (Exception ignored){
        }
    }
}

class CallUnequip extends MessageProcessor{
    public void process(){
        try{
            String[] content = getContent().split(" ", 2);
            if (content[0].equals(".unequip") && characterCheck()) {

                Relocator.takeOff(getCharacter(), content[1]);
                sendMessage(getUserName() + " unequipped " + content[1]);
            }
        }
        catch (IndexOutOfBoundsException e){
        } catch (NoSuchElementException e){
            sendMessage("You don't wear that item!");
        } catch (Exception e){}
    }
}

class CallDrop extends MessageProcessor{
    public void process(){
        try{
            String[] content = getContent().split(" ", 2);
            if (content[0].equals(".drop") && characterCheck()){

                Relocator.drop(getCharacter(), content[1]);
                sendMessage(getUserName() + " dropped " + content[1]);
            }
        } catch (NoSuchElementException e){
            sendMessage("You don't have that item in your inventory!");
        } catch (Exception e){
        }
    }
}

class CallSell extends MessageProcessor{
    public void process(){
        try{
            String[] content = getContent().split(" ", 2);
            if (content[0].equalsIgnoreCase(".sell") && characterCheck()){

                int cash = Relocator.sell(getCharacter(), content[1]);
                sendMessage(getUserName() + " sold " + content[1] + " for " + cash);
            }
        } catch (NoSuchElementException e){
            sendMessage("You don't have this item!");
        }
        catch (Exception e){}
    }
}

class CallCooldowns extends MessageProcessor{
    final static int AP_MAX_CHAR = 13;
    private String getField(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("**Available**: " + getCharacter().getActionPoints().getCurrentAP() + "/" + ActionPoints.MAX_AP + " (next in "  + (getCharacter().getActionPoints().getFirstCD()) + "s)")
                .append("\n")
                .append("\n**Regeneration**: 1 per " + getCharacter().getActionPoints().AP_RECOVERY_TIME + "s");

        return stringBuilder.toString();
    }

    public void process(){
        if (getContent().equals(".ap") && characterCheck()){
            EmbedCreateSpec embed = EmbedCreateSpec.builder()
                    .color(Color.BLUE)
                    .author(getUserName() + " - action points", null, getUserAvatarUrl())
                    .addField("\u2800", getField(), false)
                    .build();
            sendMessage(embed);
        }
    }
}


class CallShop extends MessageProcessor{
    public void process(){
        if (getContent().equals(".shop") && characterCheck()){
            sendMessage(getShop().itemsAvailable());
        }
    }
}

class CallI extends MessageProcessor{
    final static int INV_MAX_CHAR = 15;

    public void process(){
        if (getContent().equals(".i") && characterCheck()){
            EmbedCreateSpec.Builder embedBuilder = EmbedCreateSpec.builder()
                    .color(Color.BROWN)
                    .author(getUserName() + " : Inventory (" + getCharacter().getInventory().getSize() + "/" + Inventory.MAX_ITEM_NUMBER + ")", null, getUserAvatarUrl())
                    //.addField("Total"
                    //        , ":coin: " + getCharacter().getInventory().getMoney()
                    //                + " :scales: " + getCharacter().getInventory().getItemsWeight(), false)
                    .thumbnail("https://openclipart.org/image/800px/330656");
            for (Item item : getCharacter().getInventory().getItemList()){
                embedBuilder.addField(addSpaces(item.getName(), INV_MAX_CHAR), getItemInfo(item), true);
            }
            sendMessage(embedBuilder.build());
        }
    }
    private String getItemInfo(Item item){
        int empty = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n:coin: " + item.getValue());
        //stringBuilder.append("\n:scales: " + item.getWeight());
        stringBuilder.append("\n\u2800");
        if (item.hasAttack())
            stringBuilder.append("\n:dagger: " + item.getMinAttack() + "-" + item.getMaxAttack());
        else
            empty++;
        if (item.hasDefense()){
            stringBuilder.append("\n:shield: " + item.getDefence());
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
    public void process(){
        if (getContent().equals(".eq") && characterCheck()){
            EmbedCreateSpec embed = EmbedCreateSpec.builder()
                .color(Color.BLUE)
                .addField(addSpaces("Head", EQ_MAX_CHAR), getEmbedStats(getCharacter().getEquipment().getHeadEquipment()), true)
                .addField(addSpaces("Torso", EQ_MAX_CHAR), getEmbedStats(getCharacter().getEquipment().getTorsoEquipment()), true)
                .addField(addSpaces("Legs", EQ_MAX_CHAR), getEmbedStats(getCharacter().getEquipment().getLegsEquipment()), true)
                .addField(addSpaces("Feet", EQ_MAX_CHAR), getEmbedStats(getCharacter().getEquipment().getFeetEquipment()), true)
                .addField(addSpaces("Hands", EQ_MAX_CHAR), getEmbedStats(getCharacter().getEquipment().getHandsEquipment()), true)
                .addField(addSpaces("1st hand", EQ_MAX_CHAR), getEmbedStats(getCharacter().getEquipment().getFirstHandEquipment()), true)
                .addField(addSpaces("2nd hand", EQ_MAX_CHAR), getEmbedStats(getCharacter().getEquipment().getSecondHandEquipment()), true)
                .addField("Total", ":shield: " + getCharacter().getEquipment().getTotalDefence()
                        + "\n:dagger: " + getCharacter().getEquipment().getTotalMinAttack() + "-" + getCharacter().getEquipment().getTotalMaxAttack()
                        + "\n:scales: " + getCharacter().getEquipment().getTotalWeight(), true)
                .author(getUserName() + " - Equipment", null, getUserAvatarUrl())
                .timestamp(Instant.now())
                .build();
            sendMessage(embed);
        }
    }
    final static int EQ_MAX_CHAR = 13;
    private String getEmbedStats(Item item){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(item.getName());
        //stringBuilder.append("\n:coin: " + item.getValue());
        stringBuilder.append("\n:scales: " + item.getWeight());
        if (item.hasAttack()){
            stringBuilder.append("\n:dagger: " + item.getMinAttack() + "-" + item.getMaxAttack());
        }
        if (item.hasDefense()){
            stringBuilder.append("\n:shield: " + item.getDefence());
        }
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
            if (dungeon != null && characterCheck()) {
                getCharacter().getActionPoints().addCooldown(AP_FOR_QUEST);
                Monster newMonster = dungeon.getMonster();
                Fight fight = new Fight(getCharacter(), newMonster);
                ArrayList<Item> randomLoot = newMonster.getRandomLoot();
                EmbedCreateSpec.Builder embedBuilder = EmbedCreateSpec.builder()
                    .color(Color.BLUE)
                    .author(getUserName() + " uses " + AP_FOR_QUEST + " action points", null, getUserAvatarUrl())
                    .addField(newMonster.getName(), newMonster.getDescription(), false)
                    .addField(getUserName(),
                            "\n:hearts: " + getCharacter().getCombatStrength().getHealth()
                            + "\n:dagger: " + getCharacter().getCombatStrength().getMinAttack() + "-" + getCharacter().getCombatStrength().getMaxAttack()
                            + "\n:shield: " + getCharacter().getCombatStrength().getDefense()
                            + "\n:athletic_shoe: " + getCharacter().getCombatStrength().getSpeed()
                            + "\n\u2800", true)
                    .addField(newMonster.getName(),

                             "\n:hearts: " + newMonster.getCombatStrength().getHealth()
                            + "\n:dagger: " + newMonster.getCombatStrength().getMinAttack() + "-" + newMonster.getCombatStrength().getMaxAttack()
                            + "\n:shield: " + newMonster.getCombatStrength().getDefense()
                            + "\n:athletic_shoe: " + newMonster.getCombatStrength().getSpeed()
                            , true)
                    .addField(":clipboard:", printFightResult(fight, newMonster), false);
                Model.updateOneHealth(getId(), getCharacter());
                if (fight.getResults().getResultA().isVictory()) {
                    int lootNumber = getCharacter().getInventory().addItems(randomLoot);
                    embedBuilder.addField(":palm_down_hand:", printLoot(randomLoot, lootNumber), false);
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
            stringBuilder.append(monster.getName() + " dies.");
            stringBuilder.append("\n" + getUserName() + " has :hearts:" + fight.getResults().getResultA().getHealth() + " left.");
            }
        else {
            stringBuilder.append(getUserName() + " dies.");
            stringBuilder.append("\n" + monster.getName() + " has :hearts:" + fight.getResults().getResultB().getHealth() + " left.");
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
        if (getContent().equals(".sortname") && characterCheck()){
            getCharacter().getInventory().sortByName();
            sendMessage("Inventory sorted by name.");
        }
    }
}

class CallSortByValue extends MessageProcessor{
    public void process(){
        if (getContent().equals(".sortval") && characterCheck()){
            getCharacter().getInventory().sortByValueReversed();
            sendMessage("Inventory sorted by value.");
        }
    }
}

class CallHospital extends MessageProcessor{
    public void process(){
        if (getContent().equals(".hospital") && characterCheck()){
            getCharacter().getHealth().set(getCharacter().getHealth().getMax());
            sendMessage("You are healed.");
        }
    }
}

class CallSwap extends MessageProcessor{
    public void process(){
        try{
            String[] content = getContent().split(" ", 3);
            if (content[0].equalsIgnoreCase(".swap") && characterCheck()){
                int one = Integer.parseInt(content[1]);
                int two = Integer.parseInt(content[2]);
                getCharacter().getInventory().swap(one-1, two-1);
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
////NEW SORTING

class AnswerManager {
    private ArrayList<MessageProcessor> messageProcessorList = new ArrayList<>();

    public AnswerManager() {
        messageProcessorList.add(new CallHelp());
        messageProcessorList.add(new CallCreateCharacter());
        messageProcessorList.add(new CallCharTester());
        messageProcessorList.add(new CallCooldowns());
        messageProcessorList.add(new CallShop());
        messageProcessorList.add(new CallI());
        messageProcessorList.add(new CallCharInfo());
        messageProcessorList.add(new CallEquipmentInfo());
        messageProcessorList.add(new CallEquip());
        messageProcessorList.add(new CallUnequip());
        messageProcessorList.add(new CallDrop());
        messageProcessorList.add(new CallSell());
        messageProcessorList.add(new CallGive());
        messageProcessorList.add(new CallRat());
        messageProcessorList.add(new CallHospital());
        messageProcessorList.add(new CallSwap());
        messageProcessorList.add(new CallSortByName());
        messageProcessorList.add(new CallSortByValue());

        messageProcessorList.add(new CallTester());

    }

    private boolean selfSending(Message message) {
        try {
            return (message.getAuthor().get().getId().asString().equals("772821811707904022"));
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public void process(Message message) {
        if (!selfSending(message)) {
            MessageProcessor.setMessage(message);
            messageProcessorList.forEach(MessageProcessor::process);
        }
    }
}

/*
    public void process(){
        if (getContent().equals(".i") && characterCheck()){
            EmbedCreateSpec.Builder embedBuilder = EmbedCreateSpec.builder()
                    .color(Color.BROWN)
                    .author(getUserName() + " : Inventory (" + getCharacter().getInventory().getSize() + "/" + Inventory.MAX_ITEM_NUMBER + ")", null, getUserAvatarUrl())
                    .thumbnail("https://openclipart.org/image/800px/330656");

            StringBuilder stringBuilder = new StringBuilder("\u2800");
            for (Item item : getCharacter().getInventory().getItemList()){
                stringBuilder.append(getItemInfo(item));
            }
            embedBuilder.addField("\u2800", stringBuilder.toString(), false);
            sendMessage(embedBuilder.build());
        }
    }

    private String getItemInfo(Item item){
        int empty = 0;
        StringBuilder stringBuilder = new StringBuilder("\n");
        stringBuilder.append("\u2800\u2800:coin: " + item.getValue());
        stringBuilder.append("\u2800\u2800:scales: " + item.getWeight());
        //if (item.hasAttack())
            stringBuilder.append("\u2800\u2800 :axe: " + item.getAttack());
        //else
        //    empty++;
        //if (item.hasDefense()){
            stringBuilder.append("\u2800\u2800:shield: " + item.getDefence());
        //} else
        //    empty++;
        //int a = 0;
        //while (a < empty){
        //    stringBuilder.append("\n\u2800");
        //    a++;
        //}
        stringBuilder.append("\u2800\u2800" + item.getName());
        return stringBuilder.toString();
    }
     */


        /*
        if (getContent().equals(".ap") && characterCheck()){
            EmbedCreateSpec embed = EmbedCreateSpec.builder()
                .color(Color.BLUE)
                .author(getUserName() + " - action points", null, getUserAvatarUrl())
                .addField("Available: ", getCharacter().getActionPoints().getCurrentAP() + "/" + ActionPoints.MAX_AP , false)
                .addField("Recovery time: ",  String.valueOf(getCharacter().getActionPoints().AP_RECOVERY_TIME) , false)
                .addField("Next AP in: ", String.valueOf(getCharacter().getActionPoints().getFirstCD()) , false)
                .addField("All AP in: " , String.valueOf(getCharacter().getActionPoints().getLastCD()) , false)
                //.addField(addSpaces("Available", AP_MAX_CHAR), getCharacter().getActionPoints().getCurrentAP() + "/" + ActionPoints.MAX_AP, true)
                //.addField(addSpaces("Recovery time", AP_MAX_CHAR), String.valueOf(getCharacter().getActionPoints().AP_RECOVERY_TIME), true)
                //.addField(addSpaces("Next AP in ", AP_MAX_CHAR), String.valueOf(getCharacter().getActionPoints().getFirstCD()), true)
                //.addField(addSpaces("All AP in ", AP_MAX_CHAR), String.valueOf(getCharacter().getActionPoints().getLastCD()), true)
                //.thumbnail("https://openclipart.org/image/800px/330656")
                .build();
            sendMessage(embed);
        }



                        //Item item = getCharacter().getInventory().get(itemName);
                //getCharacterManager().getCharacterById(secondId).getInventory().add(item);
                //getCharacter().getInventory().removeItem(item);
        */

