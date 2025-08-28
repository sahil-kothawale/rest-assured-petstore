package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	public static String getProperty(String key, String fileName){
		
		Properties properties = new Properties();
		try {
			String filePath = "src/test/resources/" + fileName;
			System.out.println(filePath);
			FileInputStream file = new FileInputStream(filePath);
			properties.load(file);
		}
		catch(FileNotFoundException fne) {
			throw new RuntimeException("File not found: " + fileName);
		}
		catch(IOException io) {
			throw new RuntimeException("Failed to load file: " + fileName);
		}
		return properties.getProperty(key);
	}
	
	public static String getProperty(String key){
		return getProperty(key, "config.properties");
	}
	
}
