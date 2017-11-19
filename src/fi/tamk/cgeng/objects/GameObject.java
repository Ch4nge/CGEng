package fi.tamk.cgeng.objects;

import fi.tamk.cgeng.engine.KeyboardListener;
import fi.tamk.cgeng.engine.MousepadListener;
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

    private KeyboardListener keyboardListener;
    private MousepadListener mousepadListener;

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
    }

    /**
     * Functionality of gameobject(moving)
     */
    public abstract void move();

    /**
     * update method, this is being called in 
     * scenes update.
     */
    public void update(){
        move();
        updateBounds(boundsX, boundsY);            
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

}