package com.mygdx.game.tilemap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.data.*;
import com.mygdx.game.data.player.Player;

public class MapObject {
    private Cell[][] cells;
    private int numberOfTiles;
    private int tilesWeight;

    public MapObject(int numberOfTiles, int tilesWeight){
        this.numberOfTiles=numberOfTiles;
        this.tilesWeight=tilesWeight;
        cells=new Cell[numberOfTiles][numberOfTiles];

        for(int i=0;i<numberOfTiles;i++){
            for (int j=0;j<numberOfTiles;j++){
                cells[i][j]=new Cell();
                cells[i][j].getGameObject().setX(i);
                cells[i][j].getGameObject().setY(j);
            }
        }
    }

    public Player getPlayer(){
        Player player;
        for (int i=0;i<numberOfTiles;i++){
            for (int j=0;j<numberOfTiles;j++){
                if (cells[i][j].getGameObject() instanceof Player){
                    player=(Player) cells[i][j].getGameObject();
                    return player;
                }
            }
        }
        return null;
    }

    public void setGameObjectToCell(GameObject gameObject){
        int i=gameObject.getX();
        int j=gameObject.getY();
        cells[i][j].setGameObject(gameObject);

    }
    public void removeGameObjectToCell(GameObject gameObject){
        int i=gameObject.getX();
        int j=gameObject.getY();
        System.out.println("remove:");
        System.out.println("i= "+i);
        System.out.println("j= "+j);
        //cells[i][j].setGameObject(null);
        cells[i][j].setGameObject(new EmptyObject());

        System.out.println(cells[i][j].getGameObject().getImage().toString());
        gameObject=null;
    }

    public Cell getCell(int x, int y){
        return cells[x][y];
    }

    public int getTilesWeight(){
        return tilesWeight;
    }

    public void refresh(){
        for(int i=0;i<numberOfTiles;i++){
            for (int j=0;j<numberOfTiles;j++){
                cells[i][j].setGameObject(cells[i][j].getGameObject());
                cells[i][j].getGameObject().setX(i);
                cells[i][j].getGameObject().setY(j);
            }

        }
    }

    public void drawAllObjects(SpriteBatch batch){
        int a=0,b=0;
        for (int i=0;i<numberOfTiles;i++){
            for (int j=0;j<numberOfTiles;j++){

                if (!(getCell(i,j).getGameObject() instanceof Player)) {
                    batch.draw(getCell(i, j).getGameObject().getImage(),
                            getCell(i, j).getGameObject().getX() * getTilesWeight(),
                            getCell(i, j).getGameObject().getY() * getTilesWeight());
                }else {
                    a=i;
                    b=j;
                }

            }
        }

        batch.draw(getCell(a, b).getGameObject().getImage(),
                getCell(a, b).getGameObject().getX() * getTilesWeight(),
                getCell(a, b).getGameObject().getY() * getTilesWeight());
    }

    public void setAllTilesToCell(GameObject[][] gameObjects){
        for (int i=0;i<numberOfTiles;i++) {
            for (int j = 0; j < numberOfTiles; j++) {
                if (gameObjects[i][j] instanceof Tile) {
                    setGameObjectToCell(gameObjects[i][j]);
                }
                if (gameObjects[i][j] instanceof Enemy) {
                    setGameObjectToCell(gameObjects[i][j]);
                }
                if (gameObjects[i][j] instanceof Chest) {
                    setGameObjectToCell(gameObjects[i][j]);
                }
            }
        }
    }

    public void setGameObjectTiles(GameObject[][] gameObjects, TileType tileType){
        for (int i=0;i<numberOfTiles;i++){
            for (int j=0;j<numberOfTiles;j++){
                if(gameObjects[i][j] instanceof Tile){
                    gameObjects[i][j].setX(i);
                    gameObjects[i][j].setY(j);
                }
                /*if (gameObjects[i][j] instanceof Chest) {
                    gameObjects[i][j].setImage("chest_closed_img.png");
                    gameObjects[i][j].setX(i);
                    gameObjects[i][j].setY(j);

                    ((Chest) gameObjects[i][j]).setTileType(tileType);
                }*/
            }
        }
    }

    public void dispose(){
        for(int i=0;i<numberOfTiles;i++){
            for (int j=0;j<numberOfTiles;j++){
                getCell(i,j).getGameObject().getImage().dispose();
            }
        }
    }

}