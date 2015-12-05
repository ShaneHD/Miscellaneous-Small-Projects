package com.github.shanehd;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.github.shanehd.utilities.PCUtils;

/**
 * Does a netstat, waits for user input, does another one, and dipslays any new ip's
 * 
 * @author https://www.github.com/ShaneHD
 */
public class NetstatChangeInformer {
	public static void main(String[] args)  {
		HashSet<String> before = netstat(), after;
		PCUtils.messagebox("Press ok when ready to check changes", "Continue?", JOptionPane.QUESTION_MESSAGE);
		after = netstat();
		
		for(String ip : after) {
			if(!before.contains(ip))
				System.out.println(ip);
		}
	}
	
	private static HashSet<String> netstat() {
		HashSet<String> netstat = new HashSet<String>();
		
		try {
			Process p = Runtime.getRuntime().exec("netstat -n");
			Scanner scanner = new Scanner(p.getInputStream());
			
			while(scanner.hasNext()) {
				String s = scanner.nextLine();
				
				try {
					String _ip = s.split(":")[1];
					String _ = _ip.split(" ")[0];
					String ip = _ip.split(_)[1].trim();
					netstat.add(ip);
				} catch(Exception e) {}
			}
			
			scanner.close();
		} catch (IOException e) {
			PCUtils.exceptionbox(e);
			System.exit(0);
		}
		
		return netstat;
	}
}
