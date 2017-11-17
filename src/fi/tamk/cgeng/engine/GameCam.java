package fi.tamk.cgeng.engine;

import fi.tamk.cgeng.objects.*;
import fi.tamk.cgeng.tiles.*;
import java.awt.Rectangle;
import java.util.Optional;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;

public class GameCam extends Rectangle{

    private Optional<TileMap> tileMap;
    private List<Sprite> objectList = new ArrayList<>();

    public GameCam(int x, int y, int width, int height){
        setLocation(x, y);
        setSize(width, height);
    }

    public GameCam(int x, int y, int width, int height, List<Sprite> objectList){
        setLocation(x, y);
        setSize(width, height);
        setObjectList(objectList);
    }

    public void paint(Graphics g){
        tileMap.ifPresent((map) -> map.paint(g,this));
        for(Sprite s : objectList){
            if(s.getX()+s.getWidth() > x &&
               s.getX() < x + width &&
               s.getY()+s.getHeight() > y &&
               s.getY() < y + height){

                   //Draw only objects that is in screen
                   s.paint(g, (int)getX(), (int)getY());
               }
                
        }
    }

    public void centerOnObject(Sprite s){
        setLocation(s.getX() - (int)getWidth()/2 + (int)s.getWidth() / 2,
        s.getY() - (int)getHeight()/2 + (int)s.getHeight() / 2);   
    }

    public void addObject(Sprite s){
        objectList.add(s);
    }

    public void setObjectList(List<Sprite> objectList){
        this.objectList = objectList;
    }

    public void addTileMap(TileMap tileMap){
        this.tileMap = Optional.of(tileMap);
    }
    
}