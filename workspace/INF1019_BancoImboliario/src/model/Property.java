package model;


public abstract class Property {
	
	protected String name ;
	protected String imagePath;
	protected int mortgageValue;
	protected Player playerOwner;
	protected int price ;
	
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.name = imagePath.substring(imagePath.lastIndexOf("//")+1);
		this.imagePath = imagePath;
	}
	public int getMortgageValue() {
		return mortgageValue;
	}
	public void setMortgageValue(int mortgageValue){
		this.mortgageValue = mortgageValue;
	}
	public Player getPlayerOwner() {
		return playerOwner;
	}
	public void setPlayerOwner(Player playerOwner) {
		this.playerOwner = playerOwner;
	}
	public int getPrice() {
		return price;
	}
	public String getName(){
		return this.name;
	}

}
