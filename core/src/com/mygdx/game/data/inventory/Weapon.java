package com.mygdx.game.data.inventory;

import com.badlogic.gdx.graphics.Texture;

public class Weapon implements ObjectItem{
    private int id;
    private String name;
    private int atk;
    private Texture image;

    private Boolean isEquipped;

    public Weapon(int id, String name, int atk, String imagePath){
        this.id=id;
        this.name=name;
        this.atk=atk;
        image=new Texture(imagePath);

        isEquipped=false;
    }

    public Weapon(String name, int atk){
        this.name=name;
        this.atk=atk;
        isEquipped=false;
    }
    public Weapon(){
        isEquipped=false;
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

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public Boolean getEquipped() {
        return isEquipped;
    }

    public void setEquipped(Boolean equipped) {
        isEquipped = equipped;
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
