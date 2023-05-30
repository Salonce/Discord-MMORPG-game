package org.example.Business;

import org.example.Exceptions.InventoryFullException;
import org.example.Exceptions.NoSuchItemInInventoryException;

import java.util.*;
import java.util.stream.Collectors;


public class Inventory {
    public static int DEFAULT_MAX_CAPACITY = 12;

    public int getMaxCapacity() {
        return max_capacity;
    }
    public void setMaxCapacity(int max_capacity) {
        this.max_capacity = max_capacity;
    }

    private int max_capacity;


    public Inventory(){
        itemList = new ArrayList<>();
        this.money = 0;
        this.max_capacity = DEFAULT_MAX_CAPACITY;
    }

    private ArrayList<Item> itemList;
    public ArrayList<Item> getItemList(){
        return itemList;
    }

    private int money;
    public int getMoney(){return money;}
    public void setMoney(int money){
        this.money = money;
    }
    public void addMoney(int money){
        this.money =+ money;
    }

    public int getSize(){
        return itemList.size();
    }

    public boolean freeSpace(){
        return (itemList.size() < max_capacity) ? true : false;
    }

    public void add(Item item) throws InventoryFullException {
        if (freeSpace())
            itemList.add(item);
        else
            throw new InventoryFullException("Inventory is full. Can't pick up the item.");
    }

    public int addItems(ArrayList<Item> itemsToAdd) throws InventoryFullException {
        int number = 0;
        Iterator<Item> newItemsIterator= itemsToAdd.iterator();
        try {
            while (newItemsIterator.hasNext()) {
                add(newItemsIterator.next());
                number++;
            }
        }
        catch(InventoryFullException e){
            return number;
        }
        return number;
    }

    public Item get(String name) throws NoSuchItemInInventoryException {
        try {
            return itemList.stream().filter(item -> item.getName().equalsIgnoreCase(name)).findAny().get();
        } catch (NoSuchElementException e){
            throw new NoSuchItemInInventoryException();
        }
    }
    public int getItemsWeight(){return itemList.stream().map(Item::getWeight).collect(Collectors.summingInt(Integer::intValue));}
    public int getItemsValue(){return itemList.stream().map(Item::getValue).collect(Collectors.summingInt(Integer::intValue));}
    public void remove(String name) throws NoSuchItemInInventoryException {itemList.remove(get(name));}
    public void removeItem(Item item){
        itemList.remove(item);
    }
    public void sortByName(){
        itemList.sort(Comparator.comparing(Item::getName));
    }
    public void sortByValue(){
        itemList.sort(Comparator.comparing(Item::getValue));
    }
    public void sortByValueReversed(){
        itemList.sort(Comparator.comparing(Item::getValue).reversed());
    }
    public void swap(int a, int b) throws IndexOutOfBoundsException { Collections.swap(itemList, a, b);}
}