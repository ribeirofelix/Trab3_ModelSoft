package model;

public class GoToPrision implements ICard {

	private String imagePath = "images//board cards//gotoprision.png";
	private final int prisionPosition = 10 ;


	@Override
	public String getImagePath() {
		return imagePath;
	}

	@Override
	public ActionOnHouse action(Player playerHere) {
		playerHere.setPosition(prisionPosition);
		return  ActionOnHouse.NothingToDo;
	}

}
