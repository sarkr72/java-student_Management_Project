package helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class FileReaderHelper extends Helper {

	public static String[] readFile(String fName) throws FileNotFoundException {
		File file = new File(fName);
		Scanner scanner = new Scanner(file);
		String[] array = new String[40000];
		int count = 0;
		while(scanner.hasNextLine()) {
			String str = scanner.nextLine();
			array[count++] = str;
		}
		String[] data = Arrays.copyOf(array, count);
		return data;
	}
}
