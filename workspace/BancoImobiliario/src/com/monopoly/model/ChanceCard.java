package com.monopoly.model;

public class ChanceCard 
{
	private CardType _cardType;
	private String   _cardText;
	private int      _paymentAmmount;
	
	// Class Constructor
	public ChanceCard (CardType cardType, int paymentAmmount)
	{
		setCardType(cardType);
		setPaymentAmmount(paymentAmmount);
	}

	public CardType getCardType()
	{
		return _cardType;
	}

	public void setCardType(CardType _cardType) 
	{
		this._cardType = _cardType;
	}

	public String getCardText() 
	{
		return _cardText;
	}

	public void setCardText(String _cardText) 
	{
		this._cardText = _cardText;
	}

	public int getPaymentAmmount() 
	{
		return _paymentAmmount;
	}

	public void setPaymentAmmount(int _paymentAmmount) 
	{
		this._paymentAmmount = _paymentAmmount;
	}
}
