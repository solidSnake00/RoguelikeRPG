package com.mygdx.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.data.Enemy;
import com.mygdx.game.data.player.Player;

import java.text.DecimalFormat;

public class HealthGui {
    private int x;
    private int y;
    private int height;
    private int width;
    private BitmapFont bitmapFont;
    private DecimalFormat df;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    public HealthGui(int x, int y, int height, int width){
        this.x=x;
        this.y=y;
        this.height=height;
        this.width=width;
        bitmapFont=new BitmapFont();
        generator = new FreeTypeFontGenerator(Gdx.files.internal("Moder DOS 437 Win.ttf"));
        parameter=new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size=15;
        parameter.borderWidth=1;

        bitmapFont=generator.generateFont(parameter);
        df=new DecimalFormat("#.##");
    }

    public void drawEnemyGui(Enemy enemy, Camera camera, ShapeRenderer shapeRenderer, SpriteBatch batch){
        //int s;
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.setColor(new Color(0, 0, 0, 0.5f));
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        if (enemy.getHP()<=0){
            enemy.setHP(0);
        }
        bitmapFont.draw(batch,enemy.getName()+" Lv."+enemy.getLevel(),x+5,y+55);
        bitmapFont.draw(batch,"HP: "+enemy.getHPLimit()+"/"+df.format(enemy.getHP()),x+5,y+30);
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(x,y,width,height);
        shapeRenderer.end();
    }

    public void drawPlayerGui(Player player, Camera camera, ShapeRenderer shapeRenderer, SpriteBatch batch, int x, int y, int width, int height){
        //int s;
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.setColor(new Color(0, 0, 0, 0.5f));
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        if (player.getStats().getHP()<=0){
            player.getStats().setHP(0);
        }
        bitmapFont.setColor(Color.LIGHT_GRAY);
        bitmapFont.draw(batch,"HP: "+player.getStats().getHPLimit()+"/"+df.format(player.getStats().getHP()),x+5,y+60);
        bitmapFont.draw(batch,"Atk: "+player.getStats().getAtk(),x+5,y+40);
        bitmapFont.draw(batch,"Def: "+player.getStats().getDef()+"%",x+5,y+20);
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(x,y,width,height);
        shapeRenderer.end();
    }
}
