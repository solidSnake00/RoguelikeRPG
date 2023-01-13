package com.mygdx.game.data.inventory;

import com.mygdx.game.data.Door;
import com.mygdx.game.data.Enemy;
import com.mygdx.game.data.MessageTile;
import com.mygdx.game.data.Tile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImportObjectItemCSV {
    private List<Weapon> weaponList;
    private List<Shield> shieldList;
    private List<Armor> armorList;
    private List<Item> itemList;
    private List<Enemy> enemyList;
    private List<MessageTile> messageTiles;
    private MessageTile messageTile;
    private Enemy enemy;
    private Weapon weapon;
    private Shield shield;
    private Armor armor;
    private Item item;
    private Door door;
    private List<Door> doorList;
    private String line;
    private String splitBy;

    public ImportObjectItemCSV(){
        this.line="";
        this.splitBy=",";
        weaponList=new ArrayList<>();
        shieldList=new ArrayList<>();
        armorList=new ArrayList<>();
        itemList=new ArrayList<>();
        enemyList=new ArrayList<>();
        messageTiles=new ArrayList<>();
        doorList=new ArrayList<>();
    }

    public List<Weapon> importWeapons(String filePath){
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null)
            {
                String[] sd = line.split(splitBy);
                weapon=new Weapon(Integer.parseInt(sd[0]),sd[1],Integer.parseInt(sd[2]),sd[3]);
                System.out.println("imported weapon with id= "+sd[0]);
                weaponList.add(weapon);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return weaponList;
    }

    public List<Shield> importShields(String filePath){
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null)
            {
                String[] sd = line.split(splitBy);
                shield=new Shield(Integer.parseInt(sd[0]),sd[1],Integer.parseInt(sd[2]),sd[3]);
                shieldList.add(shield);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return shieldList;
    }

    public List<Item> importItems(String filePath){
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null)
            {
                String[] sd = line.split(splitBy);
                item=new Item(Integer.parseInt(sd[0]),sd[1],sd[2],sd[3]);
                if (Integer.parseInt(sd[4])==1){
                    item.setItemType(ItemType.CONSUMABLE);
                } else if (Integer.parseInt(sd[4])==2) {
                    item.setItemType(ItemType.KEY);
                }
                itemList.add(item);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return itemList;
    }

    public List<Armor> importArmors(String filePath){
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null)
            {
                String[] sd = line.split(splitBy);
                armor=new Armor(Integer.parseInt(sd[0]),sd[1],Integer.parseInt(sd[2]),Integer.parseInt(sd[3]),sd[4]);
                armorList.add(armor);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return armorList;
    }

    public List<Enemy> importEnemies(String filePath){
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null)
            {
                String[] sd = line.split(splitBy);
                enemy=new Enemy(Integer.parseInt(sd[0]),sd[1],sd[2],Integer.parseInt(sd[3]),
                        Integer.parseInt(sd[4]), Integer.parseInt(sd[5]),Integer.parseInt(sd[6]));
                enemyList.add(enemy);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return enemyList;
    }

    public List<MessageTile> importMessages(String filePath){
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null)
            {
                String[] sd = line.split(splitBy);
                messageTile=new MessageTile(Integer.parseInt(sd[0]),sd[1]);
                messageTiles.add(messageTile);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return messageTiles;
    }
    private List<Item> getItemList(){
        return itemList;
    }

    public List<Door> importDoors(String filePath){
        int s=0;
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null)
            {
                String[] sd = line.split(splitBy);
                for (int i=0;i<itemList.size();i++){
                    if (Integer.parseInt(sd[1])==itemList.get(i).getId()){

                        break;
                    }
                    s++;
                }
                door=new Door(Integer.parseInt(sd[0]),getItemList().get(s));
                doorList.add(door);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return doorList;
    }
}