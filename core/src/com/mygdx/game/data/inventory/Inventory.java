package com.mygdx.game.data.inventory;

import com.mygdx.game.data.GameObject;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<ObjectItem> objectItems;

    public Inventory(){
        objectItems=new ArrayList<>();
    }

    public void setObjectItem(ObjectItem objectItem){
        objectItems.add(objectItem);
    }

    public List<ObjectItem> getAllObjectItem(){
        return objectItems;
    }

    public List<Weapon> getAllWeapons(){
        List<Weapon> weaponList=new ArrayList<>();
        for (int i=0;i<objectItems.size();i++){
            if(objectItems.get(i) instanceof Weapon){
                weaponList.add((Weapon) objectItems.get(i));
            }
        }
        return weaponList;
    }

    public List<Shield> getAllShields(){
        List<Shield> shieldList=new ArrayList<>();
        for (int i=0;i<objectItems.size();i++){
            if(objectItems.get(i) instanceof Shield){
                shieldList.add((Shield) objectItems.get(i));
            }
        }
        return shieldList;
    }

    public List<Item> getAllItems(){
        List<Item> itemList=new ArrayList<>();
        for (int i=0;i<objectItems.size();i++){
            if(objectItems.get(i) instanceof Item){
                itemList.add((Item) objectItems.get(i));
            }
        }
        return itemList;
    }

    public Weapon getWeapon(String name){
        List<Weapon> weaponList=getAllWeapons();
        for (int i=0;i<weaponList.size();i++){
            if (weaponList.get(i).getName().equals(name)){
                return weaponList.get(i);
            }
        }
        return null;
    }
    public Weapon getWeapon(int id){
        List<Weapon> weaponList=getAllWeapons();
        for (int i=0;i<weaponList.size();i++){
            if (weaponList.get(i).getId()==id){
                return weaponList.get(i);
            }
        }
        return null;
    }
    public Item getItem(int id){
        List<Item> itemList=getAllItems();
        for (int i=0;i<itemList.size();i++){
            if (itemList.get(i).getId()==id){
                return itemList.get(i);
            }
        }
        return null;
    }

    public Shield getShield(String name){
        List<Shield> shieldList=getAllShields();
        for (int i=0;i<shieldList.size();i++){
            if (shieldList.get(i).getName().equals(name)){
                return shieldList.get(i);
            }
        }
        return null;
    }

    public Item getItem(String name){
        List<Item> itemList=getAllItems();
        for (int i=0;i<itemList.size();i++){
            if (itemList.get(i).getName().equals(name)){
                return itemList.get(i);
            }
        }
        return null;
    }

    public void deleteObjectItem(String name){
        for (int i=0;i<objectItems.size();i++){
            if (objectItems.get(i).getName().equals(name)){
                objectItems.remove(i);
                break;
            }
        }
    }
    public void deleteItem(int id){
        objectItems.remove(getItem(id));
    }

    public List<Armor> getAllArmors(){
        List<Armor> armorList=new ArrayList<>();
        for (int i=0;i<objectItems.size();i++){
            if (objectItems.get(i) instanceof Armor){
                armorList.add((Armor) objectItems.get(i));
            }
        }
        return armorList;
    }

    public Armor getArmor(String name){
        List<Armor> armorList=getAllArmors();
        for (int i=0;i<armorList.size();i++){
            if(armorList.get(i).getName().equals(name)){
                return armorList.get(i);
            }
        }
        return null;
    }

    public Armor getArmor(int id){
        List<Armor> armorList=getAllArmors();
        for (int i=0;i<armorList.size();i++){
            if(armorList.get(i).getId()==id){
                return armorList.get(i);
            }
        }
        return null;
    }

    public Shield getShield(int id){
        List<Shield> shieldList=getAllShields();
        for (int i=0;i<shieldList.size();i++){
            if(shieldList.get(i).getId()==id){
                return shieldList.get(i);
            }
        }
        return null;
    }

    public void deleteItem(GameObject gameObject){
        objectItems.remove(gameObject);
    }

}