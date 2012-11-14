package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class FactoryICard {
	private static List<ICard> chanceCards;
	private static List<ICard> terrainsCards;
	private static List<ICard> companyCards;
	
	public static void createAllTheCards(){
		
		chanceCards = createChanceCards();
	    terrainsCards = createTerrainsCards();
		companyCards = createCompanyCards();
	}
	
	public static List<ICard> getChangeCards(){
		return chanceCards;
	}
	
	public static List<ICard> getBoardCards(){
		return null;
	}
	
	private static List<ICard> createCompanyCards() {
		
		try {
			List<ICard> cards = new ArrayList<ICard>();
			
			BufferedReader chanceText = new BufferedReader( new FileReader("txt//files//Companies.txt"));
			String line = chanceText.readLine();
			
			for (int i = 1; i < 7 && chanceText.ready(); i++, line = chanceText.readLine()){
				
				int indexOfDotComa = line.indexOf(";");
				int indexOfSecondDotComa = line.indexOf(";", indexOfDotComa);
				
				PropertyCompany company = new PropertyCompany(Integer.parseInt(line.substring(indexOfDotComa, indexOfSecondDotComa)));
				
				company.setImagePath("images//property cards//company"+i+".png");
				company.setMortgageValue( Integer.parseInt( line.substring(indexOfSecondDotComa)));
				
				cards.add(company);
			}
			
			chanceText.close();
		
			return cards;
			
		} catch (IOException e){
			System.out.println("Deu ruim: " + e.getLocalizedMessage());
		}
		return null;
	}

	private static List<ICard> createTerrainsCards() {
			
			try {
				List<ICard> cards = new ArrayList<ICard>();
				
				BufferedReader chanceText = new BufferedReader( new FileReader("txt//files//Terrains.txt"));
				String line = chanceText.readLine();
				String fileName;
				
				
				for (int i = 1; i < 23 && chanceText.ready(); i++, line = chanceText.readLine()){
					
					int indexOfDotComa = line.indexOf(";");
					int indexOfSecondDotComa = line.indexOf(";", indexOfDotComa);
					fileName = line.substring(0, indexOfDotComa);
					
					
					/* read all the line */
					String group = line.substring(indexOfDotComa, indexOfSecondDotComa);
					indexOfDotComa = indexOfSecondDotComa;
					indexOfSecondDotComa = line.indexOf(";", indexOfDotComa);
					
					int rent = Integer.parseInt(line.substring(indexOfDotComa, indexOfSecondDotComa));
					indexOfDotComa = indexOfSecondDotComa;
					indexOfSecondDotComa = line.indexOf(";", indexOfDotComa);
					
					int rent1 = Integer.parseInt(line.substring(indexOfDotComa, indexOfSecondDotComa));
					indexOfDotComa = indexOfSecondDotComa;
					indexOfSecondDotComa = line.indexOf(";", indexOfDotComa);
					
					int rent2 = Integer.parseInt(line.substring(indexOfDotComa, indexOfSecondDotComa));
					indexOfDotComa = indexOfSecondDotComa;
					indexOfSecondDotComa = line.indexOf(";", indexOfDotComa);
					
					int rent3 = Integer.parseInt(line.substring(indexOfDotComa, indexOfSecondDotComa));
					indexOfDotComa = indexOfSecondDotComa;
					indexOfSecondDotComa = line.indexOf(";", indexOfDotComa);
					
					int rent4 = Integer.parseInt(line.substring(indexOfDotComa, indexOfSecondDotComa));
					indexOfDotComa = indexOfSecondDotComa;
					indexOfSecondDotComa = line.indexOf(";", indexOfDotComa);
					
					int rentHotel = Integer.parseInt(line.substring(indexOfDotComa, indexOfSecondDotComa));
					indexOfDotComa = indexOfSecondDotComa;
					indexOfSecondDotComa = line.indexOf(";", indexOfDotComa);
					
					int housePrice = Integer.parseInt(line.substring(indexOfDotComa, indexOfSecondDotComa));
					indexOfDotComa = indexOfSecondDotComa;
					indexOfSecondDotComa = line.indexOf(";", indexOfDotComa);
					
					int hotelPrice = Integer.parseInt(line.substring(indexOfDotComa, indexOfSecondDotComa));
					indexOfDotComa = indexOfSecondDotComa;
					indexOfSecondDotComa = line.indexOf(";", indexOfDotComa);
					
					int mortgage = Integer.parseInt(line.substring(indexOfDotComa));
					
					PropertyTerrain terrain = new PropertyTerrain(group, rent, rent1, rent2, rent3, rent4, rentHotel, housePrice, 
																	hotelPrice, mortgage, "images//terrain cards//"+fileName+".png");
					
					terrain.setMortgageValue( Integer.parseInt( line.substring(indexOfSecondDotComa)));
					
					cards.add(terrain);
				}
				
				chanceText.close();
			
				return cards;
				
			} catch (IOException e){
				System.out.println("Deu ruim: " + e.getLocalizedMessage());
			}
			return null;
		}
	
	private static List<ICard> createChanceCards() {
		
		try {
			List<ICard> cards = new ArrayList<ICard>();
			
			BufferedReader chanceText = new BufferedReader( new FileReader("txt//files//Chance.txt"));
			String line = chanceText.readLine();
			
			for (int i = 1; i < 31 && chanceText.ready(); i++, line = chanceText.readLine()){
				
				int indexOfDotComa = line.indexOf(";");
				Chance chance = new Chance ("images//chance cards//chance"+i+".png",
												line.substring(0, indexOfDotComa),
												Integer.parseInt(line.substring(indexOfDotComa)));
				
				cards.add(chance);
			}
			
			chanceText.close();
		
			return cards;
			
		} catch (IOException e){
			System.out.println("Deu ruim: " + e.getLocalizedMessage());
		}
		
		return null;

	}
	
}
