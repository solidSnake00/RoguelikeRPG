package com.mygdx.game.data;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;

public class MessageTile implements GameObject{
    private int id;
    private int x;
    private int y;
    private Texture image;
    private TileType tileType;
    private String text;

    public MessageTile(){
        tileType=TileType.TRAVERSABLE;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

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
