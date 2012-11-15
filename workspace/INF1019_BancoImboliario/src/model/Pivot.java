package model;

import java.awt.Color;

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
	public Color getColor(){
		Color colorReturn ;
		switch (this) {
		case Black:
			colorReturn = Color.BLACK;
			break;

		case Orange:
			colorReturn = Color.ORANGE;
			break;
		case Yellow:
			colorReturn = Color.YELLOW;
			break;
		case Purple:
			colorReturn = Color.PINK;
			break;
		case Red:
			colorReturn = Color.RED;			
			break;
		case Blue:
			colorReturn = Color.BLUE;			
			break;
		default:
			colorReturn = Color.WHITE;
			break;
		}
		return colorReturn;
	}

}
