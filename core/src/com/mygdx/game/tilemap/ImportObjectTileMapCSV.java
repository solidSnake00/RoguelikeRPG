package com.mygdx.game.tilemap;

import com.mygdx.game.data.*;
import com.mygdx.game.data.inventory.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ImportObjectTileMapCSV {
    private String line;
    private String splitBy;
    private String filePath;
    private int numberOfTiles;
    private List<Chest> chestList;
    private final List<Item> itemList;
    private final List<Shield> shieldList;
    private final List<Weapon> weaponList;
    private final List<Armor> armorList;
    private final List<Enemy> enemyList;
    private List<Enemy> enemies;
    private List<MessageTile> messageTiles;
    private List<MessageTile> messages;
    private GameObject[][] gameObjects;
    private int playerX;
    private int playerY;
    
    private List<Door> doorList;
    private List<Door> doors;

    private ImportObjectItemCSV importObjectItemCSV;
    
    private List<TransferRoom> transferRoomList;
    

    public ImportObjectTileMapCSV(){
        this.line="";
        this.splitBy=",";
        chestList=new ArrayList<>();
        enemies=new ArrayList<>();
        messages=new ArrayList<>();
        importObjectItemCSV=new ImportObjectItemCSV();
        transferRoomList=new ArrayList<>();
        
        itemList=importObjectItemCSV.importItems("assets\\items.csv");
        shieldList=importObjectItemCSV.importShields("assets\\shields.csv");
        weaponList=importObjectItemCSV.importWeapons("assets\\weapons.csv");
        armorList=importObjectItemCSV.importArmors("assets\\armors.csv");
        enemyList=importObjectItemCSV.importEnemies("assets\\enemy.csv");
        messageTiles=importObjectItemCSV.importMessages("assets\\messages.csv");
        doorList=importObjectItemCSV.importDoors("assets\\doors.csv");
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getSplitBy() {
        return splitBy;
    }

    public void setSplitBy(String splitBy) {
        this.splitBy = splitBy;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getNumberOfTiles() {
        return numberOfTiles;
    }

    public void setNumberOfTiles(int numberOfTiles) {
        this.numberOfTiles = numberOfTiles;
    }

    public void read(String filePath, GameObject[][] gameObjects){
        chestList=new ArrayList<>();
        enemies=new ArrayList<>();
        messages=new ArrayList<>();
        doors=new ArrayList<>();
        transferRoomList=new ArrayList<>();

        int i= gameObjects.length-1;
        Chest chest;
        MessageTile messageTile;
        Door door;
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null)
            {
                String[] sd = line.split(splitBy);
                for (int l=0;l< gameObjects.length;l++){

                    if (Integer.parseInt(sd[l])==5){
                        gameObjects[l][i]=new Tile();
                        gameObjects[l][i].setImage("wall.png");
                        gameObjects[l][i].setX(l);
                        gameObjects[l][i].setY(i);
                        gameObjects[l][i].setTileType(TileType.SOLID);
                    } else if (Integer.parseInt(sd[l])==6) {
                        gameObjects[l][i]=new Tile();
                        gameObjects[l][i].setImage("wall_2.png");
                        gameObjects[l][i].setX(l);
                        gameObjects[l][i].setY(i);
                        gameObjects[l][i].setTileType(TileType.SOLID);

                    } else if (Integer.parseInt(sd[l])>=100 && Integer.parseInt(sd[l])<300) {

                        if(Integer.parseInt(sd[l])==101){
                            gameObjects[l][i]=new Chest();
                            chest=(Chest) gameObjects[l][i];
                            chest.setObjectItem(itemList.get(1));
                            chest.setImage("chest_closed_img.png");
                            chest.setX(l);
                            chest.setY(i);
                            chest.setTileType(TileType.SOLID);
                            chestList.add(chest);
                        }
                        else if (Integer.parseInt(sd[l])==110){
                            gameObjects[l][i]=new Chest();
                            chest=(Chest) gameObjects[l][i];
                            chest.setObjectItem(shieldList.get(1));
                            chest.setImage("chest_closed_img.png");
                            chest.setX(l);
                            chest.setY(i);
                            chest.setTileType(TileType.SOLID);
                            chestList.add(chest);
                        }
                        else if (Integer.parseInt(sd[l])==111){
                            gameObjects[l][i]=new Chest();
                            chest=(Chest) gameObjects[l][i];
                            chest.setObjectItem(weaponList.get(1));
                            chest.setImage("chest_closed_img.png");
                            chest.setX(l);
                            chest.setY(i);
                            chest.setTileType(TileType.SOLID);
                            chestList.add(chest);
                        }
                        else if (Integer.parseInt(sd[l])==112){
                            gameObjects[l][i]=new Chest();
                            chest=(Chest) gameObjects[l][i];
                            chest.setObjectItem(weaponList.get(3));
                            chest.setImage("chest_closed_img.png");
                            chest.setX(l);
                            chest.setY(i);
                            chest.setTileType(TileType.SOLID);
                            chestList.add(chest);
                        }
                        else if (Integer.parseInt(sd[l])==201){
                            //System.out.println("chest number 201");
                            gameObjects[l][i]=new Chest();
                            chest=(Chest) gameObjects[l][i];
                            chest.setObjectItem(armorList.get(1));
                            chest.setImage("chest_closed_img.png");
                            chest.setX(l);
                            chest.setY(i);
                            chest.setTileType(TileType.SOLID);
                            chestList.add(chest);
                        } else if (Integer.parseInt(sd[l])==210) {
                            gameObjects[l][i]=new Chest();
                            chest=(Chest) gameObjects[l][i];
                            chest.setObjectItem(itemList.get(2));
                            chest.setImage("chest_closed_img.png");
                            chest.setX(l);
                            chest.setY(i);
                            chest.setTileType(TileType.SOLID);
                            chestList.add(chest);
                        }
                        else if (Integer.parseInt(sd[l])==102) {
                            gameObjects[l][i]=new Chest();
                            chest=(Chest) gameObjects[l][i];
                            chest.setObjectItem(itemList.get(3));
                            chest.setImage("chest_closed_img.png");
                            chest.setX(l);
                            chest.setY(i);
                            chest.setTileType(TileType.SOLID);
                            chestList.add(chest);
                        } else if (Integer.parseInt(sd[l])==120) {
                            gameObjects[l][i]=new Chest();
                            chest=(Chest) gameObjects[l][i];
                            chest.setObjectItem(shieldList.get(3));
                            chest.setImage("chest_closed_img.png");
                            chest.setX(l);
                            chest.setY(i);
                            chest.setTileType(TileType.SOLID);
                            chestList.add(chest);
                        } else if (Integer.parseInt(sd[l])==121) {
                            gameObjects[l][i]=new Chest();
                            chest=(Chest) gameObjects[l][i];
                            chest.setObjectItem(armorList.get(3));
                            chest.setImage("chest_closed_img.png");
                            chest.setX(l);
                            chest.setY(i);
                            chest.setTileType(TileType.SOLID);
                            chestList.add(chest);
                        }
                    }

                    else if (Integer.parseInt(sd[l])==501){
                        gameObjects[l][i]=new Enemy(enemyList.get(0));
                        Enemy enemy= (Enemy) gameObjects[l][i];
                        enemy.setX(l);
                        enemy.setY(i);
                        enemy.setEnemyAIEnum(EnemyAIEnum.RANDOM_MOVEMENT);
                        enemies.add(enemy);

                    }else if (Integer.parseInt(sd[l])==502){

                        gameObjects[l][i]=new Enemy(enemyList.get(0));
                        Enemy enemy= (Enemy) gameObjects[l][i];
                        enemy.setX(l);
                        enemy.setY(i);

                        enemy.setEnemyAIEnum(EnemyAIEnum.FOLLOW_PLAYER);
                        enemies.add(enemy);

                    } else if (Integer.parseInt(sd[l])==1001) {
                        gameObjects[l][i]=new MessageTile();
                        messageTile= (MessageTile) gameObjects[l][i];
                        messageTile.setText(messageTiles.get(0).getText());
                        messageTile.setId(messageTiles.get(0).getId());
                        messageTile.setX(l);
                        messageTile.setY(i);

                        messages.add(messageTile);

                        
                    } else if (Integer.parseInt(sd[l])==700) {
                        gameObjects[l][i]=new Door();
                        door= (Door) gameObjects[l][i];
                        door.setId(doorList.get(0).getId());
                        door.setKeyItem(doorList.get(0).getKeyItem());
                        door.setX(l);
                        door.setY(i);
                        door.setImage("door_1_img.png");

                        doors.add(door);
                        
                    } else if (Integer.parseInt(sd[l])==1) {
                        playerX=l;
                        playerY=i;

                    } else if (Integer.parseInt(sd[l])==504) {
                        gameObjects[l][i]=new Enemy(enemyList.get(1));
                        Enemy enemy= (Enemy) gameObjects[l][i];
                        enemy.setX(l);
                        enemy.setY(i);

                        enemy.setEnemyAIEnum(EnemyAIEnum.RANDOM_MOVEMENT);
                        enemies.add(enemy);
                        System.out.println("enemy added");

                    }else if (Integer.parseInt(sd[l])==506) {
                        gameObjects[l][i]=new Enemy(enemyList.get(1));
                        Enemy enemy= (Enemy) gameObjects[l][i];
                        enemy.setX(l);
                        enemy.setY(i);

                        enemy.setEnemyAIEnum(EnemyAIEnum.NO_AI);
                        enemies.add(enemy);
                    }else if (Integer.parseInt(sd[l])==507) {
                        gameObjects[l][i]=new Enemy(enemyList.get(2));
                        Enemy enemy= (Enemy) gameObjects[l][i];
                        enemy.setX(l);
                        enemy.setY(i);

                        enemy.setEnemyAIEnum(EnemyAIEnum.RANDOM_MOVEMENT);
                        enemies.add(enemy);
                    }else if (Integer.parseInt(sd[l])==509) {
                        gameObjects[l][i]=new Enemy(enemyList.get(2));
                        Enemy enemy= (Enemy) gameObjects[l][i];
                        enemy.setX(l);
                        enemy.setY(i);

                        enemy.setEnemyAIEnum(EnemyAIEnum.NO_AI);
                        enemies.add(enemy);
                    }else if (Integer.parseInt(sd[l])==510) {
                        gameObjects[l][i]=new Enemy(enemyList.get(3));
                        Enemy enemy= (Enemy) gameObjects[l][i];
                        enemy.setX(l);
                        enemy.setY(i);

                        enemy.setEnemyAIEnum(EnemyAIEnum.RANDOM_MOVEMENT);
                        enemies.add(enemy);
                    }else if (Integer.parseInt(sd[l])==512) {
                        gameObjects[l][i]=new Enemy(enemyList.get(3));
                        Enemy enemy= (Enemy) gameObjects[l][i];
                        enemy.setX(l);
                        enemy.setY(i);

                        enemy.setEnemyAIEnum(EnemyAIEnum.NO_AI);
                        enemies.add(enemy);
                    }else if (Integer.parseInt(sd[l])==513) {
                        gameObjects[l][i]=new Enemy(enemyList.get(4));
                        Enemy enemy= (Enemy) gameObjects[l][i];
                        enemy.setX(l);
                        enemy.setY(i);

                        enemy.setEnemyAIEnum(EnemyAIEnum.RANDOM_MOVEMENT);
                        enemies.add(enemy);
                    }else if (Integer.parseInt(sd[l])==515) {
                        gameObjects[l][i]=new Enemy(enemyList.get(4));
                        Enemy enemy= (Enemy) gameObjects[l][i];
                        enemy.setX(l);
                        enemy.setY(i);

                        enemy.setEnemyAIEnum(EnemyAIEnum.NO_AI);
                        enemies.add(enemy);
                    }else if (Integer.parseInt(sd[l])==516) {
                        gameObjects[l][i]=new Enemy(enemyList.get(5));
                        Enemy enemy= (Enemy) gameObjects[l][i];
                        enemy.setX(l);
                        enemy.setY(i);

                        enemy.setEnemyAIEnum(EnemyAIEnum.RANDOM_MOVEMENT);
                        enemies.add(enemy);
                    }
                    else if (Integer.parseInt(sd[l])==518) {
                        gameObjects[l][i]=new Enemy(enemyList.get(5));
                        Enemy enemy= (Enemy) gameObjects[l][i];
                        enemy.setX(l);
                        enemy.setY(i);

                        enemy.setEnemyAIEnum(EnemyAIEnum.NO_AI);
                        enemies.add(enemy);
                    }else if (Integer.parseInt(sd[l])==519) {
                        gameObjects[l][i]=new Enemy(enemyList.get(6));
                        Enemy enemy= (Enemy) gameObjects[l][i];
                        enemy.setX(l);
                        enemy.setY(i);

                        enemy.setEnemyAIEnum(EnemyAIEnum.RANDOM_MOVEMENT);
                        enemies.add(enemy);
                    }else if (Integer.parseInt(sd[l])==521) {
                        gameObjects[l][i]=new Enemy(enemyList.get(6));
                        Enemy enemy= (Enemy) gameObjects[l][i];
                        enemy.setX(l);
                        enemy.setY(i);

                        enemy.setEnemyAIEnum(EnemyAIEnum.NO_AI);
                        enemies.add(enemy);
                    }///////
                    else if (Integer.parseInt(sd[l])==802) {
                        gameObjects[l][i]=new TransferRoom();
                        TransferRoom transferRoom= (TransferRoom) gameObjects[l][i];
                        transferRoom.setId(2);
                        transferRoom.setX(l);
                        transferRoom.setY(i);
                        transferRoom.setImage("ladder_2_img.png");
                        transferRoom.setRoomId(2);

                        transferRoomList.add(transferRoom);
                        
                    } else if (Integer.parseInt(sd[l])==803) {
                        gameObjects[l][i]=new TransferRoom();
                        TransferRoom transferRoom= (TransferRoom) gameObjects[l][i];
                        transferRoom.setId(3);
                        transferRoom.setX(l);
                        transferRoom.setY(i);
                        transferRoom.setImage("ladder_img.png");
                        transferRoom.setRoomId(3);

                        transferRoomList.add(transferRoom);
                    } else {
                        gameObjects[l][i]=null;
                    }
                }
                i--;
            }
            this.gameObjects=gameObjects;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public List<Chest> getChestList(){
        return chestList;
    }

    public GameObject[][] getGameObjects(){
        return gameObjects;
    }

    public List<Enemy> getEnemyList(){
        return enemies;
    }

    public List<MessageTile> getMessageTiles(){
        return messages;
    }

    public List<Door> getDoors(){
        return doors;
    }
    public int getPlayerX(){
        return playerX;
    }
    public int getPlayerY(){
        return playerY;
    }
    public List<TransferRoom> getTransferRoomList(){
        return transferRoomList;
    }
}