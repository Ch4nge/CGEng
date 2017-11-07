package fi.tamk.cgeng.scenes;

import java.awt.Graphics;
import fi.tamk.cgeng.engine.KeyboardListener;
import fi.tamk.cgeng.engine.MousepadListener;

public abstract class Scene{

    private SceneManager sceneManager;

    public Scene(SceneManager sceneManager){
        this.sceneManager = sceneManager;
    }

    public abstract void onCreate();

    public abstract void update();

    public abstract void paint(Graphics g);

    public SceneManager getSceneManager(){
        return sceneManager;
    }

    public KeyboardListener accessKeyboardListener(){
        return sceneManager.getGame().getDisplay().getKeyboardListener();
    }

    public MousepadListener accessMousepadListener(){
        return sceneManager.getGame().getDisplay().getMousepadListener();
    }
}