package model;

public class Tribute implements ICard {

	private int value ;
	private String imagePath = "images//board cards//tribute.png";
	
	public Tribute(int value) {
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
