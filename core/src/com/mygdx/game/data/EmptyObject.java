package com.mygdx.game.data;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;

public class EmptyObject implements GameObject {

    private int x;
    private int y;
    private Texture image;
    private TileType tileType;
    public EmptyObject(){
        image=new Texture("blank.png");
        tileType=TileType.TRAVERSABLE;
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

    public void setX(int x){
        this.x=x;
    }

    public void setY(int y){
        this.y=y;
    }

    @Override
    public TileType getTileType() {
        return tileType;
    }

    @Override
    public void setTileType(TileType tileType) {

    }

    @Override
    public void die() {

    }


    @Override
    public Texture getImage() {
        return image;
    }

    @Override
    public void setImage(String imagePath) {
        image=new Texture(imagePath);
    }
}
