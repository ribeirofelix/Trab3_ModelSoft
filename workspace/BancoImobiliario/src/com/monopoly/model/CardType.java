package com.monopoly.model;

// Describes the Possible Types of a "Chance Card"
// Player pays to another player, player receives from another player
// Everyone pays the player, or Player pays everyone
public enum CardType
{
	PlayerPays, 
	PlayerReceives,
	EveryonePays,
	EveryoneReceives
}
