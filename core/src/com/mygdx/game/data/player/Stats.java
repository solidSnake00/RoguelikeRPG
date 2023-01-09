package com.mygdx.game.data.player;

public class Stats {
    private int atk;
    private int def;
    private float HP;
    private int HPLimit;
    private int MP;
    private int MPLimit;
    private int level;
    private int exp;
    private int expLimit;
    private int coins;

    public Stats(){
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

    public float getHP() {
        return HP;
    }

    public void setHP(float HP) {
        this.HP = HP;
        if(this.getHP()>this.getHPLimit()){
            this.HP=this.HPLimit;
        }

    }

    public int getHPLimit() {
        return HPLimit;
    }

    public void setHPLimit(int HPLimit) {
        this.HPLimit = HPLimit;
    }

    public int getMP() {
        return MP;
    }

    public void setMP(int MP) {
        this.MP = MP;
        if(this.getMP()>this.getMPLimit()){
            this.MP=this.MPLimit;
        }
    }

    public int getMPLimit() {
        return MPLimit;
    }

    public void setMPLimit(int MPLimit) {
        this.MPLimit = MPLimit;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
        if(this.getExp()>this.getExpLimit()){
            this.exp=this.expLimit;
        }
    }

    public int getExpLimit() {
        return expLimit;
    }

    public void setExpLimit(int expLimit) {
        this.expLimit = expLimit;
        setExp(0);
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
}
