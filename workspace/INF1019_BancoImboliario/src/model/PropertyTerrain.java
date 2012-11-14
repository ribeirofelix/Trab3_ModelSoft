package model;

public class PropertyTerrain extends Property implements  ICard {
	private String group;
	private int numberOfHouses;
	private int rent;
	private int rent1House;
	private int rent2Houses;
	private int rent3Houses;
	private int rent4Houses;
	private int rentHotel;
	private int housePrice;
	private int hotelPrice;
	private boolean hasHotel = false;
		
	
	public PropertyTerrain (String group, int rent, int rent1, int rent2, int rent3, int rent4, int rentHotel, int housePrice, int hotelPrice, int mortgage, String imagePath){
		this.group = group;
		this.rent = rent;
		this.rent1House = rent1;
		this.rent2Houses = rent2;
		this.rent3Houses = rent3;
		this.rent4Houses = rent4;
		this.rentHotel = rentHotel;
		this.housePrice = housePrice;
		this.hotelPrice = hotelPrice;
		
		super.setMortgageValue(mortgage);
		super.setImagePath(imagePath);
		
	}
	
	public int getRentValue() {
	
		if(hasHotel){
			return rentHotel;
		}
		
		switch (numberOfHouses){
		case 1:
			return rent1House;
		case 2:
			return rent2Houses; 
		case 3:	
			return rent3Houses; 
		case 4:
			return rent4Houses;
		default:
			return rent;
		}
		
	}

	public void buildHouse() {
		if(!hasHotel){
			if (numberOfHouses < 4){
				numberOfHouses++;
			
			}
			else {
				hasHotel = true;

			}
		
			//debita do fdp
		}
	}

	public int getHouseNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean hasHotel() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		
	}

	public String getGroup() {
		return Group;
	}
}
