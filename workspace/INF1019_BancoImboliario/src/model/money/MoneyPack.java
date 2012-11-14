package model.money;



public abstract class MoneyPack {
	
	protected Money money  ;
	protected int howMany ;
	
	
	public Money getMoneyType(){
		return money;
	}
	
	public int getHowManyNote (){
		return howMany;
	}
	
	
	public boolean removeMoney(int howManyNotes){
		if(this.howMany == 0 || this.howMany < howManyNotes ){
			return false;
		}
		
		this.howMany -= howManyNotes;		
		return true;
	}
	
	public void putMoney(int howManyNotes) {
		this.howMany += howManyNotes;
	}
	
	public int getAmount(){
		return this.howMany * money.getValue() ;
	}
	
}
