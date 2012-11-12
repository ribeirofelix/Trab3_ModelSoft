package model;

public abstract class Property {
	
	private String imagePath;
	private int mortgageValue ;
	private Player playerOwner ;
	
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public int getMortgageValue() {
		return mortgageValue;
	}
	public void setMortgageValue(int mortgageValue) {
		this.mortgageValue = mortgageValue;
	}
	public Player getPlayerOwner() {
		return playerOwner;
	}
	public void setPlayerOwner(Player playerOwner) {
		this.playerOwner = playerOwner;
	}

}
