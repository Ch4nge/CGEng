import java.awt.event.KeyEvent;
import fi.tamk.cgeng.objects.PhysicObject;
import fi.tamk.cgeng.tiles.TileMap;
import java.awt.image.BufferedImage;
import fi.tamk.cgeng.util.Animator;
import fi.tamk.cgeng.util.ImageLoader;
import fi.tamk.cgeng.util.SpriteSheet;
import fi.tamk.cgeng.util.Audio;
import fi.tamk.cgeng.engine.KeyboardListener;

public class MyPlayer extends PhysicObject{

    private Audio jump;
    private Animator animator;
    private boolean walking = false;
    private boolean grounded = false;

    public MyPlayer(int x, int y, int width, int height, 
        TileMap map, KeyboardListener listener){
        super(x,y,width,height,map);
        setKeyboardListener(listener);
        animator = new Animator(new SpriteSheet(ImageLoader
            .loadImage("/textures/marioanimation.png")).cropAll(3,1,64,74), 0.05, true);
        jump = new Audio("sounds/jump.wav");
        animator.start();
        setImage(animator.getCurrentFrame());
    }

    public void move(){

        if(getKeyboardListener().isKeyPressed(KeyEvent.VK_D)){
            moveX(8);
            animator.start();
            walking = true;
            if(animator.isFlipped()){
                animator.flip();
            }
        }
        if(getKeyboardListener().isKeyPressed(KeyEvent.VK_A)){
            moveX(-8);
            animator.start();
            walking = true;
            if(!animator.isFlipped()){
                animator.flip();
            }
        }
        if(getKeyboardListener().isKeyPressed(KeyEvent.VK_SPACE)){
            if(grounded){
                jump();
            }
        }

        //Reset walk animation
        if(!walking){
            animator.reset();
        }
        //Check if we are at ground or not
        getCollisionSystem().ifPresent( coll -> {
            if(coll.checkCollision(coll.getDownCollPoints(),0,10)){
                grounded = true;
            }else{
                grounded = false;
            }
        });

        walking = false;
        animator.update();
        setImage(animator.getCurrentFrame());
    }

    public void jump(){
        jump.stop();
        jump.play();
        setForce(20);
    }
}