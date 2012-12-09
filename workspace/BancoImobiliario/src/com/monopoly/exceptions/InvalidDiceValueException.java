package com.monopoly.exceptions;

@SuppressWarnings("serial")
public class InvalidDiceValueException extends Exception
{
	public InvalidDiceValueException()
	{
		super("Invalid Dice Value!");
	}
}
