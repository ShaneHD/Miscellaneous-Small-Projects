package com.github.shanehd;

import com.github.shanehd.utilities.BScanner;

/** @author https://www.github.com/ShaneHD */
public class CsgoLobbyJoiner {
	public static void main(String[] args) {
		try {
			BScanner scanner = new BScanner(System.in);
			
			Exception err;
			long id = 0;
			String url = null;
			
			do {
				err = null;
				
				try {
					String base = scanner.input("Paste the base lobby url");
					id = Long.parseLong(base.split("730/")[1].split("/")[0]);
					url = base.replace(id + "", "%s");
				} catch(Exception e) {
					System.out.println("Invalid url, enter another one.");
					err = e;
				}	
			} while(err != null);
					
			System.out.println("Press enter to join next lobby, or enter a number to skip multiple (accepts negatives)");
			
			while(true) {
				int mod = 1;
				
				try {
					mod = scanner.iinput();
				} catch(NumberFormatException e) {}
				
				id+= mod;
				String lobby = String.format(url, id);
				System.out.println("Joining: " + lobby);
				System.out.println(id);
				
				//Desktop.getDesktop().browse(URI.create(lobby));
			}
		} catch(Exception e) {
			throw(new RuntimeException(e));
		}
	}
}
