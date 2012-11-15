package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;


public class FactoryICard {
	private  List<ICard> chanceCards;
	private  List<ICard> terrainsCards;
	private  List<ICard> companyCards;
	
	public  void createAllTheCards(){
		
		chanceCards = createChanceCards();
	    terrainsCards = createTerrainsCards();
		companyCards = createCompanyCards();
	}
	
	public  List<ICard> getChangeCards(){
		return chanceCards;
	}
	
	public  List<ICard> getBoardCards(){
		List<ICard> allTheCards = new ArrayList<ICard>();
		
		for (int i = 0, chanceIdx = 0, terrainIdx = 0, companyIdx = 0; i < 40; i++){
			
			switch (i){
			/* Start point */
			case 0:	
				allTheCards.add( new Profit (200));
				break;
				
			/* Chance */
			case 2: case 12: case 16: case 22: case 27: case 37:
				allTheCards.add(chanceCards.get(chanceIdx++));
				break;
				
			/* Company */
			case 5: case 7: case 15: case 25: case 32: case 35:
				allTheCards.add(companyCards.get(companyIdx++));
				break;
				
			/* Profit */
			case 18:
				allTheCards.add( new Profit(200));
				break;
				
			/* Taxes */	
			case 24:
				allTheCards.add( new Tribute(200));
				break;
				
			/* Prision or free stop - nothing hapens*/
			case 10: case 20:
				allTheCards.add(null);
				break;
			
			/* Busted */
			case 30:
				allTheCards.add(new GoToPrision());
				break;
			
			/* Terrain */
			default:
				allTheCards.add(terrainsCards.get(terrainIdx++));
			}	
		}
		
		return allTheCards;
	}
	
	private  List<ICard> createCompanyCards() {
		
		try {
			List<ICard> cards = new ArrayList<ICard>();
			
			BufferedReader chanceText = new BufferedReader( new FileReader("txt files//Companies.txt"));
			String line = null;
			int i = 1;
			while( ( line = chanceText.readLine() ) != null){
				
				int  multiplier = 1 , mortgage = 2 ;
				
				String[] splitedLine = line.split(";");
				
				PropertyCompany company = new PropertyCompany("images//companies cards//company"+ i +".png" ,
															  Integer.parseInt(splitedLine[mortgage]) , Integer.parseInt(splitedLine[multiplier]) );
				
				
				cards.add(company);
				i++;
			}
			
			chanceText.close();
		
			return cards;
			
		} catch (IOException e){
			System.out.println("Deu ruim: " + e.getLocalizedMessage());
		}
		return null;
	}

	private  List<ICard> createTerrainsCards() {
			
			try {
				List<ICard> cards = new ArrayList<ICard>();
				
				BufferedReader terrainsFile = new BufferedReader( new FileReader("txt files//Terrains.txt"));
				String line = null ;
				
				while( (line = terrainsFile.readLine()) != null){
					int   name = 0 
							, group = 1 
							, rent = 2 
							, rent1 = 3 
							, rent2 = 4
							, rent3 = 5
							, rent4 = 6
							, hotel = 7
							, priceHouse = 8
							, priceHotel = 9
							, mortgage = 10 ;
							
						
						String[] splitedLine = line.split(";") ;
						
						PropertyTerrain terrain = new PropertyTerrain(splitedLine[group], Integer.parseInt( splitedLine[rent] ),
												Integer.parseInt(splitedLine[rent1] ), Integer.parseInt(splitedLine[rent2] ), 
												Integer.parseInt(splitedLine[rent3] ), Integer.parseInt( splitedLine[rent4] ) ,
												Integer.parseInt(splitedLine[hotel] ), Integer.parseInt(splitedLine[priceHouse] ), 
												Integer.parseInt(splitedLine[priceHotel] ), Integer.parseInt( splitedLine[mortgage] ), 
												"images//terrain cards//"+splitedLine[name]+".png");

						
						cards.add(terrain);
						
					
				}			
				
				
				terrainsFile.close();
			
				return cards;
				
			} catch (IOException e){
				System.out.println("Deu ruim: " + e.getLocalizedMessage());
			}
			return null;
		}
	
	private  List<ICard> createChanceCards() {
		
		try {
			List<ICard> cards = new ArrayList<ICard>();
			
			BufferedReader chanceText = new BufferedReader( new FileReader("txt files//Chance.txt"));
			String line = null ;
			int i =1 ;
			while (( line = chanceText.readLine() ) != null) {
				int operator = 0 , value = 1 ;
				
				String[] splitedLine = line.split(";");
				
				
				Chance chance = new Chance ("images//chance cards//chance"+i+".png",
												splitedLine[operator],
												Integer.parseInt( splitedLine[value] ) );
				
				cards.add(chance);
				i++;
			}
			
			
			chanceText.close();
		
			return cards;
			
		} catch (IOException e){
			System.out.println("Deu ruim: " + e.getLocalizedMessage());
		}
		
		return null;

	}
	
}
