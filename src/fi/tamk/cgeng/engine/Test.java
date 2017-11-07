package fi.tamk.cgeng.engine;

import fi.tamk.cgeng.scenes.*;

public class Test{


    public Test(){
        
    }

    public static void main(String... args){
        Game gc = new Game(800, 600, "Test");
        TestScene scene = new TestScene(gc.getSceneManager());
        gc.getSceneManager().setScene(scene);
        gc.start();
    }
}