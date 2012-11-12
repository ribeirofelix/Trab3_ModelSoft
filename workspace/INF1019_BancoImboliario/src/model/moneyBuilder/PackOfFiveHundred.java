package model.moneyBuilder;


public class PackOfFiveHundred extends MoneyPack {

	@Override
	public void initializePack() {
		this.money = Money.FiveHundred;
		this.howMany = 2;
	}

}
