package model;

public class PropertyCompany extends Property implements  ICard {

	private int multiplier;
	
	public PropertyCompany (String path , int mortgage ,int multiplier){
		this.multiplier = multiplier;
		this.price = mortgage * 2 ;
		this.mortgageValue = mortgage;
		this.imagePath = path;
	}
	
	@Override
	public void action() {
		// TODO Auto-generated method stub

	}
	
	public int multiplyDicePoints(int dicePoint) {
		return dicePoint * this.multiplier;
	}

}
