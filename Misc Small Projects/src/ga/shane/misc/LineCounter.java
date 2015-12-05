package ga.shane.misc;

import ga.shane.utilities.ArrayUtils;
import ga.shane.utilities.FileUtils;

import java.io.File;

/** 
 * @author https://www.github.com/ShaneHD
 */
public class LineCounter {
	static int total = 0;
	static File baseDir = new File("C:/Users/Shane/git/Global-Utilities/Global Utilities");
	static boolean path = false;
	static String[] acceptedExtensions = {
		"java"
	};
	
	public static void main(String[] args) {
		scan(baseDir);
		System.out.println("\nTotal lines: " + total);
	}
	
	static void scan(File dir) {
		for(File file : dir.listFiles()) {
			if(file.isDirectory())
				scan(file);
			else {
				if(!(acceptedExtensions.length == 0 ? true : ArrayUtils.contains(acceptedExtensions, file.getName().split("\\.")[1])))
					continue;
				
				int size = FileUtils.getFileLines(file).size();
				total+= size;				
				System.out.println((path ? file.getPath() : file.getName()) + ": " + size + " lines.");
			}
		}
	}
}
