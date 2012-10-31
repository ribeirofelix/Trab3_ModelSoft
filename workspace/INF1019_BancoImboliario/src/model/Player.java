package model;

public class Player {
	private Pivot pivot;
	//money;
	//property;
	
	public Pivot getPivot(){
		return this.pivot;
	}
	
	public Player (Pivot pivot){
		this.pivot = pivot;
		
		//money = new (all the money)
		//property = new list;
	}
	
	// change
	public double getAmountOfMoney (){
		return 0;
	}	
}
