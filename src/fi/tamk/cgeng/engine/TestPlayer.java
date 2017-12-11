package fi.tamk.cgeng.engine;

import java.awt.event.KeyEvent;
import fi.tamk.cgeng.objects.*;
import fi.tamk.cgeng.tiles.*;
import java.awt.image.BufferedImage;
import fi.tamk.cgeng.util.*;

public class TestPlayer extends PhysicObject{

    private Audio aud;
    private Animator animator;
    private boolean walking = false;
    private boolean jumped = false;

    public TestPlayer(int x, int y, int width, int height, BufferedImage image, TileMap map, KeyboardListener listener){
        super(x,y,width,height,image,map);

        setKeyboardListener(listener);
        animator = new Animator(new SpriteSheet(ImageLoader.loadImage("/textures/marioSheet.png")).cropAll(5,1,100,175), 0.1, true);
        aud = new Audio("sounds/Jump.wav");
        animator.start();
    }

    public void move(){

        if(getKeyboardListener().isKeyPressed(KeyEvent.VK_D)){
            moveX(3);
            animator.start();
            walking = true;
            if(animator.isFlipped()){
                animator.flip();
            }
        }
        if(getKeyboardListener().isKeyPressed(KeyEvent.VK_A)){
            moveX(-3);
            animator.start();
            walking = true;
            if(!animator.isFlipped()){
                animator.flip();
            }
        }

        if(getKeyboardListener().isKeyPressed(KeyEvent.VK_W)){
            aud.play();
            setForce(20);
        }
        if(getKeyboardListener().isKeyPressed(KeyEvent.VK_S))
            moveY(3);


        if(!walking){
            animator.reset();
        }

        walking = false;
        animator.update();
        setImage(animator.getCurrentFrame());
    }
}