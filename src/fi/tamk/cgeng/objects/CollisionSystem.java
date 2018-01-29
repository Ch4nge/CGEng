package fi.tamk.cgeng.objects;

import fi.tamk.cgeng.tiles.TileMap;
import java.util.ArrayList;
import java.awt.Point;
import java.util.Arrays;
/**
 * Automatic collision system for GameObjects,
 * when set to gameObject, it automatically detects
 * collision between GameObject and tileMap and prevents
 * GameObject from moving to solid tiles
 */
public class CollisionSystem {

    /**
     * Points to check collision at bottom of GameObjects
     * bounds
     */
    private ArrayList<Point> downCollPoints = new ArrayList<>();
    /**
     * Points to check collision at top of GameObjects
     * bounds
     */
    private ArrayList<Point> upCollPoints = new ArrayList<>();
    /**
     * Points to check collision at right of GameObjects
     * bounds
     */    
    private ArrayList<Point> rightCollPoints = new ArrayList<>();
    /**
     * Points to check collision at left of GameObjects
     * bounds
     */
    private ArrayList<Point> leftCollPoints = new ArrayList<>();

    /**
     * Collision point of top left corner.
     */
    private Point topLeft;
    /**
     * Collision point of top right corner.
     */
    private Point topRight;
    /**
     * Collision point of bottom left corner.
     */
    private Point botLeft;
    /**
     * Collision point of bottom right corner.
     */
    private Point botRight;

    /**
     * Collision point of top middle.
     */
    private Point topMid;
    /**
     * Collision point of right middle.
     */
    private Point rightMid;
    /**
     * Collision point of bottom middle.
     */
    private Point botMid;
    /**
     * Collision point of left middle.
     */
    private Point leftMid;

    /**
     * This CollisionSystem is used by this gameObject.
     */
    private GameObject gameObject;

    /**
     * Constructor that initializes gameObject, creates
     * collision points, and put them on right CollisionPoints
     * lists.
     * @param gameObject gameObject where this system is used
     */
    public CollisionSystem(GameObject gameObject){
        this.gameObject = gameObject;
        createPoints();

        downCollPoints.addAll(Arrays.asList(botLeft, botMid, botRight));
        upCollPoints.addAll(Arrays.asList(topLeft, topMid, topRight));
        leftCollPoints.addAll(Arrays.asList(topLeft, leftMid, botLeft));
        rightCollPoints.addAll(Arrays.asList(topRight, rightMid, botRight));
    }

    /**
     * Creates collision points
     */
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

    /**
     * updates position of all collision points.
     */
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
    
    /**
     * Returns coordinate x of gameObjects bounds
     * @return coordinate x of gameObjects bounds
     */
    private int getX(){
        return (int)gameObject.getBounds().getX();
    }
    /**
     * Returns coordinate y of gameObjects bounds
     * @return coordinate y of gameObjects bounds
     */
    private int getY(){
        return (int)gameObject.getBounds().getY();
    }
    /**
     * Returns width of gameObjects bounds
     * @return width of gameObjects bounds
     */
    private int getWidth(){
        return (int)gameObject.getBounds().getWidth();
    }
    /**
     * Returns height of gameObjects bounds
     * @return height of gameObjects bounds
     */
    private int getHeight(){
        return (int)gameObject.getBounds().getHeight();
    }
    /**
     * Returns list of downCollPoints
     * @return downCollPoints
     */
    public ArrayList<Point> getDownCollPoints(){
        return downCollPoints;
    }
    /**
     * Returns list of upCollPoints
     * @return upCollPoints
     */
    public ArrayList<Point> getUpCollPoints(){
        return upCollPoints;
    }
    /**
     * Returns list of rightCollPoints
     * @return rightCollPoints
     */
    public ArrayList<Point> getRightCollPoints(){
        return rightCollPoints;
    }
    /**
     * Returns list of downCollPoints
     * @return leftCollPoints
     */
    public ArrayList<Point> getLeftCollPoints(){
        return leftCollPoints;
    }

    /**
     * Checks if given list of CollidePoints collides with 
     * gameObject's tileMap contents. parameters x and y is to
     * check collision in coordinate relative to Points coordinates.
     * @param collList List of collision Points
     * @param x coordinate x relative to Points coordinate x
     * @param y coordinate y relative to Points coordinate y
     * @return true if colliding
     */
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

    /**
     * RuntimeException that is thrown if for some
     * reason CollisionSystem can't find gameObjects
     * TileMap
     */
    public class NoMapFoundException extends RuntimeException{}
}