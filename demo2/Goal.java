import java.awt.event.KeyEvent;
import fi.tamk.cgeng.objects.GameObject;
import fi.tamk.cgeng.tiles.TileMap;
import fi.tamk.cgeng.engine.KeyboardListener;
import java.awt.Color;
import java.awt.Graphics;

public class Goal extends GameObject{

    public Goal(int x, int y, int width, int height){
        super(x,y,width,height);
    }

    @Override
    public void paint(Graphics g){
        g.setColor(Color.GREEN);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
    @Override
    public void move(){

    }
}