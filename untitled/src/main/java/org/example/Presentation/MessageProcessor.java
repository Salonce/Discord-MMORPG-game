package org.example.Presentation;

import discord4j.common.util.Snowflake;
import discord4j.core.DiscordClient;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.spec.EmbedCreateSpec;
import org.example.Business.Character;
import org.example.Business.Shop;
import org.example.Business.WorldManagers.CharacterManager;

import java.util.NoSuchElementException;

public abstract class MessageProcessor {
    public static void setMessage(Message message) {
        try {
            MessageProcessor.id = message.getAuthor().get().getId();
            MessageProcessor.content = message.getContent();
            MessageProcessor.channel = message.getChannel().block();
            MessageProcessor.userName = message.getAuthor().get().getUsername();
            MessageProcessor.userNameId = "<@" + id.asString() + ">";
            MessageProcessor.userAvatarUrl = message.getAuthor().get().getAvatarUrl();
            MessageProcessor.character = characterManager.getCharacterById(id);
        } catch (NoSuchElementException noSuchElementException) {
            ///
        }
    }

    public abstract void process();

    private static MessageChannel channel;

    private static String content;

    protected static String getContent() {
        return content;
    }

    private static Snowflake id;

    protected static Snowflake getId() {
        return id;
    }

    //changed
    private static Character character;

    protected static Character getCharacter() {
        return characterManager.getCharacterById(id);
    }

    private static CharacterManager characterManager;

    public static CharacterManager getCharacterManager() {
        return characterManager;
    }

    private static String userName;

    protected static String getUserName() {
        return userName;
    }

    private static String userNameId;

    protected static String getUserNameId() {
        return userNameId;
    }

    private static String userAvatarUrl;

    protected static String getUserAvatarUrl() {
        return userAvatarUrl;
    }

    private static DiscordClient discordClient;

    public static void setDiscordClient(DiscordClient discordClient) {
        MessageProcessor.discordClient = discordClient;
    }

    private static Shop shop;

    public static void setShops(Shop shop) {
        MessageProcessor.shop = shop;
    }

    public static Shop getShop() {
        return MessageProcessor.shop;
    }


    public String getUsernameBySnowflake(Snowflake id) {
        final StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append(discordClient.getUserById(id).getData().block().username());
            //discordClient.getUserById(id).getData().subscribe(data -> {stringBuilder.append(data.username()); System.out.println(data.username());});
            return stringBuilder.toString();
        } catch (Exception e) {
            System.out.println("exception when catching user");
        }
        return null;
    }

    public static void setCharacterManager(CharacterManager characterManager) {
        MessageProcessor.characterManager = characterManager;
    }

    protected static boolean characterCheck() {
        if (character == null) {
            sendMessage("You need to create a character to use that command!");
            return false;
        } else return true;
    }

    protected static boolean characterNegativeCheck() {
        if (character != null) {
            sendMessage("You can't use that command if you already have a character!");
            return false;
        } else return true;
    }

    protected static void sendMessage(EmbedCreateSpec embedMessage) {
        channel.createMessage(embedMessage).block();
    }

    protected static void sendMessage(String message) {
        EmbedCreateSpec embed = EmbedCreateSpec.builder()
                .author(getUserName(), null, getUserAvatarUrl())
                .title(message)
                .build();
        sendMessage(embed);
    }

    protected static void sendPlainMessage(String message) {
        EmbedCreateSpec embed = EmbedCreateSpec.builder()
                .addField("\u2800", message, false)
                .build();
        sendMessage(embed);
    }

    protected static String addSpaces(String string, int maxWidth) {
        StringBuilder stringBuilder = new StringBuilder(string);
        while (stringBuilder.length() < maxWidth) {
            stringBuilder.append("\u2800");
        }
        return stringBuilder.toString();
    }

    protected static String emptySpaces(int number) {
        StringBuilder stringBuilder = new StringBuilder();
        while (stringBuilder.length() < number) {
            stringBuilder.append("\u2800");
        }
        return stringBuilder.toString();
    }
}
