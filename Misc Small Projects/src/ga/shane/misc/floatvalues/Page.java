package ga.shane.misc.floatvalues;

import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JFrame;

import ga.shane.utilities.PCUtils;

/** @author http://www.shane.ga */
public class Page extends JFrame {
	private static int id = 1;
	
	public Page(String url) throws IOException {
		super("" + id++);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(PCUtils.getScreenWidth() / 3, PCUtils.getScreenHeight() / 3);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setVisible(true);
		

		JEditorPane penis = new JEditorPane(url);
		add(penis);
		
	}
}
