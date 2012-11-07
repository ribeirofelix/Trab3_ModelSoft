package control;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Player;

public class GameController implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Dice dice = new Dice();
		int sizeOfWalk = 61;
		
		/* Roll the dice twice */
		int numOfHouses = dice.rollTheDice();
		numOfHouses += dice.rollTheDice();
		
		
		// colocar dado na tela
		System.out.println(numOfHouses);
		
		Player currentPlayer = Main.getCurrentPlayer();		
		
		int xCoordinate, yCoordinate; 
		for (int i = 0; i < numOfHouses ; i++) {
			
			xCoordinate = currentPlayer.getPlayerPoint().x;
			yCoordinate = currentPlayer.getPlayerPoint().y;
			
			if (currentPlayer.getPosition() >= 0 && currentPlayer.getPosition() < 10){		
				
				currentPlayer.setPlayerPoint(new Point(xCoordinate, yCoordinate + sizeOfWalk));				
			}
			else if (currentPlayer.getPosition() >= 10 && currentPlayer.getPosition() < 20){
				
				currentPlayer.setPlayerPoint(new Point(xCoordinate - sizeOfWalk, yCoordinate));
			}
			else if (currentPlayer.getPosition() >= 20 && currentPlayer.getPosition() < 30){
				
				currentPlayer.setPlayerPoint(new Point(xCoordinate, yCoordinate - sizeOfWalk));
			}
			else if (currentPlayer.getPosition() >= 30 && currentPlayer.getPosition() <= 39){
				
				currentPlayer.setPlayerPoint(new Point(xCoordinate + sizeOfWalk, yCoordinate));
			}
			
			currentPlayer.walkOnePosition();
		}
		
		Main.nextPlayer();
		Main.updateFrame();
	}
}
