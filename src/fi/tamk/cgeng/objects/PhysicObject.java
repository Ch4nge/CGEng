package fi.tamk.cgeng.objects;

import java.awt.image.BufferedImage;
import fi.tamk.cgeng.tiles.TileMap;

/**
 * GameObject, that has some physics added. Easy way
 * to create player in 2d platformers.
 */
public abstract class PhysicObject extends GameObject{

    /**
     * How fast object drops to ground
     */
    private float gravity = 1;

    /**
     * Weight of object, heavier the object is,
     * faster it drops
     */
    private float weight = 10;
    /**
     * Higher the force is, higher the object jumps.
     */
    private float force = 0;

    /**
     * Used to calculate fade time of force.
     */
    private float allForce = 0;

    /**
     * How long it takes for force to fade.
     */
    private double forceTime = 1;

    /**
     * Last time update happened.
     */
    private double lastTime = System.nanoTime() / 1000000000.0;

    /**
     * Time current update is happening.
     */
    private double firstTime = 0;

    /**
     * Calculates time between firstTime and lastTime.
     */
    private double delta = 0.0;

    /**
     * used to calculate when its time to lower force.
     */
    private double unprocessedTime = 0;

    /**
     * Constructor that initializes coordinates, size and
     * texture of the PhysicObject, default gravitySettings
     * are used.
     * @param x x coordinate of the object
     * @param y y coordinate of the object
     * @param width width of the object
     * @param height height of the object
     * @param image texture of the object
     */
    public PhysicObject(int x, int y, int width, 
        int height, BufferedImage image){
        super(x,y,width,height,image);
    }
    
    /**
     * Constructor that initializes coordinates, size and
     * texture of the PhysicObject. TileMap and CollisionSystem
     * is initialized also. Default gravitySettings are used.
     * @param x x coordinate of the object
     * @param y y coordinate of the object
     * @param width width of the object
     * @param height height of the object
     * @param image texture of the object
     * @param map TileMap this object is checking collisions with
     */
    public PhysicObject(int x, int y, int width, 
        int height, BufferedImage image, TileMap map){
        super(x,y,width,height,image,map);
    }
    
    /**
     * Constructor that initializes coordinates, size and
     * texture of the PhysicObject. TileMap and CollisionSystem
     * is initialized also. Gravity settings force's fade time
     * and weight of the object is initialized also.
     * @param x x coordinate of the object
     * @param y y coordinate of the object
     * @param width width of the object
     * @param height height of the object
     * @param weight weight of the object
     * @param time Time it takes for force to fade(seconds)
     * @param image texture of the object
     * @param map TileMap this object is checking collisions with
     */
    public PhysicObject(int x, int y, int width, 
        int height, float weight, double time, BufferedImage image, TileMap map){
        super(x,y,width,height,image,map);
        setForceTime(time);
        setWeight(weight);
    }

    /**
     * Constructor that initializes coordinates, size and
     * texture of the PhysicObject. TileMap and CollisionSystem
     * is initialized also. Gravity settings force's fade time
     * and weight of the object is initialized also.
     * @param x x coordinate of the object
     * @param y y coordinate of the object
     * @param width width of the object
     * @param height height of the object
     * @param weight weight of the object
     * @param gravity gravity of the object
     * @param time Time it takes for force to fade(seconds)
     * @param image texture of the object
     * @param map TileMap this object is checking collisions with
     */
    public PhysicObject(int x, int y, int width, 
        int height, float weight, float gravity, double time, BufferedImage image, TileMap map){
        super(x,y,width,height,image,map);
        setForceTime(time);
        setWeight(weight);
        setGravity(gravity);
    }

    /**
     * Constructor that initializes coordinates and size
     * of the PhysicObject, default gravitySettings are used.
     * @param x x coordinate of the object
     * @param y y coordinate of the object
     * @param width width of the object
     * @param height height of the object
     */
    public PhysicObject(int x, int y, int width, 
        int height){
        super(x,y,width,height);
    }
    
