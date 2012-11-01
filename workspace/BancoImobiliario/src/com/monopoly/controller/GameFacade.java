package com.monopoly.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.monopoly.exceptions.PlayerAlreadyExistsException;
import com.monopoly.model.Game;
import com.monopoly.model.Player;

public class GameFacade 
{
	public static void startNewGame(List<String> playerNames)
	{
		Game.getInstance().clearPlayers();
		
		try
		{
			for (String tempStr : playerNames)
				Game.getInstance().addPlayer(tempStr);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (PlayerAlreadyExistsException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void movePlayerPiece(Player player, int amount, boolean isForward)
	{
		Game.getInstance().movePlayerPiece(player, amount, isForward);
	}
}
