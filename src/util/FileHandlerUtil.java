package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileHandlerUtil {
	
	public static String loadFile(String file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		StringBuilder sb;
		
		try {
		    sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        //sb.append(System.lineSeparator());
		        line = br.readLine();
		    }

		} finally {
		    br.close();
		}
		
		return sb.toString();
	}
	
}
