package com.mygdx.game.UI;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.data.Chest;
import com.mygdx.game.data.Door;
import com.mygdx.game.data.GameObject;
import com.mygdx.game.data.player.Player;

public interface UIBox {

    void drawRectangle(int x, int y, int height, int width, Camera camera, String value, Texture image);
    void drawRectangle(Door door, int x, int y, int height, int width, Camera camera, String value);
    void drawRectangle(Player player, Camera camera, SpriteBatch batch);
    void drawRectangle(GameObject gameObject, Camera camera, SpriteBatch batch);

    Boolean getShow();
    void setShow(Boolean show);

    ShapeRenderer getShapeRenderer();

    void setX(int x);
    void setY(int y);
    int getX();
    int getY();
    void setHeight(int height);
    void setWidth(int width);
    int getHeight();
    int getWidth();

}
