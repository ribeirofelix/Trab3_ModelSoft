package control;


import java.awt.Component;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.EnumSet;

import javax.swing.JLabel;
import javax.swing.JPanel;

import view.*;
import model.*;

public class Main {
	
	/* Frames */
	private static FirstFrame gameFrame;
	private static TablePanel tablePanel ;
	private static int currentPlayer = -1;
	private static int numberOfPlayers;
	private static ArrayList<Player> playersArray;
	private static Board boardGame ;
	private final static String   nameLblDiceRoll = "lblDiceRollValue" 
								, nameLblGamerStatus = "lblGamerStatus"
								, namePnlCardImage = "pnlCardImage";
	
	public static FirstFrame getGameFrame (){
		return gameFrame;
	}
	
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
		
		boardGame = new Board();
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
		gameFrame = new FirstFrame(1200, 900, "Banco Imobiliario!");
		
		tablePanel = new TablePanel(new Dimension(740, 744), gameFrame ,  playersArray );
		
		GameController gmController = new GameController();
		
		/*Button Panel*/
		MyJPanel btnJpanel = new MyJPanel(new Point(0,744), new Dimension(460, 800) , gameFrame);
		//btnJpanel.setLayout(new FlowLayout());
		
		/*Roll dice button */
		MyButton btnRollDice = new MyButton("Rolar os Dados", btnJpanel);		
		btnRollDice.addActionListener(gmController);
		btnRollDice.setActionCommand("rollDices");
		btnRollDice.setBounds(btnJpanel.getHeight() + 80 , btnJpanel.getWidth()/8 , 200, 50);
		btnJpanel.add(btnRollDice);
		
		/*Dice roll value*/
		JLabel lblDiceRollValue = new JLabel();
		lblDiceRollValue.setName( nameLblDiceRoll);
		lblDiceRollValue.setBounds(btnJpanel.getHeight() + 80 , btnJpanel.getWidth()/4 , 200, 50);
		lblDiceRollValue.setFont(new Font(Font.SANS_SERIF, Font.BOLD  , 50));
		btnJpanel.add(lblDiceRollValue);
	
		/* Label for display gamer status */
		JLabel lblGamerStatus = new JLabel();
		lblGamerStatus.setName(nameLblGamerStatus);
		lblGamerStatus.setBounds(btnJpanel.getHeight()   , btnJpanel.getWidth() - 200, 500, 200);
		lblGamerStatus.setFont(new Font(Font.SANS_SERIF, Font.BOLD  , 30));
		setPlayerStatus(lblGamerStatus);
		btnJpanel.add(lblGamerStatus);
		
	
		/* Panel to show card image*/
		CardImagePanel pnlCardImage = new CardImagePanel(btnJpanel,null ) ;
		pnlCardImage.setName(namePnlCardImage);
		
		
		
		/*Buy property Button*/
		MyButton btnBuyProperty = new MyButton("Comprar Propriedade", btnJpanel);
		btnBuyProperty.addActionListener(gmController);
		btnBuyProperty.setActionCommand("buyProperty");
		btnBuyProperty.setEnabled(false);
		btnBuyProperty.setBounds(btnJpanel.getHeight() + 80 , btnJpanel.getWidth()/2 , 200, 50);
		btnJpanel.add(btnBuyProperty);
		
		/*Build House Button*/
		MyButton btnBuildHouse = new MyButton("Construir Casa/Hotel", btnJpanel);
		btnBuildHouse.addActionListener(gmController);
		btnBuildHouse.setActionCommand("buildHouse");
		btnBuildHouse.setEnabled(false);
		btnBuildHouse.setBounds(btnJpanel.getHeight() + 80, btnJpanel.getWidth()/2 + 60 , 200, 50);
		btnJpanel.add(btnBuildHouse);
		
		gameFrame.add(tablePanel);
		gameFrame.add(btnJpanel);
		gameFrame.setVisible(true);	
		
	}
	
	public static Player getCurrentPlayer(){
		if(currentPlayer < 0)
			return playersArray.get(0);
		
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
	}
	
	public static void showRollDiceAndPlayerStatus(int rollOne, int rollTwo){
		for (Component comp : gameFrame.getContentPane().getComponents() ) {
			if(comp instanceof JPanel){
				for (Component compPanl : ((JPanel) comp).getComponents()) {
					
					if(compPanl instanceof JLabel){
						JLabel lbl =(JLabel)compPanl ;
						if(lbl.getName().equals(nameLblDiceRoll))
							lbl.setText(rollOne + " " + rollTwo );
						else if ( lbl.getName().equals(nameLblGamerStatus)) {
							setPlayerStatus(lbl);
						}
<<<<<<< HEAD
						else if ( lbl.getName().equals(nameLblCard)){
							//lbl.seti
						}
=======
					
>>>>>>> 1483b9a20de0b5bef52d2ef8c32da582202e0ba7
					}
				}
			}			
		}
	}

	public static void ShowCurrentCard(){
		CardImagePanel pnlCardImage = (CardImagePanel)searchComponentInBtnJPanelByName(namePnlCardImage);
		
		ICard currntCard = boardGame.getHouseOnThisPosition(getCurrentPlayer().getPosition()).getCard() ;
		
		if(currntCard == null){
			pnlCardImage.setPathImageCard(null);
		}
		else{
			pnlCardImage.setPathImageCard(currntCard.getImagePath());
		}
		pnlCardImage.repaint();
	}
	
	private static Component searchComponentInBtnJPanelByName(String name){
		for (Component comp : gameFrame.getContentPane().getComponents() ) {
			if(comp instanceof JPanel){
				for (Component compPanl : ((JPanel) comp).getComponents()) {
					
					if(compPanl.getName() != null  && compPanl.getName().equals(name))
						return compPanl;
					
				}
			}			
		}
		return null;
	}
	
	public static void updatePlayerStatus(){
		JLabel lblGamerStatus =  (JLabel)searchComponentInBtnJPanelByName(nameLblGamerStatus);
		setPlayerStatus(lblGamerStatus);
	}
	
	private static void setPlayerStatus(JLabel lblGamerStatus) {
		Player currentGamer = getCurrentPlayer();
		lblGamerStatus.setForeground(currentGamer.getPivot().getColor());
		lblGamerStatus.setText("<html>Jogador " + currentGamer.getPivot().toString() + "<br>" +
				"Dinheiro: " + currentGamer.getAmountOfMoney() + "</html>" );
	}

	public static void enableBuyPropertyButton(boolean enable){
		for (Component comp : gameFrame.getContentPane().getComponents() ) {
			if(comp instanceof JPanel){
				for (Component compPanl : ((JPanel) comp).getComponents()) {
					
					if(compPanl instanceof MyButton && ((MyButton) compPanl).getActionCommand().equals("buyProperty")){
						((MyButton)compPanl).setEnabled(enable);
					
					}		
				}
			}			
		}
	}

	public static void enableBuildHouseButton(boolean enable){
		for (Component comp : gameFrame.getContentPane().getComponents() ) {
			if(comp instanceof JPanel){
				for (Component compPanl : ((JPanel) comp).getComponents()) {
					
					if(compPanl instanceof MyButton && ((MyButton) compPanl).getActionCommand().equals("buildHouse")){
						((MyButton)compPanl).setEnabled(enable);
					
					}
					
				}
			}			
		}
	}
	
	public static Board getBoardGame() {
		return boardGame;
	}
}
