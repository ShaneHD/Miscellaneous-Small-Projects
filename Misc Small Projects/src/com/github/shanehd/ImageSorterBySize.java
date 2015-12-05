package com.github.shanehd;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.github.shanehd.utilities.BScanner;
import com.github.shanehd.utilities.ImageUtils;
import com.github.shanehd.utilities.StringUtils;

/** @author https://www.github.com/ShaneHD */
public class ImageSorterBySize {
	public static void main(String[] args) {
		BScanner scanner = new BScanner(System.in);
		File dir = new File(scanner.input("Enter dir"));
		
		if(!dir.exists())
			throw new RuntimeException("Dir doesn't exist");
		
		File newDir = new File(dir, "/" + scanner.input("Name of folder to place found images?"));
		
		if(!newDir.exists())
			newDir.mkdir();
		
		int w = scanner.iinput("Width?");
		int h = scanner.iinput("Height?");
		int offs = 0;
		
		try {
			offs = scanner.iinput("Allowable offset? if none leave blank");
		} catch(NumberFormatException e) {}
		
		for(File file : dir.listFiles()) {
			if(!ImageUtils.isImage(file))
				continue;
			
			try {
				BufferedImage img = ImageUtils.load(file);
				
				if(img.getWidth() < w + offs || img.getWidth() > w + offs)
					continue;
				
				if(img.getHeight() < h + offs || img.getHeight() > h + offs)
					continue;
				
				try {
					Files.copy(file.toPath(), new File(newDir, "/" + file.getName()).toPath());
				} catch (IOException e) {
					System.out.println("Failed to copy " + StringUtils.quote(file.getName()));
					e.printStackTrace();
				}
				
				System.out.println("Copied " + StringUtils.quote(file.getName()));
			} catch(Exception e) {}
		}
		
		System.out.println("Done!");
	}
}
