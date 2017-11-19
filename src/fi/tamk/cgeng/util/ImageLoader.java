package fi.tamk.cgeng.util;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Class that is used to load images
 */
public class ImageLoader {


    /**
     * Loads image file and returns BufferedImage
     * @param path path of the file.
     * @return BufferedImage of given file
     */
    public static BufferedImage loadImage(String path){
        try{
            return ImageIO.read(ImageLoader.class.getResource(path));
        }catch(IOException e){
            e.printStackTrace();
            System.exit(1);
        }                  
        return null;
    }
    
}
