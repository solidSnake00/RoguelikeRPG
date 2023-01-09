package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.data.TileType;
import com.mygdx.game.data.inventory.*;
import com.mygdx.game.data.player.Player;
import com.mygdx.game.screen.GameScreen;
import jdk.vm.ci.aarch64.AArch64;

import java.util.List;

public class Main extends Game {
	String path;
	List<Weapon> weaponList;
	List<Item> itemList;
	List<Shield> shieldList;
	List<Armor> armorList;
	ImportObjectItemCSV importObjectItemCSV;

	GameState gameState;
	Player player;

	@Override
	public void create() {
		path="assets\\";
		importObjectItemCSV=new ImportObjectItemCSV();

		weaponList=importObjectItemCSV.importWeapons(path+"weapons.csv");
		itemList=importObjectItemCSV.importItems(path+"items.csv");
		shieldList=importObjectItemCSV.importShields(path+"shields.csv");
		armorList=importObjectItemCSV.importArmors(path+"armors.csv");
		gameState=GameState.NORMAL;

		setScreen(new GameScreen(this,player,weaponList,shieldList,armorList,itemList,gameState));
	}
}