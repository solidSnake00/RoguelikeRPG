package com.mygdx.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.data.Door;
import com.mygdx.game.data.GameObject;
import com.mygdx.game.data.inventory.Item;
import com.mygdx.game.data.player.Player;

public class GuiBox implements UIBox{
    private int x;
    private int y;
    private int height;
    private int width;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    public GuiBox(int x, int y, int height, int width){
        batch=new SpriteBatch();
        //shapeRenderer=new ShapeRenderer();
        font=new BitmapFont();
        this.x=x;
        this.y=y;
        this.height=height;
        this.width=width;
        generator = new FreeTypeFontGenerator(Gdx.files.internal("Moder DOS 437 Win.ttf"));
        parameter=new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size=15;
        parameter.borderWidth=1;

        font=generator.generateFont(parameter);
    }
    @Override
    public void drawRectangle(int x, int y, int height, int width, Camera camera, String value, Texture image) {

    }

    @Override
    public void drawRectangle(Door door, int x, int y, int height, int width, Camera camera, String value) {

    }

    @Override
    public void drawRectangle(Player player, Camera camera, SpriteBatch batch) {

    }

    @Override
    public void drawRectangle(GameObject gameObject, Camera camera, SpriteBatch batch) {

    }

    @Override
    public void messageUI(int x, int y, int height, int width, Camera camera, String text) {

    }

    @Override
    public Boolean getShow() {
        return null;
    }

    @Override
    public void setShow(Boolean show) {

    }

    @Override
    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }
    public SpriteBatch getBatch(){
        return batch;
    }

    public void setX(int x){
        this.x=x;
    }
    public void setY(int y){
        this.y=y;
    }
    public int getX(){
        return x;
    }
    public int getY() {
        return y;
    }
    public void setHeight(int height){
        this.height=height;
    }
    public void setWidth(int width){
        this.width=width;
    }
    public int getHeight(){
        return height;
    }
    public int getWidth(){
        return width;
    }

    public void drawRectangle(Texture image, Camera camera, SpriteBatch batch,ShapeRenderer shapeRenderer, int imageX,int imageY){

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.setColor(new Color(0, 0, 0, 0.5f));
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        batch.setColor(new Color(1, 1, 1, 0.6f));
        batch.draw(image, x+imageX, y+imageY,50,50);
        batch.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);


        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(x,y,width,height);
        shapeRenderer.end();
    }
    public void showItemName(Item item,SpriteBatch batch,Camera camera,int x,int y){
        parameter.size=14;
        font=generator.generateFont(parameter);
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        font.draw(batch,item.getName(),x,y);
        batch.end();

    }
}
