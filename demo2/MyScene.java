import fi.tamk.cgeng.scenes.Scene;
import fi.tamk.cgeng.scenes.SceneManager;
import fi.tamk.cgeng.util.ImageLoader;
import fi.tamk.cgeng.util.SpriteSheet;
import fi.tamk.cgeng.util.Utils;
import fi.tamk.cgeng.util.GameText;
import fi.tamk.cgeng.tiles.TileSet;
import fi.tamk.cgeng.tiles.TileMap;
import fi.tamk.cgeng.tiles.Tile;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class MyScene extends Scene{

    private TileSet tileSet;
    private TileMap tileMap;
    private MyPlayer player;

    private ArrayList<Enemy> enemies = new ArrayList<>();
    private Goal goal = new Goal(500,170,100,300);
    private GameText text = new GameText("Deaths: 0", 30, 30);

    private int deaths = 0;

    public MyScene(SceneManager manager){
        super(manager);
        //Init tilemap
        tileSet = TileSet.generateTileSet(64,64,2,1, 
            new SpriteSheet(ImageLoader.loadImage("/textures/tileset.png")));
        tileSet.getTile(0).setSolid(true);
        tileSet.setScale(0.5f);
        tileMap = new TileMap("textures/level.txt",20,20, tileSet);
        //Init player
        player = new MyPlayer(50,170,32,32,tileMap,accessKeyboardListener());
        //Add enemies
        enemies.add(new Enemy(165,40,32,32,tileMap,accessKeyboardListener()));
        enemies.add(new Enemy(210,550,32,32,tileMap,accessKeyboardListener()));
        enemies.add(new Enemy(255,40,32,32,tileMap,accessKeyboardListener()));
        enemies.add(new Enemy(300,550,32,32,tileMap,accessKeyboardListener()));
        enemies.add(new Enemy(345,40,32,32,tileMap,accessKeyboardListener()));
        enemies.add(new Enemy(390,550,32,32,tileMap,accessKeyboardListener()));
        enemies.add(new Enemy(435,40,32,32,tileMap,accessKeyboardListener()));
    }

    @Override
    public void onCreate(){

    }

    @Override
    public void paint(Graphics g){
        tileMap.paint(g);
        goal.paint(g);
        player.paint(g);
        for(Enemy e : enemies){
            e.paint(g);
        }
        text.paint(g);
    }

    @Override
    public void update(){
        player.update();
        for(Enemy e : enemies){
            e.update();
            if(player.collideWith(e)){
                player.setX(50);
                player.setY(170);
                deaths++;
                text.setText("Deaths: "+deaths);
            }
        }   
        if(player.collideWith(goal)){
            getSceneManager().setScene(new MyScene2(getSceneManager()));
        }
    }

    @Override
    public void dispose(){

    }
}