package model;

public class PropertyCompany extends Property implements  ICard {

	private int multiplier;
	
	public PropertyCompany (int multiplier){
		this.multiplier = multiplier;
		this.price = 200;
	}
	
	@Override
	public void action() {
		// TODO Auto-generated method stub

	}
	
	public int multiplyDicePoints(int dicePoint) {
		return dicePoint * this.multiplier;
	}

}
