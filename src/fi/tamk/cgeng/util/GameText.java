package fi.tamk.cgeng.util;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;

/**
 * Draws given text to game. 
 */
public class GameText{
    /**
     * Font that is used
     */
    private Font font;
    /**
     * x coordinate of text
     */
    private int x;
    /**
     * y coordinate of text
     */
    private int y;
    /**
     * Font size
     */
    private float size = 30;
    /**
     * Text that is being drawn to screen
     */
    private String text;
    /**
     * Color of the text
     */
    private Color color;

    /**
     * Constructor that initializes text, x and y.
     * Uses basic Font that is TimesRoman
     * @param text text that is being drawn
     * @param x x coordinate of text
     * @param y y coordinate of text
     */
    public GameText(String text, int x, int y){
        setText(text);
        setX(x);
        setY(y);
        color = Color.BLACK;
        font = new Font("TimesRoman", Font.PLAIN, (int) size);
    }

    /**
     * Paints text to screen.
     * @param text is drawn by Graphics object
     */
    public void paint(Graphics g){
        g.setFont(font);
        g.setColor(color);
        g.drawString(text, x, y);
    }
    
    /**
     * Changes text
     * @param text new text
     */
    public void setText(String text){
        this.text = text;
    }

    /**
     *@return text
     */
    public String getText(){
        return text;
    }
    /**
     * Sets new font to game
     * @param font font of to game
     */
    public void setFont(Font font){
        this.font = font;
    }
    /**
     * Sets new font to game
     * @param font Name of the font
     */
    public void setFont(String font){
        this.font = new Font(font, Font.PLAIN, (int)size);
    }

    /**
     * Sets size of the font
     * @param size size of the font
     */
    public void setSize(float size){
        this.size = size;
        font = font.deriveFont(size);
    }

    /**
     * Sets x coordinate of text
     * @param x x coordinate of text
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * Sets y coordinate of text
     * @param y y coordinate of text
     */
    public void setY(int y){
        this.y = y;
    }
    /**
     * Returns x coordinate of text
     * @return x coordinate of text
     */
    public int getX(){
        return x;
    }

    /**
     * Returns y coordinate of text
     * @return y coordinate of text
     */
    public int getY(){
        return y;
    }
    /**
     * Sets color of the font
     * @param c color of the font
     */
    public void setColor(Color c){
        color = c;
    }
    /**
     * Returns color of the text.
     * @return color of the text
     */
    public Color getColor(){
        return color;
    }
}