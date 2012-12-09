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
		this.price = mortgage * 2;
		this.numberOfHouses = 0;
		super.setMortgageValue(mortgage);
		super.setImagePath(imagePath);
		
	}
	
	public String getNumberOfHouseOrHotel (){
		if (this.hasHotel){
			return "Hotel";
		}
		else{
			if (this.numberOfHouses == 0){
				return "Aluguel";
			}
			
			if (this.numberOfHouses == 1){
				return "Uma casa";
			}
			
			return Integer.toString(numberOfHouses) + " casas";
		}		
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

	public boolean buildHouse() {
		if(!this.hasHotel){
			
	
			if (this.numberOfHouses < 4){
				if (this.playerOwner.removeMoney(this.housePrice)){
					this.numberOfHouses++;
					
					return true;
				}
			}
			
			else {
				
				if (this.playerOwner.removeMoney(this.hotelPrice)){
					this.hasHotel = true;
					
					return true;
				}				
			}
		}
		
		return false;
	}

	public int getHouseNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean hasHotel() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getGroup() {
		return group;
	}

	@Override
	public void action(Player playerHere) {
		// TODO Auto-generated method stub
		
	}
}
