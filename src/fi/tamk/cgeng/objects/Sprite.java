package fi.tamk.cgeng.objects;

import java.util.Optional;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Sprite is picture or shape that is 
 * drawn on the screen
 */
public class Sprite{

    /**
     * x coordinate of Sprite
     */
    private int x;
    /**
     * y coordinate of Sprite
     */
    private int y;
    /**
     * width of sprite
     */
    private int width;
    /**
     * height of sprite
     */
    private int height;

    /**
     * Optional texture of sprite, all Sprites
     * dosen't have picture.
     */
    private Optional<BufferedImage> image;

    /**
     * Constructor that initializes all attributes of this
     * class.
     * @param x x coordinate of sprite
     * @param y y coordinate of sprite
     * @param width width of sprite
     * @param height height of sprite
     * @param image texture of sprite
     */
    public Sprite(int x, int y, int width, int height, BufferedImage image){
        setHeight(height);
        setWidth(width);
        setX(x);
        setY(y);
        setImage(image);
    }

    /**
     * Constructor that initializes x, y, width and height
     * of the sprite.
     * @param x x coordinate of sprite
     * @param y y coordinate of sprite
     * @param width width of sprite
     * @param height height of sprite
     */
    public Sprite(int x, int y, int width, int height){
        setHeight(height);
        setWidth(width);
        setX(x);
        setY(y);
        image = Optional.empty();
    }

    /**
     * Draws Sprite to screen, draw image if it is present.
     * @param g painted with Graphics class
     */
    public void paint(Graphics g){
        image.ifPresent((image) -> 
            g.drawImage(image, x, y, width, height, null));
    }

    /**
     * Draws Sprite to screen, draw image if it is present.
     * you can also change position of sprite by using
     * xOffset and yOffset parameters, used by default in
     * GameCam class. 
     * @param g painted with Graphics class
     * @param xOffset how much object is moved to left
     * @param xOffset how much object is moved to up
     */
    public void paint(Graphics g, int xOffset, int yOffset){
        image.ifPresent((image) -> 
            g.drawImage(image, x-xOffset, y-yOffset, width, height, null));
    }

    /**
     * Sets height of the Sprite, cant be less than 0
     * @param height height of the sprite
     */
    public void setHeight(int height){
        if(height < 0){
            this.height = 0;
        }else{
            this.height = height;
        }
    }

    /**
     * Returns height of the spirte
     * @return height of the sprite
     */
    public int getHeight(){
        return height;
    }

    /**
     * Sets width of the Sprite, cant be less than 0
     * @param width width of the sprite
     */
    public void setWidth(int width){
        if(width < 0){
            this.width = 0;
        }else{
            this.width = width;
        }
    }

    /**
     * Returns height of the spirte
     * @return height of the sprite
     */
    public int getWidth(){
        return width;
    }

    /**
     * Sets x coordinate of the sprite
     * @param x x coordinate of the sprite
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * Returns x coordinate of the spirte
     * @return x coordinate of the sprite
     */
    public int getX(){
        return x;
    }

    /**
     * Sets y coordinate of the sprite
     * @param y y coordinate of the sprite
     */
    public void setY(int y){
        this.y = y;
    }

    /**
     * Returns y coordinate of the spirte
     * @return y coordinate of the sprite
     */
    public int getY(){
        return y;
    }

    /**
     * Sets Image of the sprite
     * @param image image of the sprite
     */
    public void setImage(BufferedImage image){
        this.image = Optional.of(image);
    }

    /**
     * Returns image of the sprite
     * @return image of the sprite
     */
    public Optional<BufferedImage> getImage(){
        return image;
    }
}