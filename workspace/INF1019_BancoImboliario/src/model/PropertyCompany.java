package model;

public class PropertyCompany extends Property implements  ICard {

	private int multiplier;
	
	public PropertyCompany (String path , int mortgage ,int multiplier){
		this.multiplier = multiplier;
		this.price = mortgage * 2 ;
		super.setImagePath(path);
		super.setMortgageValue(mortgage);
		
	}
	

	
	public int multiplyDicePoints(int dicePoint) {
		return dicePoint * this.multiplier;
	}

	@Override
	public void action(Player playerHere) {
		// TODO Auto-generated method stub
		
	}

}
