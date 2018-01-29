import java.awt.event.KeyEvent;
import fi.tamk.cgeng.objects.GameObject;
import fi.tamk.cgeng.tiles.TileMap;
import fi.tamk.cgeng.engine.KeyboardListener;
import java.awt.Color;
import java.awt.Graphics;

public class MyPlayer extends GameObject{


    public MyPlayer(int x, int y, int width, int height, 
        TileMap map, KeyboardListener listener){
        super(x,y,width,height,map);
        setKeyboardListener(listener);
    }

    @Override
    public void paint(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
    @Override
    public void move(){
        if(getKeyboardListener().isKeyPressed(KeyEvent.VK_D)){
            moveX(4);
        }
        if(getKeyboardListener().isKeyPressed(KeyEvent.VK_A)){
            moveX(-4);
        }
        if(getKeyboardListener().isKeyPressed(KeyEvent.VK_S)){
            moveY(4);
        }
        if(getKeyboardListener().isKeyPressed(KeyEvent.VK_W)){
            moveY(-4);
        }
    }
}