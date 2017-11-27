package fi.tamk.cgeng.objects;

import fi.tamk.cgeng.tiles.TileMap;
import java.util.ArrayList;
import java.awt.Point;
import java.util.Arrays;

public class CollisionSystem {

    private ArrayList<Point> downCollPoints = new ArrayList<>();
    private ArrayList<Point> upCollPoints = new ArrayList<>();
    private ArrayList<Point> rightCollPoints = new ArrayList<>();
    private ArrayList<Point> leftCollPoints = new ArrayList<>();

    private Point topLeft;
    private Point topRight;
    private Point botLeft;
    private Point botRight;

    private Point topMid;
    private Point rightMid;
    private Point botMid;
    private Point leftMid;

    private GameObject gameObject;


    public CollisionSystem(GameObject gameObject){
        this.gameObject = gameObject;
        createPoints();

        downCollPoints.addAll(Arrays.asList(botLeft, botMid, botRight));
        upCollPoints.addAll(Arrays.asList(topLeft, topMid, topRight));
        leftCollPoints.addAll(Arrays.asList(topLeft, leftMid, botLeft));
        rightCollPoints.addAll(Arrays.asList(topRight, rightMid, botRight));
    }

    private void createPoints(){
        //corners
        topLeft = new Point(getX(), getY());
        topRight = new Point(getX() + getWidth(), getY());
        botLeft = new Point(getX(), getY()+ getHeight());
        botRight = new Point(getX()+ getWidth(), getY()+getHeight());

        //middle
        topMid = new Point(getX() + getWidth()/2, getY());
        rightMid = new Point(getX()+ getWidth(), getY() + getHeight() /2);
        botMid = new Point(getX() + getWidth()/2, getY()+getHeight());
        leftMid = new Point(getX(), getY() + getHeight()/2);
    }

    public void updatePoints(){
        //corners
        topLeft.setLocation(getX(), getY());
        topRight.setLocation(getX() + getWidth(), getY());
        botLeft.setLocation(getX(), getY()+ getHeight());
        botRight.setLocation(getX()+ getWidth(), getY()+getHeight());

        //middle
        topMid.setLocation(getX() + getWidth()/2, getY());
        rightMid.setLocation(getX()+ getWidth(), getY() + getHeight() /2);
        botMid.setLocation(getX() + getWidth()/2, getY()+getHeight());
        leftMid.setLocation(getX(), getY() + getHeight()/2);
    }
    

    private int getX(){
        return (int)gameObject.getBounds().getX();
    }

    private int getY(){
        return (int)gameObject.getBounds().getY();
    }
    
    private int getWidth(){
        return (int)gameObject.getBounds().getWidth();
    }

    private int getHeight(){
        return (int)gameObject.getBounds().getHeight();
    }

    public ArrayList<Point> getDownCollPoints(){
        return downCollPoints;
    }
    public ArrayList<Point> getUpCollPoints(){
        return upCollPoints;
    }
    public ArrayList<Point> getRightCollPoints(){
        return rightCollPoints;
    }
    public ArrayList<Point> getLeftCollPoints(){
        return leftCollPoints;
    }

    public boolean checkCollision(ArrayList<Point> collList, int x, int y){
        TileMap map = gameObject.getTileMap().orElse(null);
        if(map == null){
            throw new NoMapFoundException();
        }
        for(Point p : collList){
            if(map.tileAtCoord((int)p.getX()+x, (int)p.getY()+y).isSolid()){
                return true;
            }
        }
        return false;
    }

    public class NoMapFoundException extends RuntimeException{}
}