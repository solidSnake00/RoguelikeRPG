package com.mygdx.game;

import com.mygdx.game.data.GameObject;
import com.mygdx.game.tilemap.DecorationTileMap;
import com.mygdx.game.tilemap.ImportObjectTileMapCSV;

public class Room {
    private int id;
    private int numberOfTiles;
    private GameObject[][] gameObjects;
    private ImportObjectTileMapCSV importObjectTileMapCSV;

    private DecorationTileMap decorationTileMap;

    public Room(int id,int numberOfTiles){
        this.id=id;
        this.numberOfTiles=numberOfTiles;
        gameObjects= new GameObject[numberOfTiles][numberOfTiles];
        importObjectTileMapCSV=new ImportObjectTileMapCSV();
        decorationTileMap=new DecorationTileMap(numberOfTiles,numberOfTiles,32);
    }

    public void importCSV(String path){
        importObjectTileMapCSV.read(path,gameObjects);
    }

    public void setDecorationTileMapImage(String imagePath){
        decorationTileMap.setImage(imagePath);
    }


    public DecorationTileMap getDecorationTileMap() {
        return decorationTileMap;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfTiles() {
        return numberOfTiles;
    }

    public void setNumberOfTiles(int numberOfTiles) {
        this.numberOfTiles = numberOfTiles;
    }

    public GameObject[][] getGameObjects() {
        return importObjectTileMapCSV.getGameObjects();
    }

    public void setGameObjects(GameObject[][] gameObjects) {
        this.gameObjects = gameObjects;
    }

    public ImportObjectTileMapCSV getImportObjectTileMapCSV() {
        return importObjectTileMapCSV;
    }

    public void setImportObjectTileMapCSV(ImportObjectTileMapCSV importObjectTileMapCSV) {
        this.importObjectTileMapCSV = importObjectTileMapCSV;
    }
}
