package fi.tamk.cgeng.util;

import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;


public class Animator {

    /**
     * List of different frames of your animation.
     */
    private BufferedImage[] frames;
    /**
     * List of generated flipped frames of your animation.
     */
    private BufferedImage[] flippedFrames;

    /**
     * Image of the animation that is shown currently.
     */
    private BufferedImage currentFrame;

    /**
     * Index of currentFrame.
     */
    private int currentFrameID;

    /**
     * Calculates how much time has passed between
     * iterations.
     */
    private double delta;

    /**
     * Attribute that stores delta time, when this 
     * is greater than frameTime, frame updates.
     */
    private double unprocessedTime;

    /**
     * Time when current update is happening
     */
    private double firstTime;

    /**
     * Time when last update happened
     */
    private double lastTime;

    /**
     * Time how long it takes to switch image
     * of the animation
     */
    private double frameTime;

    /**
     * Tells if animation is running or not
     */
    private boolean running;

    /**
     * Tells if animation is flipped or not
     */
    private boolean flipped;

    /**
     * Constructor that initializes all needed attributes.
     * Generates flipped animation if generateFlipped
     * parameter is true. 
     * @param animation frames of the animation
     * @param time Time it takes to switch between images(seconds)
     * @param generateFlipped true = generate flipped animation
     */
    public Animator(BufferedImage[] animation, double time, boolean generateFlipped){
        frames = animation;
        currentFrameID = 0;
        currentFrame = frames[currentFrameID];
        if(generateFlipped){
            generateAnimationFlip();   
        }
        frameTime = time;
        setFrameTime(time);
        running = false;
        flipped = false;
        unprocessedTime = 0;
        lastTime = System.nanoTime() / 1000000000.0;
        
    }

    /**
     * Updates images state
     */
    public void update(){
        if(!running){
            lastTime = System.nanoTime() / 1000000000.0;
        }
        
        if(running){
            firstTime = System.nanoTime() / 1000000000.0;
            delta = firstTime - lastTime;
            lastTime = firstTime;
            unprocessedTime += delta;
            if(unprocessedTime > frameTime){
                unprocessedTime = 0;
                if(currentFrameID < frames.length-1){
                    currentFrameID++;
                }else{
                    currentFrameID = 0;
                }
                if(!flipped){
                    currentFrame = frames[currentFrameID];
                }else{
                    currentFrame = flippedFrames[currentFrameID];
                }
                delta = 0;
            }
        }
    }
    /**
     * Starts the animation
     */
    public void start(){
        running = true;
    }

    /**
     * Stops the animation
     */
    public void stop(){
        running = false;
    }

    /**
     * Resets the animation
     */
    public void reset(){
        unprocessedTime = 0;
        running = false;
        currentFrameID = 0;
        if(!flipped){
            currentFrame = frames[currentFrameID];
        }else{
            currentFrame = flippedFrames[currentFrameID];
        }
        delta = 0;
    }   

    /**
     * Returns the current frame that is being shown
     * @return currentFrame
     */
    public BufferedImage getCurrentFrame(){
        return currentFrame;
    }
    /**
     * Returns array of all images of animation.
     * @return Array of animations images.
     */
    public BufferedImage[] getFrames(){
        return frames;
    }
    /**
     * Returns time it takes between frame updates
     * @return frameTime
     */
    public double getFrameTime(){
        return frameTime;
    }

    /**
     * Sets the time it takes betwewn frame updates
     * @param frameTime new frameTime
     */
    public void setFrameTime(double frameTime){
        if(frameTime < 0){
            this.frameTime = 0;
        }else{
            this.frameTime = frameTime;
        }
    }
    /**
     * Returns if animation is running or not
     * @return if animation is running or not
     */
    public boolean isRunning(){
        return running;
    }
    /**
     * sets running variable
     * @param running new running value
     */
    public void setRunning(boolean running){
        this.running = running;
    }

    /**
     * Tells if image is flipped or not
     * @return tells if image is flipped or not
     */
    public boolean isFlipped(){
        return flipped;
    }

    /**
     * Flips the animation
     */
    public void flip(){
        if(flippedFrames != null){
            flipped = !flipped;
        }else{
            throw new NoFlippedAnimationFoundException();
        }
            
    }

    /**
     * Generate flipped animation
     */
    public void generateAnimationFlip(){
        flippedFrames = new BufferedImage[frames.length];
        for(int i = 0; i < frames.length; i++){
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-frames[i].getWidth(null), 0);
            AffineTransformOp op = new AffineTransformOp(tx, 
                AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            flippedFrames[i] = op.filter(frames[i], null);
        }
    }

    /**
     * Exception that is thrown if user is trying to
     * flip animation, when there is no flipped animation
     * generated.
     */
    public class NoFlippedAnimationFoundException 
        extends RuntimeException{}
}