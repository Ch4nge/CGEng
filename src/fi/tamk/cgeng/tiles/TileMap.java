package fi.tamk.cgeng.tiles;

import java.awt.Graphics;
import fi.tamk.cgeng.util.*;

public class TileMap{
    
    private int[][] tileMap;
    private TileSet tileSet;


    public TileMap(int[][] tileMap, TileSet tileSet){
        setTileSet(tileSet);
        setTileMap(tileMap);
    }

    public TileMap(String path, int width, int height, TileSet tileSet){
        loadMap(path, width, height);
        setTileSet(tileSet);
    }

    public void paint(Graphics g){
        for(int i = 0; i < tileMap.length; i++){
            for(int j = 0; j < tileMap[i].length; j++){
                tileSet.getTile(tileMap[i][j])
                .paint(g,(i*tileSet.getTileWidth()),(j*tileSet.getTileHeight()),
                tileSet.getTileWidth(),tileSet.getTileHeight());
            }
        }
    }

    private void loadMap(String path, int width, int height){
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        
        //load Map from file
        tileMap = new int[width][height];
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                tileMap[x][y] = Utils.parseInt(tokens[(x+y * width)]);
            }
        }
    }

    public void setTileSet(TileSet tileSet){
        this.tileSet = tileSet;
    }

    public void setTileMap(int[][] tileMap){
        this.tileMap = tileMap;
    }

    public Tile tileAt(int x, int y){
        if(x > 0 && x < tileMap.length && y > 0 && y < tileMap[0].length){
            return tileSet.getTile(tileMap[x][y]);
        }
        return tileSet.getTile(0);
    }

    public Tile tileAtCoord(int x, int y){
        if(x/tileSet.getTileWidth() > 0 && 
            x/tileSet.getTileWidth() < tileMap.length && 
            y/tileSet.getTileHeight() > 0 && 
            y/tileSet.getTileHeight() < tileMap[0].length)
            {
            return tileSet.getTile(tileMap[x / tileSet.getTileWidth()]
            [y / tileSet.getTileHeight()]);
        }
        return tileSet.getTile(0);
    }
}