package control;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import model.ActionOnHouse;
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
				JOptionPane.showMessageDialog(Main.getGameFrame(), "Jogador n�o tem dinheiro suficiente", "Opss", JOptionPane.WARNING_MESSAGE);
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
		
		/*Decide if the roll is manually or not. */
		if(manuallyRoll == 0){
			Dice dice = new Dice();
			
			
			/* Roll the dice twice */
			int rollOne = dice.rollTheDice();
			int rollTwo = dice.rollTheDice();
			numOfHouses = rollOne + rollTwo;
			
			/* If the rolls was the same. Play Again */
			if(rollOne == rollTwo)
				Main.repeatPlayer();
			
			Main.showRollDiceAndPlayerStatus(rollOne, rollTwo);
		}
		else{
			numOfHouses = manuallyRoll;
			Main.showRollDiceAndPlayerStatus(manuallyRoll/2, manuallyRoll/2);
		}
		
		/* Walk with the player */
		Player currentPlayer = Main.getCurrentPlayer();		
		currentPlayer.walk(numOfHouses);
				
		
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
		
		}
	
		Main.updateFrame();
		Main.ShowCurrentCard();
		
		
	}
	
	private void doActionOnHouse(ActionOnHouse action, Player currentPlayer, int diceRoll ){
		switch (action) {
		case CanBuildOnIt:
			Main.enableBuildHouseButton(true);
			Main.enableBuyPropertyButton(false);
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
			break;
		case NothingToDo:
			break;
		default:
			break;
		}
	}
	
}
