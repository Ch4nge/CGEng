package fi.tamk.cgeng.engine;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class Display extends JFrame{

    private final Game gc;
    private KeyboardListener keyboardListener;
    private MousepadListener mousepadListener;

    public Display(Game gc){
        this.gc = gc;
        this.keyboardListener = new KeyboardListener();
        this.mousepadListener = new MousepadListener();

        setSize(gc.getScreenWidth(), gc.getScreenHeight());
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setFocusable(true);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setTitle(gc.getScreenTitle());

        addKeyListener(keyboardListener);
        addMouseListener(mousepadListener);
        
        add(gc, BorderLayout.CENTER);
        setVisible(true);
    }

    public KeyboardListener getKeyboardListener(){
        return keyboardListener;
    }

    public MousepadListener getMousepadListener(){
        return mousepadListener;
    }

}