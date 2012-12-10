package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
	private ArrayList<House> houses = new ArrayList<>(40);
	private List<Chance> chances  = new ArrayList<Chance>();
	
	public void addOnChances(Chance chance){
		chances.add(chance);
	}
	
	// mock
	public Chance getGoToPrision (){
		for(Chance cha : this.chances){
			if (cha.getOperator().equalsIgnoreCase("wasArrested"))
				return cha;
		}
		
		return null;
	}
	
	public Chance getWayOut(){
		for(Chance cha : this.chances){
			if (cha.getOperator().equalsIgnoreCase("wayOut"))
				return cha;
		}
		
		return null;
	}
	
	// end mock
	
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
		
		if (removedChance.getOperator().equalsIgnoreCase("wayOut")){
			return removedChance;
		}
		
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
	

	public boolean canPlayerBuildHouseOnIt (int index, Player player){
		
		ICard currentHouse = this.houses.get(index).getCard();
		
		if (currentHouse instanceof PropertyTerrain){
			boolean itCan = true;
			String currentHouseGroup = ((PropertyTerrain) currentHouse).getGroup();
			
			for (int idx = 0; idx < this.houses.size(); idx++){
				currentHouse = this.houses.get(idx).getCard();
				
				if (currentHouse instanceof PropertyTerrain){
					/* If card is from the same group */
					if (currentHouseGroup.equalsIgnoreCase(((PropertyTerrain) currentHouse).getGroup())){
						
						/* Check if player doesn't owns it*/
						if (player.getPivot() != ((PropertyTerrain) currentHouse).getPlayerOwner().getPivot()){
							itCan = false;
						}
					}
				}/*if*/
				
			}/*for*/
			
			return itCan;
		}
		
		return false;

	}
	
	public Property getPropertyByName(String name){
		
		
		for (House currHouse : this.houses) {
			
			if(currHouse.getCard() instanceof Property){
				if( ( (Property)currHouse.getCard() ).getName().equals(name) ){
					return  ( Property)currHouse.getCard();
				}
			}
		}
		return null;
		
	}
	
	public void setRaffledChanceOnChanceHouse(int positon , Chance chance){
		if(houses.get(positon).getCard() instanceof ChanceHouse){
			((ChanceHouse)houses.get(positon).getCard()).setRaffledChance(chance);
		}
	}
	
	public ActionOnHouse getActionOnHouse(Player player){
		return houses.get(player.getPosition()).getCard().action(player);
	}
}
