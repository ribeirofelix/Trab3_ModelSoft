package model;

public interface ICard {
	
	ActionOnHouse action(Player playerHere);
	String getImagePath();
}
