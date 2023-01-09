package com.mygdx.game.data.player;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.data.GameObject;

public class Lock {
    private int x;
    private int y;
    private Texture image;
    private Boolean isLocked;
    private GameObject gameObject;

    public Lock(String imagePath){
        image=new Texture(imagePath);
        isLocked=false;
    }

    public Boolean getLocked() {
        return isLocked;
    }

    public void setLocked(Boolean locked) {
        isLocked = locked;
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

    public GameObject getGameObject() {
        return gameObject;
    }

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }
}
