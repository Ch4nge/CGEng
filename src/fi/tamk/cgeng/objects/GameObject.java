package fi.tamk.cgeng.objects;

import fi.tamk.cgeng.engine.KeyboardListener;
import fi.tamk.cgeng.engine.MousepadListener;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class GameObject extends Sprite{

    private Rectangle bounds;
    private int boundsX = 0;
    private int boundsY = 0;

    private KeyboardListener keyboardListener;
    private MousepadListener mousepadListener;


    public GameObject(int x, int y, int width, int height, BufferedImage image){
        super(x,y,width,height,image);
        setBounds(0,0,width,height);
    }

    public GameObject(int x, int y, int width, int height){
        super(x,y,width,height);
        setHeight(height);
        setWidth(width);
        setX(x);
        setY(y);
    }

    public abstract void move();

    public void update(){
        move();
        updateBounds(boundsX, boundsY);            
    }

    public void updateBounds(int boundsX, int boundsY){
        bounds.setLocation(getX()+boundsX, getY()+boundsY);
    }


    public void setBounds(int boundsX, int boundsY, int width, int height){
        setBoundsX(boundsX);
        setBoundsY(boundsY);
        bounds = new Rectangle(getX()+boundsX,getY()+boundsY,width, height);        
    }

    public Rectangle getBounds(){
        return bounds;
    }


    public void setMousepadListener(MousepadListener listener){
        mousepadListener = listener;
    }

    public void setKeyboardListener(KeyboardListener listener){
        keyboardListener = listener;
    }

    public KeyboardListener getKeyboardListener(){
       return keyboardListener; 
    }

    public MousepadListener getMousepadListener(){
        return mousepadListener;
    }

    public void setBoundsX(int boundsX){
        this.boundsX = boundsX;
    }

    public void setBoundsY(int boundsY){
        this.boundsY = boundsY;
    }
    public int getBoundsX(){
        return boundsX;
    }

    public int getBoundsY(){
        return boundsY;
    }

    public boolean collideWith(GameObject object){
        return bounds.intersects(object.getBounds());
    }
    public boolean collideWith(Rectangle rect){
        return bounds.intersects(rect);
    }

}