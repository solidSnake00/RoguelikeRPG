package com.mygdx.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
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
   private final Main main;
   private SpriteBatch batch, batch2;
   private OrthographicCamera camera, camera2;
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
   private List<Door> doorList;

   private MessageTile messageTile;
   private BitmapFont font;
   private List<MessageTile> messageTiles;
   private OrthographicCamera cameraMapUI;
   private Viewport uiMapView;
   private List<Room> roomList;
   private Room activeRoom;
   private List<TransferRoom> transferRoomList;

   private Sound backgroundMusic;

   public Room getRoomById(int id, List<Room> roomList){
       for (Room value : roomList) {
           if (value.getId() == id) {
               return value;
           }
       }
       return null;
   }


    public GameScreen(Main main, Player player,List<Weapon> weaponList, List<Shield> shieldList,
                      List<Armor> armorList, List<Item> itemList, GameState gameState){
        this.main=main;
        this.weaponList=weaponList;
        this.shieldList=shieldList;
        this.armorList=armorList;
        this.itemList=itemList;
        this.gameState=gameState;
    }

    @Override
    public void show() {
        super.show();
        backgroundMusic= Gdx.audio.newSound(Gdx.files.internal("001.ogg"));

        messageTile=new MessageTile(0,1,8,"this a test message");
        font=new BitmapFont();


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
/////////////////////////////////////////////////////////////
        roomList=new ArrayList<>();
        room=new Room(1,81);
        room.importCSV(path+"level1-1.csv");
        room.setDecorationTileMapImage("ground.png");

        roomList.add(room);

        room=new Room(2,20);
        room.importCSV(path+"level 1_Tile Layer 1.csv");
        room.setDecorationTileMapImage("ground.png");
        roomList.add(room);

        room=new Room(3,20);
        room.importCSV(path+"level 2_Tile Layer 1.csv");
        room.setDecorationTileMapImage("ground.png");
        roomList.add(room);

        activeRoom=getRoomById(2,roomList);
        numberOfTiles=activeRoom.getNumberOfTiles();
        gameObjects=activeRoom.getGameObjects();
        chestList=activeRoom.getImportObjectTileMapCSV().getChestList();
        enemyList=activeRoom.getImportObjectTileMapCSV().getEnemyList();
        messageTiles=activeRoom.getImportObjectTileMapCSV().getMessageTiles();
        doorList=activeRoom.getImportObjectTileMapCSV().getDoors();
        transferRoomList=activeRoom.getImportObjectTileMapCSV().getTransferRoomList();
////////////////////////////////////////////////////////////////////

        batch = new SpriteBatch();
        camera=new OrthographicCamera();
        camera.setToOrtho(false, 640, 480);

        camera.update();

        camera2=new OrthographicCamera();
        camera2.setToOrtho(false,640,480);
        camera2.update();
        cameraMapUI=new OrthographicCamera();
        cameraMapUI.setToOrtho(false,640,480);
        cameraMapUI.update();

        mapObject =new MapObject(numberOfTiles,32);
        groundDecorationMap=activeRoom.getDecorationTileMap();

        mapObject.setAllTilesToCell(gameObjects);
        mapObject.setGameObjectToCell(player);

        for (Chest chest : chestList) {
            mapObject.setGameObjectToCell(chest);
        }
        for (Enemy enemy: enemyList){
            mapObject.setGameObjectToCell(enemy);
        }
        for (MessageTile tile : messageTiles) {
            mapObject.setGameObjectToCell(tile);
        }
        for (Door door1 : doorList) {
            mapObject.setGameObjectToCell(door1);
        }
        for (TransferRoom transferRoom : transferRoomList){
            mapObject.setGameObjectToCell(transferRoom);
        }

        player.calculateStats();
        player.setX(activeRoom.getImportObjectTileMapCSV().getPlayerX());
        player.setY(activeRoom.getImportObjectTileMapCSV().getPlayerY());

        fitViewport=new FitViewport(640,480,camera);
        playerVector =new Vector2(player.getX()*32,player.getY()*32);
        cameraVector=new Vector2(camera.position.x,camera.position.y);
        player.setTileType(TileType.PLAYER);

        uiMapView=new FitViewport(200,200,cameraMapUI);

        uiMapView.setScreenBounds(Gdx.graphics.getWidth()-290,Gdx.graphics.getHeight()-300,200,200);
        //backgroundMusic.play(0.05f);
        backgroundMusic.loop(0.2f);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        ScreenUtils.clear(Color.BLACK, false);
        fitViewport.apply();


        for (int i=0;i<transferRoomList.size();i++){
            if (player.getX()==transferRoomList.get(i).getX() && player.getY()==transferRoomList.get(i).getY()){

                //if (transferRoomList.get(i).getId()==2){
                    activeRoom=getRoomById(transferRoomList.get(i).getId(),roomList);
                    numberOfTiles=activeRoom.getNumberOfTiles();
                    gameObjects=activeRoom.getGameObjects();
                    chestList=activeRoom.getImportObjectTileMapCSV().getChestList();
                    enemyList=activeRoom.getImportObjectTileMapCSV().getEnemyList();
                    messageTiles=activeRoom.getImportObjectTileMapCSV().getMessageTiles();
                    doorList=activeRoom.getImportObjectTileMapCSV().getDoors();
                    transferRoomList=activeRoom.getImportObjectTileMapCSV().getTransferRoomList();
                    groundDecorationMap=activeRoom.getDecorationTileMap();

                    player.setX(activeRoom.getImportObjectTileMapCSV().getPlayerX());
                    player.setY(activeRoom.getImportObjectTileMapCSV().getPlayerY());

                    mapObject =new MapObject(activeRoom.getNumberOfTiles(),32);
                    mapObject.setAllTilesToCell(gameObjects);
                    mapObject.setGameObjectToCell(player);

                    for (Chest chest:chestList){
                        mapObject.setGameObjectToCell(chest);
                    }
                    for (MessageTile message:messageTiles){
                        mapObject.setGameObjectToCell(message);
                    }
                    for (Door door1:doorList){
                        mapObject.setGameObjectToCell(door1);
                    }
                for (TransferRoom transferRoom : transferRoomList){
                    mapObject.setGameObjectToCell(transferRoom);
                }
                break;
                //}
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
            if (camera.zoom>1.1f){
                camera.zoom+=0;
            }else {
                camera.zoom += 0.1f;
            }
        }else if (Gdx.input.isKeyJustPressed(Input.Keys.B)){
            if (camera.zoom<0.4f){
                camera.zoom-=0;
            }else {
                camera.zoom -= 0.1f;
            }
        }

        if (camera.zoom!=1){
            camera.position.x=((player.getX())*32)+15;
            camera.position.y=player.getY()*32;
        }


        camera.update();
        camera2.update();
        cameraMapUI.position.x=player.getX()*5+201;
        cameraMapUI.position.y=player.getY() *5+201;
        cameraMapUI.update();

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
                    } else if (enemy.getEnemyAIEnum()==EnemyAIEnum.NO_AI) {

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
        for (Door value : doorList) {

            if (value.getShowUI()) {
                value.drawUI(camera2);
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.R) || player.getStats().getHP()<=0) {
            backgroundMusic.stop();
            main.setScreen(new GameScreen(main,player,weaponList,shieldList,armorList,itemList,gameState));
        }

        guiShield.drawRectangle(player.getEquipment().getLeftHand().getImage(),camera2,batch2,shapeRenderer,-15,0);
        guiWeapon.drawRectangle(player.getEquipment().getRightHand().getImage(),camera2,batch2,shapeRenderer,15,-3);
        guiWardrobe.drawRectangle(player.getEquipment().getWardrobe().getImage(),camera2,batch2,shapeRenderer,0,0);
        guiItem.drawRectangle(player.getEquipment().getItemSlot().getImage(),camera2,batch2,shapeRenderer,0,0);
        if (player.getEquipment().getItemSlot().getId()!=0) {
            guiItem.showItemName(player.getEquipment().getItemSlot(), batch2, camera2, 45, 15);
        }

        for (int i=0;i<messageTiles.size();i++) {
            if (player.getX() == messageTiles.get(i).getX() && player.getY() == messageTiles.get(i).getY()) {

                messageTiles.get(i).drawMessage(100, 150, 100, 400, camera2, messageTiles.get(i).getText());

            }
        }




            /*uiMapView.apply();

            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setProjectionMatrix(cameraMapUI.combined);
            shapeRenderer.setColor(new Color(0, 0, 0, 0.8f));
            shapeRenderer.rect((cameraMapUI.position.x)-100, (cameraMapUI.position.y)-100, 400, 400);
            for (int i=0;i<activeRoom.getNumberOfTiles();i++){
                for (int j=0;j<activeRoom.getNumberOfTiles();j++){
                    if (mapObject.getCell(i,j).getGameObject().getTileType()==TileType.SOLID){
                        shapeRenderer.setColor(new Color(1, 0, 0, 0.5f));
                        shapeRenderer.rect(i*5+201,j*5+201,5,5);
                    }else if (mapObject.getCell(i,j).getGameObject().getTileType()==TileType.ENEMY){
                        shapeRenderer.setColor(new Color(0f,1f,1f,0.5f));
                        shapeRenderer.rect(i*5+201,j*5+201,5,5);
                    }
                }
            }
            shapeRenderer.setColor(new Color(0,0,1,0.5f));
            shapeRenderer.rect(player.getX()*5+201, player.getY() *5+201,5,5);
            shapeRenderer.end();

            Gdx.gl.glDisable(GL20.GL_BLEND);*/

    }


    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        fitViewport.update(width,height,true);
        uiMapView.update(width,height,true);
        uiMapView.setScreenBounds(width-(width/4),30,200,200);
        System.out.println("width: "+width);
        System.out.println("height: "+height);


    }

    @Override
    public void dispose() {
        super.dispose();
        batch2.dispose();
        bloodImg.dispose();
        backgroundMusic.dispose();
        player.disposeSound();

        batch.dispose();
        mapObject.dispose();
        groundDecorationMap.getImage().dispose();

        shapeRenderer.dispose();

    }
}