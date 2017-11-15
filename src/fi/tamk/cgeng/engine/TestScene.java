package fi.tamk.cgeng.engine;

import fi.tamk.cgeng.scenes.*;
import fi.tamk.cgeng.util.*;
import fi.tamk.cgeng.objects.*;
import fi.tamk.cgeng.tiles.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;

public class TestScene extends Scene{

    int x = 50;
    int y = 50;
    private TileSet tileSet;
    private TileMap tileMap;
    private TestPlayer testPlayer;
    private TestPlayer testPlayer2;

    public TestScene(SceneManager manager){
        super(manager);
        tileSet = TileSet.generateTileSet(32,32,5,2, 
            new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png")));
        tileSet.getTile(2).setSolid(true);
        tileMap = new TileMap("textures/level.txt",40,30, tileSet);
        testPlayer = new TestPlayer(0,0,50,80,ImageLoader.loadImage("/textures/mario.png"),accessKeyboardListener());
        testPlayer.setTileMap(tileMap);

        testPlayer2 = new TestPlayer(100,100,50,80,ImageLoader.loadImage("/textures/mario.png"),accessKeyboardListener());
    }

    public void onCreate(){
        
    }

    public void update(){
        testPlayer.update();

        if(accessMousepadListener().isMousePressed())
            System.out.println("Mouse pressed");
        if(testPlayer.collideWith(testPlayer2.getBounds()))
            System.out.println("COLLIDING");
    }

    public void paint(Graphics g){
        g.setColor(Color.RED);
        tileMap.paint(g);
        tileSet.getTile(0).paint(g,500,500,32,32);
        testPlayer.paint(g);
        testPlayer2.paint(g);
    }

}