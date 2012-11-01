package com.monopoly.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.monopoly.view.MainWindow;

public class MainWindowTester {

	/**
	 * Allows developers to test the MainWindow class
	 */
	public static void main(String[] args) 
	{
		try 
		{
			@SuppressWarnings("unused")
			MainWindow hndMainWindow = new MainWindow();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

	}

}
