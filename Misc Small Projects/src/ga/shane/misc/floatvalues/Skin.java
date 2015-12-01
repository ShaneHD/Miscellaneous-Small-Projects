package ga.shane.misc.floatvalues;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

/** @author http://www.shane.ga */
public class Skin {
	public Skin(String inspectLink) throws IOException {
		String url = String.format("https://glws.org/#%s", inspectLink.split("%20")[1]);
		Desktop.getDesktop().browse(URI.create(url));
	}
}
