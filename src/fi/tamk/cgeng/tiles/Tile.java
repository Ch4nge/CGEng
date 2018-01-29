package fi.tamk.cgeng.tiles;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
/**
 * Tile represents single tile on the tilemap.
 * has texture, id and info about if its solid
 * tile or not(Can you move through the tile).
 */
public class Tile{

    /**
     * Each tile on the tileset has unique id.
     */
    private int id;
    /**
     * Texture of the tile.
     */
    private BufferedImage texture;
    /**
     * Boolean value used to check if you can
     * move through the tile or not.
     */
    private boolean solid = false;

    /**
     * initializes texture and id
     * @param texture texture of the tile.
     * @param id id of the tile.
     */
    public Tile(BufferedImage texture, int id){
        this.texture = texture;
        setID(id);
    }

    /**
     * Paints tile to JPanel
     * @param g Graphics object that is drawn on JPanel
     * @param x x coordinate of tile
     * @param y y coordinate of tile
     * @param width width of tile
     * @param height height of tile
     */
    public void paint(Graphics g, int x, int y, int width, int height){
        g.drawImage(texture, x, y, width, height, null);
    }

    /**
     * returns tiles id
     * @return tiles id
     */
    public int getID(){
        return id;
    }
    /**
     * sets ID of the tile.
     * @param id new id of the tile
     */
    public void setID(int id){
        if(id < 0 || id >255){
            id = 0;
        }else{
            this.id = id;
        }
    }

    /**
     * returns texture of the tile.
     * @return texture of the tile
     */
    public BufferedImage getTexture(){
        return texture;
    }

    /**
     * returns if tile is solid or not
     * @return solid value of the tile
     */
    public boolean isSolid(){
        return solid;
    }

    /**
     * Sets tiles solid value
     * @param solid new solid value
     */
    public void setSolid(boolean solid){
        this.solid = solid;
    }
}