package model;

import control.Main;

public class Chance implements ICard {
	private Player playerOnwer;
	private String imagePath;
	private String operator;
	private int value;
	
	public Chance (String imagePath, String operator, int value){
		this.imagePath = imagePath;
		this.operator = operator;
		this.value = value;
	}
	
	
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Player getPlayerOnwer() {
		return playerOnwer;
	}

	public void setPlayerOnwer(Player playerOnwer) {
		this.playerOnwer = playerOnwer;
	}


	@Override
	public ActionOnHouse action(Player playerHere) {
		this.playerOnwer = playerHere;
		
		if (operator.equalsIgnoreCase("wayOut")){
			playerHere.setWayOutPrisionCard(this);
		}
		
		if (operator.equalsIgnoreCase("wasArrested")){
			playerHere.putPlayerOnPrision();
			playerHere.setTurnThatPlayerWasArrested(Main.getRounds());
			playerHere.setPosition(GoToPrision.prisionPosition);
		}
		
		else if (operator.equalsIgnoreCase("plus")){
			playerOnwer.putMoney(value);
		}
		else if (operator.equalsIgnoreCase("minus")){
			playerOnwer.removeMoney(this.value);
		}
		
		
		return ActionOnHouse.NothingToDo;
	}

}
