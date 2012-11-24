package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
	private ArrayList<House> houses = new ArrayList<>(40);
	private List<Chance> chances  = new ArrayList<Chance>();
	
	public Board() {
		
		FactoryICard factCards = new FactoryICard();
		factCards.createAllTheCards();
		int inx = 0;
		for (ICard card : factCards.getBoardCards()) {
			houses.add(new House( inx, card));
			inx++;
		}
		for (ICard cardChance : factCards.getChangeCards()) {
			chances.add( (Chance )cardChance);
		}
		Collections.shuffle(chances);
		
	}
	
	public House getHouseOnThisPosition (int index){
		return houses.get(index);
	}
	
	public Property getPropertyAt(int position) {
		ICard cardAtHouse = houses.get(position).getCard()	;
		if(cardAtHouse instanceof Property){
			return ( Property)cardAtHouse;
		}
		return null;
	}
	
	public boolean isPurchasable(int position){
		ICard cardAtHouse = houses.get(position).getCard() ;
		if (cardAtHouse  instanceof Property){
			 if( ( (Property) cardAtHouse ).getPlayerOwner() == null ){
				 return true;
			 }
		}
		return false;
	}
	
	public Chance getOneChance(){
		Chance removedChance = chances.remove(0);
		chances.add(removedChance);
		return removedChance;
	}
	public boolean isChance(int position){
		ICard cardAtHouse = houses.get(position).getCard() ;
		if (cardAtHouse  instanceof Chance){
			 return true;
		}
		return false;
	}
	
	public void payForIt(int position , Player player){
		
		ICard card = houses.get(position).getCard();
		
		if(card instanceof Property){
			Property property = (Property)card;
			boolean haveToPay = property.getPlayerOwner() == player ? false : true ;
			if(haveToPay){
				
			}
		}
		
		
	}
	
}
