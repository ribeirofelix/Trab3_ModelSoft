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
	public ActionOnHouse action(Player playerHere) {
		if(playerHere.hasProperty(this)){
			return ActionOnHouse.NothingToDo;
		}
		else{
			if(this.playerOwner == null){
				return ActionOnHouse.CanBuyIt;
			}
		}
		return ActionOnHouse.PayforIt;
	}

	public boolean chargeMoney(Player playerToCharge, int diceRoll){
		
		if(playerToCharge.removeMoney(multiplyDicePoints(diceRoll))){
		
			this.playerOwner.putMoney(multiplyDicePoints(diceRoll));
			return true;
		}
		return false ;
	}
}
