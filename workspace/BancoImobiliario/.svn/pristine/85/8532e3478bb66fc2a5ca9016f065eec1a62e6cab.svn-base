package com.monopoly.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.monopoly.components.BoardPanel;
import com.monopoly.components.DicePanel;
import com.monopoly.controller.GameFacade;
import com.monopoly.exceptions.InvalidDiceValueException;
import com.monopoly.exceptions.InvalidPlayersQuantityException;
import com.monopoly.exceptions.InvalidTerrainException;
import com.monopoly.exceptions.PlayerAlreadyExistsException;
import com.monopoly.model.Game;
import com.monopoly.model.MovementResult;
import com.monopoly.model.Player;

@SuppressWarnings("serial")
public class MainWindow extends JFrame 
{
	/*** Attributes ***/
	private Properties _prop;
	
	/*** Components ***/
	private final MenuBar _menuBar;
	
	private final Menu _menuFile;
	private final MenuItem _fileNew;
	private final MenuItem _fileExit;
	
	private final Menu _menuGame;
	private final MenuItem _gameThrowDice;
	private final MenuItem _gameBuy;
	private final MenuItem _gameBuild;
	private final MenuItem _gameSellTerrain;
	private final MenuItem _gameSellProperty;
	private final MenuItem _gameTradeTerrain;	
	private final MenuItem _gameEndTurn;
	private final MenuItem _checkPlayerProfile;
	
	private BorderLayout _borderLayout;
	private BoardPanel _pnlBoard;
	
	private Container _statsContainer;
	private GridLayout _statsGrid;
	private JPanel _pnlStats;
	private DicePanel _diceOne;
	private DicePanel _diceTwo;
	private JTextArea _txtStats;
	
	/**
	 * Constructor responsible for building the whole main window interface
	 * @throws IOException When config.properties has exception
	 * @throws FileNotFoundException When config.properties does not exist
	 */
	public MainWindow() throws FileNotFoundException, IOException
	{
		/* Creates and sets attributes' instances */
		_prop = new Properties();
		_prop.load (new FileInputStream("config.properties"));
		
		/* Creates components' instances */
		// Menu Bar
		_menuBar = new MenuBar();
		
		// File Menu
		_menuFile = new Menu("File");
		_fileNew = new MenuItem("New Game");
		_fileExit = new MenuItem("Exit");
		
		// Game Menu
		_menuGame =  new Menu("Game");
		_gameThrowDice =  new MenuItem("Throw Dice");
		_gameBuy =  new MenuItem("Buy");
		_gameBuild = new MenuItem("Build");
		_gameSellTerrain = new MenuItem("Sell Terrain");
		_gameSellProperty = new MenuItem("Sell Property");
		_gameTradeTerrain = new MenuItem("Trade Terrain");
		
		_gameEndTurn =  new MenuItem("End Turn");
		_checkPlayerProfile = new MenuItem("Check Player Profile");
		
		// Border Layout
		_borderLayout = new BorderLayout();
		this.setLayout(_borderLayout);
		
		// Board
		_pnlBoard = new BoardPanel(_prop.getProperty("board_path"), 950, 702);
		
		// Stats Panel
		_statsContainer = new Container();
		_statsGrid = new GridLayout(3, 1);
		_pnlStats = new JPanel();
		_diceOne = new DicePanel();
		_diceTwo = new DicePanel();
		_txtStats = new JTextArea();
		
		/* Configures MenuBar */
		// File Menu
		_menuFile.add(_fileNew);
		_menuFile.add(_fileExit);
		
		// Game Menu
		_menuGame.add(_gameThrowDice);
		_menuGame.add(_gameBuy);
		_menuGame.add(_gameBuild);
		_menuGame.add(_gameSellTerrain);
		_menuGame.add(_gameSellProperty);
		_menuGame.add(_gameTradeTerrain);		
		_menuGame.add(_gameEndTurn);
		_menuGame.add(_checkPlayerProfile);
		
		// Menu Bar
		_menuBar.add (_menuFile);
		_menuBar.add(_menuGame);
		
		/* Stats Grid */
		// Dices
		_statsContainer.setSize(new Dimension(300, 740));
		_statsGrid.addLayoutComponent("diceOne", _diceOne);
		_statsGrid.addLayoutComponent("diceTwo", _diceTwo);
		_statsGrid.addLayoutComponent("lblStats", _txtStats);
		_pnlStats.add(_diceOne);
		_pnlStats.add(_diceTwo);
		_pnlStats.add(_txtStats);
		_pnlStats.setLayout(_statsGrid);
		_statsContainer.add(_pnlStats);
		_statsContainer.setBounds(955, 0, 300, 740);
		
		/* Sets action listeners */
		_fileNew.addActionListener(new NewGameListener());
		_fileExit.addActionListener(new ExitListener());
		_gameThrowDice.addActionListener(new DiceListener());
		_gameBuy.addActionListener(new BuyListener());
		_gameBuild.addActionListener(new BuildListener());
		_gameSellTerrain.addActionListener(new SellTerrainListener());
		_gameSellProperty.addActionListener(new SellPropertyListener());
		_gameTradeTerrain.addActionListener(new TradeTerrainListener());
		_gameEndTurn.addActionListener(new EndTurnListener());
		_checkPlayerProfile.addActionListener(new CheckPlayerProfile());
		
		/* Sets Border Layout */
		
		/* Adds components to screen */
		this.setBackground(Color.WHITE);
		_pnlStats.setPreferredSize(new Dimension(350, 740));
		_pnlStats.setSize(new Dimension(350, 740));
		_pnlStats.setMinimumSize(new Dimension(350, 740));
		_pnlStats.setMaximumSize(new Dimension(350, 740));
		_txtStats.setEditable(false);
		_txtStats.setOpaque(true);
		this.add(_pnlBoard, BorderLayout.LINE_START);
		this.add(_pnlStats, BorderLayout.LINE_END);
		
		/* Configures Window */
		this.setMenuBar(_menuBar);
		this.setSize(1300, 750);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("Monopoly");
		
		// Frame resizing is not allowed
		this.setResizable(false);
	}
	
