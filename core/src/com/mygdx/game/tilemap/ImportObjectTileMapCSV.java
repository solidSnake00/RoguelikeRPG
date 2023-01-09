package com.mygdx.game.tilemap;

import com.mygdx.game.data.*;
import com.mygdx.game.data.inventory.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImportObjectTileMapCSV {
    private String line;
    private String splitBy;
    private String filePath;
    private int numberOfTiles;
    private List<Chest> chestList;
    private List<Item> itemList;
    private List<Shield> shieldList;
    private List<Weapon> weaponList;
    private List<Armor> armorList;
    private List<Enemy> enemyList;
    private List<Enemy> enemies;
    private GameObject[][] gameObjects;

    private ImportObjectItemCSV importObjectItemCSV;

    public ImportObjectTileMapCSV(){
        this.line="";
        this.splitBy=",";
        chestList=new ArrayList<>();
        enemies=new ArrayList<>();
        importObjectItemCSV=new ImportObjectItemCSV();
        itemList=importObjectItemCSV.importItems("assets\\items.csv");
        shieldList=importObjectItemCSV.importShields("assets\\shields.csv");
        weaponList=importObjectItemCSV.importWeapons("assets\\weapons.csv");
        armorList=importObjectItemCSV.importArmors("assets\\armors.csv");
        enemyList=importObjectItemCSV.importEnemies("assets\\enemy.csv");
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

        int i= gameObjects.length-1;
        Chest chest;
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

                    }else {
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
}