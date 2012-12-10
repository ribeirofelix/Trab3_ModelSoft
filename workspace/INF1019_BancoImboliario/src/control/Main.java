package control;


import java.awt.Component;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.EnumSet;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
								, namePnlCardImage = "pnlCardImage"
								, nameLblOwner = "lblOwnerStatus"
								, nameCmbProperties = "cmbProperties"
								, nameCmbPlayerToNegociate = "cmbPlayerToNegociate"
								, nameCmbNegType = "cmbNegType"
								, valueTxt = "valueTxt"
								, nameTxtManuallyRoll = "nameTxtManuallyRoll";
	private static boolean playAgain = false ;
	private static int rounds = 0 ;
	
	
	
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
		gameFrame = new FirstFrame(1500, 990, "Banco Imobiliario!");
		
		tablePanel = new TablePanel(new Dimension(740, 800), gameFrame ,  playersArray );
		
		GameController gmController = new GameController();
		
		/*Button Panel*/
		MyJPanel pnlMenuPlayer = new MyJPanel(new Point(0,744), new Dimension(460, 800) , gameFrame);


		/*Roll dice button */
		MyButton btnRollDice = new MyButton("Rolar os Dados", pnlMenuPlayer);		
		btnRollDice.addActionListener(gmController);
		btnRollDice.setActionCommand(ActionCommand.RollDices.name());
		btnRollDice.setBounds(pnlMenuPlayer.getHeight() + 80 , pnlMenuPlayer.getWidth()/8 , 200, 50);
		pnlMenuPlayer.add(btnRollDice);
		
		
		/* Manually roll */
		JTextField txtManuallyRoll = new JTextField();
		txtManuallyRoll.setBounds(btnRollDice.getX() + btnRollDice.getWidth() + 10, btnRollDice.getY(), 100, 50);
		txtManuallyRoll.setName(nameTxtManuallyRoll);
		pnlMenuPlayer.add(txtManuallyRoll);
		
		
		
		/*Dice roll value*/
		JLabel lblDiceRollValue = new JLabel();
		lblDiceRollValue.setName( nameLblDiceRoll);
		lblDiceRollValue.setBounds(pnlMenuPlayer.getHeight() + 80 , pnlMenuPlayer.getWidth()/4 , 200, 50);
		lblDiceRollValue.setFont(new Font(Font.SANS_SERIF, Font.BOLD  , 50));
		pnlMenuPlayer.add(lblDiceRollValue);
	
		/* Label for display gamer status */
		JLabel lblGamerStatus = new JLabel();
		lblGamerStatus.setName(nameLblGamerStatus);
		lblGamerStatus.setBounds(pnlMenuPlayer.getHeight()   , pnlMenuPlayer.getWidth() - 150, 500, 200);
		lblGamerStatus.setFont(new Font(Font.SANS_SERIF, Font.BOLD  , 30));
		setPlayerStatus(lblGamerStatus,getCurrentPlayer());
		pnlMenuPlayer.add(lblGamerStatus);
		
	
		/* Panel to show card image*/
		CardImagePanel pnlCardImage = new CardImagePanel(pnlMenuPlayer,null ) ;
		pnlCardImage.setName(namePnlCardImage);
		
		/* Label for show owner status */
		JLabel lblOwner = new JLabel();
		lblOwner.setName(nameLblOwner);
		lblOwner.setBounds(pnlMenuPlayer.getHeight()   , pnlCardImage.getY() - 50 + pnlCardImage.getHeight(), 500, 200);
		lblOwner.setFont(new Font(Font.SANS_SERIF, Font.BOLD  , 30));
		pnlMenuPlayer.add(lblOwner);
		
		/*Buy property Button*/
		MyButton btnBuyProperty = new MyButton("Comprar Propriedade", pnlMenuPlayer);
		btnBuyProperty.addActionListener(gmController);
		btnBuyProperty.setActionCommand(ActionCommand.BuyProperty.name());
		btnBuyProperty.setEnabled(false);
		btnBuyProperty.setBounds(pnlMenuPlayer.getHeight() + 80 , pnlMenuPlayer.getWidth()/2 , 200, 50);
		pnlMenuPlayer.add(btnBuyProperty);
		
		/*Build House Button*/
		MyButton btnBuildHouse = new MyButton("Construir Casa/Hotel", pnlMenuPlayer);
		btnBuildHouse.addActionListener(gmController);
		btnBuildHouse.setActionCommand(ActionCommand.BuildHouse.name());
		btnBuildHouse.setEnabled(false);
		btnBuildHouse.setBounds(pnlMenuPlayer.getHeight() + 80, pnlMenuPlayer.getWidth()/2 + 60 , 200, 50);
		pnlMenuPlayer.add(btnBuildHouse);
		
		/* Choose Negociation (Sell or Buy) ComboBox */
		JComboBox<Negociation> cmbNegociation = new JComboBox<>(Negociation.values());
		cmbNegociation.setActionCommand(ActionCommand.ChooseNegociationType.name());
		cmbNegociation.setBounds(pnlMenuPlayer.getHeight() , lblOwner.getY() + lblOwner.getHeight() - 30 , 50, 35);
		cmbNegociation.setName(nameCmbNegType);
		cmbNegociation.addActionListener(gmController);
		pnlMenuPlayer.add(cmbNegociation);
		
		/* Choose Player to Negociate ComboBox */
		JComboBox<Pivot> cmbPlayersToNegociate = new JComboBox<>(Pivot.values() );
		cmbPlayersToNegociate.setBounds(cmbNegociation.getX() + cmbNegociation.getWidth() + 10 , lblOwner.getY() + lblOwner.getHeight() - 30 , 100, 35);
		cmbPlayersToNegociate.addActionListener(gmController);
		cmbPlayersToNegociate.setActionCommand(ActionCommand.ShowPropertiesOfSeller.name());
		cmbPlayersToNegociate.setName(nameCmbPlayerToNegociate);
		pnlMenuPlayer.add(cmbPlayersToNegociate);
		
		/* Choose Property to Negotiate ComboBox */
		JComboBox<String> cmbPropertiesNames = new JComboBox<String>(getCurrentPlayer().getPropertiesNames());
		cmbPropertiesNames.setBounds(cmbPlayersToNegociate.getX() + cmbPlayersToNegociate.getWidth() + 10 , lblOwner.getY() + lblOwner.getHeight() - 30 , 200, 35);
		cmbPropertiesNames.setName(nameCmbProperties);
		//cmbPropertiesNames.addActionListener(gmController);
		pnlMenuPlayer.add(cmbPropertiesNames);
		
		/* Input the value */
		JTextField value = new JTextField();
		value.setBounds(cmbPropertiesNames.getX() + cmbPropertiesNames.getWidth() + 10, lblOwner.getY() + lblOwner.getHeight() - 30, 100, 35);
		value.setName(valueTxt);
		pnlMenuPlayer.add(value);
		
		/* Do it button */
		MyButton doItBtn = new MyButton("Aceito", pnlMenuPlayer);
		doItBtn.setBounds(value.getX() + value.getWidth() + 10, lblOwner.getY() + lblOwner.getHeight() - 30, 100, 35);
		doItBtn.setActionCommand(ActionCommand.FinalizeNegociation.name());
		doItBtn.addActionListener(gmController);
		pnlMenuPlayer.add(doItBtn);
		
		rounds ++;
		
		gameFrame.add(tablePanel);
		gameFrame.add(pnlMenuPlayer);
		gameFrame.setVisible(true);	
		
	}
	
	public static Player getCurrentPlayer(){
		if(currentPlayer < 0)
			return playersArray.get(0);
		if(currentPlayer  > playersArray.size() - 1){
			return null ;
		}
		return playersArray.get(currentPlayer);
	}
	
	public static void nextPlayer(){
		if(playAgain)
			playAgain = false ;		
		else
			currentPlayer++;
		
		if (currentPlayer == numberOfPlayers){
			rounds++;
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
							setPlayerStatus(lbl,getCurrentPlayer());
						}

					}
				}
			}			
		}
	}

	public static void ShowCurrentCard(){
		CardImagePanel pnlCardImage = (CardImagePanel)searchComponentInBtnJPanelByName(namePnlCardImage);
		
		Player currPlayer = getCurrentPlayer();
		if(currPlayer == null){
			pnlCardImage.setPathImageCard(null);
			pnlCardImage.repaint();
			return ;
		}
		ICard currntCard = boardGame.getHouseOnThisPosition(currPlayer.getPosition()).getCard() ;
		
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
		setPlayerStatus(lblGamerStatus,getCurrentPlayer());
	}
	
	public static void updatePlayerStatus(Player owner){
		JLabel lblGamerStatus =  (JLabel)searchComponentInBtnJPanelByName(nameLblOwner);
		if(owner == null){
			lblGamerStatus.setText("");
		}
		else{
			setPlayerStatus(lblGamerStatus,owner);
		}
	}
	
	private static void setPlayerStatus(JLabel lblGamerStatus , Player playerToShow) {
		Player currentGamer = playerToShow;
		lblGamerStatus.setForeground(currentGamer.getPivot().getColor());
		lblGamerStatus.setText("<html>Jogador " + currentGamer.getPivot().toString() + "<br>" +
				"Dinheiro: " + currentGamer.getAmountOfMoney() + "<br>" +
				currentGamer.getNumberOfHousesOrHotel(boardGame.getHouseOnThisPosition(currentGamer.getPosition()).getCard()) + "</html>" );
	}

	public static void enableBuyPropertyButton(boolean enable){
		for (Component comp : gameFrame.getContentPane().getComponents() ) {
			if(comp instanceof JPanel){
				for (Component compPanl : ((JPanel) comp).getComponents()) {
					
					if(compPanl instanceof MyButton && ((MyButton) compPanl).getActionCommand().equals(ActionCommand.BuyProperty.name())){
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
					
					if(compPanl instanceof MyButton && ((MyButton) compPanl).getActionCommand().equals(ActionCommand.BuildHouse.name())){
						((MyButton)compPanl).setEnabled(enable);
					
					}
					
				}
			}			
		}
	}
	
	public static Board getBoardGame() {
		return boardGame;
	}
	
	public static void repeatPlayer(){
		playAgain = true ;
		
	}

	public static void removeMeFromNegociation(){
		JComboBox<Pivot> cmbPlayers = (JComboBox<Pivot> ) searchComponentInBtnJPanelByName(nameCmbPlayerToNegociate) ;
		
		Player prevPlayer = null ;
		
		if(rounds == 1 && currentPlayer == 0 ){
			cmbPlayers.removeItem(getCurrentPlayer().getPivot());
			return;
		}
		
		if(currentPlayer == 0){
			prevPlayer = playersArray.get(numberOfPlayers-1);
		}
		else{
			prevPlayer = playersArray.get(currentPlayer-1);
		}
		cmbPlayers.removeItem(getCurrentPlayer().getPivot());
		cmbPlayers.addItem(prevPlayer.getPivot());
		
		
		
	}
	
	public static void updateNegotiablesProperties(){
		
		Negociation negType = (Negociation) ( (JComboBox<Negociation>) searchComponentInBtnJPanelByName(nameCmbNegType) ).getSelectedItem();
		
		/*Get the Combobox Properties by Name and clean it*/
		JComboBox<String> cmbProperties = (JComboBox<String>) searchComponentInBtnJPanelByName(nameCmbProperties);
		cmbProperties.removeAllItems();
		
		/*Get the Combobox Players by Name */
		JComboBox<Pivot> cmbPlayers = (JComboBox<Pivot> ) searchComponentInBtnJPanelByName(nameCmbPlayerToNegociate) ;		
		
		String [] propNames = null ;
		/*If the player want to Sell , show its properties */
		if(negType == Negociation.Sell){	
			propNames = getCurrentPlayer().getPropertiesNames();		
		}
		else{/*Else, get the selected player to negociate, and show its properties */
			
			propNames = getSelectedPlayerByPivot(cmbPlayers).getPropertiesNames();
				
		}
		/*Update the ComboBox*/
		for (String nameProperty : propNames) {
			cmbProperties.addItem(nameProperty);
		}
		
	}

	private static Player getSelectedPlayerByPivot(JComboBox<Pivot> cmbPlayers) {
		Pivot choosen = (Pivot) cmbPlayers.getSelectedItem();
		
		
		for (Player player : playersArray) {
			if( choosen.compareTo(player.getPivot()) == 0 ){
				return player;
			}
		}
		return null;
	}

	/* This method return a number inputed on a manually roll.
	 * If has no inputed value, it will returns 0  */
	public static int getManuallyRoll(){
		JTextField input = (JTextField) searchComponentInBtnJPanelByName(nameTxtManuallyRoll);
		if(!input.getText().isEmpty()){
			String rollValue = input.getText();
			if(rollValue.matches("((-|\\+)?[0-9])+")){
				return Integer.parseInt(rollValue);
			}
			
		}
	
		return 0;
		
	}

	public static void showSellerProperties(){
		
		if( ( Negociation)( (  JComboBox<Negociation>)searchComponentInBtnJPanelByName(nameCmbNegType) ).getSelectedItem() == Negociation.Sell)
			return ;
		
		/*Get the Combobox Properties by Name and clean it*/
		JComboBox<String> cmbProperties = (JComboBox<String>) searchComponentInBtnJPanelByName(nameCmbProperties);
		cmbProperties.removeAllItems();
		
		/*Get the Combobox Players by Name */
		JComboBox<Pivot> cmbPlayers = (JComboBox<Pivot> ) searchComponentInBtnJPanelByName(nameCmbPlayerToNegociate) ;		
		
		String [] proNames = getSelectedPlayerByPivot(cmbPlayers).getPropertiesNames();
		
		for (String nameProp : proNames) {
			cmbProperties.addItem(nameProp);
		}
		
		
	}
	
	public static void bankruptcyPlayer(Player player){
		JOptionPane.showMessageDialog(Main.getGameFrame(), "Jogador não tem dinheiro suficiente. Você faliu.", "Opss", JOptionPane.WARNING_MESSAGE);
		playersArray.remove(player);
		numberOfPlayers--;
		if(playersArray.size() == 1){
			JOptionPane.showMessageDialog(Main.getGameFrame(), "Você ganhou" +playersArray.get(0).getPivot().name() +"!Todos faliram e você é o mais rico!", "Parabéns", JOptionPane.WARNING_MESSAGE);
			
			
			setPlayerStatus((JLabel)searchComponentInBtnJPanelByName(nameLblGamerStatus) , playersArray.get(0));
			
		}
		
	}

	public static void negotiateProperty() {
		String value = ((JTextField) searchComponentInBtnJPanelByName(valueTxt)).getText();
		if(value.matches("((-|\\+)?[0-9])+")){
			int money = Integer.parseInt(value);
			
			Player playerSelected = getSelectedPlayerByPivot((JComboBox<Pivot>)searchComponentInBtnJPanelByName(nameCmbPlayerToNegociate));
			
			JComboBox<String> cmbProperties = (JComboBox<String> )searchComponentInBtnJPanelByName(nameCmbProperties);
			Property propSelected = boardGame.getPropertyByName((String)cmbProperties.getSelectedItem());
			if(propSelected != null){
				/*If the property is of the selected player. 
				 * He's selling his property. So, he wins money. */
				if(playerSelected.hasProperty(propSelected)){
					playerSelected.removeProperty(propSelected);
					playerSelected.putMoney(money);
					
					getCurrentPlayer().removeMoney(money);
					getCurrentPlayer().addProperty(propSelected);
				}
				else{
					
					getCurrentPlayer().removeProperty(propSelected);
					getCurrentPlayer().putMoney(money);
					
					playerSelected.removeMoney(money);
					playerSelected.addProperty(propSelected);					
					
				}
			}
			updatePlayerStatus();
			updateNegotiablesProperties();
			
		}
		
	}

}
