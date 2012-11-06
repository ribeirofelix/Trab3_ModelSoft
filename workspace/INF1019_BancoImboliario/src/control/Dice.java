package control;

import java.util.Random;
	
public class Dice {
	private Random dice;
	
	public Dice (){
		this.dice = new Random (System.nanoTime());
	}
	
	public int rollTheDice (){	
		return dice.nextInt(5)+1;
	}
}
