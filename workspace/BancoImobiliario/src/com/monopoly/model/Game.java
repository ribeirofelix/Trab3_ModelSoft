package com.monopoly.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.monopoly.exceptions.PlayerAlreadyExistsException;
import com.monopoly.exceptions.PlayerNotExistsException;


public class Game
{
	private static Game _instance;
	
	private List<Player> _listPlayers;
	
	private Game()
	{
		_listPlayers = new ArrayList<Player>();
	}
	
	public static Game getInstance()
	{
		if (_instance == null)
			_instance = new Game();
		
		return _instance;
	}
	
	public List<Player> getPlayers()
	{
		return _listPlayers;
	}
	
	public void addPlayer(String playerName) throws PlayerAlreadyExistsException, 
		FileNotFoundException, IOException
	{
		for (Player tempPlayer : _listPlayers)
		{
			if (tempPlayer.getName().equals(playerName)) 
				throw new PlayerAlreadyExistsException();
		}
		
		_listPlayers.add(new Player(playerName, _listPlayers.size() + 1));
	}
	
	public void removePlayer(String playerName) throws PlayerNotExistsException
	{
		for (Player tempPlayer : _listPlayers)
		{
			if (tempPlayer.getName().equals(playerName))
			{
				_listPlayers.remove(tempPlayer);
				return;
			}
		}
		
		throw new PlayerNotExistsException();
	}
	
	public void clearPlayers()
	{
		_listPlayers.clear();
	}
	
	public void movePlayerPiece(Player player, int amount, boolean isForward)
	{
		int total;
		
		if (!isForward)
		{
			total = player.getPiecePosition() + amount;
			if (total >= 40) total -= 40;
		}
		else
		{
			total = player.getPiecePosition() - amount;
			if (total < 0) total = 39 + total;
		}
		
		player.setPiecePosition(total);
	}
}
