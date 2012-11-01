package com.monopoly.model;


public class UnbuyableTerrain 
{
	private TerrainType _type ;
	private String      _terrainName;
	
	
	// Class Constructor
	public UnbuyableTerrain (TerrainType type, String name)
	{
		setType(type);
		setName(name);
	}


	public TerrainType getType() 
	{
		return _type;
	}


	public void setType(TerrainType _type) 
	{
		this._type = _type;
	}


	public String getName()
	{
		return _terrainName;
	}


	public void setName(String _terrainName) 
	{
		this._terrainName = _terrainName;
	}
}
