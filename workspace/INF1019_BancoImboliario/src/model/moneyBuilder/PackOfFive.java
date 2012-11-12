package model.moneyBuilder;


public class PackOfFive extends MoneyPack {

	@Override
	public void initializePack() {
		this.money = Money.Five;
		this.howMany = 10;

	}


}
