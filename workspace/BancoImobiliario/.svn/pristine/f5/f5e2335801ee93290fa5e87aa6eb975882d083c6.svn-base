package com.monopoly.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import com.monopoly.model.CompanyTerrain;
import com.monopoly.model.ITerrain;
import com.monopoly.model.PropertyTerrain;
import com.monopoly.model.TerrainType;
import com.monopoly.model.UnbuyableTerrain;

public class TerrainLoader 
{
	 static public List<ITerrain> LoadProperties () throws FileNotFoundException, IOException
	 {
		List<ITerrain> boardTerrains;
		ITerrain       terrain;
		HashMap <String, String> keyValuePair;
		Properties     propLoader;
		String         propertyName;
		String         propertyValue;
		String         key;
		String         value;
		String         type;
		String[]       propertyAttributes;
		String[]       keyValue;
		Pattern        regex;
		int            terrainIndex;
		
		// Creating empty list of terrains, property loader
		boardTerrains = new ArrayList<ITerrain>();
		keyValuePair  = new HashMap<String, String>();
		propLoader    = new Properties();
		terrain		  = null;
		
		// Reading Properties from config file
		propLoader.load( new FileInputStream ("config.properties"));
		
		for (terrainIndex = 0 ; terrainIndex < 40 ; terrainIndex++)
		{
			// Generating property name
			propertyName = "terrain_" + terrainIndex;
			
			//  Getting Property value from config file
			propertyValue = propLoader.getProperty (propertyName);
			
			// Creating Regex Instance
			regex = Pattern.compile ("#");
			
			// Spliting properties
			propertyAttributes = regex.split (propertyValue);
			
			// Creating new Regex for second split
			regex = Pattern.compile(":");
			
			// Iterating over attribute of the property
			for (String prop : propertyAttributes)
			{
				// Spliting property text into key and value
				keyValue = regex.split (prop);

				// Building Dictionary
				if (keyValue.length == 2)
				{
					key   = keyValue[0];
					value = keyValue[1];
					
					keyValuePair.put (key, value);
				}
			}
			
			// Creating Instance of terrain based on the type of the terrain
			type = keyValuePair.get("type");
			
			if (type.equals ("0")) // Property Terrain
			{
				int aquisitionCost  = Integer.valueOf (keyValuePair.get("price"));
				int plainRent       = Integer.valueOf ( keyValuePair.get("rent"));
				int oneHouseRent    = Integer.valueOf ( keyValuePair.get("one"));
				int twoHousesRent   = Integer.valueOf ( keyValuePair.get("two"));
				int threeHousesRent = Integer.valueOf ( keyValuePair.get("three"));
				int fourHousesRent  = Integer.valueOf ( keyValuePair.get("four"));
				int hotelRent       = Integer.valueOf ( keyValuePair.get("with_hotel"));
				int houseCost       = Integer.valueOf ( keyValuePair.get("each_house"));
				int hotelCost       = Integer.valueOf ( keyValuePair.get("hotel"));
				int mortgage        = Integer.valueOf ( keyValuePair.get("mortgage"));   
				int color           = Integer.valueOf ( keyValuePair.get("color"));  
				
				// Creating terrain instance
				terrain = new PropertyTerrain ("", aquisitionCost);
				((PropertyTerrain)terrain).setRentValues(plainRent, oneHouseRent, twoHousesRent, threeHousesRent, fourHousesRent, hotelRent);
				((PropertyTerrain)terrain).setCosts(houseCost, hotelCost, mortgage);
				((PropertyTerrain)terrain).setColor(color);
			}
			else if (type.equals ("1")) // Company Terrain
			{
				int price       = Integer.valueOf (keyValuePair.get("price"));
				int multiplier  = Integer.valueOf (keyValuePair.get("multiplier"));
				int mortgage    = Integer.valueOf (keyValuePair.get("mortgage"));
				
				// Creating terrain instance
				terrain = new CompanyTerrain("", price, mortgage, multiplier);
			}
			else if (type.equals("2"))
			{
				terrain = new UnbuyableTerrain(TerrainType.Chance, "");
			}
			else if (type.equals("3"))
			{
				terrain = new UnbuyableTerrain(TerrainType.IncomeTax, "");
			}
			else if (type.equals("4"))
			{
				terrain = new UnbuyableTerrain(TerrainType.Profit, "");
			}
			else if (type.equals("5"))
			{
				terrain = new UnbuyableTerrain(TerrainType.FreeStop, "");
			}
			else if (type.equals("6"))
			{
				terrain = new UnbuyableTerrain(TerrainType.StartPoint, "");
			}
			else if (type.equals("7"))
			{
				terrain = new UnbuyableTerrain(TerrainType.PrisonVisitors, "");
			}
			else if (type.equals("8"))
			{
				terrain = new UnbuyableTerrain(TerrainType.GoToPrison, "");
			}
			else if (type.equals("9"))
			{
				terrain = new UnbuyableTerrain(TerrainType.Prison, "");
			}
			
			// Adding Instance of terrain to list
			boardTerrains.add (terrain);
		}
		
		return boardTerrains;
	 }
}
