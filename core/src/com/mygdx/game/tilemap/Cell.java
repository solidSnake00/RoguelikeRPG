package com.mygdx.game.tilemap;

import com.mygdx.game.data.GameObject;
import com.mygdx.game.data.EmptyObject;

public class Cell {
    private int x;
    private int y;
    private GameObject gameObject;

    public Cell(){
        gameObject=new EmptyObject();
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

    public GameObject getGameObject() {
        return gameObject;
    }

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }
}
