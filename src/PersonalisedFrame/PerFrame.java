package PersonalisedFrame;

import javax.swing.JFrame;

public class PerFrame extends JFrame{
	
	public PerFrame(int wide, int height, String title, boolean isMain) {
		
		setSize(wide, height);
		setTitle(title);
		setLocationRelativeTo(null);
		
		if (isMain) setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
 