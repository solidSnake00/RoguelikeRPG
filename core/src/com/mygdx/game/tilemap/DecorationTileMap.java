package com.mygdx.game.tilemap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DecorationTileMap {
    private int tileWeight;
    private int numberOfTilesX;
    private int numberOfTilesY;
    private Texture image;

    public DecorationTileMap(){}


    public DecorationTileMap(int numberOfTilesX,int numberOfTilesY, int tileWeight){
        this.numberOfTilesX=numberOfTilesX;
        this.numberOfTilesY=numberOfTilesY;
        this.tileWeight=tileWeight;
    }

    public int getTileWeight() {
        return tileWeight;
    }

    public void setTileWeight(int tileWeight) {
        this.tileWeight = tileWeight;
    }

    public int getNumberOfTilesX() {
        return numberOfTilesX;
    }

    public void setNumberOfTilesX(int numberOfTilesX) {
        this.numberOfTilesX = numberOfTilesX;
    }

    public int getNumberOfTilesY() {
        return numberOfTilesY;
    }

    public void setNumberOfTilesY(int numberOfTilesY) {
        this.numberOfTilesY = numberOfTilesY;
    }

    public Texture getImage(){
        return image;
    }

    public void setImage(String imagePath){
        image=new Texture(imagePath);
    }

    public void drawDecorationMap(SpriteBatch batch){
        for (int i=0;i<numberOfTilesX;i++){
            for (int j=0;j<numberOfTilesY;j++){
                batch.draw(image, i*tileWeight,j*tileWeight);
            }
        }
    }
}
