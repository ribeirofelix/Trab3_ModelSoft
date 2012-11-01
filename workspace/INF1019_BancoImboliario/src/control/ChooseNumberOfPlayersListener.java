package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseNumberOfPlayersListener implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent event) {
		switch (event.getActionCommand()){
		case "two": 
			Main.setNumberOfPlayers(2); break;
		case "three": 
			Main.setNumberOfPlayers(3); break;
		case "four": 
			Main.setNumberOfPlayers(4); break;
		case "five": 
			Main.setNumberOfPlayers(5); break;
		case "six": 
			Main.setNumberOfPlayers(6); break;
		}		
		Main.initializeNumberOfPlayers();
	}
	

}
