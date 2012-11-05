package control;

import java.awt.Dimension;
import java.awt.Panel;
import java.awt.Point;
import java.util.ArrayList;
import java.util.EnumSet;
import view.*;
import model.*;

public class Main {
	
	/* Frames */
	private static FirstFrame gameFrame;
	
	private static int currentPlayer = 0;
	private static int numberOfPlayers;
	private static ArrayList<Player> playersArray;
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
		gameFrame = new FirstFrame(340, 275, "Escolha o numero de jogadores");
		bringChooseNumberOfPlayersPanel ();		
		
	
		
	}

	public static void initializeNumberOfPlayers() {
		/* Fill playersArray*/
		playersArray = new ArrayList<>();
		Object[] pivots = EnumSet.allOf(Pivot.class).toArray();
		
		// HARDCODE - MUDAR ISSO
		int iniX = (744/11)*10 , iniY = (740/11) ;
		
		for (int i = 0; i < numberOfPlayers; i++){
			playersArray.add( new Player( (Pivot) pivots[i] , new Point(iniX,iniY)  )  );
			iniY += iniY/2;
			
		}
		bringGamePanel();
	}

	private static void bringChooseNumberOfPlayersPanel() {
		ChooseNumberOfPlayersPanel chooseNOFP = new ChooseNumberOfPlayersPanel(new Dimension(300, 210), gameFrame);
		ChooseNumberOfPlayersListener btnLstner = new ChooseNumberOfPlayersListener();
		MyButton button2 = new MyButton("2", chooseNOFP);
		button2.addActionListener(btnLstner);
		button2.setActionCommand("two");
		button2.setBounds(32, 32, 79, 79);
		
		MyButton button3 = new MyButton("3", chooseNOFP);
		button3.addActionListener(btnLstner);
		button3.setActionCommand("three");
		button3.setBounds(127, 32, 79, 79);
		
		MyButton button4 = new MyButton("4", chooseNOFP);
		button4.addActionListener(btnLstner);
		button4.setActionCommand("four");
		button4.setBounds(221, 32, 79, 79);
		
		MyButton button5 = new MyButton("5", chooseNOFP);
		button5.addActionListener(btnLstner);
		button5.setActionCommand("five");
		button5.setBounds(32, 127, 79, 79);
		
		MyButton button6 = new MyButton("6", chooseNOFP);
		button6.addActionListener(btnLstner);
		button6.setActionCommand("six");
		button6.setBounds(127, 127, 79, 79);
		
		chooseNOFP.add(button2);
		chooseNOFP.add(button3);
		chooseNOFP.add(button4);
		chooseNOFP.add(button5);
		chooseNOFP.add(button6);
		
		gameFrame.add(chooseNOFP);
		gameFrame.setVisible(true);
		
	}

	public static void bringGamePanel() {
		gameFrame.setVisible(false);
		gameFrame = new FirstFrame(1200, 800, "Banco Imobiliario!");
		
		TablePanel tablePanel = new TablePanel(new Dimension(740, 744), gameFrame ,  playersArray );
		GameController gmController = new GameController();
		
		
	
		MyButton button = new MyButton("Rolar os Dados", gameFrame);
		
		button.addActionListener(gmController);
		button.setActionCommand("two");
		button.setBounds(tablePanel.getHeight() - 20 , 4*tablePanel.getWidth()/5 , 30, 30);
		tablePanel.add(button);
		
		gameFrame.add(tablePanel);
		//gameFrame.add(button2);
		gameFrame.setVisible(true);	
		
	}
	
	public static Player getCurrentPlayer(){
		return playersArray.get(currentPlayer);
	}
	
	public static void nextPlayer(){
		currentPlayer++;
		
		if (currentPlayer == numberOfPlayers){
			currentPlayer = 0;
		}
	}
}
