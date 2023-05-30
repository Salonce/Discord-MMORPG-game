package org.example.Presentation;

import discord4j.core.object.entity.Message;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class AnswerManager {
    private ArrayList<MessageProcessor> messageProcessorList = new ArrayList<>();

    public AnswerManager() {
        messageProcessorList.add(new CallHelp());
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
