package control;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Board;
import model.Chance;
import model.ICard;
import model.Player;
import model.Property;

public class GameController implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "rollDices":
			rollDiceAction();
			break;
		case "buyProperty":
			buyProperty();
			break;
		default:
			break;
		}
		
	}

	private void buyProperty(){
		
		Player currentPleyer = Main.getCurrentPlayer();
		System.out.println(currentPleyer.getAmountOfMoney());
		Property propertyToBuy = Main.getBoardGame().getPropertyAt(currentPleyer.getPosition());
		
		currentPleyer.buyProperty(propertyToBuy);
		Main.updatePlayerStatus();
	}
	
	private void rollDiceAction() {
		
		/*If rollDice is clicked , have to set a next player to play. */
		Main.nextPlayer();		
		
		Dice dice = new Dice();
		int sizeOfWalk = 61;
		
		/* Roll the dice twice */
		int rollOne = dice.rollTheDice();
		int rollTwo = dice.rollTheDice();
		int numOfHouses = rollOne + rollTwo;
		
		
		Main.showRollDiceAndPlayerStatus(rollOne, rollTwo);

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
		
		if( Main.getBoardGame().isPurchasable( currentPlayer.getPosition() ) ){
			Main.enableBuyPropertyButton(true);
		}
		else{
			if( Main.getBoardGame().isChance(currentPlayer.getPosition()) ){
				Chance cardChance = Main.getBoardGame().getOneChance();
				 cardChance.setPlayerOnwer(currentPlayer);
				cardChance.action();
				Main.updatePlayerStatus();
			}
			Main.enableBuyPropertyButton(false);
		}
		
		Main.ShowCurrentCard();		
		Main.updateFrame();
	}
	
	
	
	
}
