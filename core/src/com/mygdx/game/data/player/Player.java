package com.mygdx.game.data.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.GameState;
import com.mygdx.game.data.*;
import com.mygdx.game.data.inventory.*;
import com.mygdx.game.tilemap.MapObject;

import java.text.DecimalFormat;
import java.util.List;

public class Player implements GameObject {
   public static Boolean moved;
    private String name;
    private final Stats stats;
    private final Inventory inventory;
    private final Equipment equipment;
    private int x;
    private int y;
    private Texture image;

    private Lock lock;
    private int a;
    private int f;
    private int c;
    private int b;
    private TileType tileType;
    private final BitmapFont font;
    private final DecimalFormat df;

    public Player(Weapon weapon, Shield shield, Armor armor, Item item){
        moved=false;
        x=0;
        y=0;
        df=new DecimalFormat("#.##");

        stats=new Stats();
        equipment=new Equipment();
        inventory=new Inventory();
        initialize(weapon,shield, armor, item);
        a=inventory.getAllShields().indexOf(equipment.getLeftHand());
        f=inventory.getAllWeapons().indexOf(equipment.getRightHand());
        c=inventory.getAllArmors().indexOf(equipment.getWardrobe());
        font=new BitmapFont();

        lock=new Lock("lock_img.png");

    }
    public Player(String imagePath, Weapon weapon, Shield shield, Armor armor, Item item){
        moved=false;
        x=0;
        y=0;
        image=new Texture(imagePath);

        df=new DecimalFormat("#.##");

        stats=new Stats();
        equipment=new Equipment();
        inventory=new Inventory();
        initialize(weapon,shield,armor, item);
        a=inventory.getAllShields().indexOf(equipment.getLeftHand());
        f=inventory.getAllWeapons().indexOf(equipment.getRightHand());
        c=inventory.getAllArmors().indexOf(equipment.getWardrobe());
        b=inventory.getAllItems().indexOf(equipment.getItemSlot());
        font=new BitmapFont();
        lock=new Lock("lock_img.png");

        //uiBox=new DialogBox();
    }

