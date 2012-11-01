package com.monopoly.model;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.monopoly.exceptions.InvalidPlayersQuantityException;

public class Player 
{
	private String _name;
	private int _cashLeft;
	private int _number;
	private int _piecePosition;
	
	// Class Constructor
	public Player (String playerName, int number) throws FileNotFoundException, IOException
	{
		Properties prop = new Properties();
		prop.load (new FileInputStream("config.properties"));
		
		setName(playerName);
		_cashLeft = new Integer(prop.getProperty("player_start_amount").trim());
		_number = number;
		setPiecePosition(9);
	}
	
	public String getName() 
	{
		return _name;
	}

	public void setName(String _name) 
	{
		this._name = _name;
	}

	public int getNumber()
	{
		return _number;
	}

	public void setNumber(int _number)
	{
		this._number = _number;
	}
	
	public int getPiecePosition()
	{
		return _piecePosition;
	}

	public void setPiecePosition(int _piecePosition)
	{
		this._piecePosition = _piecePosition;
	}
	
	public int getCash()
	{
		return _cashLeft;
	}

	// Adds Cash to the player
	public void makePayment (int payment)
	{
		_cashLeft = _cashLeft - payment;
	}
	
	// Debits Cash from the player
	public void receivePayment (int payment)
	{
		_cashLeft = _cashLeft + payment;
	}
	
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
