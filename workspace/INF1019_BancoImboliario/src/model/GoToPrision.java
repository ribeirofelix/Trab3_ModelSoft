package model;

import control.Main;

public class GoToPrision implements ICard {

	private String imagePath = "images//board cards//gotoprision.png";
	public static final int prisionPosition = 10 ;


	@Override
	public String getImagePath() {
		return imagePath;
	}

	@Override
	public ActionOnHouse action(Player playerHere) {
		playerHere.putPlayerOnPrision();
		playerHere.setTurnThatPlayerWasArrested(Main.getRounds());
		playerHere.setPosition(prisionPosition);
		return  ActionOnHouse.NothingToDo;
	}

}
