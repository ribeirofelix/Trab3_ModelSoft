package com.monopoly.model;

public class MovementResult 
{
	private TerrainType _terrainType;
	private String      _movementMessage;
	
	// Class Constructor
	public MovementResult (TerrainType type, String message)
	{
		_terrainType     = type;
		_movementMessage = message;
	}
	
	// Getters
	public String GetMessage ()
	{
		return _movementMessage;
	}
	
	public TerrainType getTerrainType()
	{
		return _terrainType;
	}
}
