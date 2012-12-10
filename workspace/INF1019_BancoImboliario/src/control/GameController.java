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
			if(Main.getNumberOfPlayers()==1)
				return ;
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
			Main.updatePlayerStatus();
			Main.enableBuildHouseButton(false);
		}
	}

	private void buyProperty(){
		
		Player currentPleyer = Main.getCurrentPlayer();
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
		
		
		Main.updateFrame();
		Main.ShowCurrentCard();
		
		
	}
	
	private void doActionOnHouse(ActionOnHouse action, Player currentPlayer, int diceRoll ){
		switch (action) {
		case AlreadyOwnsIt:
			if( Main.getBoardGame().canPlayerBuildHouseOnIt(currentPlayer.getPosition()	, currentPlayer) )
				Main.enableBuildHouseButton(true);
			else
				Main.enableBuildHouseButton(false);
			Main.enableBuyPropertyButton(false);
			Main.updatePlayerStatus();
			break;

		case CanBuyIt:
			Main.enableBuyPropertyButton(true);
			Main.enableBuildHouseButton(false);
			break;
		case PayforIt:
			Property steppedProperty = Main.getBoardGame().getPropertyAt(currentPlayer.getPosition());
			boolean couldPay = false ;
			if(steppedProperty instanceof PropertyTerrain)
				couldPay = ((PropertyTerrain)steppedProperty).chargeMoney(currentPlayer);
			else
				couldPay = ((PropertyCompany)steppedProperty).chargeMoney(currentPlayer, diceRoll);
			if (!couldPay){
				Main.bankruptcyPlayer(currentPlayer);
				return ;
			}
			Main.updatePlayerStatus();
			Main.updatePlayerStatus(steppedProperty.getPlayerOwner());
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
			Main.updatePlayerStatus();
			Main.enableBuildHouseButton(false);
			Main.enableBuildHouseButton(false);
			break;
		default:
			break;
		}
	}
	
}
