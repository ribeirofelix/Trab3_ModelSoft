package model;

public class FreePass implements ICard {

	@Override
	public ActionOnHouse action(Player playerHere) {
		
		return ActionOnHouse.NothingToDo;
	}

	@Override
	public String getImagePath() {
		// TODO Auto-generated method stub
		return null;
	}

}
