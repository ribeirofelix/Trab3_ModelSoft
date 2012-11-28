package model;

import java.util.List;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import java.util.HashMap;


import model.money.*;

public class Player {
	private Pivot pivot;
	private Point playerPoint ;
	private int position;
	private ArrayList<Property> myProperties  ;
	private HashMap<Money , MoneyPack> myMoney = new HashMap<Money,MoneyPack>();
	
	public Pivot getPivot(){
		return this.pivot;
	}
	
	public Player (Pivot pivot , Point initialPositon ){
		this.pivot = pivot;
		this.playerPoint = initialPositon;
		this.position = 0;
		
		/* Creating money */
		myMoney.put(Money.FiveHundred ,new PackOfFiveHundred());
		myMoney.put(Money.Hundred,new PackOfHundred());
		myMoney.put(Money.Fifty,new PackOfFifty());
		myMoney.put(Money.Ten,new PackOfTen());
		myMoney.put(Money.Five,new PackOfFive());
		myMoney.put(Money.One,new PackOfOne());
		
		myProperties = new ArrayList<Property>();
	
	}
	
	public String getNumberOfHousesOrHotel(ICard property){
			
		for (Property pro : this.myProperties){
			
			if (pro.equals(property) && property instanceof PropertyTerrain){
				return ((PropertyTerrain)property).getNumberOfHouseOrHotel();
			}
		}		
		
		return "";
	}
	
	public int getAmountOfMoney (){
		int amount = 0 ;
		for (MoneyPack pack : this.myMoney.values()) {
			amount += pack.getAmount();
		}
		return amount;
	}
	
	public Point getPlayerPoint() {
		return playerPoint;
	}

	public void setPlayerPoint(Point playerPoint) {
		this.playerPoint = playerPoint;
	}
	
	public void walkOnePosition (){
		this.position ++;
		
		if (this.position > 39){
			this.position = 0;
		}
	}
	
	public int getPosition(){
		return this.position;
	}
	
	public boolean buyProperty(Property property){
		
		if(property.getPrice() <= getAmountOfMoney()  && property.getPlayerOwner() == null){
			myProperties.add(property);
		
			property.setPlayerOwner(this);
			
			removeMoney(property.getPrice());
			return true;
			
		}
		
		return false;
	}

	public boolean hasProperty(Property property){
		
		if (property == null || this.myProperties.size() == 0){
			return false;
		}
		
		for (Property pro : this.myProperties){
			if (property.equals(pro)){
				return true;
			}
		}
		
		return false;
	}
	
	public boolean removeMoney(int howMuch){
		
		if(howMuch <= getAmountOfMoney() ){
			
			/* Put values from HashMap<MoneyPack> in a LinkedList to
			 * sort it .*/
			List<MoneyPack> listPackMoney = new LinkedList<MoneyPack>( this.myMoney.values() );
			Collections.sort(listPackMoney,new Comparator<MoneyPack>() {
				public int compare(MoneyPack o1, MoneyPack o2) {
		               return ((Comparable) ((MoneyPack) (o2)).getMoneyType().getValue() )
		              .compareTo(((MoneyPack) (o1)).getMoneyType().getValue() );
		          }
			});
			
			/* Iterate over the list to remove the money */
			for (MoneyPack pack  : listPackMoney) {
				
				
				while( ( howMuch / pack.getMoneyType().getValue() ) <= pack.getHowManyNote() && 
						pack.getAmount() > 0 && ( howMuch / pack.getMoneyType().getValue() ) >= 1   ){
					
					int removed ;
					if ( ( removed = pack.removeMoney(howMuch / pack.getMoneyType().getValue() ) ) > 0 ){
						howMuch -= pack.getMoneyType().getValue() * removed;			
					}
					
				}
				
			}
			
			/* Verify if has some value to pay. If true , exchange money*/
			if(howMuch > 0 ){
				if(howMuch < 5)
					this.exchangeMoney(Money.Five);	
				
				else if (howMuch < 10)				
					this.exchangeMoney(Money.Ten);
				
				else if(howMuch < 50)				
					this.exchangeMoney(Money.Fifty);
				
				else if(howMuch < 100)				
					this.exchangeMoney(Money.Hundred);
				
				else if(howMuch < 500)				
					this.exchangeMoney(Money.FiveHundred);
				
				removeMoney(howMuch);
				
			}
			else{
				return true;
			}
			
		}
		
		return false ;
	}
	
	public void putMoney(int howMuch){
		/* Put values from HashMap<MoneyPack> in a LinkedList to
		 * sort it .*/
		List<MoneyPack> listPackMoney = new LinkedList<MoneyPack>( this.myMoney.values() );
		Collections.sort(listPackMoney,new Comparator<MoneyPack>() {
			public int compare(MoneyPack o1, MoneyPack o2) {
	               return ((Comparable) ((MoneyPack) (o2)).getMoneyType().getValue() )
	              .compareTo(((MoneyPack) (o1)).getMoneyType().getValue() );
	          }
		});
		
		/* Iterate over the list to remove the money */
		for (MoneyPack pack  : listPackMoney) {
			
			
			while(  ( howMuch / pack.getMoneyType().getValue() ) >= 1   ){
				
				int putted ;
				if ( ( putted = pack.putMoney( (howMuch / pack.getMoneyType().getValue() ) ) ) > 0 ){
					howMuch -= pack.getMoneyType().getValue() * putted;			
				}
				
			}
			
		}
	}
	
	private void exchangeMoney(Money moneyToTrade){
		MoneyPack pack = this.myMoney.get(moneyToTrade);
		
		if(pack.getMoneyType()  == moneyToTrade){
			switch (moneyToTrade) {
			case Five:
				pack.removeMoney(1);
				this.myMoney.get(Money.One).putMoney(5);
				break;
			case Ten:
				pack.removeMoney(1);
				this.myMoney.get(Money.Five).putMoney(2);
				
				break;
			case Fifty:
				pack.removeMoney(1);
				this.myMoney.get(Money.Ten).putMoney(5);
			
				break;
			case Hundred:
				pack.removeMoney(1);
				this.myMoney.get(Money.Fifty).putMoney(2);
			
				break;
			case FiveHundred:
				pack.removeMoney(1);
				this.myMoney.get(Money.Hundred).putMoney(5);				
				break;
			default:
				break;
			}
		}		
	}

	public String[] getPropertiesNames(){
		List<String> names = new ArrayList<>();
		
		for (Property prop : this.myProperties) {
			names.add(prop.getName());
		}
		return  names.toArray(new String[0]);
	}
	
	public void removeProperty(Property propertyToRemove){
		if(hasProperty(propertyToRemove)){
			this.myProperties.remove(propertyToRemove);
		}
		
	}

	public void addProperty(Property property){
		this.myProperties.add(property);
	}

}
