package model;


public class Profit implements ICard {

	private int value ;
	private String imagePath = "images//board cards//profit.png";
	
	public Profit(int value) {
		this.value = value;
	}
	
	@Override
	public String getImagePath() {
		return imagePath;
	}
	@Override
	public ActionOnHouse action(Player playerHere) {
		playerHere.putMoney(value);
		return ActionOnHouse.NothingToDo;
		
	}
	
	

}
