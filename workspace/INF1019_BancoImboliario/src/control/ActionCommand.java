package control;

public enum ActionCommand {

	RollDices("roll"),
	BuyProperty("buy"),
	BuildHouse("build"),
	ChooseNegociationType("negType"),
	ShowPropertiesOfSeller("showPropSeller"),
	FinalizeNegociation("finalizeNeg");
	
	private String value;
	private ActionCommand(String value){
		this.value = value;
	}
	public String getValue(){
		return value;
	}
}
