package com.monopoly.components;

import java.awt.Graphics;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.ImageIcon;

import com.monopoly.exceptions.InvalidDiceValueException;

@SuppressWarnings("serial")
public class DicePanel extends ImagePanel
{
	private int _diceValue;
	
	public DicePanel ()
	{
		super();
		
		// Loads config.properties file
		Properties prop = new Properties();
		
		try 
		{
			prop.load(new FileInputStream("config.properties"));
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
	
	public int getDiceValue()
	{
		return _diceValue;
	}
	
	public void setDiceValue(int value) throws InvalidDiceValueException
	{
		Properties prop = new Properties();
		
		if (value < 1 || value > 6) throw new InvalidDiceValueException();
			
		_diceValue = value;
		
		try 
		{
			prop.load(new FileInputStream("config.properties"));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		// Loading Images based on dice results
		switch (_diceValue)
		{
			case 1:
				_image = new ImageIcon(prop.getProperty ("dice_one")).getImage();
			break;
			
			case 2:
				_image = new ImageIcon(prop.getProperty ("dice_two")).getImage();
			break;
			
			case 3:
				_image = new ImageIcon(prop.getProperty ("dice_three")).getImage();
			break;
			
			case 4:
				_image = new ImageIcon(prop.getProperty ("dice_four")).getImage();
			break;
			
			case 5:
				_image = new ImageIcon(prop.getProperty ("dice_five")).getImage();
			break;
			
			case 6:
				_image = new ImageIcon(prop.getProperty ("dice_six")).getImage();
			break;
			
			default:
				_image = new ImageIcon(prop.getProperty ("dice_error")).getImage();
			break;
		}
		
		this.revalidate();
	}
	
	@Override
	public void paintComponent (Graphics g)
	{
		Properties prop = new Properties();
		
		try 
		{
			prop.load(new FileInputStream("config.properties"));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		// Loading Images based on dice results
		switch (_diceValue)
		{
			case 1:
				_image = new ImageIcon(prop.getProperty ("dice_one")).getImage();
			break;
			
			case 2:
				_image = new ImageIcon(prop.getProperty ("dice_two")).getImage();
			break;
			
			case 3:
				_image = new ImageIcon(prop.getProperty ("dice_three")).getImage();
			break;
			
			case 4:
				_image = new ImageIcon(prop.getProperty ("dice_four")).getImage();
			break;
			
			case 5:
				_image = new ImageIcon(prop.getProperty ("dice_five")).getImage();
			break;
			
			case 6:
				_image = new ImageIcon(prop.getProperty ("dice_six")).getImage();
			break;
			
			default:
				_image = new ImageIcon(prop.getProperty ("dice_error")).getImage();
			break;
		}
		
		// Sets size of image based on Width and Height of the Panel
		g.drawImage (_image, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}
