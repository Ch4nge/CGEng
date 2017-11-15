package fi.tamk.cgeng.objects;

import java.util.Optional;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Sprite{


    private int x;
    private int y;
    private int width;
    private int height;

    private Optional<BufferedImage> image;

    public Sprite(int x, int y, int width, int height, BufferedImage image){
        setHeight(height);
        setWidth(width);
        setX(x);
        setY(y);
        setImage(image);
    }

    public Sprite(int x, int y, int width, int height){
        setHeight(height);
        setWidth(width);
        setX(x);
        setY(y);
    }

    public void paint(Graphics g){
        image.ifPresent((image) -> 
            g.drawImage(image, x, y, width, height, null));
    }

    public void setHeight(int height){
        if(height < 0){
            this.height = 0;
        }else{
            this.height = height;
        }
    }

    public int getHeight(){
        return height;
    }

    public void setWidth(int width){
        if(width < 0){
            this.width = 0;
        }else{
            this.width = width;
        }
    }

    public int getWidth(){
        return width;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getX(){
        return x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getY(){
        return y;
    }

    public void setImage(BufferedImage image){
        this.image = Optional.of(image);
    }

    public Optional<BufferedImage> getImage(){
        return image;
    }
}