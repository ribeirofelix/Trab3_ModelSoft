package model;

import java.util.ArrayList;

public class Board {
	private ArrayList<House> houses = new ArrayList<>(40);
	
	
	public Board() {
		for (int i = 0; i < 40 ; i++) {
			
			houses.add(new House(i,new PropertyCompany(i) ));
		}
	}
	
	public House getHouseOnThisPosition (int index){
		return houses.get(index);
	}
}
