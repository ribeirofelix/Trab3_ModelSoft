package com.monopoly.view;

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

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.monopoly.components.BoardPanel;
import com.monopoly.controller.GameFacade;
import com.monopoly.exceptions.InvalidPlayersQuantityException;
import com.monopoly.exceptions.PlayerAlreadyExistsException;
import com.monopoly.model.Game;

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
	
	private final Menu _menuTest;
	private final MenuItem _movePlayerOneRight;
	private final MenuItem _movePlayerOneLeft;
	private final MenuItem _movePlayerTwoRight;
	private final MenuItem _movePlayerTwoLeft;
	
	private BoardPanel _pnlBoard;
	
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
		
		// Test Menu
		_menuTest = new Menu("Test");
		_movePlayerOneRight = new MenuItem("Move Player 1 Forward");
		_movePlayerOneLeft = new MenuItem("Move Player 1 Backwards");
		_movePlayerTwoRight = new MenuItem("Move Player 2 Forward");
		_movePlayerTwoLeft = new MenuItem("Move Player 2 Backwards");
		
		// Board
		_pnlBoard = new BoardPanel(_prop.getProperty("board_path"));
		
		/* Configures MenuBar */
		// File menu
		_menuFile.add (_fileNew);
		_menuFile.add (_fileExit);
		
		// Test menu
		_menuTest.add(_movePlayerOneRight);
		_menuTest.add(_movePlayerOneLeft);
		_menuTest.add(_movePlayerTwoRight);
		_menuTest.add(_movePlayerTwoLeft);
		
		// Menu Bar
		_menuBar.add (_menuFile);
		_menuBar.add (_menuTest);
		
		/* Sets action listeners */
		_fileNew.addActionListener (new NewGameListener());
		_fileExit.addActionListener (new ExitListener());
		_movePlayerOneRight.addActionListener(new MoveOneForward());
		_movePlayerOneLeft.addActionListener(new MoveOneBackward());
		_movePlayerTwoRight.addActionListener(new MoveTwoForward());
		_movePlayerTwoLeft.addActionListener(new MoveTwoBackward());
		
		/* Adds components to screen */
		this.add(_pnlBoard);
		
		/* Configures Window */
		this.setMenuBar(_menuBar);
		this.setSize(950, 750);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("Monopoly");
		
		// Frame resizing is not allowed
		this.setResizable (false);
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
					playerNames.add(auxName);
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
			GameFacade.startNewGame(playerNames);
			MainWindow.this._pnlBoard.repaint();
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
	
	private class MoveOneForward implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			int diceOne = -1;
			int diceTwo = -1;
			int dice;
			
			if (Game.getInstance().getPlayers().size() == 0)
			{
				JOptionPane.showMessageDialog(MainWindow.this, "Game has not been initialized");
				return;
			}
			
			while (diceOne <= 0) diceOne = 1 + ((int)(Math.random() * 5));
			while (diceTwo <= 0) diceTwo = 1 + ((int)(Math.random() * 5));
			dice = diceOne + diceTwo;
			
			GameFacade.movePlayerPiece(Game.getInstance().getPlayers().get(0), dice, true);
			MainWindow.this._pnlBoard.repaint();
		}
	}
	
	private class MoveOneBackward implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			int diceOne = -1;
			int diceTwo = -1;
			int dice;
			
			if (Game.getInstance().getPlayers().size() == 0)
			{
				JOptionPane.showMessageDialog(MainWindow.this, "Game has not been initialized");
				return;
			}
			
			while (diceOne <= 0) diceOne = 1 + ((int)(Math.random() * 5));
			while (diceTwo <= 0) diceTwo = 1 + ((int)(Math.random() * 5));
			dice = diceOne + diceTwo;
			
			GameFacade.movePlayerPiece(Game.getInstance().getPlayers().get(0), dice, false);
			MainWindow.this._pnlBoard.repaint();
		}
	}
	
	private class MoveTwoForward implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			int diceOne = -1;
			int diceTwo = -1;
			int dice;
			
			if (Game.getInstance().getPlayers().size() == 0)
			{
				JOptionPane.showMessageDialog(MainWindow.this, "Game has not been initialized");
				return;
			}
			
			while (diceOne <= 0) diceOne = 1 + ((int)(Math.random() * 5));
			while (diceTwo <= 0) diceTwo = 1 + ((int)(Math.random() * 5));
			dice = diceOne + diceTwo;
			
			GameFacade.movePlayerPiece(Game.getInstance().getPlayers().get(1), dice, true);
			MainWindow.this._pnlBoard.repaint();
		}
	}
	
	private class MoveTwoBackward implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			int diceOne = -1;
			int diceTwo = -1;
			int dice;
			
			if (Game.getInstance().getPlayers().size() == 0)
			{
				JOptionPane.showMessageDialog(MainWindow.this, "Game has not been initialized");
				return;
			}
			
			while (diceOne <= 0) diceOne = 1 + ((int)(Math.random() * 5));
			while (diceTwo <= 0) diceTwo = 1 + ((int)(Math.random() * 5));
			dice = diceOne + diceTwo;
			
			GameFacade.movePlayerPiece(Game.getInstance().getPlayers().get(1), dice, false);
			MainWindow.this._pnlBoard.repaint();
		}
	}
}
