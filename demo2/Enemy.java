import java.awt.event.KeyEvent;
import fi.tamk.cgeng.objects.GameObject;
import fi.tamk.cgeng.tiles.TileMap;
import fi.tamk.cgeng.engine.KeyboardListener;
import java.awt.Color;
import java.awt.Graphics;

public class Enemy extends GameObject{

    private int speed = 4;
    private boolean down = true;

    public Enemy(int x, int y, int width, int height, 
        TileMap map, KeyboardListener listener){
        super(x,y,width,height,map);
        setKeyboardListener(listener);
    }

    @Override
    public void paint(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
    @Override
    public void move(){
        if(down){
            moveY(speed);
        }else{
            moveY(-speed);
        }
        
        if(getY() > 550){
            down = false;
        }else if(getY() < 40){
            down = true;
        }
    }
}