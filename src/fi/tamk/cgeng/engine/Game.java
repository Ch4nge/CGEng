package fi.tamk.cgeng.engine;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import fi.tamk.cgeng.scenes.*;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Game class handels game loop, drawing and updating
 * of Scenes, and launching launching the game
 */
public class Game extends JPanel implements Runnable {

    /**
     * Thread that starts the game loop
     */
    private Thread thread;

    /**
     * Boolean that tells if game is running or not
     */
    private boolean running = false;

    /**
     * This is cap of fps rate
     */
    private final double UPDATE_LIMIT = 1.0 / 60.0;

    /**
     * Display's height
     */
    private int height;

    /**
     * Display's width
     */
    private int width;

    /**
     * Display's title
     */
    private String title;

    /**
     * sceneManager contains current Scene
     * that is being drawn
     */
    private SceneManager sceneManager;

    /**
     * Games display (window)
     */
    private Display display;

    /**
     * Initializes Display and scene manager
     * @param width width of the display
     * @param height height of the display
     * @param title title of the display
     */
    public Game(int width, int height, String title){
        setScreenWidth(width);
        setScreenHeight(height);
        setScreenTitle(title);
        setBackground(Color.black);
        display = new Display(this);
        sceneManager = new SceneManager(this);
    }

    /**
     * method that starts the gameloop.
     */
    public void start(){
        display.add(this);
        thread = new Thread(this);
        thread.run();
    }

    /**
     * Game loop
     */
    @Override
    public void run(){
        running = true;

        boolean render = false;
        double firstTime = 0;
        double lastTime = System.nanoTime() / 1000000000.0;
        double delta = 0;
        double unprocessedTime = 0;

        double frameTime = 0;
        int frames = 0;
        int fps = 0;
        
        while(running){
            
            render = false;
            firstTime = System.nanoTime() / 1000000000.0;
            delta = firstTime - lastTime;
            lastTime = firstTime;

            unprocessedTime += delta;

            frameTime += delta;

            while(unprocessedTime >= UPDATE_LIMIT){
                unprocessedTime -= UPDATE_LIMIT;
                render = true;

                //Update the game
                update();

                if(frameTime > 1){
                    fps = frames;
                    frameTime = 0;
                    frames = 0;
                    System.out.println(fps);
                }
            }

            if(render){
                //Paint the game
                repaint();                
                frames++;
            }else{
                try{
                    Thread.sleep(1);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
        dispose();
    }

    /**
     * Call's Scenes update method
     */
    public void update(){
        sceneManager.getScene().ifPresent((scene) -> 
            scene.update());
    }

    /**
     * Paints Scenes contents to this JPanel
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        sceneManager.getScene().ifPresent((scene) ->
            scene.paint(g2));
    }

    /**
     * Stops the gameloop
     */
    public void stop(){
        running = false;
    }

    /**
     * Called after when game stops running
     */
    private void dispose(){
        sceneManager.getScene().ifPresent((scene) -> 
            scene.dispose());
    }

    /**
     * @return width of display
     */
    public int getScreenWidth(){
        return width;
    }

    /**
     * set's width of display, can't be less than 0
     * @param width width of display
     */
    public void setScreenWidth(int width){
        if(width < 0){
            this.width = 0;
        }else{
            this.width = width;
        }
    }

    /**
     * @return height of display
     */
    public int getScreenHeight(){
        return height;
    }
    /**
     * set's height of display, can't be less than 0
     * @param height height of display
     */
    public void setScreenHeight(int height){
        if(height < 0){
            this.height = 0;
        }else{
            this.height = height;
        }
    }

    /**
     * @return title of the display
     */
    public String getScreenTitle(){
        return title;
    }

    /**
     * Changes title of the display
     * @param title title of the display
     */
    public void setScreenTitle(String title){
        this.title = title;
    }


    /**
     * @return games SceneManager
     */
    public SceneManager getSceneManager(){
        return sceneManager;
    }


    /**
     * @return games display
     */
    public Display getDisplay(){
        return display;
    }
}