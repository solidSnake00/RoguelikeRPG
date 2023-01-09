package com.mygdx.game.data.inventory;

import com.badlogic.gdx.graphics.Texture;

public class Shield implements ObjectItem{
    private int id;
    private String name;
    private int def;
    private Texture image;

    private Boolean isEquipped;

    public Shield(int id, String name, int def, String imagePath){
        this.id=id;
        this.name=name;
        this.def=def;
        image=new Texture(imagePath);

        isEquipped=false;
    }

    public Shield(String name, int def){
        this.name=name;
        this.def=def;
    }
    public Shield(){}

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

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
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
