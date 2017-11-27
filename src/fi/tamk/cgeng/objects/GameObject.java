package fi.tamk.cgeng.objects;

import fi.tamk.cgeng.engine.KeyboardListener;
import fi.tamk.cgeng.engine.MousepadListener;
import fi.tamk.cgeng.tiles.TileMap;
import java.util.Optional;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


/**
 * GameObject is object that has additional
 * useful features compared to Sprite. Easy
 * access to different listeneres, possibility
 * to check collisions between GameObjects and
 * rectangles usage: Override move method, that
 * is being called by default in update
 */
public abstract class GameObject extends Sprite{

    private Rectangle bounds;
    private int boundsX = 0;
    private int boundsY = 0;

    private Optional<TileMap> map;
    private Optional<CollisionSystem> collSys;

    private KeyboardListener keyboardListener;
    private MousepadListener mousepadListener;

    /**
     * Constructor that initializes x, y, width, height and texture
     * aswell as TileMap and automatic collision detection between object
     * and TileMap. 
     * @param x x coordinate of GameObject
     * @param y y coordinate of GameObject
     * @param width width of GameObject
     * @param height height of GameObject
     * @param image Texture of GameObject
     * @param map tileMap we are checking collisions with
     */
    public GameObject(int x, int y, int width, int height, BufferedImage image, TileMap map){
        super(x,y,width,height,image);
        setBounds(0,0,width,height);
        addCollisionSystem(map);
    }
    
    /**
     * Constructor that initializes x, y, width, height and texture
     * of GameObject. 
     * @param x x coordinate of GameObject
     * @param y y coordinate of GameObject
     * @param width width of GameObject
     * @param height height of GameObject
     * @param image Texture of GameObject
     */
    public GameObject(int x, int y, int width, int height, BufferedImage image){
        super(x,y,width,height,image);
        setBounds(0,0,width,height);
        map = Optional.empty();
        collSys = Optional.empty();
    }

    /**
     * Constructor that initializes x, y, width and height
     * of GameObject
     * @param x x coordinate of GameObject
     * @param y y coordinate of GameObject
     * @param width width of GameObject
     * @param height height of GameObject
     */
    public GameObject(int x, int y, int width, int height){
        super(x,y,width,height);
        setHeight(height);
        setWidth(width);
        setX(x);
        setY(y);
        map = Optional.empty();
        collSys = Optional.empty();
    }

    /**
     * Functionality of gameobject(moving)
     */
    public abstract void move();


    /**
     * Adds automatic collision system between tileMap
     * and object
     */
    public void addCollisionSystem(TileMap map){
        collSys = Optional.of(new CollisionSystem(this));
        this.map = Optional.of(map);
    }

    /**
     * update method, this is being called in 
     * scenes update.
     */
    public void update(){
        move();
        updateBounds(boundsX, boundsY);
        collSys.ifPresent( coll -> coll.updatePoints());
    }

    /**
     * Updates position of colliding bounds.
     * @param boundsX x coordinate of bounds
     * @param boundsY y coordinate of bounds
     */
    public void updateBounds(int boundsX, int boundsY){
        bounds.setLocation(getX()+boundsX, getY()+boundsY);
    }

    /**
     * Set bounds to GameObject
     * @param boundsX x coordinate of bounds
     * @param boundsY y coordinate of bounds
     * @param width width of bounds
     * @param height height of bounds
     */
    public void setBounds(int boundsX, int boundsY, int width, int height){
        setBoundsX(boundsX);
        setBoundsY(boundsY);
        bounds = new Rectangle(getX()+boundsX,getY()+boundsY,width, height);        
    }

    /**
     * Returns bounds of GameObject
     * @return bounds
     */
    public Rectangle getBounds(){
        return bounds;
    }

    /**
     * Set MousePadListener to this GameObject
     * @param listener new MousepadListener
     */
    public void setMousepadListener(MousepadListener listener){
        mousepadListener = listener;
    }

    /**
     * Set KeyboardListener to this GameObject
     * @param listener new KeyboardListener
     */
    public void setKeyboardListener(KeyboardListener listener){
        keyboardListener = listener;
    }

    /**
     * Returns KeyboardListener
     * @return KeyboardListener
     */
    public KeyboardListener getKeyboardListener(){
       return keyboardListener; 
    }

    /**
     * Returns MousepadListener
     * @return MousepadListener
     */
    public MousepadListener getMousepadListener(){
        return mousepadListener;
    }

    /**
     * Set x coordinate of bounds
     * @param boundsX x coordinate of bounds
     */
    public void setBoundsX(int boundsX){
        this.boundsX = boundsX;
    }

    /**
     * Set y coordinate of bounds
     * @param boundsY y coordinate of bounds
     */
    public void setBoundsY(int boundsY){
        this.boundsY = boundsY;
    }
    /**
     * Return x coordinate of bounds
     * @return x coordinate of bounds
     */
    public int getBoundsX(){
        return boundsX;
    }

    /**
     * Return y coordinate of bounds
     * @return y coordinate of bounds
     */
    public int getBoundsY(){
        return boundsY;
    }

    /**
     * Checks if this GameObject collides with other
     * GameObject
     * @param object GameObject we are checking
     * @return true if collides with given GameObject
     */
    public boolean collideWith(GameObject object){
        return bounds.intersects(object.getBounds());
    }
    
    /**
     * Checks if this GameObject collides with rectangle
     * @param rect Rectangle we are checking
     * @return true if collides with given Rectangle
     */
    public boolean collideWith(Rectangle rect){
        return bounds.intersects(rect);
    }

    /**
     * Returns Optional TileMap this object is checking collisions
     * with.
     * @return TileMap of the GameObject
     */
    public Optional<TileMap> getTileMap(){
        return map;
    }

    /**
     * Used to move player in x -axis, checks for 
     * collisions if CollisionSystem is present, use negative number
     * to move left, positive number to right
     * @param speed how much we are moving on x-axis
     */
    public void moveX(int speed){
        if(collSys.isPresent()){
            collSys.ifPresent( colls -> {
                if(!colls.checkCollision(colls.getLeftCollPoints(),speed,0) &&
                   !colls.checkCollision(colls.getRightCollPoints(),speed,0)){
                    setX(getX()+speed);
                }
            });
        }else{
            setX(getX() + speed);
        }
    }

    /**
     * Used to move player in y -axis, checks for 
     * collisions if CollisionSystem is present, use negative number
     * to move up, positive number to down
     * @param speed how much we are moving on y-axis
     */
    public void moveY(int speed){
        if(collSys.isPresent()){
            collSys.ifPresent( colls -> {
                if(!colls.checkCollision(colls.getDownCollPoints(), 0, speed) &&
                   !colls.checkCollision(colls.getUpCollPoints(),0, speed)){
                    setY(getY()+speed);
                }
            });
        }else{
            setY(getY() + speed);
        }
    }
}