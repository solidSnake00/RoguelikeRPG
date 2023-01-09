package com.mygdx.game.data;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;

public class Tile implements GameObject {
    private int x;
    private int y;
    private Texture image;
    private TileType tileType;

    public Tile(){
    }

    @Override
    public void drawUI(Camera camera) {

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Texture getImage() {
        return image;
    }

    public void setImage(String imagePath) {
        image=new Texture(imagePath);
    }

    public TileType getTileType() {
        return tileType;
    }

    @Override
    public void die() {

    }

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }
}
