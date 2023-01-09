package com.mygdx.game.data.inventory;

import com.badlogic.gdx.graphics.Texture;

public interface ObjectItem {
    void setImage(String imagePath);
    Texture getImage();

    String getName();

    void setId(int id);
    int getId();

}
