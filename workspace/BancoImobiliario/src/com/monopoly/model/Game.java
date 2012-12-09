package com.monopoly.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.monopoly.controller.ChanceCardLoader;
import com.monopoly.controller.TerrainLoader;
import com.monopoly.exceptions.PlayerAlreadyExistsException;
import com.monopoly.exceptions.PlayerNotExistsException;

public class Game
{
	private static Game _instance;
	
	private List<Player>     _gamePlayers;
	private List<ITerrain>   _boardTerrains;
	private List<ChanceCard> _gameCards;
	private Player           _currentPlayer;
	
	// Private Class Constructor
	private Game()
	{
		_gamePlayers   = new ArrayList<Player>();
		
		// Parsing Properties from the config file and loading them to list of Iterrains
		try 
		{
			_boardTerrains = TerrainLoader.LoadProperties ();
			_gameCards     = ChanceCardLoader.LoadCards ();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	// Singleton Method
	public static Game getInstance()
	{
		if (_instance == null)
			_instance = new Game ();
		
		return _instance;
	}
	
	// Gets list of Players in the game
	public List<Player> getPlayers()
	{
		return _gamePlayers;
	}
	
	public List<ITerrain> getBoardTerrains()
	{
		return _boardTerrains;
	}
	
	public List<ChanceCard> getChanceCards()
	{
		return _gameCards;
	}
	
	// Gets reference to the current player
	public Player getCurrentPlayer()
	{
		return _currentPlayer;
	}

	// Adds a new player to the game
	public void addPlayer (String playerName) throws PlayerAlreadyExistsException, 
		FileNotFoundException, IOException
	{
		for (Player tempPlayer : _gamePlayers)
		{
			if (tempPlayer.getName().equals(playerName)) 
				throw new PlayerAlreadyExistsException();
		}
		
		_gamePlayers.add(new Player (playerName, _gamePlayers.size() + 1));
	}
	
	// Removes player from the game
	public void removePlayer (String playerName) throws PlayerNotExistsException
	{
		for (Player tempPlayer : _gamePlayers)
		{
			if (tempPlayer.getName().equals(playerName))
			{
				_gamePlayers.remove(tempPlayer);
				return;
			}
		}
		
		throw new PlayerNotExistsException();
	}
	
	// Updates the current player variable to point to the next one
	public void updateCurrentPlayer()
	{
		int lastPlayer;
		int firstPlayer;
		int currentPlayerIndex;
		
		// Checking for current player index
		lastPlayer  = _gamePlayers.size() - 1;
		firstPlayer = 0 ;
		
		currentPlayerIndex = _gamePlayers.indexOf (_currentPlayer);
		
		if (currentPlayerIndex == lastPlayer)
		{
			_currentPlayer = _gamePlayers.get (firstPlayer);
		}
		else
		{
			currentPlayerIndex++;
			_currentPlayer = _gamePlayers.get (currentPlayerIndex);
		}
	}

	// Clear List of players (used for new game)
	public void clearPlayers()
	{
		_gamePlayers.clear();
	}
	
	// Sets currentPlayer as the first of the list
	public void setupPlayersOrder()
	{
		int firstPlayer = 0;
		
		// Setting up first player
		_currentPlayer = _gamePlayers.get (firstPlayer);
	}
}



