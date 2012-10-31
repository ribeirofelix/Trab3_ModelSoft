package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class DecidePlayerOrderPanel extends JPanel implements ActionListener{

	
	private static final long serialVersionUID = 1L;

	public DecidePlayerOrderPanel (Dimension dimension, Container ct){
		this.setSize(dimension);
		this.setLayout(null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
}
