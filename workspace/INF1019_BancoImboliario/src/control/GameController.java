package control;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Player;

public class GameController implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Dice dice = new Dice();
		int numOfHouses = dice.rollTheDice();
		numOfHouses += dice.rollTheDice();
		int sizeOfWalk = 67 ;
		// FIX IT
		System.out.println(numOfHouses);
		
		Player currentPlayer = Main.getCurrentPlayer();		
		
		int xCoordinate, yCoordinate; 
		for (int i = 0; i < numOfHouses ; i++) {
			
			xCoordinate = currentPlayer.getPlayerPoint().x;
			yCoordinate = currentPlayer.getPlayerPoint().y;
			
			if (currentPlayer.getPosition() >= 0 && currentPlayer.getPosition() < 10){		
				
				currentPlayer.setPlayerPoint(new Point(xCoordinate, yCoordinate+sizeOfWalk));				
			}
			else if (currentPlayer.getPosition() >= 10 && currentPlayer.getPosition() < 20){
				
				currentPlayer.setPlayerPoint(new Point(xCoordinate-sizeOfWalk+6, yCoordinate));
			}
			else if (currentPlayer.getPosition() >= 20 && currentPlayer.getPosition() < 30){
				
				currentPlayer.setPlayerPoint(new Point(xCoordinate, yCoordinate-sizeOfWalk));
			}
			else if (currentPlayer.getPosition() >= 30 && currentPlayer.getPosition() < 40){
				
				currentPlayer.setPlayerPoint(new Point(xCoordinate+sizeOfWalk-6, yCoordinate));
			}
			
			currentPlayer.walkNPositions(1);
		}
		
		
		Main.nextPlayer();
		Main.updateFrame();
	}
}
