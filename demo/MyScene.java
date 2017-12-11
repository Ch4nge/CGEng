import fi.tamk.cgeng.scenes.Scene;
import fi.tamk.cgeng.scenes.SceneManager;
import fi.tamk.cgeng.util.ImageLoader;
import fi.tamk.cgeng.util.SpriteSheet;
import fi.tamk.cgeng.util.Audio;
import fi.tamk.cgeng.util.Utils;
import fi.tamk.cgeng.util.GameCam;
import fi.tamk.cgeng.util.GameText;
import fi.tamk.cgeng.tiles.TileSet;
import fi.tamk.cgeng.tiles.TileMap;
import fi.tamk.cgeng.tiles.Tile;
import fi.tamk.cgeng.objects.Sprite;
import java.awt.Graphics;
import java.awt.event.KeyEvent;


public class MyScene extends Scene{

    private TileSet tileSet;
    private TileMap tileMap;
    private GameCam cam;
    private Audio music;
    private MyPlayer player;
    private Sprite ui;
    private GameText text;
    private int tileID;
    private boolean tileChanged = false;
    private boolean writing = false;

    public MyScene(SceneManager manager){
        super(manager);
        //Init tilemap
        tileSet = TileSet.generateTileSet(64,64,3,1, 
            new SpriteSheet(ImageLoader.loadImage("/textures/tileset.png")));
        tileSet.getTile(1).setSolid(true);
        tileSet.getTile(0).setSolid(true);
        tileMap = new TileMap("textures/level.txt",13,20, tileSet);
        //Init player
        player = new MyPlayer(5,5,64,79,tileMap,accessKeyboardListener());
        tileID = 0;

        ui = new Sprite(675,25,100,100,ImageLoader.loadImage("/textures/ui_upright.png"));

        cam = new GameCam(0,0,800,600);
        cam.setXLock(true);
        cam.addTileMap(tileMap);
        cam.addObject(player);

        text = new GameText("Mario game", 50, 50);

        music = new Audio("sounds/Pim Poy Pocket.wav");
        music.loop();
    }

    @Override
    public void onCreate(){

    }

    @Override
    public void paint(Graphics g){
        cam.paint(g);
        ui.paint(g);
        text.paint(g);
        tileSet.getTile(tileID).paint(g,700,50,50,50);
    }

    @Override
    public void update(){
        if(accessKeyboardListener().isKeyReleased(KeyEvent.VK_E)){
                tileChanged = false;
        }
        if(accessKeyboardListener().isKeyReleased(KeyEvent.VK_R)){
                writing = false;
        }
        if(player.getY()+player.getHeight()/2 < tileMap.getTileMap()[0].length * 64-300 &&
           player.getY()+player.getHeight()/2 > 300){
            cam.centerOnObject(player);
        }
        player.update();    
        if(accessMousepadListener().isMousePressed()){
            tileMap.changeTileAtCoord(
                accessMousepadListener().getX()+cam.getX(),
                accessMousepadListener().getY()+cam.getY()-32,
                tileID
            ); 
        }
        if(accessKeyboardListener().isKeyPressed(KeyEvent.VK_E)){
            if(tileID < 2 && !tileChanged){
                tileID++;
                tileChanged = true;
            }else if(!tileChanged){
                tileID = 0;
                tileChanged = true;
            }
        }
        if(accessKeyboardListener().isKeyPressed(KeyEvent.VK_R)){
            if(!writing){
                Utils.tilemapToFile("testLevel.txt", tileMap.getTileMap());
            }
            writing = true;
        }
    }

    @Override
    public void dispose(){
        music.dispose();
    }
}