    public Player(Player player){
        moved=false;
        name=player.name;
        stats=player.stats;
        inventory=player.inventory;
        equipment=player.equipment;
        x= player.x;
        y= player.y;
        image=player.image;

        lock=player.lock;
        a=player.a;
        f= player.f;
        c=player.c;
        b=player.b;
        tileType=player.tileType;
        font=player.font;

        df=player.df;
    }

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }

    @Override
    public void drawUI(Camera camera) {
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int x){
        this.x=x;
    }
    public void setY(int y){
        this.y=y;
    }

    @Override
    public TileType getTileType() {
        return null;
    }

    @Override
    public void die() {

    }

    public Texture getImage(){
        return image;
    }

    @Override
    public void setImage(String imagePath) {
        image=new Texture(imagePath);
    }

    public void moveRight(){
        x++;
        moved=true;

    }
    public void moveLeft(){
        x--;
        moved=true;
    }
    public void moveUp(){
        y++;
        moved=true;
    }
    public void moveDown(){
        y--;
        moved=true;
    }

    public void update(MapObject mapObject, Camera camera, GameState gameState,SpriteBatch batch, Camera camera2){
        if (gameState==GameState.NORMAL){
            moved=false;

            interactEquipment();
            openChest(mapObject,camera);

            if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
                if(mapObject.getCell(getX()+1, getY() ).getGameObject().getTileType() != TileType.SOLID &&
                        mapObject.getCell(getX()+1, getY() ).getGameObject().getTileType() != TileType.ENEMY) {
                    moveRight();
                }
                else {
                    x=this.getX();

                    if(mapObject.getCell(getX()+1, getY() ).getGameObject() instanceof Enemy){//.getTileType() == TileType.ENEMY){
                        lock.setLocked(true);
                        lock.setGameObject(mapObject.getCell(getX()+1, getY() ).getGameObject());
                        lock.setX(lock.getGameObject().getX());
                        lock.setY(lock.getGameObject().getY());
                    }
                    if (mapObject.getCell(getX()+1, getY() ).getGameObject() instanceof Door){
                        interactDoor(mapObject.getCell(getX()+1, getY() ).getGameObject(),camera);
                    }
                }
            }
            else if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
                if(mapObject.getCell(getX()-1, getY() ).getGameObject().getTileType() != TileType.SOLID &&
                        mapObject.getCell(getX()-1, getY() ).getGameObject().getTileType() != TileType.ENEMY) {
                    moveLeft();
                }
                else {
                    x=this.getX();
                    if(mapObject.getCell(getX()-1, getY() ).getGameObject() instanceof Enemy){//.getTileType() == TileType.ENEMY){
                        lock.setLocked(true);
                        lock.setGameObject(mapObject.getCell(getX()-1, getY()).getGameObject());
                        lock.setX(lock.getGameObject().getX());
                        lock.setY(lock.getGameObject().getY());
                    }
                    if (mapObject.getCell(getX()-1, getY() ).getGameObject() instanceof Door){
                        interactDoor(mapObject.getCell(getX()-1, getY() ).getGameObject(),camera);
                    }
                }
            }
            else if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                if(mapObject.getCell(getX(), getY()+1 ).getGameObject().getTileType() != TileType.SOLID &&
                        mapObject.getCell(getX(), getY()+1 ).getGameObject().getTileType() != TileType.ENEMY) {
                    moveUp();
                }else {
                    y=this.getY();
                    if(mapObject.getCell(getX(), getY()+1 ).getGameObject() instanceof Enemy){//.getTileType() == TileType.ENEMY){
                        lock.setLocked(true);
                        lock.setGameObject(mapObject.getCell(getX(), getY()+1 ).getGameObject());
                        lock.setX(lock.getGameObject().getX());
                        lock.setY(lock.getGameObject().getY());
                    }
                    if (mapObject.getCell(getX(), getY()+1 ).getGameObject() instanceof Door){
                        interactDoor(mapObject.getCell(getX(), getY()+1 ).getGameObject(),camera);
                    }
                }
            }
            else if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
                if(mapObject.getCell(getX(), getY()-1 ).getGameObject().getTileType() != TileType.SOLID &&
                        mapObject.getCell(getX(), getY()-1 ).getGameObject().getTileType() != TileType.ENEMY) {
                    moveDown();
                }else{
                    y=this.getY();
                    if(mapObject.getCell(getX(), getY()-1 ).getGameObject() instanceof Enemy){//.getTileType() == TileType.ENEMY){
                        lock.setLocked(true);
                        lock.setGameObject(mapObject.getCell(getX(), getY()-1 ).getGameObject());
                        lock.setX(lock.getGameObject().getX());
                        lock.setY(lock.getGameObject().getY());
                    }
                    if (mapObject.getCell(getX(), getY()-1 ).getGameObject() instanceof Door){
                        interactDoor(mapObject.getCell(getX(), getY()-1 ).getGameObject(),camera);
                    }
                }

            }
            ///////////////////////
            if (Gdx.input.isKeyJustPressed(Input.Keys.Q)){
                useItem(equipment.getItemSlot());
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.M)){
                for (int i=0;i<inventory.getAllItems().size();i++){
                    System.out.println(inventory.getAllItems().get(i).getName());
                }
            }

        }

        if (gameState==GameState.LOCKED && lock.getLocked()){
            if (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)){
                dealDamage(mapObject, camera2,batch);
            }
        }
    }
    ///////////////////////////////
    private void initialize(Weapon weapon, Shield shield, Armor armor, Item item){
        inventory.setObjectItem(weapon);
        inventory.setObjectItem(shield);
        inventory.setObjectItem(armor);
        inventory.setObjectItem(item);
        stats.setAtk(3);
        stats.setHPLimit(100);
        stats.setMPLimit(10);
        stats.setDef(1);
        stats.setHP(stats.getHPLimit());
        stats.setMP(stats.getMPLimit());
        stats.setExp(1);
        stats.setExpLimit(20);
        stats.setCoins(0);
        stats.setLevel(1);
        equipment.setLeftHand(shield);
        equipment.setRightHand(weapon);
        equipment.setWardrobe(armor);
        equipment.setItemSlot(item);
    }

    private void interactEquipment(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)){
            a++;
            if (a >=inventory.getAllShields().size()){
                a =0;
            }
            equipment.setLeftHand(inventory.getAllShields().get(a));
            calculateStats();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)){
            f++;
            if (f >=inventory.getAllWeapons().size()){
                f =0;
            }
            equipment.setRightHand(inventory.getAllWeapons().get(f));
            calculateStats();

        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.S)){
            c++;
            if (c >=inventory.getAllArmors().size()){
                c =0;
            }
            equipment.setWardrobe(inventory.getAllArmors().get(c));
            calculateStats();

        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)){
            b++;
            if (b >=inventory.getAllItems().size()){
                b =0;
            }
            equipment.setItemSlot(inventory.getAllItems().get(b));
            //System.out.println(inventory.getAllItems().get());
        }
    }
    //////////////////////////////
    public void calculateStats(){
        stats.setAtk(equipment.getRightHand().getAtk()+equipment.getWardrobe().getAtk());
        stats.setDef(equipment.getLeftHand().getDef()+equipment.getWardrobe().getDef());

        if(stats.getExp()>=stats.getExpLimit()){
            stats.setLevel(stats.getLevel()+1);
        }
        System.out.println("player atk: "+stats.getAtk()+"\nplayer def: "+stats.getDef());
    }

    public Stats getStats(){
        return stats;
    }

    public Lock getLock() {
        return lock;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
    }

    public Equipment getEquipment(){
        return equipment;
    }

    public void drawPlayerEquipments(SpriteBatch batch){
        batch.draw(equipment.getWardrobe().getImage(),x*32,y*32);
        batch.draw(equipment.getRightHand().getImage(),x*32,y*32);
        batch.draw(equipment.getLeftHand().getImage(),x*32,y*32);
        if (lock.getLocked()) {
            batch.draw(lock.getImage(), lock.getX() * 32, lock.getY() * 32);
        }
    }
    /////////////////////////////////////////////////////

    private void openChest(MapObject mapObject, Camera camera){
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            if (mapObject.getCell(getX()+1,getY()).getGameObject() instanceof Chest &&
                    !((Chest) mapObject.getCell(getX() + 1, getY()).getGameObject()).getIsEmpty()){

                interactChest((Chest) mapObject.getCell(getX()+1,getY()).getGameObject(), camera);
            }
            if (mapObject.getCell(getX()-1,getY()).getGameObject() instanceof Chest &&
                    !((Chest) mapObject.getCell(getX() - 1, getY()).getGameObject()).getIsEmpty()){

                interactChest((Chest) mapObject.getCell(getX()-1,getY()).getGameObject(),camera);
            }
            if (mapObject.getCell(getX(),getY()+1).getGameObject() instanceof Chest &&
                    !((Chest) mapObject.getCell(getX(), getY() + 1).getGameObject()).getIsEmpty()){

                interactChest((Chest) mapObject.getCell(getX(),getY()+1).getGameObject(),camera);
            }
            if (mapObject.getCell(getX(),getY()-1).getGameObject() instanceof Chest &&
                    !((Chest) mapObject.getCell(getX(), getY() - 1).getGameObject()).getIsEmpty()){

                interactChest((Chest) mapObject.getCell(getX(),getY()-1).getGameObject(),camera);
            }
        }
    }
    private void interactChest(Chest chest, Camera camera){
        List<ObjectItem> objectItemList;

        chest.setImage("chest_open_img.png");

        chest.setIsEmpty(true);
        inventory.setObjectItem(chest.getObjectItem());
        objectItemList=inventory.getAllObjectItem();

        System.out.println("inventory list:");
        for (ObjectItem objectItem : objectItemList) {
            System.out.println(objectItem.getName());
        }
    }

    private void dealDamage(MapObject mapObject, Camera camera,SpriteBatch batch){
        Enemy enemy= (Enemy) lock.getGameObject();

        if(enemy.getHP()<=0){
            lock.setLocked(false);
            enemy.setTileType(TileType.TRAVERSABLE);
            enemy.setImage("blank.png");
            enemy.setEnemyAIEnum(EnemyAIEnum.NO_AI);


            EmptyObject emptyObject=new EmptyObject();
            emptyObject.setY(enemy.getY());
            emptyObject.setX(enemy.getX());
            mapObject.setGameObjectToCell(emptyObject);

        }else {
            float defAbs= (float) (stats.getAtk()*enemy.getDef())/100;

            System.out.println("defabs= "+df.format(defAbs));
            enemy.setHP((enemy.getHP()-(getStats().getAtk()-defAbs)));
            enemy.attack(this);
            System.out.println("players HP: "+df.format(stats.getHP()));
        }

    }

    public void useItem(Item item){
        if (item.getId()==1){
            stats.setHP(getStats().getHP()+10);
            System.out.println("item used");
        }

        if (item.getId()!=0 && item.getItemType()==ItemType.CONSUMABLE) {
            inventory.deleteItem(item.getId());
            b--;
            if (b <= 0) {
                b = 0;
            }
            equipment.setItemSlot(inventory.getAllItems().get(b));
        }
    }

    private void interactDoor(GameObject gameObject, Camera camera){
        Door door= (Door) gameObject;
        for (int i=0;i<inventory.getAllItems().size();i++){
            if (inventory.getAllItems().get(i).getId()==door.getKeyItem().getId()){
                door.setOpened(true);
                door.setTileType(TileType.TRAVERSABLE);
                door.setShowUI(true);

                break;
            }
        }
        door.setShowUI(true);

    }

}