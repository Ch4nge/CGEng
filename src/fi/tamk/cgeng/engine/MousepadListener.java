package fi.tamk.cgeng.engine;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class MousepadListener implements MouseListener{

    /**
     * Coordinate x of the cursor
     */
    private int mouseX = 0;
    /**
     * Coordinate y of the cursor
     */
    private int mouseY = 0;

    /**
     * Tells if mousebutton is pressed or not
     */
    private boolean pressed = false;


    @Override
    public void mouseClicked(MouseEvent e){
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseEntered(MouseEvent e){

    }

    @Override
    public void mouseExited(MouseEvent e){

    }

    @Override
    public void mousePressed(MouseEvent e){
        mouseClicked(e);
        pressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e){
        pressed = false;
    }

    /**
     * @return true if mousebutton is pressed
     */
    public boolean isMousePressed(){
        return pressed;
    }

    /**
     * @return true if mousebutton is not pressed
     */
    public boolean isMouseReleased(){
        return !pressed;
    }

    /**
     * Returns coordinate x of mouses cursor
     * @return cursors x coordinate
     */
    public int getX(){
        return mouseX;
    }

    /**
     * Returns coordinate y of mouses cursor
     * @return cursors x coordinate
     */
    public int getY(){
        return mouseY;
    }
}