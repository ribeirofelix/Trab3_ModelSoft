package com.monopoly.model;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import com.monopoly.exceptions.InvalidPlayersQuantityException;

public class Player 
{
	private String  _name;
	private int     _playerNumber;
	private int     _cashLeft;
	private int     _piecePosition;
	private boolean _isJailed;
	private int     _jailedRounds;
	private List<Integer> _ownedTerrains;
	
	// The key value pair denotes: <terrain nº, number of properties>
	private HashMap<Integer, Integer> _propertiesBuilt;
	
	// Class Constructor
	public Player(String playerName, int playerNumber) throws FileNotFoundException, IOException
	{
		Properties prop = new Properties();
		prop.load (new FileInputStream("config.properties"));
		
		// Setting Player Properties
		_ownedTerrains = new ArrayList<Integer>();
		_propertiesBuilt = new HashMap<Integer, Integer>();
		_cashLeft = new Integer (prop.getProperty("player_start_amount").trim());
		setName(playerName);
		setPiecePosition(0);
		setPlayerNumber (playerNumber);
		
		// Setting default values for jail variables
		_isJailed     = false;
		_jailedRounds = 0;
	}
	
	// Gets the name of the Player
	public String getName() 
	{
		return _name;
	}

	// Sets the name of the Player
	public void setName(String _name) 
	{
		this._name = _name;
	}

	// Gets the current terrain index of the player
	public int getPiecePosition()
	{
		return _piecePosition;
	}

	// Sets the current terrain index of the player
	// "Moves" the player to another position
	public void setPiecePosition(int _piecePosition)
	{
		this._piecePosition = _piecePosition;
	}
	
	// Sets the Player number attribute
	public void setPlayerNumber(int playerNumber)
	{
		_playerNumber = playerNumber;
	}
	
	// Gets the player number attribute
	public int getPlayerNumber()
	{
		return _playerNumber;
	}
	
	// Gets the current cash the player has left
	public int getCash()
	{
		return _cashLeft;
	}
	
	// Gets the number of terrains owned by the player
	public int getOwnedTerrainsCount()
	{
		return _ownedTerrains.size();
	}
	
	public List<Integer> getOwnedTerrains()
	{
		return _ownedTerrains;
	}
	
	public HashMap<Integer, Integer> getPropertiesBuilt()
	{
		return _propertiesBuilt;
	}

	// Checks whether the player has enough cash to make a purchase
	// or rent. Returns true if he has, false otherwise.
	public boolean hasEnoughCash(int cost)
	{
		if ( (_cashLeft - cost) < 0 )
		{
			return false;
		}
		
		return true;
	}
	
	// Checks whether the player owns the terrain maped by this index
	// Returns true if the player owns this terrain, false otherwise
	public boolean ownsTerrain(int terrainIndex)
	{
		// Checks if the list of owned terrains contains this terrain
		if (_ownedTerrains.contains(terrainIndex))
		{
			return true;
		}
		
		return false;
	}
	
	// Adds the terrain maped by the index received to the list of
	// owned terrains of this player
	public void buyTerrain(int cost, int terrainIndex)
	{
		ITerrain terrain = Game.getInstance().getBoardTerrains().get(terrainIndex);
		
		// Sets terrain id to no player
		if (terrain.getType() == TerrainType.Property)
		{
			((PropertyTerrain)terrain).setOwnerId(getPlayerNumber());
		}
		else if (terrain.getType() == TerrainType.Company)
		{
			((CompanyTerrain)terrain).setOwnerId(getPlayerNumber());
		}
		
		// Checking if there is enough cash
		if (hasEnoughCash(cost))
		{
			// Debits the terrain cost
			makePayment (cost);
		
			// 	Adding terrain index to list
			_ownedTerrains.add(terrainIndex);
			getPropertiesBuilt().put(terrainIndex, 0);
		}
	}
	
	// Sells the terrain, if the player owns it.
	// Removes from the list of owned terrains and
	// Notice that this method DOES NOT REFUND THE PLAYER
	// MakePayment method must be invoked from the caller in order
	// to add the mortgage value back to the player
	// Returns true if the sell was OK, false otherwise
	public boolean sellTerrain(int terrainIndex)
	{
		ITerrain terrain = Game.getInstance().getBoardTerrains().get(terrainIndex);
		
		// Sets terrain id to no player
		if (terrain.getType() == TerrainType.Property)
		{
			((PropertyTerrain)terrain).setOwnerId(0);
		}
		else if (terrain.getType() == TerrainType.Company)
		{
			((CompanyTerrain)terrain).setOwnerId(0);
		}
		
		// Checking if the player owns the terrain he is trying to sell
		if (_ownedTerrains.contains (terrainIndex))
		{
			// Iterating to remove the terrain from the list
			for (int index = 0 ; index < _ownedTerrains.size() ; index++)
			{
				if (_ownedTerrains.get(index) == terrainIndex)
				{
					_ownedTerrains.remove(index);
					
					getPropertiesBuilt().remove(terrainIndex);
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean sellProperty(int terrainIndex)
	{
		PropertyTerrain terrain = (PropertyTerrain)Game.getInstance().getBoardTerrains().get(terrainIndex);
		Integer propertiesCount = _propertiesBuilt.get(terrainIndex);
		
		if (!ownsTerrain(terrainIndex))
		{
			return false;
		}
		
		if (propertiesCount <= 0)
		{
			return false;
		}
		else if (propertiesCount == 5)
		{
			propertiesCount--;
			this.receivePayment(terrain.getHotelCost() / 2);
			_propertiesBuilt.remove(terrainIndex);
			_propertiesBuilt.put(terrainIndex, propertiesCount);
		}
		else
		{
			propertiesCount--;
			this.receivePayment(terrain.getHouseCost() / 2);
			_propertiesBuilt.remove(terrainIndex);
			_propertiesBuilt.put(terrainIndex, propertiesCount);
		}
		
		return true;
	}
	
	// Debits Cash to the player
	public void makePayment(int payment)
	{
		if (hasEnoughCash (payment))
		{
			_cashLeft = _cashLeft - payment;
		}
	}
	
	// Adds Cash from the player
	public void receivePayment(int payment)
	{
		_cashLeft = _cashLeft + payment;
	}
	
	// Sets whether the player is in jail or not
	public void setPlayerJail(boolean isJailed)
	{
		_isJailed = isJailed;
		
		// Reseting jailed rounds if player was set free
		if (isJailed == false)
			_jailedRounds = 0;
	}
	
	// Checks whether a player is jailed or not
	public boolean isJailed()
	{
		return _isJailed;
	}
	
	// Increments the counting of rounds the player is jailed
	public void incrementJailedRound()
	{
		_jailedRounds++;
	}
	
	// Gets the number of rounds the player is jailed
	public int getJailedRounds()
	{
		return _jailedRounds;
	}
	
	// Gets the color of the piece that represents this player
	public static Color getPieceColorByIndex(int number) 
			throws InvalidPlayersQuantityException
	{
		switch (number)
		{
			case 1: return Color.RED;
			case 2: return Color.GREEN;
			case 3:	return Color.CYAN;
			case 4:	return Color.BLUE;
			case 5:	return Color.YELLOW;
			case 6:	return Color.MAGENTA;
			default: throw new InvalidPlayersQuantityException();
		}
	}
}