	/**
	 * 
	 * @category event
	 * @description Event for New Game menu item click 
	 *
	 */
	private class NewGameListener implements ActionListener
	{	
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			String amountStr = "";
			Integer amount;
			ArrayList<String> playerNames = new ArrayList<String>();
			Player currentPlayer;
			String lblMessage = "";
			
			int confirmMode = JOptionPane.showConfirmDialog
			(
				MainWindow.this,
				"Are you sure you want to start a new game?"
			);
			
			/* Confirmation message box */
			if (confirmMode == JOptionPane.NO_OPTION || confirmMode == JOptionPane.CANCEL_OPTION) 
				return;

			/* Gets total amount of players to be added */
			amountStr = JOptionPane.showInputDialog("Please insert the number of players: 2-6");
			amount = new Integer(amountStr.trim());
			
			/* Validates amount of players according to manual */
			try
			{
				if (amount < 2 || amount > 6) throw new InvalidPlayersQuantityException();
			}
			catch (InvalidPlayersQuantityException err)
			{
				JOptionPane.showMessageDialog(MainWindow.this, err.getMessage());
				return;
			}
			
			/* Creates a sequence of input boxes to grab player names */
			for (int i = 1; i < (amount + 1); i++)
			{
				String auxName = JOptionPane.showInputDialog("Insert player " + i + " name:");
				
				/* Validates repeated player names */
				if (!playerNames.contains(auxName))
				{
					playerNames.add (auxName);
				}
				else
				{
					try
					{
						throw new PlayerAlreadyExistsException();
					}
					catch (PlayerAlreadyExistsException err)
					{
						JOptionPane.showMessageDialog(MainWindow.this, err.getMessage());
						return;
					}
				}
			}
			
			/* Starts new game */
			GameFacade.startNewGame (playerNames);
			
			currentPlayer = GameFacade.getCurrentPlayer();
			
			lblMessage += "\n Player : " + currentPlayer.getName() + "\n";
			lblMessage += " Cash : " + currentPlayer.getCash() + "\n";
			lblMessage += " Properties Owned : " + currentPlayer.getOwnedTerrainsCount();
			
			MainWindow.this._txtStats.setText(lblMessage);
			
