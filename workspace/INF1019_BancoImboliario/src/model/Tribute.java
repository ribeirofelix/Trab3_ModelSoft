package model;

public class Tribute implements ICard {

	private int value ;
	private String imagePath = "images//board cards//tribute.png";
	
	public Tribute(int value) {
		this.value = value;
	}
	
	@Override
	public String getImagePath() {
		return imagePath;
	}

	@Override
	public ActionOnHouse action(Player playerHere) {
		playerHere.removeMoney(value);
		return ActionOnHouse.NothingToDo;
		
	}

}
