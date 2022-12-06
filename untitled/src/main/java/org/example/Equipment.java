package org.example;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

class Equipment{
    private Item headEquipment = ManagerItem.NO_HELMET;
    private Item torsoEquipment = ManagerItem.NO_ARMOR;
    private Item legsEquipment = ManagerItem.NO_TROUSERS;
    private Item handsEquipment = ManagerItem.NO_GLOVES;
    private Item feetEquipment = ManagerItem.NO_BOOTS;
    private Item firstHandEquipment = ManagerItem.NO_WEAPON;
    private Item secondHandEquipment = ManagerItem.NO_SHIELD;

    private ArrayList<Item> readAsList(){
        ArrayList<Item> arrayList = new ArrayList<>();
        arrayList.add(headEquipment);
        arrayList.add(torsoEquipment);
        arrayList.add(legsEquipment);
        arrayList.add(handsEquipment);
        arrayList.add(feetEquipment);
        arrayList.add(firstHandEquipment);
        arrayList.add(secondHandEquipment);
        return arrayList;
    }

    public int getTotalDefence(){ return this.readAsList().stream().collect(Collectors.summingInt(Item::getDefence)); }
    public int getTotalMinAttack(){ return this.readAsList().stream().collect(Collectors.summingInt(Item::getMinAttack)); }
    public int getTotalMaxAttack(){ return this.readAsList().stream().collect(Collectors.summingInt(Item::getMaxAttack)); }
    public int getTotalWeight(){
        return this.readAsList().stream().collect(Collectors.summingInt(Item::getWeight));
    }


    public void equip(Item item) throws NotWearableItemException {
        if (item != null){
            if (item.getWearable() == Wearable.HEAD)
                headEquipment = item;
            else if (item.getWearable() == Wearable.TORSO)
                torsoEquipment = item;
            else if (item.getWearable() == Wearable.LEGS)
                legsEquipment = item;
            else if (item.getWearable() == Wearable.FEET)
                feetEquipment = item;
            else if (item.getWearable() == Wearable.HANDS)
                handsEquipment = item;
            else if (item.getWearable() == Wearable.FIRSTHAND)
                firstHandEquipment = item;
            else if (item.getWearable() == Wearable.SECONDHAND)
                secondHandEquipment = item;
            else
                throw new NotWearableItemException();
        }
    }

    public Optional<Item> get(String name) throws NoSuchItemInEquipmentException {
        if (headEquipment.getName().equalsIgnoreCase(name) && headEquipment.isReal())
            return Optional.ofNullable(headEquipment);
        else if (torsoEquipment.getName().equalsIgnoreCase(name) && torsoEquipment.isReal())
            return Optional.ofNullable(torsoEquipment);
        else if (legsEquipment.getName().equalsIgnoreCase(name) && legsEquipment.isReal())
            return Optional.ofNullable(legsEquipment);
        else if (handsEquipment.getName().equalsIgnoreCase(name) && handsEquipment.isReal())
            return Optional.ofNullable(handsEquipment);
        else if (feetEquipment.getName().equalsIgnoreCase(name) && feetEquipment.isReal())
            return Optional.ofNullable(feetEquipment);
        else if (firstHandEquipment.getName().equalsIgnoreCase(name) && firstHandEquipment.isReal())
            return Optional.ofNullable(firstHandEquipment);
        else if (secondHandEquipment.getName().equalsIgnoreCase(name) && secondHandEquipment.isReal())
            return Optional.ofNullable(secondHandEquipment);
        else
            return Optional.ofNullable(null);
    }

    public Item get(Wearable wearable){
        if (wearable == Wearable.HEAD)
            return headEquipment;
        else if (wearable == Wearable.TORSO)
            return torsoEquipment;
        else if (wearable == Wearable.LEGS)
            return legsEquipment;
        else if (wearable == Wearable.HANDS)
            return handsEquipment;
        else if (wearable == Wearable.FEET)
            return feetEquipment;
        else if (wearable == Wearable.FIRSTHAND)
            return firstHandEquipment;
        else if (wearable == Wearable.SECONDHAND)
            return secondHandEquipment;
        return null;
    }

    public boolean remove(String name) {
        if (headEquipment.getName().equalsIgnoreCase(name)){
                this.headEquipment = ManagerItem.get("NO_HELMET").get();
                return true;
        }
        else if (torsoEquipment.getName().equalsIgnoreCase(name)){
                this.torsoEquipment = ManagerItem.get("NO_ARMOR").get();
                return true;
        }
        else if (legsEquipment.getName().equalsIgnoreCase(name)){
                this.legsEquipment = ManagerItem.get("NO_TROUSERS").get();
                return true;
        }
        else if (handsEquipment.getName().equalsIgnoreCase(name)){
                this.handsEquipment = ManagerItem.get("NO_GLOVES").get();
                return true;
        }
        else if (feetEquipment.getName().equalsIgnoreCase(name)){
                this.feetEquipment = ManagerItem.get("NO_BOOTS").get();
                return true;
        }
        else if (firstHandEquipment.getName().equalsIgnoreCase(name)){
                this.firstHandEquipment = ManagerItem.get("NO_WEAPON").get();
                return true;
        }
        else if (secondHandEquipment.getName().equalsIgnoreCase(name)){
                this.secondHandEquipment = ManagerItem.get("NO_SHIELD").get();
                return true;
        }
        return false;
    }



    public Item getHeadEquipment() {
        return headEquipment;
    }
    public Item getTorsoEquipment() {
        return torsoEquipment;
    }
    public Item getLegsEquipment() {
        return legsEquipment;
    }
    public Item getHandsEquipment() {
        return handsEquipment;
    }
    public Item getFeetEquipment() {
        return feetEquipment;
    }
    public Item getFirstHandEquipment() {
        return firstHandEquipment;
    }
    public Item getSecondHandEquipment() {
        return secondHandEquipment;
    }
}


/*

        return readAsList().stream().collect(Collectors.summingInt(Item::getWeight));

 */


/*
    private ArrayList<Item> getEquipmentList(){
        ArrayList<Item> arrayList = new ArrayList<>();
        arrayList.add(headEquipment);
        arrayList.add(torsoEquipment);
        arrayList.add(legsEquipment);
        arrayList.add(handsEquipment);
        arrayList.add(feetEquipment);
        arrayList.add(firstHandEquipment);
        arrayList.add(secondHandEquipment);
        return arrayList;
    }
*/


