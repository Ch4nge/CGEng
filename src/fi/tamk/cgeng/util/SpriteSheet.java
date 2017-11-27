package fi.tamk.cgeng.util;
import java.awt.image.BufferedImage;

/**
 * Contains Buffered image that can be cropped
 * using crop method.
 */
public class SpriteSheet {
    /**
     * Image of SpriteSheet
     */
    private BufferedImage sheet;
    
    /**
     * Constructor that initializes spriteSheet
     * @param sheet Sheet image
     */
    public SpriteSheet(BufferedImage sheet){
        this.sheet = sheet;
    }
    /**
     * method that crops image and returns given
     * part as Buffered image
     * @param x x coordinate where we start cropping
     * @param y y coordinate where we start cropping
     * @param width width of cropped area
     * @param height height of cropped area
     * @return Buffered image of given area
     */
    public BufferedImage crop(int x, int y, int width, int height){
        return sheet.getSubimage(x, y, width, height);
    }

    /**
     * Crops all images from SpriteSheet and returns
     * array of BufferedImages
     * @return Contents of SpriteSheet as BufferedImage array
     */
    public BufferedImage[] cropAll(int width, int height, int imgWidth, int imgHeight){
        BufferedImage[] tmp = new BufferedImage[width * height];
        int cropPointX = 0;
        int cropPointY = 0;
        int index = 0;

        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                cropPointX = j * imgWidth;
                cropPointY = i * imgHeight;
                tmp[index] = crop(cropPointX, cropPointY,
                    imgWidth, imgHeight);
                index++;
            }
        }
        return tmp;
    }
}
