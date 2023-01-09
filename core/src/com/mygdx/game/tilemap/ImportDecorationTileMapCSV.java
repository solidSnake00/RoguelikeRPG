package com.mygdx.game.tilemap;

public class ImportDecorationTileMapCSV {
    private String line;
    private String splitBy;
    private String filePath;
    private int numberOfTiles;

    private DecorationTileMap decorationTileMap;

    public ImportDecorationTileMapCSV(){
        this.line="";
        this.splitBy=",";
        decorationTileMap=new DecorationTileMap();
    }


}
