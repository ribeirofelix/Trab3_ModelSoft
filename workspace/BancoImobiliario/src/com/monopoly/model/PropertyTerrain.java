package com.monopoly.model;

public class PropertyTerrain
{
	private TerrainColor _color;
	private int _acquisitionCost;
	private int _plainRent;
	private int _oneHouseRent;
	private int _twoHousesRent;
	private int _threeHouseRent;
	private int _fourHousesRent;
	private int _hotelRent;
	private int _houseCost;
	private int _hotelCost;
	private int _mortgage; // Hipoteca
	private int _housesBuilt; // Counts number of built houses
	private boolean _hasHotel; // True if there is an hotel, false otherwise
	
	// Class Constructor
	public PropertyTerrain (String name, int acquisitionCost) 
	{	
		// Default Values
		setHousesBuilt(0);
		setHasHotel(false);
		
		// Setting Acquisiton Cost
		setAcquisitionCost(acquisitionCost);
	}
	
	
	// Sets the value of rent for each state of the terrain.
	// If the terrain has 1 to 4 houses, or a hotel
	public void SetRentValues (int plain, int oneHouse, int twoHouses, int threeHouses, int fourHouses, int hotel)
	{
		setPlainRent(plain);
		setOneHouseRent(oneHouse);
		setTwoHousesRent(twoHouses);
		setThreeHouseRent(threeHouses);
		setFourHousesRent(fourHouses);
		setHotelRent(hotel);
	}
	
	// Sets the Cost per house, Hotel Cost and Mortage Values
	public void SetCosts (int costPerHouse, int hotelCost, int mortgage)
	{
		setHouseCost(costPerHouse);
		setHotelCost(hotelCost);
		setMortgage(mortgage);
	}


	public TerrainColor getColor() 
	{
		return _color;
	}


	public void setColor(TerrainColor _color) 
	{
		this._color = _color;
	}


	public int getAcquisitionCost() 
	{
		return _acquisitionCost;
	}


	public void setAcquisitionCost(int _acquisitionCost) 
	{
		this._acquisitionCost = _acquisitionCost;
	}


	public int getPlainRent() 
	{
		return _plainRent;
	}


	public void setPlainRent(int _plainRent) 
	{
		this._plainRent = _plainRent;
	}


	public int getOneHouseRent() 
	{
		return _oneHouseRent;
	}


	public void setOneHouseRent(int _oneHouseRent)
	{
		this._oneHouseRent = _oneHouseRent;
	}


	public int getTwoHousesRent() 
	{
		return _twoHousesRent;
	}


	public void setTwoHousesRent(int _twoHousesRent) 
	{
		this._twoHousesRent = _twoHousesRent;
	}


	public int getThreeHouseRent()
	{
		return _threeHouseRent;
	}


	public void setThreeHouseRent(int _threeHouseRent) 
	{
		this._threeHouseRent = _threeHouseRent;
	}


	public int getFourHousesRent() 
	{
		return _fourHousesRent;
	}


	public void setFourHousesRent(int _fourHousesRent)
	{
		this._fourHousesRent = _fourHousesRent;
	}


	public int getHotelRent()
	{
		return _hotelRent;
	}


	public void setHotelRent(int _hotelRent) 
	{
		this._hotelRent = _hotelRent;
	}


	public int getHouseCost() 
	{
		return _houseCost;
	}


	public void setHouseCost(int _houseCost) 
	{
		this._houseCost = _houseCost;
	}


	public int getHotelCost() 
	{
		return _hotelCost;
	}


	public void setHotelCost(int _hotelCost)
	{
		this._hotelCost = _hotelCost;
	}


	public int getMortgage()
	{
		return _mortgage;
	}


	public void setMortgage(int _mortgage) 
	{
		this._mortgage = _mortgage;
	}


	public int getHousesBuilt() 
	{
		return _housesBuilt;
	}


	public void setHousesBuilt(int _housesBuilt)
	{
		this._housesBuilt = _housesBuilt;
	}


	public boolean isHasHotel()
	{
		return _hasHotel;
	}


	public void setHasHotel(boolean _hasHotel)
	{
		this._hasHotel = _hasHotel;
	}
}
