package com.monopoly.model;

public class PropertyTerrain implements ITerrain
{
	private TerrainColor _color;
	private int _acquisitionCost;
	private int _plainRent;
	private int _oneHouseRent;
	private int _twoHousesRent;
	private int _threeHousesRent;
	private int _fourHousesRent;
	private int _hotelRent;
	private int _houseCost;
	private int _hotelCost;
	private int _mortgage;     // Hipoteca
	private int _housesBuilt;  // Counts number of built houses
	private boolean _hasHotel; // True if there is an hotel, false otherwise
	private int _ownerId;
	
	// Class Constructor
	public PropertyTerrain (String name, int acquisitionCost) 
	{	
		// Default Values
		setHousesBuilt(0);
		setHasHotel(false);
		
		// Setting Acquisiton Cost
		setAcquisitionCost (acquisitionCost);
	}
		
	// Sets the value of rent for each state of the terrain.
	// If the terrain has 1 to 4 houses, or a hotel
	public void setRentValues (int plain, int oneHouse, int twoHouses, int threeHouses, int fourHouses, int hotel)
	{
		setPlainRent(plain);
		setOneHouseRent(oneHouse);
		setTwoHousesRent(twoHouses);
		setThreeHouseRent(threeHouses);
		setFourHousesRent(fourHouses);
		setHotelRent(hotel);
	}
	
	// Sets the Cost per house, Hotel Cost and Mortage Values
	public void setCosts (int costPerHouse, int hotelCost, int mortgage)
	{
		setHouseCost(costPerHouse);
		setHotelCost(hotelCost);
		setMortgage(mortgage);
	}

	// Gets the color of this terrain. 
	// Color is used to group terrains
	public TerrainColor getColor() 
	{
		return _color;
	}

	// Sets the color of this terrain. 
	// Color is used to group terrains
	public void setColor (int color) 
	{
		switch (color)
		{
			case 0:
				this._color = TerrainColor.Orange;
			break;

			case 1:
				this._color = TerrainColor.Purple;
			break;
			
			case 2:
				this._color = TerrainColor.Red;
			break;

			case 3:
				this._color = TerrainColor.Yellow;
			break;
			
			case 4:
				this._color = TerrainColor.Green;
			break;
			
			case 5:
				this._color = TerrainColor.Blue;
			break;
			
			case 6:
				this._color = TerrainColor.Pink;
			break;
			
			case 7:
				this._color = TerrainColor.DarkBlue;
			break;
		}
	}

	// Gets the cost to buy this terrain
	public int getAcquisitionCost() 
	{
		return _acquisitionCost;
	}

	// Sets the cost to buy this terrain
	public void setAcquisitionCost(int _acquisitionCost) 
	{
		this._acquisitionCost = _acquisitionCost;
	}

	// Gets the rent value of this terrain with no houses built
	public int getPlainRent() 
	{
		return _plainRent;
	}

	// Sets the rent value of this terrain with no houses built
	public void setPlainRent(int _plainRent) 
	{
		this._plainRent = _plainRent;
	}

	// Calculates the rent of this terrain based on the current state
	// (number of houses built / if there is a hotel or not)
	public int calculateRent ()
	{
		int rent;
		
		switch (_housesBuilt)
		{
			case 0 : // no houses built
				rent =  _plainRent;
			break;
			
			case 1: // 1 house built
				rent = _oneHouseRent;
			break;
			
			case 2: // 2 house built
				rent = _twoHousesRent;
			break;
			
			case 3: // 3 house built
				rent = _threeHousesRent;
			break;
			
			case 4: // 4 house built
				rent = _fourHousesRent;
			break;
			
			default:
				rent = _plainRent;
			break;
		}
		
		// Checking if there's any hotel built
		// If true, rent value is calculated using the hotel rent cost
		if (_hasHotel)
		{
			rent = _hotelRent;
		}
		
		return rent;
	}

	// Sets the price to rent this terrain with one house built
	public void setOneHouseRent(int _oneHouseRent)
	{
		this._oneHouseRent = _oneHouseRent;
	}

	// Sets the price to rent this terrain with two house built
	public void setTwoHousesRent(int _twoHousesRent) 
	{
		this._twoHousesRent = _twoHousesRent;
	}

	// Sets the price to rent this terrain with three house built
	public void setThreeHouseRent(int _threeHouseRent) 
	{
		this._threeHousesRent = _threeHouseRent;
	}

	// Sets the price to rent this terrain with four house built
	public void setFourHousesRent(int _fourHousesRent)
	{
		this._fourHousesRent = _fourHousesRent;
	}

	// Sets the price to rent this terrain with a hotel built
	public void setHotelRent(int _hotelRent) 
	{
		this._hotelRent = _hotelRent;
	}

	// Gets the cost to build each house
	public int getHouseCost() 
	{
		return _houseCost;
	}

	// Sets the cost to build each house
	public void setHouseCost(int _houseCost) 
	{
		this._houseCost = _houseCost;
	}

	// Gets the cost to build a hotel
	public int getHotelCost() 
	{
		return _hotelCost;
	}

	// Sets the cost to build a hotel
	public void setHotelCost(int _hotelCost)
	{
		this._hotelCost = _hotelCost;
	}

	// Gets the mortgage value
	public int getMortgage()
	{
		return _mortgage;
	}

	// Sets the mortgage value
	public void setMortgage(int _mortgage) 
	{
		this._mortgage = _mortgage;
	}

	// Gets number of built houses in this terrain
	public int getHousesBuilt() 
	{
		return _housesBuilt;
	}

	// Sets number of built houses in this terrain
	public void setHousesBuilt(int _housesBuilt)
	{
		this._housesBuilt = _housesBuilt;
	}

	// True if there is a hotel built, false otherwise
	public boolean hasHotel()
	{
		return _hasHotel;
	}

	// Sets the HasHotel attribute to the value received
	public void setHasHotel (boolean _hasHotel)
	{
		this._hasHotel = _hasHotel;
	}

	public int getOwnerId()
	{
		return _ownerId;
	}
	
	public void setOwnerId(int id)
	{
		_ownerId = (id >= 1 && id <= Game.getInstance().getPlayers().size()) ? id : -1;
	}
	
	public boolean hasOwner()
	{
		if (_ownerId >= 1 && _ownerId <= Game.getInstance().getPlayers().size())
		{
			return true;
		}
		
		return false;
	}
	
	// Gets the type of this terrain
	@Override
	public TerrainType getType() 
	{
		return TerrainType.Property;
	}
}
