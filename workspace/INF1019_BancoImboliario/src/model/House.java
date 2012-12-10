package model;

public class House {
	
	private int position ;
	private  ICard card ;
	
	public House(int position , ICard card) {
		this.position = position;
		this.card = card ;
	}

	public ICard getCard() {
		
		return card;
	}

	
}
