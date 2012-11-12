package model;

import java.awt.Point;

public class Player {
	private Pivot pivot;
	private Point playerPoint ;
	private int position;
	
	public Pivot getPivot(){
		return this.pivot;
	}
	
	public Player (Pivot pivot , Point initialPositon ){
		this.pivot = pivot;
		this.playerPoint = initialPositon;
		this.position = 0;
		
		
		//money = new (all the money)
		//property = new list;
	}
	
	// change
	public double getAmountOfMoney (){
		return 0;
	}
	
	public Point getPlayerPoint() {
		return playerPoint;
	}

	public void setPlayerPoint(Point playerPoint) {
		this.playerPoint = playerPoint;
	}
	
	public void walkOnePosition (){
		this.position ++;
		
		if (this.position > 39){
			this.position = 0;
		}
	}
	
	public int getPosition(){
		return this.position;
	}
	
	public void initialMoney(){
				
		
	}
}
