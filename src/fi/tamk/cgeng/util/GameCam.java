package fi.tamk.cgeng.util;

import fi.tamk.cgeng.objects.*;
import fi.tamk.cgeng.tiles.*;
import java.util.Optional;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;

/**
 * Camera that sees given tileMap and objects
 * and paint them relative to cameras position.
 */
public class GameCam{

    /**
     * Optional tileMap of camera.
     */
    private Optional<TileMap> tileMap;
    /**
     * List of objects that camera sees.
     */
    private List<Sprite> objectList = new ArrayList<>();
    /**
     * Cameras position x
     */
    private int x;
    /**
     * Cameras position y
     */
    private int y;
    /**
     * Cameras width
     */
    private int width;
    /**
     * Cameras height
     */
    private int height;
    /**
     * Tells if y axis is locked or not
     */
    private boolean yLock = false;

    /**
     * Tells if x axis is locked or not
     */
    private boolean xLock = false;

    /**
     * Constructor that initializes x, y, width and height
     * attributes of Camera
     * @param x x coordinate of camera
     * @param y y coordinate of camera
     * @param width width of camera
     * @param height height of camera 
     */
    public GameCam(int x, int y, int width, int height){
        setLocation(x, y);
        setSize(width, height);
    }

    /**
     * Constructor that initializes x, y, width and height
     * attributes of Camera, aswell as List of objects, that this
     * camera sees.
     * @param x x coordinate of camera
     * @param y y coordinate of camera
     * @param width width of camera
     * @param height height of camera 
     * @param objectList list of seen objects
     */
    public GameCam(int x, int y, int width, int height, List<Sprite> objectList){
        setLocation(x, y);
        setSize(width, height);
        setObjectList(objectList);
    }

    /**
     * Paints objects and tileMap to screen, relative
     * to cameras locaction.
     * @param g Everything is painted with Graphics class
     */
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

    /**
     * Changes cameras position, so that given
     * Sprite or GameObject is in middle of camera.
     */
    public void centerOnObject(Sprite s){
        setLocation(s.getX() - getWidth()/2 + s.getWidth() / 2,
        s.getY() - getHeight()/2 + s.getHeight() / 2);   
    }

    /**
     * Add new GameObject or Sprite to list of 
     * seen objects
     */
    public void addObject(Sprite s){
        objectList.add(s);
    }

    /**
     * Remove object from objectList
     */
    public void removeObject(Sprite s){
        objectList.remove(s);
    }

    /**
     * Sets objectList
     * @param objectList new objectList
     */
    public void setObjectList(List<Sprite> objectList){
        this.objectList = objectList;
    }

    /**
     * Adds TileMap to camera
     * @param tileMap New tileMap
     */
    public void addTileMap(TileMap tileMap){
        this.tileMap = Optional.of(tileMap);
    }

    /**
     * Removes TileMap from camera
     */
    public void removeTileMap(){
        this.tileMap = Optional.empty();
    }

    /**
     * Returns x coordinate of camera
     * @return x coordinate of camera
     */
    public int getX(){
        return x;
    }

    /**
     * Returns y coordinate of camera
     * @return y coordinate of camera
     */
    public int getY(){
        return y;
    }

    /**
     * Returns width of camera
     * @return width of camera
     */
    public int getWidth(){
        return width;
    }

    /**
     * Returns height of camera
     * @return height of camera
     */
    public int getHeight(){
        return height;
    }

    /**
     * Sets x coordinate of camera
     * @param x x coordinate of camera
     */
    public void setX(int x){
        if(!xLock){
            this.x = x;
        }
    }
    /**
     * Sets y coordinate of camera
     * @param y y coordinate of camera
     */
    public void setY(int y){
        if(!yLock){
            this.y = y;
        }
    }
    /**
     * Sets width of camera
     * @param width width of camera
     */
    public void setWidth(int width){
        this.width = width;
    }
    /**
     * Sets height of camera
     * @param height height of camera
     */
    public void setHeight(int height){
        this.height = height;
    }
    /**
     * Locks or unlocks Y axis
     * @param lock true to lock, false to unlock
     */
    public void setYLock(boolean lock){
        this.yLock = lock;
    }
    /**
     * Locks or unlocks X axis
     * @param lock true to lock, false to unlock
     */
    public void setXLock(boolean lock){
        this.xLock = lock;
    }
    /**
     * Returns if x axis is locked or not
     * @return if x axis is locked or not
     */
    public boolean getXLock(){
        return xLock;
    }
    /**
     * Returns if x axis is locked or not
     * @return if x axis is locked or not
     */
    public boolean getYLock(){
        return yLock;
    }
    /**
     * Sets cameras location to given coordinates.
     * @param x x coordinate of camera
     * @param y y coordinate of camera
     */
    public void setLocation(int x, int y){
        setX(x);
        setY(y);
    }
    /**
     * Sets width and height of camera
     * @param width width of camera
     * @param height height of camera
     */
    public void setSize(int width, int height){
        setWidth(width);
        setHeight(height);
    }
}