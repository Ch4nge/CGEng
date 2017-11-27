package fi.tamk.cgeng.scenes;

import java.util.Optional;
import java.awt.Graphics;
import fi.tamk.cgeng.engine.*;
/**
 * SceneManager is class that contains current Scene,
 * that is being rendered at game loop. Changing the
 * scenes happens throuhg this class.
 */
public class SceneManager{

    /**
     * Game object where this SceneManager is located
     */
    private final Game game;

    /**
     * Current scene that is being drawn at gameloop.
     */
    private Optional<Scene> currentScene;

    /**
     * Constructor, initializes currentScene and game
     * variables
     */
    public SceneManager(Game game){
        currentScene = Optional.empty();
        this.game = game;
    }

    /**
     * Changes current scene and calls its onCreate method
     * @param scene New scene
     */
    public void setScene(Scene scene){
        currentScene = Optional.of(scene);
        currentScene.ifPresent((s) -> s.onCreate());
    }

    /**
     * @return currentScene
     */
    public Optional<Scene> getScene(){
        return currentScene;
    }

    /**
     * @return Game object where this SceneManager
     * is located
     */
    public Game getGame(){
        return game;
    }


}