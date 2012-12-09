package model;


public class Profit implements ICard {

	private int value ;
	private String imagePath = "images//board cards//profit.png";
	
	public Profit(int value) {
		this.value = value;
	}
	@Override
	public void action() {
		// TODO Auto-generated method stub

	}
	@Override
	public String getImagePath() {
		return imagePath;
	}
	
	

}
