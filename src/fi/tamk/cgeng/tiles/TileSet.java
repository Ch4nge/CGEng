package fi.tamk.cgeng.tiles;
import fi.tamk.cgeng.util.SpriteSheet;

public class TileSet{

    public static TileSet generateTileSet(int tileWidth, int tileHeight, 
        int sheetCols, int sheetRows, SpriteSheet sheet){

            Tile[] tiles = new Tile[256];
            int cropPointX = 0;
            int cropPointY = 0;
            int id = 0;

            for(int i = 0; i < sheetRows; i++){
                for(int j = 0; j < sheetCols; j++){
                    cropPointX = j * tileWidth;
                    cropPointY = i * tileHeight;
                    tiles[id] = new Tile(sheet.crop(cropPointX, 
                        cropPointY, tileWidth, tileHeight), id);
                    id++;
                }
            }
            TileSet tmpSet = new TileSet(tiles);
            tmpSet.setTileHeight(tileHeight);
            tmpSet.setTileWidth(tileWidth);
            return tmpSet;
    }


    public final int DEFAULT_TILEWIDTH = 32;
    public final int DEFAULT_TILEHEIGHT = 32;

    private Tile[] tiles = new Tile[256];
    private int tileWidth;
    private int tileHeight;

    public TileSet(){
        setTileWidth(DEFAULT_TILEWIDTH);
        setTileHeight(DEFAULT_TILEHEIGHT);
    }
    
    public TileSet(int tileWidth, int tileHeight){
        setTileWidth(tileWidth);
        setTileHeight(tileHeight);
    }

    public TileSet(Tile[] tiles){
        this.tiles = tiles;
        setTileWidth(DEFAULT_TILEWIDTH);
        setTileHeight(DEFAULT_TILEHEIGHT);
    }

    public TileSet(Tile[] tiles, int tileHeight, int tileWidth){
        this.tiles = tiles;
        setTileHeight(tileHeight);
        setTileWidth(tileWidth);
    }

    public void addTile(Tile tile){
        tiles[tile.getID()] = tile;
    }

    public Tile getTile(int index){
        return tiles[index];
    }

    public Tile[] getTiles(){
        return tiles;
    }

    public void setTileWidth(int tileWidth){
        if(tileWidth < 1){
            this.tileWidth = 1;
        }else{
            this.tileWidth = tileWidth;
        }
    }

    public void setTileHeight(int tileHeight){
        if(tileHeight < 1){
            this.tileHeight = 1;
        }else{
            this.tileHeight = tileHeight;        
        }
    }
    
    public int getTileHeight(){
        return tileHeight;
    }

    public int getTileWidth(){
        return tileWidth;
    }

}