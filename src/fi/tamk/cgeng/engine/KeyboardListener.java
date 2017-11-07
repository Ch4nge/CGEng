package fi.tamk.cgeng.engine;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyboardListener implements KeyListener{

    /**
     * boolean values for all buttons on the keyboard.
     * if key is being pressed value is true.
     */
    private boolean[] keys = new boolean[256];

    @Override
    public void keyPressed(KeyEvent event){
        keys[event.getKeyCode()] = true;
    }

    @Override
    public void keyTyped(KeyEvent event){
        
    }

    @Override
    public void keyReleased(KeyEvent event){
        keys[event.getKeyCode()] = false;   
    }

    /**
     * Checks if given key is pressed down, use
     * KeyEvent's final int values for easy usage
     * (VK_W,VK_A,VK_S,VK_D)
     * @param key checking if this key is pressed down
     * @return true if key is pressed down
     */
    public boolean isKeyPressed(int key){
        return keys[key];
    }

    /**
     * Checks if given key is not pressed down, use
     * KeyEvent's final int values for easy usage
     * (VK_W,VK_A,VK_S,VK_D)
     * @param key checking if this key is not pressed down
     * @return true if key is not pressed down
     */
    public boolean isKeyReleased(int key){
        return !keys[key];
    }

}