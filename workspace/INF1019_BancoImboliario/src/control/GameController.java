package control;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Chance;
import model.ICard;
import model.Player;
import model.Property;

import model.PropertyCompany;
import model.PropertyTerrain;

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
		case "buildHouse":
			buildHouse();
			break;
		default:
			break;
		}
		
	}

	private void buildHouse() {
		
		ICard playerCard = Main.getBoardGame().getHouseOnThisPosition(Main.getCurrentPlayer().getPosition()).getCard();
		
		if( playerCard instanceof PropertyTerrain){
			if (!((PropertyTerrain) playerCard).buildHouse()){
				JOptionPane.showMessageDialog(Main.getGameFrame(), "Jogador não tem dinheiro suficiente", "Opss", JOptionPane.WARNING_MESSAGE);
			}
			
			Main.enableBuildHouseButton(false);
		}
	}

	private void buyProperty(){
		
		Player currentPleyer = Main.getCurrentPlayer();
		System.out.println(currentPleyer.getAmountOfMoney());
		Property propertyToBuy = Main.getBoardGame().getPropertyAt(currentPleyer.getPosition());
		
		currentPleyer.buyProperty(propertyToBuy);
			
		Main.updatePlayerStatus();
		Main.enableBuyPropertyButton(false);
	}
	
	private void rollDiceAction() {
		
		/*If rollDice is clicked , have to set a next player to play. */
		Main.nextPlayer();		
		
		Main.updatePlayerStatus(null);
		
		
		Dice dice = new Dice();
		int sizeOfWalk = 61;
		
		/* Roll the dice twice */
		int rollOne = dice.rollTheDice();
		int rollTwo = dice.rollTheDice();
		int numOfHouses = rollOne + rollTwo;
		
		// se os dados forem iguais setar o nextPlayer para o player atual. ele joga de novo
		
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
		
		
		/* If player already owns the place */
		if (currentPlayer.hasProperty(Main.getBoardGame().getPropertyAt(currentPlayer.getPosition()))){
			
			/* if the player can build a house */
			if(Main.getBoardGame().canPlayerBuildHouseOnIt(currentPlayer.getPosition(), currentPlayer)){
				
				Main.enableBuildHouseButton(true);
			}
			else{
				Main.enableBuildHouseButton(false);
			}
		}
		else{
	
			if( Main.getBoardGame().isPurchasable( currentPlayer.getPosition() ) ){
				Main.enableBuyPropertyButton(true);
			}
			else{	
				if( Main.getBoardGame().isChance(currentPlayer.getPosition()) ){
					Chance cardChance = Main.getBoardGame().getOneChance();
					cardChance.setPlayerOnwer(currentPlayer);
					cardChance.action();
					
				}
				else{
					Property steppedProperty = Main.getBoardGame().getPropertyAt(currentPlayer.getPosition());
					
					if(steppedProperty != null && steppedProperty.getPlayerOwner() != currentPlayer ){
					
						int howManyToPay = 0 ;
						if(steppedProperty instanceof PropertyCompany){
							howManyToPay = ((PropertyCompany)steppedProperty).multiplyDicePoints( numOfHouses) ;
						}
						else{						
							howManyToPay = ((PropertyTerrain)steppedProperty).getRentValue() ;
						}
						currentPlayer.removeMoney(howManyToPay);
						
						steppedProperty.getPlayerOwner().putMoney(howManyToPay);
						Main.updatePlayerStatus(steppedProperty.getPlayerOwner());
					}
					
				}
				Main.enableBuyPropertyButton(false);
				Main.updatePlayerStatus();
			}
		
		}
	
		Main.updateFrame();
		Main.ShowCurrentCard();
		
		
	}
	
	
	
}
