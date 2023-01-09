package com.mygdx.game.UI;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class HealthBar {
    private int x;
    private int y;
    private int value;
    private int percent;
    private int valueLimit;
    private ShapeRenderer shapeRenderer;

    public HealthBar(){
        shapeRenderer=new ShapeRenderer();
    }

    public int getValueLimit() {
        return valueLimit;
    }

    public void setValueLimit(int valueLimit) {
        this.valueLimit = valueLimit;
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getPercent() {
        percent=(value*100)/valueLimit;
        return percent;
    }


    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    public void setShapeRenderer(ShapeRenderer shapeRenderer) {
        this.shapeRenderer = shapeRenderer;
    }

    public void draw(Camera camera){
        shapeRenderer.begin();
    }
}
