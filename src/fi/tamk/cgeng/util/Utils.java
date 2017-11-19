package fi.tamk.cgeng.util;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Contains different kind of useful
 * utility methods
 */
public class Utils {

    /**
     * Reads file and returns String of file
     * contents
     * @param path path of file
     * @return String of file contents
     */
    public static String loadFileAsString(String path){
        StringBuilder builder = new StringBuilder();
        
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            while((line = br.readLine()) != null){
                builder.append(line + "\n");
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return builder.toString();
    }
    
    /**
     * Converts String to int, if not succesful
     * catch an error.
     * @param number number as string
     * @return converted int
     */
    public static int parseInt(String number){
        try{
            return Integer.parseInt(number);
        }catch(NumberFormatException e){
            e.printStackTrace();
            return 0;
        }
    }
}
