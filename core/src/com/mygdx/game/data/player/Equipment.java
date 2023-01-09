package com.mygdx.game.data.player;

import com.mygdx.game.data.inventory.*;

public class Equipment {

    private Slot<Weapon> rightHand;
    private Slot<Shield> leftHand;
    private Slot<Armor> wardrobe;
    private Slot<Item> itemSlot;

    public Equipment(){
        rightHand=new Slot<>();
        leftHand=new Slot<>();
        wardrobe =new Slot<>();
        itemSlot=new Slot<>();
    }
    public void setItemSlot(Item item){
        itemSlot.setObjectItem(item);
    }
    public Item getItemSlot(){
        return itemSlot.getObjectItem();
    }

    public void setRightHand(Weapon weapon) {
        //weapon.setEquipped(true);
        rightHand.setObjectItem(weapon);
    }

    public void setLeftHand(Shield shield) {

        leftHand.setObjectItem(shield);
    }

    public void setWardrobe(Armor armor){
        wardrobe.setObjectItem(armor);
    }

    public Weapon getRightHand(){
        return rightHand.getObjectItem();
    }

    public Shield getLeftHand(){
        return leftHand.getObjectItem();
    }

    public Armor getWardrobe(){
        return wardrobe.getObjectItem();
    }

    private void unEquipWeapon(Weapon weapon){
        weapon.setEquipped(false);
    }
}
