package model.moneyBuilder;


public class PackOfTen extends MoneyPack {

	@Override
	public void initializePack() {
		this.money = Money.Ten;
		this.howMany = 10;

	}

	

}
