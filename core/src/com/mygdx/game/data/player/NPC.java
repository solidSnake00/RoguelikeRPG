package com.mygdx.game.data.player;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.data.GameObject;
import com.mygdx.game.data.TileType;

public class NPC implements GameObject {
    private int id;
    private int x;
    private int y;
    private Texture image;
    private TileType tileType;

    public NPC(){}
    @Override
    public void drawUI(Camera camera) {

    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public Texture getImage() {
        return image;
    }

    @Override
    public void setImage(String imagePath) {
        image=new Texture(imagePath);

    }

    @Override
    public void setX(int x) {
        this.x=x;

    }

    @Override
    public void setY(int y) {
        this.y=y;

    }

    @Override
    public TileType getTileType() {
        return tileType;
    }

    @Override
    public void setTileType(TileType tileType) {
        this.tileType=tileType;

    }

    @Override
    public void die() {

    }
}
