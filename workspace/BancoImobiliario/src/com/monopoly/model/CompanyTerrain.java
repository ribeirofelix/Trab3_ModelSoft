package com.monopoly.model;

public class CompanyTerrain implements ITerrain
{
	private int _acquisitionCost;
	private int _mortgage;
	private int _diceMultiplier;
	private int _ownerId;
	
	// Class Constructor
	public CompanyTerrain(String name, int acquisitionCost, int mortgage, int diceMultiplier) 
	{	
		// Setting Attributes
		setAcquisitionCost(acquisitionCost);
		setDiceMultiplier(diceMultiplier);
		
		this._mortgage = mortgage;
		setOwnerId(-1);
	}

	// Gets the Cost to buy the terrains
	public int getAcquisitionCost() 
	{
		return _acquisitionCost;
	}

	// Sets the cost to buy the terrain
	public void setAcquisitionCost(int _acquisitionCost) 
	{
		this._acquisitionCost = _acquisitionCost;
	}

	// Gets the DiceMultiplier attribute of this company
	public int getDiceMultiplier()
	{
		return _diceMultiplier;
	}

	// Sets the DiceMultiplier attribute of this company
	public void setDiceMultiplier(int _diceMultiplier)
	{
		this._diceMultiplier = _diceMultiplier;
	}
	
	// Gets the Mortgage value of this terrain
	public int getMortgage()
	{
		return _mortgage;
	}
	
	public int getOwnerId()
	{
		return _ownerId;
	}
	
	public void setOwnerId(int id)
	{
		_ownerId = (id >= 1 && id <= Game.getInstance().getPlayers().size()) ? id : -1;
	}
	
	public boolean hasOwner()
	{
		if (_ownerId >= 1 && _ownerId <= Game.getInstance().getPlayers().size())
		{
			return true;
		}
		
		return false;
	}
	
	// Calculates the cost to stop by this company based on the dices sum
	// of the player who stopped by
	public int calculateStopByCost(int dicesSum)
	{
		return dicesSum * _diceMultiplier;
	}
	
	// Gets the Type of this Terrain
	@Override
	public TerrainType getType() 
	{
		return TerrainType.Company;
	}
}
