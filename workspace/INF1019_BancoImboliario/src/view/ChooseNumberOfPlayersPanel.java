package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import control.MonopolyMain;
import javax.swing.JPanel;

public class ChooseNumberOfPlayersPanel extends JPanel implements ActionListener  {
	
	private static final long serialVersionUID = 1L;

	public ChooseNumberOfPlayersPanel (Dimension dimension, Container ct){
		this.setSize(dimension);
		this.setLayout(null);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		switch (event.getActionCommand()){
		case "two": 
			MonopolyMain.setNumberOfPlayers(2); break;
		case "three": 
			MonopolyMain.setNumberOfPlayers(3); break;
		case "four": 
			MonopolyMain.setNumberOfPlayers(4); break;
		case "five": 
			MonopolyMain.setNumberOfPlayers(5); break;
		case "six": 
			MonopolyMain.setNumberOfPlayers(6); break;
		}		
		/* Back to main*/
	}
	
}
