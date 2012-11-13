package model;

import java.awt.Point;
import java.util.ArrayList;

import model.money.MoneyPack;
import model.money.PackOfFifty;
import model.money.PackOfFive;
import model.money.PackOfFiveHundred;
import model.money.PackOfHundred;
import model.money.PackOfOne;
import model.money.PackOfTen;

public class Player {
	private Pivot pivot;
	private Point playerPoint ;
	private int position;
	private ArrayList<Property> myProperties ;
	private ArrayList<MoneyPack> myMoney = new ArrayList(7);
	
	public Pivot getPivot(){
		return this.pivot;
	}
	
	public Player (Pivot pivot , Point initialPositon ){
		this.pivot = pivot;
		this.playerPoint = initialPositon;
		this.position = 0;
		
		/* Creating money */
		myMoney.add(new PackOfFiveHundred());
		myMoney.add(new PackOfHundred());
		myMoney.add(new PackOfFifty());
		myMoney.add(new PackOfTen());
		myMoney.add(new PackOfFive());
		myMoney.add(new PackOfOne());
		
		
	
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
