package model;

public class ChanceHouse implements ICard {
	

	private Chance raffledChance ;

	@Override
	public ActionOnHouse action(Player playerHere) {
		return ActionOnHouse.GetChance;
				
	}
	
	

	@Override
	public String getImagePath() {
		
		return raffledChance.getImagePath();
	}


	public void setRaffledChance(Chance raffledChance) {
		this.raffledChance = raffledChance;
	}

}
