package fi.tamk.cgeng.engine;

import fi.tamk.cgeng.objects.*;
import fi.tamk.cgeng.tiles.*;
import java.util.Optional;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;

public class GameCam{

    private Optional<TileMap> tileMap;
    private List<Sprite> objectList = new ArrayList<>();
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean yLock = false;
    private boolean xLock = false;

    public GameCam(int x, int y, int width, int height){
        setLocation(x, y);
        setSize(width, height);
    }

    public GameCam(int x, int y, int width, int height, List<Sprite> objectList){
        setLocation(x, y);
        setSize(width, height);
        setObjectList(objectList);
    }

    public void paint(Graphics g){
        tileMap.ifPresent((map) -> map.paint(g,this));
        for(Sprite s : objectList){
            if(s.getX()+s.getWidth() > x &&
               s.getX() < x + width &&
               s.getY()+s.getHeight() > y &&
               s.getY() < y + height){

                   //Draw only objects that is in screen
                   s.paint(g, (int)getX(), (int)getY());
               }
                
        }
    }

    public void centerOnObject(Sprite s){
        setLocation(s.getX() - getWidth()/2 + s.getWidth() / 2,
        s.getY() - getHeight()/2 + s.getHeight() / 2);   
    }

    public void addObject(Sprite s){
        objectList.add(s);
    }

    public void removeObject(Sprite s){
        objectList.remove(s);
    }

    public void setObjectList(List<Sprite> objectList){
        this.objectList = objectList;
    }

    public void addTileMap(TileMap tileMap){
        this.tileMap = Optional.of(tileMap);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public void setX(int x){
        if(!xLock){
            this.x = x;
        }
    }
    public void setY(int y){
        if(!yLock){
            this.y = y;
        }
    }
    public void setWidth(int width){
        this.width = width;
    }
    public void setHeight(int height){
        this.height = height;
    }
    public void setYLock(boolean lock){
        this.yLock = lock;
    }
    public void setXLock(boolean lock){
        this.xLock = lock;
    }
    public boolean getXLock(){
        return xLock;
    }
    public boolean getYLock(){
        return yLock;
    }
    public void setLocation(int x, int y){
        setX(x);
        setY(y);
    }
    public void setSize(int width, int height){
        setWidth(width);
        setHeight(height);
    }
}