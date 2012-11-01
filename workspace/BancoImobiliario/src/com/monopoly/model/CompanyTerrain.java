package com.monopoly.model;

public class CompanyTerrain
{
	private int _acquisitionCost;
	private int _diceMultiplier;
	
	public CompanyTerrain (TerrainType type, String name, int acquisitionCost, int diceMultiplier) 
	{	
		// Setting Attributes
		setAcquisitionCost(acquisitionCost);
		setDiceMultiplier(diceMultiplier);
	}

	public int getAcquisitionCost() 
	{
		return _acquisitionCost;
	}

	public void setAcquisitionCost(int _acquisitionCost) 
	{
		this._acquisitionCost = _acquisitionCost;
	}

	public int getDiceMultiplier()
	{
		return _diceMultiplier;
	}

	public void setDiceMultiplier(int _diceMultiplier)
	{
		this._diceMultiplier = _diceMultiplier;
	}
}