			MainWindow.this._pnlStats.revalidate();
			MainWindow.this._pnlBoard.revalidate();
			MainWindow.this._txtStats.revalidate();
			MainWindow.this.repaint();
		}
	}

	/**
	 * 
	 * @category event
	 * @description Event for exit menu item click 
	 *
	 */
	private class ExitListener implements ActionListener
	{	
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			MainWindow.this.dispose();
			MainWindow.this.setVisible(false);
		}
	}
	
	/**
	 * 
	 * @category event
	 * @description Event for throw dice game menu click 
	 *
	 */
	private class DiceListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Random firstDice;
			Random secondDice;
			int diceOne;
			int diceTwo;
			MovementResult result;
			Player currentPlayer;
			String lblMessage = "";
			
			firstDice  = new Random();
			secondDice = new Random();
			
			diceOne = firstDice.nextInt(6) + 1; // + 1 avoids Zero
			diceTwo = secondDice.nextInt(6) + 1; // + 1 avoids Zero
			
			try 
			{
				MainWindow.this._diceOne.setDiceValue(diceOne);
				MainWindow.this._diceTwo.setDiceValue(diceTwo);
			} 
			catch (InvalidDiceValueException e) 
			{
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			
			// Moves the current Player Piece
			GameFacade.movePiece (Game.getInstance().getCurrentPlayer(), diceOne, diceTwo);
			
			// Checks if there is any consequence of this move
			// Eg: Chance Card Spot, Pays Rent to other player, goes to prison..
			result   = GameFacade.getMovementResult(Game.getInstance().getCurrentPlayer(), diceOne, diceTwo);
			
			currentPlayer = GameFacade.getCurrentPlayer();
			
			lblMessage += "\n Player : " + currentPlayer.getName() + "\n";
			lblMessage += " Cash : " + currentPlayer.getCash() + "\n";
			lblMessage += " Properties Owned : " + currentPlayer.getOwnedTerrainsCount();
			
			MainWindow.this._txtStats.setText(lblMessage);
			
			MainWindow.this._pnlStats.revalidate();
			MainWindow.this._pnlBoard.revalidate();
			MainWindow.this._diceOne.revalidate();
			MainWindow.this._diceTwo.revalidate();
			MainWindow.this._txtStats.revalidate();
			MainWindow.this.repaint();
			
			
			// Building Message Box
			if (result.GetMessage() != "")
			{
				JOptionPane.showMessageDialog(null, result.GetMessage());
			} 
		}
	}
	
	/**
	 * 
	 * @category event
	 * @description Event for buy game menu click 
	 *
	 */
	private class BuyListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			boolean boughtTerrain;
			Player currentPlayer;
			String lblMessage = "";
			
			boughtTerrain = GameFacade.tryBuyTerrain(Game.getInstance().getCurrentPlayer());
			
			if (boughtTerrain)
			{
				JOptionPane.showMessageDialog(null, "Terrain Added to Current Player. Congratulations");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Could not buy terrain. Another player owns it already or current player does not have enough cash to buy it");
			}
			
			currentPlayer = GameFacade.getCurrentPlayer();
			
			lblMessage += "\n Player : " + currentPlayer.getName() + "\n";
			lblMessage += " Cash : " + currentPlayer.getCash() + "\n";
			lblMessage += " Properties Owned : " + currentPlayer.getOwnedTerrainsCount();
			
			MainWindow.this._txtStats.setText(lblMessage);
			
			MainWindow.this._pnlStats.revalidate();
			MainWindow.this._pnlBoard.revalidate();
			MainWindow.this._txtStats.revalidate();
			MainWindow.this.repaint();
		}
	}
	
	private class EndTurnListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Player currentPlayer;
			String message;
			String lblMessage = "";
			
			GameFacade.updateCurrentPlayer();
			
			currentPlayer = GameFacade.getCurrentPlayer();
			
			message = "Turn Ended. Next Player is " + currentPlayer.getName();
			
			JOptionPane.showMessageDialog(null,message);
			
			lblMessage += "\n Player : " + currentPlayer.getName() + "\n";
			lblMessage += " Cash : " + currentPlayer.getCash() + "\n";
			lblMessage += " Properties Owned : " + currentPlayer.getOwnedTerrainsCount();
			
			MainWindow.this._txtStats.setText(lblMessage);
			
			MainWindow.this._pnlStats.revalidate();
			MainWindow.this._pnlBoard.revalidate();
			MainWindow.this._txtStats.revalidate();
			MainWindow.this.repaint();
		}
	}
	
	private class CheckPlayerProfile implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Player currentPlayer;
			String message;
			String playerName;
			int playerCash;
			int propertiesOwned;
			
			
			// Getting current Player reference
			currentPlayer = GameFacade.getCurrentPlayer();
			
			playerName = currentPlayer.getName();
			playerCash = currentPlayer.getCash();
			propertiesOwned = currentPlayer.getOwnedTerrainsCount();
			
			// Assembling Message
			message = "\n Player : " + playerName + "\n Cash : " + playerCash + "\n Properties Owned : " + propertiesOwned;
			
			JOptionPane.showMessageDialog(null, message);
		}
	}
	
	private class BuildListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Player currentPlayer;
			String lblMessage = "";
			
			if (GameFacade.buildProperty(Game.getInstance().getCurrentPlayer()))
			{
				JOptionPane.showMessageDialog(null, "Success! You have built a new property!");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Failure! You might not have enough cash, the terrain might be of someone else or\nyou do not fulfill all of the requirements to build a new property there!");
			}
			
			currentPlayer = GameFacade.getCurrentPlayer();
			
			lblMessage += "\n Player : " + currentPlayer.getName() + "\n";
			lblMessage += " Cash : " + currentPlayer.getCash() + "\n";
			lblMessage += " Properties Owned : " + currentPlayer.getOwnedTerrainsCount();
			
			MainWindow.this._txtStats.setText(lblMessage);
			
			MainWindow.this._pnlStats.revalidate();
			MainWindow.this._pnlBoard.revalidate();
			MainWindow.this._txtStats.revalidate();
			MainWindow.this.repaint();
		}
	}
	
	private class SellTerrainListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Player currentPlayer;
			String lblMessage = "";
			
			if (GameFacade.sellTerrain(Game.getInstance().getCurrentPlayer(), Game.getInstance().getCurrentPlayer().getPiecePosition()))
			{
				JOptionPane.showMessageDialog(null, "Success! You have sold your terrain!");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Failure! You cannot sell this terrain maybe because it has properties or because it does not belong to you!");
			}
			
			currentPlayer = GameFacade.getCurrentPlayer();
			
			lblMessage += "\n Player : " + currentPlayer.getName() + "\n";
			lblMessage += " Cash : " + currentPlayer.getCash() + "\n";
			lblMessage += " Properties Owned : " + currentPlayer.getOwnedTerrainsCount();
			
			MainWindow.this._txtStats.setText(lblMessage);
			
			MainWindow.this._pnlStats.revalidate();
			MainWindow.this._pnlBoard.revalidate();
			MainWindow.this._txtStats.revalidate();
			MainWindow.this.repaint();
		}
	}
	
	private class SellPropertyListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Player currentPlayer;
			String lblMessage = "";
			
			if (GameFacade.sellProperty(Game.getInstance().getCurrentPlayer(), Game.getInstance().getCurrentPlayer().getPiecePosition()))
			{
				JOptionPane.showMessageDialog(null, "Success! You have sold your property!");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Failure! You cannot sell a property maybe because it does not exist or the terrain is not yours");
			}
			
			currentPlayer = GameFacade.getCurrentPlayer();
			
			lblMessage += "\n Player : " + currentPlayer.getName() + "\n";
			lblMessage += " Cash : " + currentPlayer.getCash() + "\n";
			lblMessage += " Properties Owned : " + currentPlayer.getOwnedTerrainsCount();
			
			MainWindow.this._txtStats.setText(lblMessage);
			
			MainWindow.this._pnlStats.revalidate();
			MainWindow.this._pnlBoard.revalidate();
			MainWindow.this._txtStats.revalidate();
			MainWindow.this.repaint();
		}
	}
	
	private class TradeTerrainListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Player currentPlayer;
			String lblMessage = "";
			Integer terrainFrom = -1;
			Integer terrainTo = -1;
			int acceptedTrade = -1;
			
			terrainFrom = new Integer(JOptionPane.showInputDialog("Please insert your terrain number to be traded (1-40):").trim());
			terrainTo = new Integer(JOptionPane.showInputDialog("Please insert the terrain you want (1-40):").trim());
			
			if (GameFacade.getTerrainOwner(terrainFrom) != Game.getInstance().getCurrentPlayer())
			{
				JOptionPane.showMessageDialog(null, "This terrain is not yours!");
				return;
			}
			
			if (GameFacade.getTerrainOwner(terrainTo) == null)
			{
				JOptionPane.showMessageDialog(null, "This terrain belongs to nobody!");
				return;
			}
			
			acceptedTrade = JOptionPane.showConfirmDialog(null, "Does " + GameFacade.getTerrainOwner(terrainTo).getName() + " agree?");
			
			if (acceptedTrade != JOptionPane.YES_OPTION)
			{
				JOptionPane.showMessageDialog(null, GameFacade.getTerrainOwner(terrainTo).getName() + " did not agree to change!");
				return;
			}
			
			try 
			{
				if (GameFacade.tradeTerrain(Game.getInstance().getCurrentPlayer(), terrainFrom, terrainTo))
				{
					JOptionPane.showMessageDialog(null, "Success! You have traded your terrain!");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Failure! Trade invalid");
					return;
				}
			} 
			catch (HeadlessException e) 
			{
				e.printStackTrace();
			} 
			catch (InvalidTerrainException e) 
			{
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			
			currentPlayer = GameFacade.getCurrentPlayer();
			
			lblMessage += "\n Player : " + currentPlayer.getName() + "\n";
			lblMessage += " Cash : " + currentPlayer.getCash() + "\n";
			lblMessage += " Properties Owned : " + currentPlayer.getOwnedTerrainsCount();
			
			MainWindow.this._txtStats.setText(lblMessage);
			
			MainWindow.this._pnlStats.revalidate();
			MainWindow.this._pnlBoard.revalidate();
			MainWindow.this._txtStats.revalidate();
			MainWindow.this.repaint();
		}
	}
}
