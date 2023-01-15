package com.mygdx.game.data;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GameState;
import com.mygdx.game.data.player.Player;
import com.mygdx.game.tilemap.MapObject;

import java.text.DecimalFormat;
import java.util.Random;

public class Enemy implements GameObject{
    private int id;
    private String name;
    private int x;
    private int y;
    private Texture image;
    private TileType tileType;

    private int level;
    private int atk;
    private int def;
    private int HPLimit;
    private float HP;

    private DecimalFormat decimalFormat;

    private Boolean isLocked;

    private Random random;

    public Boolean moved;
    private EnemyAIEnum enemyAIEnum;
    private int exp;

    public Enemy(){
        isLocked=false;
        moved=false;
        decimalFormat=new DecimalFormat("#.##");
        random=new Random();
        tileType=TileType.ENEMY;
    }

    public Enemy(Enemy enemy){
        isLocked=false;
        moved=false;
        decimalFormat=new DecimalFormat("#.##");
        random=new Random();
        tileType=TileType.ENEMY;
        this.id=enemy.id;
        this.name=enemy.name;
        this.level=enemy.level;
        this.atk=enemy.atk;
        this.def=enemy.def;
        this.HPLimit=enemy.HPLimit;
        this.image=enemy.image;
        this.exp=enemy.exp;
        setHP(HPLimit);

    }

    public Enemy(int id, String name, String imagePath, int level, int atk, int def, int HPLimit){

        tileType=TileType.ENEMY;
        this.id=id;
        this.name=name;
        this.level=level;
        this.atk=atk;
        this.def=def;
        this.HPLimit=HPLimit;
        setHP(HPLimit);
        moved=false;
        isLocked=false;
        image=new Texture(imagePath);
        decimalFormat=new DecimalFormat("#.##");
        random=new Random();
    }

    public EnemyAIEnum getEnemyAIEnum() {
        return enemyAIEnum;
    }

    public void setEnemyAIEnum(EnemyAIEnum enemyAIEnum) {
        this.enemyAIEnum = enemyAIEnum;
    }

    @Override
    public void drawUI(Camera camera) {

    }


    public int getX() {
        return x;
    }


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
    public void setImage(Texture image){
        this.image=image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setTileType(TileType tileType){
        this.tileType=tileType;
    }
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getHPLimit() {
        return HPLimit;
    }

    public void setHPLimit(int HPLimit) {
        this.HPLimit = HPLimit;
        HP=getHPLimit();
    }

    public float getHP() {
        return HP;
    }

    public void setHP(float HP) {
        this.HP = HP;
    }
    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsLocked(){
        return isLocked;
    }
    public void setIsLocked(Boolean isLocked){
        this.isLocked=isLocked;
    }

    public void attack(Player player){
        float defAbs= (float) (getAtk()*player.getEquipment().getLeftHand().getDef() +
                getAtk()*player.getEquipment().getWardrobe().getDef())/100;

        System.out.println("defabs= "+decimalFormat.format(defAbs));
        player.getStats().setHP(player.getStats().getHP()-(getAtk()-defAbs));
    }

    public void moveRight(){
        x++;
    }
    public void moveLeft(){
        x--;
    }
    public void moveUp(){
        y++;
    }
    public void moveDown(){
        y--;
    }

    public void followPlayer(Player player, MapObject mapObject, GameState gameState){
        if (gameState==GameState.NORMAL) {
            if (player.getX()-1 >x && (mapObject.getCell(x + 1, y).getGameObject().getTileType()!= TileType.SOLID &&
                    mapObject.getCell(x + 1, y).getGameObject().getTileType()!= TileType.ENEMY)){


                moveRight();
                EmptyObject emptyObject=new EmptyObject();
                emptyObject.setX(x-1);
                emptyObject.setY(y);
                mapObject.setGameObjectToCell(emptyObject);
                moved=true;
            } else if (player.getX()+1 < x && (mapObject.getCell(x - 1, y).getGameObject().getTileType() != TileType.SOLID &&
                    mapObject.getCell(x - 1, y).getGameObject().getTileType()!= TileType.ENEMY)){


                moveLeft();
                EmptyObject emptyObject=new EmptyObject();
                emptyObject.setX(x+1);
                emptyObject.setY(y);
                mapObject.setGameObjectToCell(emptyObject);
                moved=true;
            }
            else if (player.getY()-1 > y && (mapObject.getCell(x, y+1).getGameObject().getTileType() != TileType.SOLID &&
                    mapObject.getCell(x, y+1).getGameObject().getTileType()!= TileType.ENEMY)){

                moveUp();
                EmptyObject emptyObject=new EmptyObject();
                emptyObject.setX(x);
                emptyObject.setY(y-1);
                mapObject.setGameObjectToCell(emptyObject);
                moved=true;

            } else if (player.getY()+1 < y && (mapObject.getCell(x , y-1).getGameObject().getTileType() != TileType.SOLID &&
                    mapObject.getCell(x , y-1).getGameObject().getTileType()!= TileType.ENEMY)){

                moveDown();
                EmptyObject emptyObject=new EmptyObject();
                emptyObject.setX(x);
                emptyObject.setY(y+1);
                mapObject.setGameObjectToCell(emptyObject);
                moved=true;
            }
        }
    }

    public void randomMovement(Player player,MapObject mapObject, GameState gameState){
        //Player player1=mapObject.getPlayer();
        if (gameState==GameState.NORMAL){

            int randomNum = random.nextInt((7 - 1) + 1) + 1;

            if (randomNum==1 && mapObject.getCell(getX(),getY()+1).getGameObject().getTileType() != TileType.SOLID ){

                moveUp();
                if (x==player.getX() && y==player.getY()){
                    randomMovement(player,mapObject,gameState);
                }
                EmptyObject emptyObject=new EmptyObject();
                emptyObject.setX(x);
                emptyObject.setY(y-1);
                mapObject.setGameObjectToCell(emptyObject);
                moved=true;
            }

            if (randomNum==2 && mapObject.getCell(getX(),getY()-1).getGameObject().getTileType() != TileType.SOLID){
                moveDown();
                if (x==player.getX() && y==player.getY()){
                    randomMovement(player,mapObject,gameState);
                }
                EmptyObject emptyObject=new EmptyObject();
                emptyObject.setX(x);
                emptyObject.setY(y+1);
                mapObject.setGameObjectToCell(emptyObject);
                moved=true;
            }
            if (randomNum==3 && mapObject.getCell(getX()-1,getY()).getGameObject().getTileType() != TileType.SOLID){
                moveLeft();
                if (x==player.getX() && y==player.getY()){
                    randomMovement(player,mapObject,gameState);
                }
                EmptyObject emptyObject=new EmptyObject();
                emptyObject.setX(x+1);
                emptyObject.setY(y);
                mapObject.setGameObjectToCell(emptyObject);
                moved=true;
            }
            if (randomNum==4 && mapObject.getCell(getX()+1,getY()).getGameObject().getTileType() != TileType.SOLID){
                moveRight();
                if (x==player.getX() && y==player.getY()){
                    randomMovement(player,mapObject,gameState);
                }
                EmptyObject emptyObject=new EmptyObject();
                emptyObject.setX(x-1);
                emptyObject.setY(y);
                mapObject.setGameObjectToCell(emptyObject);
                moved=true;
            }

        }
    }

    @Override
    public void die(){

    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}
