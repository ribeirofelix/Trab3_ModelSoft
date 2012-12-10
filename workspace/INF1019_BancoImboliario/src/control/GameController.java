package control;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import model.ActionOnHouse;
import model.Board;
import model.Chance;
import model.ICard;
import model.Player;
import model.Property;
import model.PropertyCompany;
import model.PropertyTerrain;

public class GameController implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (ActionCommand.valueOf(e.getActionCommand())) {
		case RollDices:
			rollDiceAction();
			break;
		case BuyProperty:
			buyProperty();
			break;
		case BuildHouse:
			buildHouse();
			break;
		case ChooseNegociationType:
			Main.updateNegotiablesProperties();
			break;
		case ShowPropertiesOfSeller:
			Main.showSellerProperties();
			break;
		case FinalizeNegociation:
			
			Main.negotiateProperty();
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
		Main.removeMeFromNegociation();
		
		int manuallyRoll = Main.getManuallyRoll(); 
		int numOfHouses ;
		
		
		Player currentPlayer = Main.getCurrentPlayer();	
		int rollTwo, rollOne;
		
		/*Decide if the roll is manually or not. */
		if(manuallyRoll == 0){
			Dice dice = new Dice();
						
			/* Roll the dice twice */
			rollOne = dice.rollTheDice();
			rollTwo = dice.rollTheDice();
			numOfHouses = rollOne + rollTwo;
			
			/* If the rolls was the same. Play Again */
			if(rollOne == rollTwo)
				Main.repeatPlayer();
			
			Main.showRollDiceAndPlayerStatus(rollOne, rollTwo);
		}
		else{
			
			numOfHouses = manuallyRoll;
			Main.showRollDiceAndPlayerStatus(manuallyRoll/2, manuallyRoll/2);
			rollOne = rollTwo = manuallyRoll/2;
		}
		
	
		
		/* See if player is on prision*/
		if (currentPlayer.getIsPlayerOnPrision()){
			
			//currentPlayer.setWayOutPrisionCard(Main.getBoardGame().getWayOut());
			
			/* If rolls the same number */
			if (rollOne == rollTwo){
				currentPlayer.freePlayer();
			}
			
			/* Player has Free pass */
			else if (currentPlayer.HasWayOutPass()){
				Main.getBoardGame().addOnChances(currentPlayer.getWayOutPrisionCard());
				currentPlayer.freePlayer();
			}
			
			/* Player has already done the time */
			else if (Main.getRounds() - currentPlayer.getTurnThatPlayerWasArrested() > 3){
				currentPlayer.removeMoney(50);
				currentPlayer.freePlayer();
			}
				
			else{ 
				Main.updateFrame();
				Main.ShowCurrentCard();
				return;
			}
		}
		
		/* Walk with the player */	
		currentPlayer.walk(numOfHouses);
			
		/* Do action in accordance with the house */
		ActionOnHouse action = Main.getBoardGame().getActionOnHouse(currentPlayer);
		doActionOnHouse(action, currentPlayer, numOfHouses);
		
		/* If player already owns the place 
		if (currentPlayer.hasProperty(Main.getBoardGame().getPropertyAt(currentPlayer.getPosition()))){
			
			 if the player can build a house 
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
				//Main.getBoardGame().getHouseOnThisPosition(currentPlayer.getPosition()).getCard().action(currentPlayer);
				if( Main.getBoardGame().isChance(currentPlayer.getPosition()) ){
					Chance cardChance = Main.getBoardGame().getOneChance();
					cardChance.action(currentPlayer);
					
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
		
		}*/
	
		Main.updateFrame();
		Main.ShowCurrentCard();
		
		
	}
	
	private void doActionOnHouse(ActionOnHouse action, Player currentPlayer, int diceRoll ){
		switch (action) {
		case CanBuildOnIt:
			Main.enableBuildHouseButton(true);
			Main.enableBuyPropertyButton(false);
			Main.updatePlayerStatus();
			break;

		case CanBuyIt:
			Main.enableBuyPropertyButton(true);
			Main.enableBuildHouseButton(false);
			break;
		case PayforIt:
			Property steppedProperty = Main.getBoardGame().getPropertyAt(currentPlayer.getPosition());
			if(steppedProperty instanceof PropertyTerrain)
				((PropertyTerrain)steppedProperty).chargeMoney(currentPlayer);
			else
				((PropertyCompany)steppedProperty).chargeMoney(currentPlayer, diceRoll);
			Main.updatePlayerStatus();
			Main.enableBuildHouseButton(false);
			Main.enableBuyPropertyButton(false);
			break;
		case GetChance:
			Chance cardChance = Main.getBoardGame().getOneChance();
			Main.getBoardGame().setRaffledChanceOnChanceHouse(currentPlayer.getPosition(), cardChance);
			cardChance.action(currentPlayer);
			Main.updatePlayerStatus();
			Main.enableBuildHouseButton(false);
			Main.enableBuildHouseButton(false);
		case NothingToDo:
			Main.enableBuildHouseButton(false);
			Main.enableBuildHouseButton(false);
			break;
		default:
			break;
		}
	}
	
}
