package model.moneyBuilder;

public class Bank {
	
	private MoneyPack moneyPack ;
	
	public void setMoneyPack(MoneyPack moneyPack ){
		this.moneyPack = moneyPack;
	}
	
	public void buildMoneyPack() {
		moneyPack.initializePack();		
	}
	

}
