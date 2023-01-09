package com.mygdx.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.data.Door;
import com.mygdx.game.data.GameObject;
import com.mygdx.game.data.player.Player;

public class DialogBox implements UIBox{
    private int x;
    private int y;
    private int width;
    private int height;
    private Boolean show;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch batch;
    private BitmapFont font;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    public DialogBox(){
        shapeRenderer=new ShapeRenderer();
        batch=new SpriteBatch();
        generator = new FreeTypeFontGenerator(Gdx.files.internal("Moder DOS 437 Win.ttf"));
        parameter=new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size=20;
        //parameter.borderWidth=1;
        show=true;

        font=generator.generateFont(parameter);
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    @Override
    public Boolean getShow(){
        return show;
    }

    @Override
    public void setShow(Boolean show) {
        this.show = show;
    }

    @Override
    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    public void setShapeRenderer(ShapeRenderer shapeRenderer) {
        this.shapeRenderer = shapeRenderer;
    }

    @Override
    public void drawRectangle(int x, int y, int width, int height, Camera camera, String value, Texture image){
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            Timer.schedule(new Timer.Task(){
                @Override
                public void run() {
                    setShow(false);
                }
            }, 2);
        }
        if (getShow()){
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setProjectionMatrix(camera.combined);
            shapeRenderer.setColor(/*Color.GRAY*/Color.DARK_GRAY);
            shapeRenderer.rect(x,y,width,height);
            shapeRenderer.end();

            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setProjectionMatrix(camera.combined);
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.rect(x,y,width,height);
            shapeRenderer.end();

            batch.begin();
            batch.setProjectionMatrix(camera.combined);
            batch.draw(image,x,y+30,55,55);
            font.draw(batch,value,x+55,y+65);
            batch.end();
        }
    }
    ////////////
    public void drawRectangle(Door door, int x, int y, int width, int height, Camera camera, String value){
        if(Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)){
            Timer.schedule(new Timer.Task(){
                @Override
                public void run() {
                    door.setShowUI(false);
                }
            }, 2);
        }
        if (getShow()){
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setProjectionMatrix(camera.combined);
            shapeRenderer.setColor(/*Color.GRAY*/Color.DARK_GRAY);
            shapeRenderer.rect(x,y,width,height);
            shapeRenderer.end();

            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setProjectionMatrix(camera.combined);
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.rect(x,y,width,height);
            shapeRenderer.end();

            batch.begin();
            batch.setProjectionMatrix(camera.combined);
            font.draw(batch,value,x+5,y+65);
            batch.end();
        }
    }
    ////////////

    @Override
    public void drawRectangle(Player player, Camera camera, SpriteBatch batch) {

    }

    @Override
    public void drawRectangle(GameObject gameObject, Camera camera, SpriteBatch batch) {

    }

    public SpriteBatch getBatch(){
        return batch;
    }
}
