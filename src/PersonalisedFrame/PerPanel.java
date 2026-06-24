package PersonalisedFrame;

import java.awt.*;

import javax.swing.JPanel;

public class PerPanel extends JPanel{
	
	public static final int FLOWLAYOUT=0;
	public static final int BORDERLAYOUT=1;
	public static final int GRIDLAYOUT=2;
	
	public PerPanel(int standardLayout) {
		
		if (standardLayout == 1) setLayout(new BorderLayout());
		if (standardLayout == 2) setLayout(new GridLayout());
	}
	
}
