package control;

import java.awt.Dimension;
import java.util.EnumSet;
import view.*;
import model.*;

public class MonopolyMain {
	
	/* Frames */
	private static FirstFrame chooseNumberOfPlayers;
	private static DecidePlayerOrderFrame decidePlayerOrderFrame ; 
	
	
	private static int numberOfPlayers;
	private static Player[] playersArray;
	/* If position x contains number y, it means that the player y (playersArray) is the x st 
	 * player to play */
	private static int[] playersOrder;
	
	public static int getNumberOfPlayers(){
		return numberOfPlayers;
	}
	
	public static void setNumberOfPlayers(int number){
		numberOfPlayers = number;
	}
	
	public static void main(String[] args) {
		chooseNumberOfPlayers = new FirstFrame(340, 275, "Escolha o numero de jogadores");
		
		bringChooseNumberOfPlayersPanel ();		
		chooseNumberOfPlayers.exitFrame();
		
		/* Fill playersArray*/
		Player[] playersArray = new Player[numberOfPlayers];
		Object[] pivots = EnumSet.allOf(Pivot.class).toArray();
		
		for (int i = 0; i < numberOfPlayers; i++){
			playersArray[i] = new Player( (Pivot) pivots[i]);
		}
		
		decidePlayerOrderFrame = new DecidePlayerOrderFrame(600, 500, "Role os dados!");
		bringDecidePlayerOrderPanel();
		
		
	}

	private static void bringChooseNumberOfPlayersPanel() {
		ChooseNumberOfPlayersPanel chooseNOFP = new ChooseNumberOfPlayersPanel(new Dimension(300, 210), chooseNumberOfPlayers);
		
		MyButton button2 = new MyButton("2", chooseNOFP);
		button2.addActionListener(chooseNOFP);
		button2.setActionCommand("two");
		button2.setBounds(32, 32, 79, 79);
		
		MyButton button3 = new MyButton("3", chooseNOFP);
		button3.addActionListener(chooseNOFP);
		button3.setActionCommand("three");
		button3.setBounds(127, 32, 79, 79);
		
		MyButton button4 = new MyButton("4", chooseNOFP);
		button4.addActionListener(chooseNOFP);
		button4.setActionCommand("four");
		button4.setBounds(221, 32, 79, 79);
		
		MyButton button5 = new MyButton("5", chooseNOFP);
		button5.addActionListener(chooseNOFP);
		button5.setActionCommand("five");
		button5.setBounds(32, 127, 79, 79);
		
		MyButton button6 = new MyButton("6", chooseNOFP);
		button6.addActionListener(chooseNOFP);
		button6.setActionCommand("six");
		button6.setBounds(127, 127, 79, 79);
		
		chooseNOFP.add(button2);
		chooseNOFP.add(button3);
		chooseNOFP.add(button4);
		chooseNOFP.add(button5);
		chooseNOFP.add(button6);
		
		chooseNumberOfPlayers.add(chooseNOFP);
		chooseNumberOfPlayers.setVisible(true);
		
	}

	public static void bringDecidePlayerOrderPanel() {
		DecidePlayerOrderPanel decidePOP = new DecidePlayerOrderPanel(new Dimension(300, 210), decidePlayerOrderFrame);
		
		MyButton button2 = new MyButton("2", decidePOP);
		button2.addActionListener(decidePOP);
		button2.setActionCommand("two");
		button2.setBounds(32, 32, 79, 79);
		
		
	}

}
