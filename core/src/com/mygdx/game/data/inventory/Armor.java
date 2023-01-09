package com.mygdx.game.data.inventory;

import com.badlogic.gdx.graphics.Texture;

public class Armor implements ObjectItem {
    private int id;
    private String name;
    private int atk;
    private int def;
    private Texture image;

    private Boolean isEquipped;

    public Armor(int id, String name, int atk,int def, String imagePath){
        this.id=id;
        this.name=name;
        this.atk=atk;
        this.def=def;
        image=new Texture(imagePath);

        isEquipped=false;
    }

    public Armor() {
    }

    public Armor(String name, Texture image) {
        this.name = name;
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public Texture getImage() {
        return image;
    }


    @Override
    public void setImage(String imagePath) {
        image = new Texture(imagePath);
    }

    @Override
    public String getName() {
        return name;
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