package fi.tamk.cgeng.engine;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class Display extends JFrame{

    /**
     * Game object
     */
    private final Game g;

    /**
     * Games KeyboardListener
     */
    private KeyboardListener keyboardListener;

    /**
     * Games MousepadListener
     */
    private MousepadListener mousepadListener;

    /**
     * Initializes KeyboardListener, MousepadListener and
     * Window of the game. 
     * @param g Game object where this Display is created
     */
    public Display(Game g){
        this.g = g;
        this.keyboardListener = new KeyboardListener();
        this.mousepadListener = new MousepadListener();

        setSize(g.getScreenWidth(), g.getScreenHeight());
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setFocusable(true);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setTitle(g.getScreenTitle());

        addKeyListener(keyboardListener);
        addMouseListener(mousepadListener);
        
        add(g, BorderLayout.CENTER);
        setVisible(true);
    }

    /**
     * @return KeyboardListener of the game
     */
    public KeyboardListener getKeyboardListener(){
        return keyboardListener;
    }

    /**
     * MousepadListener of the game 
     */
    public MousepadListener getMousepadListener(){
        return mousepadListener;
    }

}