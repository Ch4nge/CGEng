package fi.tamk.cgeng.util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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
     * Writes 2d int array to file, easy tool to 
     * write TileMaps to txt files
     * @param filename name of the file
     * @param x 2d int array that is saved to file
     */
    public static void tilemapToFile (String filename, int[][] x){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
            for (int i = 0; i < x[0].length; i++) {
                for(int j = 0; j < x.length; j++){
                    writer.write(x[j][i]+" ");
                }
            writer.newLine();
            }
        }catch(IOException e){
            e.printStackTrace();
        }  
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
