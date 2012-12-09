package com.monopoly.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import com.monopoly.model.CardType;
import com.monopoly.model.ChanceCard;

public class ChanceCardLoader 	
{
	static public List<ChanceCard> LoadCards () throws FileNotFoundException, IOException
	{
		List<ChanceCard> loadedCards;
		HashMap <String, String> keyValuePair;
		String			 fileLine;
		String			 key;
		String		     value;
		String			 propertyName;
		String[]         cardAttributes;
		String[]         keyValue;
		String			 cardType;
		Properties       cardLoader;
		Pattern          outerRegex;
		Pattern			 innerRegex;
		
		// Creating Needed Objects
		loadedCards  = new ArrayList<ChanceCard>();
		keyValuePair = new HashMap<String, String>();
		outerRegex   = Pattern.compile("#");
		innerRegex   = Pattern.compile(":");
		cardLoader   = new Properties();
		cardLoader.load ( new FileInputStream("config.properties"));
		
		// Iterating over config.properties entries
		for (int index = 0 ; index < 30 ; index ++)
		{
			// Creating name of property to be fetched
			propertyName = "chance_" + index;
			
			fileLine = cardLoader.getProperty (propertyName);
			
			cardAttributes = outerRegex.split (fileLine);
			
			for (String attribute : cardAttributes )
			{
				keyValue = innerRegex.split (attribute);
				
				// If there is Key and Value on the splited string array
				if (keyValue.length == 2)
				{
					key   = keyValue[0];
					value = keyValue[1];
					
					keyValuePair.put (key, value);
				}
			}
			
			cardType          = keyValuePair.get("type");
			String   cardText = keyValuePair.get("text");
			int	     cost     = Integer.valueOf(keyValuePair.get("value"));
			CardType type;
			
			if (cardType.equals ("0"))
			{
				type = CardType.PlayerPays;
			}
			else if (cardType.equals("1"))
			{
				type = CardType.PlayerReceives;
			}
			else if (cardType.equals("2"))
			{
				type = CardType.EveryonePays;
			}
			else if (cardType.equals("3"))
			{
				type = CardType.EveryoneReceives;
			}
			else if (cardType.equals("4"))
			{
				type = CardType.PrisonExitVoucher;
			}
			else if (cardType.equals("5"))
			{
				type = CardType.GoToStartPoint;
			}
			else 
			{
				type = CardType.GoToPrison;
			}
			
			// Creating instance of card to be saved
			ChanceCard card = new ChanceCard (type, cost);
			card.setCardText (cardText);
			
			// Adding card to list
			loadedCards.add (card);
		}
		
		// Returns assembled list of cards
		return loadedCards;
	}
}
