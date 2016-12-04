package util;

public class StringUtil {
	
	
	public static String deleteQuotes(String input, int numberOfQuotes) {
		int index = 1;
		String str = input;
		
		while (index <= numberOfQuotes) {
			str = str.replaceAll("^\"|\"$", "");
			index++;
		}
		
		return str;
	}

}
