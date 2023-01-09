package com.mygdx.game.data;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.UI.DialogBox;
import com.mygdx.game.UI.UIBox;
import com.mygdx.game.data.inventory.Item;
import com.mygdx.game.data.inventory.ObjectItem;

public class Chest implements GameObject{
    private int x;
    private int y;
    private Texture image;
    private TileType tileType;
    private ObjectItem objectItem;
    private Boolean isEmpty;
    private Boolean isLocked;
    private UIBox uiBox;
    public Chest(){
        isEmpty=false;
        isLocked=false;
        uiBox=new DialogBox();
        objectItem=new Item();
        objectItem.setId(0);
        tileType=TileType.SOLID;

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
    public void die() {

    }

    public void setTileType(TileType tileType){
        this.tileType=tileType;
    }

    public ObjectItem getObjectItem(){
        return objectItem;
    }

    public void setObjectItem(ObjectItem objectItem){
        this.objectItem=objectItem;
    }

    public Boolean getIsEmpty(){
        return isEmpty;
    }
    public void setIsEmpty(Boolean isEmpty){
        this.isEmpty=isEmpty;
    }

    public Boolean getIsLocked(){
        return isLocked;
    }

    public void setIsLocked(Boolean isLocked){
        this.isLocked=isLocked;
    }

    @Override
    public void drawUI(Camera camera){
        uiBox.drawRectangle(200,100,300,100,camera, getObjectItem().getName(), getObjectItem().getImage());
    }

    public UIBox getUiBox(){
        return uiBox;
    }
}