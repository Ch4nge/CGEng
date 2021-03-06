package fi.tamk.cgeng.scenes;

import java.awt.Graphics;
import fi.tamk.cgeng.engine.KeyboardListener;
import fi.tamk.cgeng.engine.MousepadListener;

/**
 * Scenes methods are called in Game class,
 * Game can change between different scenes through
 * SceneManager.
 */
public abstract class Scene{

    /**
     * sceneManager of the game
     */
    private SceneManager sceneManager;

    /**
     * initalizes sceneManager variable
     * @param sceneManager sceneManager of game
     */
    public Scene(SceneManager sceneManager){
        this.sceneManager = sceneManager;
    }

    /**
     * called when Scene is set as currentScene
     * in SceneManager
     */
    public abstract void onCreate();

    /**
     * Updating of scene (movement, inputs)
     * happens in this method.
     */
    public abstract void update();

    /**
     * Painting of this Scene happens in this 
     * method
     * @param g Graphics object this Scene is drawn with
     */
    public abstract void paint(Graphics g);

    /**
     * Disposes contents of this scene
     */
     public abstract void dispose();
    /**
     * @return sceneManager of this Scene.
     */
    public SceneManager getSceneManager(){
        return sceneManager;
    }

    /**
     * Short access to games KeyboardListener
     * @return KeyboardListener
     */
    public KeyboardListener accessKeyboardListener(){
        return sceneManager.getGame().getDisplay().getKeyboardListener();
    }

    /**
     * Short access to games MousepadListener
     * @return MousepadListener
     */
    public MousepadListener accessMousepadListener(){
        return sceneManager.getGame().getDisplay().getMousepadListener();
    }
}