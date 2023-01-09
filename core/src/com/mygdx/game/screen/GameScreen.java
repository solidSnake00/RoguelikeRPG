package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.GameState;
import com.mygdx.game.Main;
import com.mygdx.game.Room;
import com.mygdx.game.UI.GuiBox;
import com.mygdx.game.UI.HealthGui;
import com.mygdx.game.data.*;
import com.mygdx.game.data.inventory.Armor;
import com.mygdx.game.data.inventory.Item;
import com.mygdx.game.data.inventory.Shield;
import com.mygdx.game.data.inventory.Weapon;
import com.mygdx.game.data.player.Player;
import com.mygdx.game.tilemap.DecorationTileMap;
import com.mygdx.game.tilemap.MapObject;

import java.util.ArrayList;
import java.util.List;

public class GameScreen extends ScreenAdapter {
   private Main main;
   private SpriteBatch batch, batch2;
   private OrthographicCamera camera, camera2;
   private Player player1;
   private MapObject mapObject;
   private GameObject[][] gameObjects;
   private int numberOfTiles;
   private DecorationTileMap groundDecorationMap;
   private String path;
   private final List<Weapon> weaponList;
   private final List<Shield> shieldList;
   private final List<Item> itemList;
   private final List<Armor> armorList;
   private GuiBox guiWeapon, guiWardrobe, guiItem, guiShield;
   private List<Chest> chestList;
   private GameState gameState;
   private FitViewport fitViewport;
   private ShapeRenderer shapeRenderer;
   private Room room;
   private List<Enemy> enemyList;
   private Player player;
   private HealthGui healthGui;
   private Texture bloodImg;
   private Vector2 playerVector;
   private Vector2 cameraVector;

   private Door door;

    public GameScreen(Main main, Player player,List<Weapon> weaponList, List<Shield> shieldList,
                      List<Armor> armorList, List<Item> itemList, GameState gameState){
        this.main=main;
        this.weaponList=weaponList;
        this.shieldList=shieldList;
        this.armorList=armorList;
        this.itemList=itemList;
        this.gameState=gameState;
        this.player1=player;
    }

    @Override
    public void show() {
        super.show();

        door=new Door();
        door.setImage("door_1_img.png");
        door.setX(9);
        door.setY(12);
        door.setKeyItem(itemList.get(1));

        shapeRenderer=new ShapeRenderer();
        bloodImg=new Texture("blood_img.png");
        enemyList=new ArrayList<>();
        player=new Player("player_img.png",weaponList.get(0),shieldList.get(0),armorList.get(0),itemList.get(0));

        player.setX(2);
        player.setY(2);
        player.getEquipment().setItemSlot(itemList.get(0));
        player.setTileType(TileType.SOLID);

        healthGui =new HealthGui(450,400,75,150);
        ////////////////
        batch2=new SpriteBatch();
        guiShield =new GuiBox(100,80,50,50);
        guiWeapon=new GuiBox(15,80,50,50);
        guiWardrobe=new GuiBox(60,140,50,50);
        guiItem=new GuiBox(60,20,50,50);

        chestList=new ArrayList<>();
        path="assets\\";

        room=new Room(1,20);
        numberOfTiles=room.getNumberOfTiles();
        room.importCSV(path+"wall_layer.csv");
        gameObjects=room.getGameObjects();
        chestList=room.getImportObjectTileMapCSV().getChestList();
        enemyList=room.getImportObjectTileMapCSV().getEnemyList();


        batch = new SpriteBatch();
        camera=new OrthographicCamera();
        camera.setToOrtho(false, 640, 480);

        camera.update();

        camera2=new OrthographicCamera();
        camera2.setToOrtho(false,640,480);
        camera2.update();

        mapObject =new MapObject(numberOfTiles,32);

        room.setDecorationTileMapImage("ground.png");
        groundDecorationMap=room.getDecorationTileMap();

        mapObject.setAllTilesToCell(gameObjects);

        //mapObject.setGameObjectToCell(player);
        mapObject.setGameObjectToCell(door);
        mapObject.setGameObjectToCell(player);

        for (Chest chest : chestList) {
            mapObject.setGameObjectToCell(chest);
        }
        for (Enemy enemy: enemyList){
            mapObject.setGameObjectToCell(enemy);
        }

        player.calculateStats();

        fitViewport=new FitViewport(640,480,camera);
        playerVector =new Vector2(player.getX()*32,player.getY()*32);
        cameraVector=new Vector2(camera.position.x,camera.position.y);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        ScreenUtils.clear(Color.BLACK, false);
        if (Gdx.input.isKeyJustPressed(Input.Keys.U)){
            room.setId(2);
            room.setNumberOfTiles(20);
            room.importCSV(path+"wall_layer_2.csv");
            gameObjects= room.getGameObjects();
            chestList=room.getImportObjectTileMapCSV().getChestList();
            enemyList=room.getImportObjectTileMapCSV().getEnemyList();

            mapObject =new MapObject(numberOfTiles,32);
            mapObject.setAllTilesToCell(gameObjects);
            mapObject.setGameObjectToCell(player);

            for (Chest chest : chestList) {
                mapObject.setGameObjectToCell(chest);
            }


        }

        if (player.getX()<=gameObjects[0][0].getX()+5){
            camera.position.x=(gameObjects[0][0].getX()+5)*32;

        } else if (player.getX()>gameObjects[gameObjects.length-1][0].getX()-5) {
            camera.position.x=(gameObjects[gameObjects.length-1][0].getX()-5)*32;

        } else {
            camera.position.x =(player.getX() * 32);
        }

        if (player.getY()<=gameObjects[0][0].getY()+5){
            camera.position.y=(gameObjects[0][0].getY()+5)*32;

        } else if (player.getY()>gameObjects[0][gameObjects.length-1].getY()-5) {
            camera.position.y=(gameObjects[0][gameObjects.length-1].getY()-5)*32;

        }else {
            camera.position.y =(player.getY()* 32);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.N)){
            camera.zoom+=0.1f;
        }else if (Gdx.input.isKeyJustPressed(Input.Keys.B)){
            camera.zoom-=0.1f;
        }

