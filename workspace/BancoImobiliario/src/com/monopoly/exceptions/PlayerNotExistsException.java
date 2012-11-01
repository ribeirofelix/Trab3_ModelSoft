package com.monopoly.exceptions;

@SuppressWarnings("serial")
public class PlayerNotExistsException extends Exception
{
	public PlayerNotExistsException()
	{
		super("The selected player does not exist");
	}
}
