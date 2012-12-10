package model;

public class House {
	
	
	private  ICard card ;
	
	public House(int position , ICard card) {
		
		this.card = card ;
	}

	public ICard getCard() {
		
		return card;
	}

	
}
