package fi.tamk.cgeng.objects;

import java.awt.image.BufferedImage;
import fi.tamk.cgeng.tiles.TileMap;


public abstract class PhysicObject extends GameObject{

    private float gravity = 1;

    private float weight = 10;

    private float force = 0;

    private float allForce = 0;

    private double forceTime = 1;

    private double lastTime = System.nanoTime() / 1000000000.0;

    private double firstTime = 0;

    private double delta = 0.0;

    private double unprocessedTime = 0;

    public PhysicObject(int x, int y, int width, 
        int height, BufferedImage image){
        super(x,y,width,height,image);
    }
    public PhysicObject(int x, int y, int width, 
        int height, BufferedImage image, TileMap map){
        super(x,y,width,height,image,map);
    }

    @Override
    public void update(){
        super.update();
        calculateForce();
        moveY(Math.round(gravity * weight - force));
    }

    private void calculateForce(){

        lastTime = firstTime;
        firstTime = System.nanoTime() / 1000000000.0;
        delta = firstTime - lastTime;
        unprocessedTime += delta;

        if(unprocessedTime > forceTime / 10){
            unprocessedTime = 0;
            if(force > 1){
                force -= allForce * 0.1f;
            }else{
                force = 0;
            }
        }
    }
    public void addForce(float force){
        this.force += force;
        allForce = this.force;
    }

    public void setForce(float force){
        this.force = force;
        allForce = this.force;
    }

}