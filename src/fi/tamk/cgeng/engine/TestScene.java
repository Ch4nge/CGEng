package fi.tamk.cgeng.engine;

import fi.tamk.cgeng.scenes.*;
import fi.tamk.cgeng.util.*;
import fi.tamk.cgeng.objects.*;
import fi.tamk.cgeng.tiles.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;

public class TestScene extends Scene{

    private TileSet tileSet;
    private TileMap tileMap;
    private TestPlayer testPlayer;
    private TestPlayer testPlayer2;
    private GameCam cam;
    private GameText teksti;


    public TestScene(SceneManager manager){
        super(manager);
        tileSet = TileSet.generateTileSet(32,32,5,2, 
            new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png")));
        tileSet.getTile(2).setSolid(true);
        tileMap = new TileMap("textures/level.txt",40,30, tileSet);
        testPlayer = new TestPlayer(0,0,50,80,ImageLoader.loadImage("/textures/mario.png"), tileMap, accessKeyboardListener());

        testPlayer2 = new TestPlayer(100,100,50,80,ImageLoader.loadImage("/textures/mario.png"), tileMap, accessKeyboardListener());
        cam = new GameCam(0,0,800,600);
        cam.addObject(testPlayer);
        cam.addObject(testPlayer2);
        cam.addTileMap(tileMap);
        cam.centerOnObject(testPlayer);
        teksti = new GameText("LOL!",50,50);
    }

    public void onCreate(){
        
    }

    public void update(){
        testPlayer.update();
        cam.centerOnObject(testPlayer);
    }

    public void paint(Graphics g){
        g.setColor(Color.RED);
        cam.paint(g);
        teksti.paint(g);
    }

    public void dispose(){
        
    }

}