package fi.tamk.cgeng.engine;

import fi.tamk.cgeng.scenes.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;

public class TestScene extends Scene{

    int x = 50;
    int y = 50;

    public TestScene(SceneManager manager){
        super(manager);
    }

    public void onCreate(){
        
    }

    public void update(){
        if(accessKeyboardListener().isKeyPressed(KeyEvent.VK_D))
            x += 2;
        if(accessKeyboardListener().isKeyPressed(KeyEvent.VK_A))
            x -= 2;
        if(accessKeyboardListener().isKeyPressed(KeyEvent.VK_W))
            y -= 2;
        if(accessKeyboardListener().isKeyPressed(KeyEvent.VK_S))
            y += 2;

        if(accessMousepadListener().isMousePressed())
            System.out.println("Mouse pressed");
    }

    public void paint(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(x,y,50,50);
    }

}