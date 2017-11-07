package fi.tamk.cgeng.scenes;

import java.util.Optional;
import java.awt.Graphics;
import fi.tamk.cgeng.engine.*;

public class SceneManager{

    private final Game game;
    private Optional<Scene> currentScene;

    public SceneManager(Game game){
        currentScene = Optional.empty();
        this.game = game;
    }
    public void paint(Graphics g){
        currentScene.ifPresent((scene) -> scene.paint(g));
    }

    public void setScene(Scene scene){
        currentScene = Optional.of(scene);
    }

    public Optional<Scene> getScene(){
        return currentScene;
    }

    public Game getGame(){
        return game;
    }


}