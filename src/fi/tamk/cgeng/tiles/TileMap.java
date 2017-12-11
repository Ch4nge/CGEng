package fi.tamk.cgeng.tiles;

import java.awt.Graphics;
import fi.tamk.cgeng.util.Utils;
import fi.tamk.cgeng.util.GameCam;

/**
 * TileMap is map of tiles. Map is used to draw
 * tiles from TileSet to correct locations using
 * Tiles id information.
 */
public class TileMap{
    
    /**
     * int array that contains id's of tiles. tileMap
     * is used to draw tiles to correct locations.
     */
    private int[][] tileMap;
    /**
     * TileSet is Set where Tiles information(texture, id
     * width and height) is taken. 
     */
    private TileSet tileSet;

    /**
     * Constructor that initializes tileMap and tileSet
     * attributes.
     * @param tileMap int Array of tile id's
     * @param tileSet Set of tiles
     */
    public TileMap(int[][] tileMap, TileSet tileSet){
        setTileSet(tileSet);
        setTileMap(tileMap);
    }

    /**
     * Constructor that loads tileMap from file and initializes
     * tileSet
     * @param path path of file
     * @param width tileMaps width in tiles
     * @param height tileMaps height in tiles
     * @param tileSet tileSet where tiles info is taken. 
     */
    public TileMap(String path, int width, int height, TileSet tileSet){
        loadMap(path, width, height);
        setTileSet(tileSet);
    }

    /**
     * Paints whole tileSet to screen.
     * @param g map is rendered by Graphics class
     */
    public void paint(Graphics g){
        for(int i = 0; i < tileMap.length; i++){
            for(int j = 0; j < tileMap[i].length; j++){
                tileSet.getTile(tileMap[i][j])
                .paint(g,(i*tileSet.getTileWidth()),(j*tileSet.getTileHeight()),
                tileSet.getTileWidth(),tileSet.getTileHeight());
            }
        }
    }

    /**
     * Paints whole tileSet to screen, developer is able to
     * move all the tiles with xOffset and yOffset parameters.
     * @param g map is rendered by Garphics class
     * @param xOffset how much tiles are moved left
     * @param yOffset how much tiles are moved to up
     */
    public void paint(Graphics g, int xOffset, int yOffset){
        for(int i = 0; i < tileMap.length; i++){
            for(int j = 0; j < tileMap[i].length; j++){
                tileSet.getTile(tileMap[i][j])
                .paint(g,(i*tileSet.getTileWidth())-xOffset,(j*tileSet.getTileHeight()-yOffset),
                tileSet.getTileWidth(),tileSet.getTileHeight());
            }
        }
    }

    /**
     * Paints only tiles which given GameCam is looking at.
     * Called by default in GameCam's paint method.
     * @param g map is rendered by Garphics class
     * @param cam GameCam that you are using
     */
    public void paint(Graphics g, GameCam cam){
        for(int i = 0; i < tileMap.length; i++){
            for(int j = 0; j < tileMap[i].length; j++){
                if(i*tileSet.getTileWidth()+tileSet.getTileWidth() > cam.getX() &&
                   i*tileSet.getTileWidth() < cam.getX()+cam.getWidth() &&
                   j*tileSet.getTileHeight()+tileSet.getTileHeight() > cam.getY() &&
                   j*tileSet.getTileHeight() < cam.getY()+cam.getHeight()
                   ){
                    tileSet.getTile(tileMap[i][j])
                    .paint(g,(i*tileSet.getTileWidth())-cam.getX(),
                    (j*tileSet.getTileHeight()-cam.getY()),
                    tileSet.getTileWidth(),tileSet.getTileHeight());
                }
            }
        }
    }

    /**
     * Loads tileMap from file.
     * @param path path of file.
     * @param width width of tileMap in tiles
     * @param height height of tileMap in tiles
     */
    private void loadMap(String path, int width, int height){
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        
        tileMap = new int[width][height];
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                tileMap[x][y] = Utils.parseInt(tokens[(x+y * width)]);
            }
        }
    }


    /**
     * Changes tileSet.
     * @param tileSet new TileSet
     */
    public void setTileSet(TileSet tileSet){
        this.tileSet = tileSet;
    }

    /**
     * Changes tileMap.
     * @param tileMap new TileMap
     */
    public void setTileMap(int[][] tileMap){
        this.tileMap = tileMap;
    }

    /**
     * Returns int map of tiles.
     * @return int map of tiles
     */
    public int[][] getTileMap(){
        return tileMap;
    }

    /**
     * Returns tile at tileMap's [x][y]
     * @param x x coordinate of tileMap
     * @param y y coordinate of tileMap
     */
    public Tile tileAt(int x, int y){
        if(x > 0 && x < tileMap.length && y > 0 && y < tileMap[0].length){
            return tileSet.getTile(tileMap[x][y]);
        }
        return tileSet.getTile(0);
    }

    /**
     * Returns tile at coordinate(in pixels).
     * Returns tile with id of 0 if there is no tile
     * @param x x coordinate in pixels
     * @param y y coordinate in pixels
     */
    public Tile tileAtCoord(int x, int y){
        if(x < 0|| y < 0){
            return tileSet.getTile(0);
        }
        if(x/tileSet.getTileWidth() >= 0 && 
            x/tileSet.getTileWidth() < tileMap.length && 
            y/tileSet.getTileHeight() >= 0 && 
            y/tileSet.getTileHeight() < tileMap[0].length)
            {
            return tileSet.getTile(tileMap[x / tileSet.getTileWidth()]
            [y / tileSet.getTileHeight()]);
        }
        return tileSet.getTile(0);
    }

    /**
     * Changes tile at tileMap[x][y]
     * @param x x of tile being changed
     * @param y y of tile being changed
     * @param id new id of the tile
     */
     public void changeTileAt(int x, int y, int id){
         if(x >= 0 && x < tileMap.length &&
            y >= 0 && y < tileMap[0].length){
                tileMap[x][y] = id;
            }
     }
    /**
     * Changes tile at coordinate x, y.
     * @param x x coordinate of tile
     * @param y y coordinate of tile
     * @param id ne id of the tile
     */
     public void changeTileAtCoord(int x, int y, int id){
        int indexX = x/tileSet.getTileWidth();
        int indexY = (int)(y/tileSet.getTileHeight());
        changeTileAt(indexX, indexY, id);
     }
}