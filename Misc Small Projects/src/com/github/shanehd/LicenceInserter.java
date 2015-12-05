package com.github.shanehd;

import java.io.File;

import com.github.shanehd.utilities.FileUtils;

/**
 * Inserts a licence header into all src files in a directory (and its sub dirs)
 * 
 * @author https://www.github.com/ShaneHD
 */
public class LicenceInserter {
	static String licence;
	
	public static void main(String[] args) {
//		Start directory, will edit .java files inside it and its sub dirs
		File dir = FileUtils.newFile("C:\\Users\\Shane\\git");
		
		licence = FileUtils.getFileContents(FileUtils.newFileInsideClasspath("ga/shane/misc/licence.txt"));
		modify(dir, true);

	}

	static void modify(File dir, boolean remove) {
		System.out.println("|== " + dir.getAbsolutePath() + " ==|");
		
		for(File file : dir.listFiles()) {
			if(file.isDirectory())
				modify(file, remove);
			else { 
				if(!file.getName().endsWith(".java"))
					continue;
				
				String contents = FileUtils.getFileContents(file);
				boolean containsLicence = contents.contains(licence);
				
				if(remove && containsLicence)
					contents = contents.replace(licence, "");
				else if(!remove && !containsLicence)
					contents = licence + contents;
				else
					continue;
				
				FileUtils.write(contents, file);
				System.out.println((remove ? "Removed licence from file: " : "Added licence to file: ") + file.getAbsolutePath());
			}
		}
	}
}
