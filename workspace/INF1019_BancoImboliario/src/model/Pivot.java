package model;

public enum Pivot {
	Black ("images//black_pin.png" ),
	Orange ("images//orange_pin.png" ),
	Yellow ( "images//yellow_pin.png"),
	Purple ( "images//purple_pin.png"),
	Red ( "images//red_pin.png"),
	Blue ( "images//blue_pin.png" );
	
	private String value;
	private Pivot(String value){
		this.value = value;
	}
	public String getValue(){
		return value;
	}

}
