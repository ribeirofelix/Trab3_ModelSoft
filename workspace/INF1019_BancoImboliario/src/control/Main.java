package control;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.EnumSet;
import view.*;
import model.*;

public class Main {
	
	/* Frames */
	private static FirstFrame gameFrame;
	private static TablePanel tablePanel ;
	private static int currentPlayer = 0;
	private static int numberOfPlayers;
	private static ArrayList<Player> playersArray;
	
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
		
		/* First player pivot drawing point */	
		int iniX = 666 , iniY = 44 ;
		
		for (int i = 0; i < numberOfPlayers; i++){
			playersArray.add( new Player( (Pivot) pivots[i] , new Point(iniX,iniY)  )  );
			iniX += 18;
			
			/* Next row */
			if (i%2 != 0){
				iniY += 18; 
				
				/* Put iniX on first column */
				iniX -= 36;
			}
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
		
		tablePanel = new TablePanel(new Dimension(740, 744), gameFrame ,  playersArray );
		
		GameController gmController = new GameController();
		
		/*Button Panel*/
		MyJPanel btnJpanel = new MyJPanel(new Point(0,744), new Dimension(460, 800) , gameFrame);
		
		MyButton button = new MyButton("Rolar os Dados", gameFrame);
	
		
		button.addActionListener(gmController);
		button.setActionCommand("two");
		button.setBounds(btnJpanel.getHeight() + 80 , btnJpanel.getWidth()/8 , 200, 50);
		btnJpanel.add(button);
		
		gameFrame.add(tablePanel);
		gameFrame.add(btnJpanel);
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

	public static void updateFrame() {
		
				
		tablePanel.validate();
		tablePanel.repaint();
		
		//gameFrame.update(gameFrame.getGraphics());
		//gameFrame.getComponent(0).update(gameFrame.getComponent(0).getGraphics() );
		//gameFrame.getComponent(0).paint(gameFrame.getComponent(0).getGraphics());
		
		
		//gameFrame.paintAll(gameFrame.getComponent(0).getGraphics());
		
	}
}
