package fi.tamk.cgeng.tiles;
import fi.tamk.cgeng.util.SpriteSheet;

/**
 * Contains set of Tiles, aswell as information about.
 * every tile in the set. TileMap uses TileSet to 
 * draw these tiles to different positions.
 */
public class TileSet{

    /**
     * Fast way to generate TileSet. Solid values of set
     * has to be set manually.
     * @param tileWidth width of single tile on SpriteSheet
     * @param tileHeight height of single tile on SpriteSheet
     * @param sheetCols ammount of columns on SpriteSheet
     * @param sheetRows ammount of rows on SpriteSheet
     * @param sheet sheet where textures of the TileSet are taken
     * @return TileSet of given SpriteSheet.
     */
    public static TileSet generateTileSet(int tileWidth, int tileHeight, 
        int sheetCols, int sheetRows, SpriteSheet sheet){

            Tile[] tiles = new Tile[1000];
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

    /**
     * Default width of the tile(if nothing else is set)
     */
    public final int DEFAULT_TILEWIDTH = 32;
    /**
     * Default height of the tile(if nothing else is set)
     */
    public final int DEFAULT_TILEHEIGHT = 32;

    /**
     * Array that contains different Tiles, maxium size of
     * TilSet is 1000 Tiles. 
     */
    private Tile[] tiles = new Tile[1000];
    
    /**
     * Width of each tile in the set.
     */
    private int tileWidth;

    /**
     * Height of each tile in the set.
     */
    private int tileHeight;

    /**
     * Constructor that initializes width and height with
     * default values
     */
    public TileSet(){
        setTileWidth(DEFAULT_TILEWIDTH);
        setTileHeight(DEFAULT_TILEHEIGHT);
    }
    /**
     * Constructor that initializes width and height
     * @param tileWidth width of tile in TileSet
     * @param tileHeight height of tile in TileSet
     */
    public TileSet(int tileWidth, int tileHeight){
        setTileWidth(tileWidth);
        setTileHeight(tileHeight);
    }

    /**
     * Constructor that initializes tilemap. And
     * tile height and with with default values.
     * @param tiles Array of tiles that is added to TileSet
     */
    public TileSet(Tile[] tiles){
        this.tiles = tiles;
        setTileWidth(DEFAULT_TILEWIDTH);
        setTileHeight(DEFAULT_TILEHEIGHT);
    }

    /**
     * Constructor that initializes tilemap, tilewidth and
     * tileheight.
     * @param tiles Array of tiles that is added to TileSet
     * @param tileHeight Height of single tile on TileSet
     * @param tileWidth Width of single tile on TileSet
     */
    public TileSet(Tile[] tiles, int tileHeight, int tileWidth){
        this.tiles = tiles;
        setTileHeight(tileHeight);
        setTileWidth(tileWidth);
    }

    /**
     * Add tile to TileSet
     * @param tile tile that is added
     */
    public void addTile(Tile tile){
        tiles[tile.getID()] = tile;
    }

    /**
     * Returns tile from index
     * @return index(id) of tile
     */
    public Tile getTile(int index){
        return tiles[index];
    }

    /**
     * Returns list of tiles on TileSet
     * @return TileSets tilelist.
     */
    public Tile[] getTiles(){
        return tiles;
    }

    /**
     * Set width of each tile on tileset. Width cant be
     * less than 1.
     * @param tileWidth width of tile
     */
    public void setTileWidth(int tileWidth){
        if(tileWidth < 1){
            this.tileWidth = 1;
        }else{
            this.tileWidth = tileWidth;
        }
    }

    /**
     * Set height of each tile on tileset. Height cant be
     * less than 1.
     * @param tileHeight height of tile
     */
    public void setTileHeight(int tileHeight){
        if(tileHeight < 1){
            this.tileHeight = 1;
        }else{
            this.tileHeight = tileHeight;        
        }
    }
    
    /**
     * Returns height of each tile on set
     * @return Height of tile
     */
    public int getTileHeight(){
        return tileHeight;
    }

    /**
     * Returns width of each tile on set
     * @return Width of tile
     */
    public int getTileWidth(){
        return tileWidth;
    }

}