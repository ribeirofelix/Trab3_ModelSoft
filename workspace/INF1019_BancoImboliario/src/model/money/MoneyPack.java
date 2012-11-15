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
	
	
	public int removeMoney(int howManyNotes){
		if(this.howMany == 0 || this.howMany < howManyNotes ){
			return 0;
		}
		
		this.howMany -= howManyNotes;		
		return howManyNotes;
	}
	
	public int putMoney(int howManyNotes) {
		this.howMany += howManyNotes;
		return howManyNotes;
	}
	
	public int getAmount(){
		return this.howMany * money.getValue() ;
	}
	
}
