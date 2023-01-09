package com.mygdx.game.data.inventory;

public class Slot <T>{
    private T objectItem;

    public Slot(){}
    public Slot(T objectItem){
        this.objectItem=objectItem;
    }

    public T getObjectItem() {
        return objectItem;
    }

    public void setObjectItem(T objectItem) {
        this.objectItem = objectItem;
    }
}