        if (camera.zoom!=1){
            camera.position.x=((player.getX())*32)+15;
            camera.position.y=player.getY()*32;
        }

        camera.update();
        camera2.update();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.setColor(Color.LIGHT_GRAY);
        shapeRenderer.rect(camera.position.x-400,camera.position.y-300,1000,1000);
        shapeRenderer.end();


        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        groundDecorationMap.drawDecorationMap(batch);
        batch.end();


        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        groundDecorationMap.drawDecorationMap(batch);
        mapObject.drawAllObjects(batch);
        player.drawPlayerEquipments(batch);
        batch.end();

        player.update(mapObject, camera2, gameState,batch, camera);

        for (Enemy enemy : enemyList) {

            if (enemy != null) {
                if (enemy.getHP()<=0){
                    batch.begin();
                    batch.setProjectionMatrix(camera.combined);
                    batch.draw(bloodImg,enemy.getX()*32,enemy.getY()*32);
                    batch.end();
                }

                if (Player.moved) {
                    if (enemy.getEnemyAIEnum() == EnemyAIEnum.FOLLOW_PLAYER) {
                        //enemy.followPlayer(player, mapObject, gameState);
                    } else if (enemy.getEnemyAIEnum() == EnemyAIEnum.RANDOM_MOVEMENT) {
                        enemy.randomMovement(player,mapObject, gameState);
                    }
                    mapObject.setGameObjectToCell(enemy);
                }
            }
        }


        if (player.getLock().getLocked()) {
            gameState = GameState.LOCKED;
            healthGui.drawEnemyGui((Enemy) player.getLock().getGameObject(),camera2,shapeRenderer,batch2);
        }else {
            gameState=GameState.NORMAL;
        }
        healthGui.drawPlayerGui(player,camera2,shapeRenderer,batch2,20,400,150,75);


        for (Chest chest : chestList) {
            if (chest.getIsEmpty() && chest.getUiBox().getShow()) {
                chest.drawUI(camera2);
            }
        }
        if (door.getShowUI()){
            door.drawUI(camera2);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.E) || player.getStats().getHP()<=0) {
            dispose();
            show();
        }
        guiShield.drawRectangle(player.getEquipment().getLeftHand().getImage(),camera2,batch2,shapeRenderer,-15,0);
        guiWeapon.drawRectangle(player.getEquipment().getRightHand().getImage(),camera2,batch2,shapeRenderer,15,-3);
        guiWardrobe.drawRectangle(player.getEquipment().getWardrobe().getImage(),camera2,batch2,shapeRenderer,0,0);
        guiItem.drawRectangle(player.getEquipment().getItemSlot().getImage(),camera2,batch2,shapeRenderer,0,0);


        if (Gdx.input.isKeyPressed(Input.Keys.L)) {

            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setProjectionMatrix(camera2.combined);
            shapeRenderer.setColor(new Color(0, 0, 0, 0.5f));
            shapeRenderer.rect(10, 10, 200, 200);
            shapeRenderer.end();
            batch2.begin();
            batch2.setProjectionMatrix(camera2.combined);
            batch2.setColor(new Color(1, 1, 1, 0.5f));
            batch2.draw(player.getImage(), 100, 100);
            batch2.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);
        }


    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        fitViewport.update(width,height,true);
    }

    @Override
    public void dispose() {
        super.dispose();
        batch2.dispose();
        bloodImg.dispose();

        batch.dispose();
        mapObject.dispose();
        groundDecorationMap.getImage().dispose();

        shapeRenderer.dispose();

    }
}