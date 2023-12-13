package utilities;

import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
   private static FileInputStream input;
   private static Properties properties;

    static {
        String path="C:\\Users\\aydi1\\IdeaProjects\\MindtekTestNGFramework\\src\\test\\resources\\cofigurations\\Configuration.properties";
        try{
             input=new FileInputStream(path);
         properties=new Properties();
            properties.load(input);
        } catch (FileNotFoundException e) {
            System.out.println("Path for properties file is invalid");
        }catch (IOException e){
            System.out.println("Failed to load the properties file");
        }finally{
            try {
                assert input!=null;
                input.close();
            } catch (IOException e) {
                System.out.println("Exception occurred while trying to close input object");
            }
        }
    }
    public static String getProperty(String key){
      return properties.getProperty(key);
    }

}
