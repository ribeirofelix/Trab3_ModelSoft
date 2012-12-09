package com.monopoly.exceptions;

@SuppressWarnings("serial")
public class InvalidPlayersQuantityException extends Exception
{
	public InvalidPlayersQuantityException()
	{
		super("The number of players is lower/higher than the limit (2-6)");
	}
}
