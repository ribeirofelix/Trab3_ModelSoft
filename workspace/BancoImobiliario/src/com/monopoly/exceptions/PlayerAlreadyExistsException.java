package com.monopoly.exceptions;

@SuppressWarnings("serial")
public class PlayerAlreadyExistsException extends Exception
{
	public PlayerAlreadyExistsException()
	{
		super("The player to be added already exists");
	}
}
