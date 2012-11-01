package model;

import java.awt.Point;
import java.util.ArrayList;

public class Player {
	private Pivot pivot;
	private Point playerPoint ;
	private ArrayList<ArrayList<Money>> lsMoney ;
	
	public Pivot getPivot(){
		return this.pivot;
	}
	
	public Player (Pivot pivot , Point initialPositon ){
		this.pivot = pivot;
		this.playerPoint = initialPositon;
		this.lsMoney = new ArrayList<>();
		
		
		
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
}
