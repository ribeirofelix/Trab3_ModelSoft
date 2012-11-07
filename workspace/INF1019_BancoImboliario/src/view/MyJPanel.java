package view;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;


import javax.swing.JPanel;

public class MyJPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	
	public MyJPanel(Point point , Dimension dim, Frame frame) {
		super();
		setBounds(point.x, point.y, dim.width, dim.height);
		setLayout(null);
		setVisible(true);
		setPreferredSize(dim);
		frame.add(this);
		
	}
	
	

	

}
