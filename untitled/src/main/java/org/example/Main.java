package org.example;
import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;

public class Main {
    public static void main(String[] args) {
        //log the bot in
        final String token = args[0];
        final DiscordClient client = DiscordClient.create(token);
        final GatewayDiscordClient gateway = client.login().block();

        //create a list of characters for users
        final AnswerManager answerManager = new AnswerManager();
        final CharacterManager characterManager = new CharacterManager("YES");
            Model.addAllToUsersDb();
            Model.loadAllIds(characterManager);
        final Shop basicShop = new BasicShop();

        MessageProcessor.setCharacterManager(characterManager);
        MessageProcessor.setDiscordClient(client);
        MessageProcessor.setShops(basicShop);



        gateway.on(MessageCreateEvent.class).subscribe(event -> {
            answerManager.process(event.getMessage());
        });
        gateway.onDisconnect().block();
    }
}