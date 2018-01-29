import fi.tamk.cgeng.engine.Game;

public class Launcher{

    public static void main(String... args){
        Game gc = new Game(640, 640, "Demo2");
        MyScene scene = new MyScene(gc.getSceneManager());
        gc.getSceneManager().setScene(scene);
        gc.start();
    }
}