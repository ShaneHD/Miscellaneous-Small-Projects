package ga.shane.misc.floatvalues;

import java.awt.Desktop;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/** @author http://www.shane.ga */
public class CsgoMarketFloatChecker {
	public static void main(String[] args) {		
		try {			
			Scanner scanner = new Scanner(new InputStreamReader(new URL("http://steamcommunity.com/market/listings/730/Glock-18%20|%20Grinder%20%28Factory%20New%29").openStream()));
			ArrayList<String> skins = new ArrayList<String>();
			
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				
				if(line.contains("var g_rgAssets = ")) {
					Exception e = null;
					
					int split = 1;
					
					do {
						try {
							String part = line.split("csgo_econ_action_preview%20")[split];
							String[] assetid = part.split("%assetid%");
							
							String p1 = assetid[0];
							String p2 = line.split("\"id\":\"")[split].split("\"")[0];
							String p3 = assetid[1].split("\",\"name\"")[0];
							
							System.out.println("steam://rungame/730/76561202255233023/+csgo_econ_action_preview%20" + String.join(p1, p2,p3));
							//skins.add(String.join(p1, p2, p3));
							
//							steam://rungame/730/76561202255233023/+csgo_econ_action_preview%20M320115763990886270A4261599237D4811685132910881222
							
							split++;
						} catch(Exception ex) {
							e = ex;
						}
					} while(e == null);
					
					for(String skin : skins) {
						String formatted = String.format("https://glws.org/#%s", skin);
						System.out.println(formatted);
						//Desktop.getDesktop().browse(URI.create(String.format("https://glws.org/#%s", skin)));
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
