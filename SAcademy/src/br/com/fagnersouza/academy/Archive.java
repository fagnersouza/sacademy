/*
 * www.fagnersouza.com.br
 */
package br.com.fagnersouza.academy;

import br.com.fagnersouza.log.Tracer;
import br.com.fagnersouza.util.UtilString;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 *
 * @author fagner.souza
 */
class Archive {
    public static final String USER_HOME = System.getProperty("user.home");
    public static final String OS = System.getProperty("os.name");
    private static final String SACADEMY = "SAcademy";
    private static final String S = "\\";
    
    /**
     * Get the path for the SAcademy library.
     * @return Path for library
     */
    public static String getPath(){
      
        String VIDEO = "";
        
        if(isWindows())
            VIDEO = "Videos";
        
        return USER_HOME + "\\" + VIDEO + "\\" + SACADEMY;
    }
    
    private static boolean isWindows() {
        return (OS.toLowerCase().contains("win"));
    }

    private static boolean isMac() {
        return (OS.toLowerCase().contains("mac"));
    }

    private static boolean isUnix() {
        return (OS.toLowerCase().contains("nux"));
    }
    
    /**
     * Create, if not exists, the directory for the SAcademy library
     * where the videos will be maintained.
     * @return  true if the library was/is created
     *          false if the library could not be created
     */
    public static boolean create(){
        try{
            File library = new File(getPath());

            library.mkdir();
            
            return (library.exists() && library.isDirectory());
        }catch(Exception e){
            Tracer.logger.error("",e);            
        }
        
        return false;
    }
    
    private static String formatFileName(String fileName){
        if(fileName == null || fileName.isEmpty())
            return "";

        fileName = fileName.trim();
        fileName = UtilString.removeAccent(fileName);
        fileName = fileName.replace(" ", "");
        fileName = fileName.toUpperCase();

        return fileName;
    }
    
    public static boolean createRecord(String dirName, String fileName, String contents){
        if(assertString(dirName))
            return false;
              
        if(assertString(fileName))
            return false;
         
        if(assertString(contents))
            return false; 
        
        dirName = formatFileName(dirName);
        
        try {
            String path = getPath() + S + dirName;
            
            File f = new File(path);
            f.mkdirs();
            
            
            FileWriter fout = new FileWriter(path + S + fileName);
            
            fout.write(contents);
            fout.close();
            
            return true;
        } catch (FileNotFoundException  ex) {
            Tracer.logger.error("", ex);
        } catch (IOException ex) {
            Tracer.logger.error("", ex);
        }
        
        return false;
    }
    
    public static boolean copyAsset(String asset,String target, String dirName){
        if(assertString(dirName))
            return false;
         
        if(assertString(asset) || !new File(asset).exists())
            return false; 
        
        dirName = formatFileName(dirName);

         try {
            String path = getPath() + S + dirName;
            
            File f = new File(path);
            f.mkdirs();
            
            
            Files.copy(Paths.get(asset),Paths.get(path+S+target),REPLACE_EXISTING);            
            
            return true;
        } catch (FileNotFoundException  ex) {
            Tracer.logger.error("", ex);
        } catch (IOException ex) {
            Tracer.logger.error("", ex);
        }       
         
        return false;
    }
    
    private static boolean assertString(String s){
        return (s == null || s.isEmpty());
    }
}
