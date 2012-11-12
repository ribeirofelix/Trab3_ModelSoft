package model.moneyBuilder;




public class PackOfOne extends MoneyPack {

	@Override
	public void initializePack() {
		this.money = Money.One ;
		this.howMany = 8 ;

	}


}
