package fi.tamk.cgeng.tiles;

import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Tile{

    private int id;
    private BufferedImage texture;
    private boolean solid = false;

    public Tile(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;
    }

    public void paint(Graphics g, int x, int y, int width, int height){
        g.drawImage(texture, x, y, width, height, null);
    }

    public int getID(){
        return id;
    }
    public void setID(int id){
        if(id < 0 || id >255){
            id = 0;
        }else{
            this.id = id;
        }
    }

    public BufferedImage getTexture(){
        return texture;
    }

    public boolean isSolid(){
        return solid;
    }

    public void setSolid(boolean solid){
        this.solid = solid;
    }
}