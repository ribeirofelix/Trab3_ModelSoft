package model;

public enum Money {
	One (1),
	Five(5),
	Ten(10),
	Fifty(50),
	Hundred(100),
	FiveHundred(500);
	
	private int value;
	private Money(int value){
		this.value = value;
	}
	public int getValue(){
		return value;
	}
}