    /**
     * Constructor that initializes coordinates and size
     * of the PhysicObject. TileMap and CollisionSystem
     * is initialized also. Default gravitySettings are used.
     * @param x x coordinate of the object
     * @param y y coordinate of the object
     * @param width width of the object
     * @param height height of the object
     * @param map TileMap this object is checking collisions with
     */
    public PhysicObject(int x, int y, int width, 
        int height, TileMap map){
        super(x,y,width,height,map);
    }
    
    /**
     * Constructor that initializes coordinates and size
     * of the PhysicObject. TileMap and CollisionSystem
     * is initialized also. Gravity settings force's fade time
     * and weight of the object is initialized also.
     * @param x x coordinate of the object
     * @param y y coordinate of the object
     * @param width width of the object
     * @param height height of the object
     * @param weight weight of the object
     * @param time Time it takes for force to fade(seconds)
     * @param map TileMap this object is checking collisions with
     */
    public PhysicObject(int x, int y, int width, 
        int height, float weight, double time, TileMap map){
        super(x,y,width,height,map);
        setForceTime(time);
        setWeight(weight);
    }

    /**
     * Constructor that initializes coordinates and size
     * of the PhysicObject. TileMap and CollisionSystem
     * is initialized also. Gravity settings force's fade time
     * and weight of the object is initialized also.
     * @param x x coordinate of the object
     * @param y y coordinate of the object
     * @param width width of the object
     * @param height height of the object
     * @param weight weight of the object
     * @param gravity gravity of the object
     * @param time Time it takes for force to fade(seconds)
     * @param map TileMap this object is checking collisions with
     */
    public PhysicObject(int x, int y, int width, 
        int height, float weight, float gravity, double time, TileMap map){
        super(x,y,width,height,map);
        setForceTime(time);
        setWeight(weight);
        setGravity(gravity);
    }


    /**
     * update method, this is being called in scenes
     * update. Gravity is added to this method.
     */
    @Override
    public void update(){
        super.update();
        calculateForce();
        moveY(Math.round(gravity * weight - force));
    }

    /**
     * Calculates when and how much force should fade
     * over time
     */
    private void calculateForce(){

        lastTime = firstTime;
        firstTime = System.nanoTime() / 1000000000.0;
        delta = firstTime - lastTime;
        unprocessedTime += delta;

        if(gravity * weight - force < 0){
            getCollisionSystem().ifPresent( coll -> {
                if(coll.checkCollision(coll.getUpCollPoints(),0,
                    (int)(gravity * weight - force))){
                    force -= force - gravity * weight;
                }
            });
        }
        if(unprocessedTime > forceTime / 10){
            unprocessedTime = 0;
            if(force > 1){
                force -= allForce * 0.1f;
            }else{
                force = 0;
            }
        }
    }
    /**
     * Add's force to current force ammount.
     * @param force added force
     */
    public void addForce(float force){
        this.force += force;
        allForce = this.force;
    }

    /**
     * Sets force to given ammount.
     * @param force new force
     */
    public void setForce(float force){
        this.force = force;
        allForce = this.force;
    }

    /**
     * Sets all neccessary physics settings of PhysicObject
     * @param weight weight of object
     * @param gravity gravity
     * @param time forces fade time
     */
    public void setFysicsSettings(float weight,float gravity, double time){
        setWeight(weight);
        setGravity(gravity);
        setForceTime(time);
    }

    /**
     * Sets gravity attribute
     * @param gravity new gravity
     */
    public void setGravity(float gravity){
        this.gravity = gravity;
    }
    /**
     * Returns gravity attribute.
     * @return gravity
     */
    public float getGravity(){
        return gravity;
    }

    /**
     * Sets weight attribute.
     * @param weight new weigth
     */
    public void setWeight(float weight){
        this.weight = weight;
    }
    /**
     * Returns weight attribute.
     * @return weight
     */
    public float getWeight(){
        return weight;
    }

    /**
     * Sets forces fade time(seconds)
     * @param forceTime Forces fade time in seconds
     */
    public void setForceTime(double forceTime){
        if(forceTime > 0){
            this.forceTime = forceTime;
        }else{
            forceTime = 0;
        }
    }
    /**
     * Return forces fade time
     * @return forces fade time
     */
    public double getForceTime(){
        return forceTime;
    }
}