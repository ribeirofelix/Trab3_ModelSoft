package com.monopoly.model;

public class ChanceCard 
{
	private CardType _cardType;
	private String   _cardText;
	private int      _paymentAmmount;
	
	// Class Constructor
	public ChanceCard (CardType cardType, int paymentAmmount)
	{
		setCardType (cardType);
		setPaymentAmmount (paymentAmmount);
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

	public int CalculateCost (int numberOfPlayers)
	{
		if (_cardType == CardType.EveryoneReceives || _cardType == CardType.EveryonePays)
		{
			return (numberOfPlayers * _paymentAmmount); // X times the payment ammount where X = Number of Players
		}
		
		return _paymentAmmount;
	}

	public void setPaymentAmmount(int _paymentAmmount) 
	{
		this._paymentAmmount = _paymentAmmount;
	}
}
