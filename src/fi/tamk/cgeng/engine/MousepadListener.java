package fi.tamk.cgeng.engine;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class MousepadListener implements MouseListener{


    private int mouseX, mouseY = 0;
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

    public boolean isMousePressed(){
        return pressed;
    }

    public boolean isMouseReleased(){
        return !pressed;
    }

    public int getX(){
        return mouseX;
    }

    public int getY(){
        return mouseY;
    }
}