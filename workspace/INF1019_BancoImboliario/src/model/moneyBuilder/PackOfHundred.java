package model.moneyBuilder;


public class PackOfHundred extends MoneyPack {

	@Override
	public void initializePack() {
		this.money = Money.Hundred;
		this.howMany = 8;

	}


}
