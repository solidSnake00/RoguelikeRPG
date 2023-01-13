package com.mygdx.game.data;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Room;

public class TransferRoom implements GameObject{
    private int id;
    private int x;
    private int y;
    private Texture image;
    private Room room;
    private int roomId;
    private TileType tileType;

    public TransferRoom(){}
    public TransferRoom(int id,int x,int y, String imagePath,int roomId){
        this.id=id;
        this.x=x;
        this.y=y;
        image=new Texture(imagePath);
        this.roomId=roomId;
        tileType=TileType.TRAVERSABLE;
    }

    @Override
    public void drawUI(Camera camera) {

    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public Texture getImage() {
        return image;
    }

    @Override
    public void setImage(String imagePath) {
        image=new Texture(imagePath);

    }

    @Override
    public void setX(int x) {
        this.x=x;


    }

    @Override
    public void setY(int y) {
        this.y=y;

    }

    @Override
    public TileType getTileType() {
        return tileType;
    }

    @Override
    public void setTileType(TileType tileType) {
        this.tileType=tileType;
    }

    @Override
    public void die() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
}
