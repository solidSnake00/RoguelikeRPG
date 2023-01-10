package com.mygdx.game.data.inventory;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.data.TileType;

public class Item implements ObjectItem{
    private int id;

    private String name;
    private String description;
    private Texture image;
    private ItemType itemType;

    public Item(int id, String name, String description, String imagePath){
        this.id=id;
        this.name=name;
        this.description=description;
        image=new Texture(imagePath);
    }

    public Item(String name){
        this.name=name;
    }
    public Item(){}

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    @Override
    public void setImage(String imagePath) {
        image=new Texture(imagePath);
    }

    public Texture getImage(){
        return image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
