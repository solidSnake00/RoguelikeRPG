package com.mygdx.game.data;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.UI.DialogBox;
import com.mygdx.game.UI.UIBox;
import com.mygdx.game.data.inventory.Item;

public class Door implements GameObject{
    private int id;
    private int x;
    private int y;
    private Texture image;
    private TileType tileType;
    private UIBox uiBox;
    private Item keyItem;
    private Boolean isOpened;
    private Boolean showUI;

    public Door(){
        uiBox=new DialogBox();
        keyItem=new Item();
        isOpened=false;
        tileType=TileType.SOLID;
        showUI=false;
    }

    public Door(int id, Item keyItem){
        this.id=id;
        uiBox=new DialogBox();
        this.keyItem=keyItem;
        isOpened=false;
        tileType=TileType.SOLID;
        showUI=false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getShowUI() {
        return showUI;
    }

    public void setShowUI(Boolean showUI) {
        this.showUI = showUI;
    }

    @Override
    public void drawUI(Camera camera) {
        if (!isOpened) {
            uiBox.drawRectangle(this,200, 100, 350, 100, camera, "This door is locked");
        }
        else {
            uiBox.drawRectangle(this,200, 100, 350, 100, camera, "This door is opened using a key");
        }

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

    public UIBox getUiBox() {
        return uiBox;
    }

    public void setUiBox(UIBox uiBox) {
        this.uiBox = uiBox;
    }

    public Item getKeyItem() {
        return keyItem;
    }

    public void setKeyItem(Item keyItem) {
        this.keyItem = keyItem;
    }

    public Boolean getOpened() {
        return isOpened;
    }

    public void setOpened(Boolean opened) {
        isOpened = opened;
        if (isOpened){
            setImage("door_1_open_img.png");
        }
    }
}
