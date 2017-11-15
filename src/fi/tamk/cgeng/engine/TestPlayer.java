package fi.tamk.cgeng.engine;

import java.awt.event.KeyEvent;
import fi.tamk.cgeng.objects.*;
import fi.tamk.cgeng.tiles.*;
import java.awt.image.BufferedImage;


public class TestPlayer extends GameObject{

    private TileMap tileMap;

    public TestPlayer(int x, int y, int width, int height, BufferedImage image,KeyboardListener listener){
        super(x,y,width,height,image);
        setKeyboardListener(listener);
    }

    public void setTileMap(TileMap tileMap){
        this.tileMap =tileMap;
    }

    public void move(){
        if(getKeyboardListener().isKeyPressed(KeyEvent.VK_D))
            setX(getX()+3);
        if(getKeyboardListener().isKeyPressed(KeyEvent.VK_A))
            setX(getX()-3);
        if(getKeyboardListener().isKeyPressed(KeyEvent.VK_W))
            setY(getY()-3);
        if(getKeyboardListener().isKeyPressed(KeyEvent.VK_S) &&
            !tileMap.tileAtCoord(getX(),getY()+3).isSolid())
            setY(getY()+3);
    }
}