package com.mygdx.game.data;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.data.TileType;

public interface GameObject {
    void drawUI(Camera camera);
    int getX();
    int getY();

    Texture getImage();
    void setImage(String imagePath);

    void setX(int x);
    void setY(int y);

    TileType getTileType();
    void setTileType(TileType tileType);

    void die();
}
