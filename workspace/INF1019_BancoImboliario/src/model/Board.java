package model;

import java.util.ArrayList;

public class Board {
	private ArrayList<House> houses = new ArrayList<>(40);
	
	
	public Board() {
		for (int i = 0; i < 20 ; i++) {
			
			houses.add(new House(i,new PropertyCompany(i) ));
		}
		for (int i = 0; i < 20 ; i++) {
			
			houses.add(new House(i,new Chance() ));
		}
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
}
