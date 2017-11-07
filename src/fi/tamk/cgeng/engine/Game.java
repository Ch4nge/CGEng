package fi.tamk.cgeng.engine;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import fi.tamk.cgeng.scenes.*;

public class Game extends JPanel implements Runnable {

    private Thread thread;

    private boolean running = false;
    private final double UPDATE_LIMIT = 1.0 / 60.0;

    private int height;
    private int width;
    private String title;

    private SceneManager sceneManager;
    private Display display;

    public Game(int width, int height, String title){
        setScreenWidth(width);
        setScreenHeight(height);
        setScreenTitle(title);
        display = new Display(this);
        sceneManager = new SceneManager(this);
    }

    public void start(){
        display.add(this);
        thread = new Thread(this);
        thread.run();
    }

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

    public void update(){
        sceneManager.getScene().ifPresent((scene) -> 
            scene.update());
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        sceneManager.getScene().ifPresent((scene) ->
            scene.paint(g));
    }

    public void stop(){

    }

    private void dispose(){

    }

    public int getScreenWidth(){
        return width;
    }
    public void setScreenWidth(int width){
        if(width < 0){
            this.width = 0;
        }else{
            this.width = width;
        }
    }

    public int getScreenHeight(){
        return height;
    }

    public void setScreenHeight(int height){
        if(height < 0){
            this.height = 0;
        }else{
            this.height = height;
        }
    }

    public String getScreenTitle(){
        return title;
    }

    public void setScreenTitle(String title){
        this.title = title;
    }

    public SceneManager getSceneManager(){
        return sceneManager;
    }

    public Display getDisplay(){
        return display;
    }
}