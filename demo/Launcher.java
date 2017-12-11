import fi.tamk.cgeng.engine.Game;

public class Launcher{

    public static void main(String... args){
        Game gc = new Game(800, 600, "SUPERCOOLMARIOGAME");
        MyScene scene = new MyScene(gc.getSceneManager());
        gc.getSceneManager().setScene(scene);
        gc.start();
    }
}