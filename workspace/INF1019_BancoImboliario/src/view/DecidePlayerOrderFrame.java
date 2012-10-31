package view;

import java.awt.Dimension;
import javax.swing.JFrame;

public class DecidePlayerOrderFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private final Dimension dimension;
	
	public DecidePlayerOrderFrame (int width, int height, String title){
		super(title);
		
		this.dimension = new Dimension(width, height);
		this.setPreferredSize(this.dimension);
		this.setLocationRelativeTo(null);
		
		this.pack();
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
}
	

