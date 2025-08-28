package utilities;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	
	public static <T> T convertJsonFileToObject(String fileName, Class<T> modelClass) {
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			File file = new File("src/test/resources/testData" + fileName);
			return mapper.readValue(file, modelClass);
		} 
		catch (IOException e) {
			throw new RuntimeException("Failed to read " + fileName + " file", e);
		}
	}
	
}
