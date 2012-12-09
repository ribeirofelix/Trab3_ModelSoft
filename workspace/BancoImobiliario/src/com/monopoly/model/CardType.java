package com.monopoly.model;

// Describes the Possible Types of a "Chance Card"
// Player pays to another player, player receives from another player
// Everyone pays the player, or Player pays everyone
public enum CardType
{
	PlayerPays,      // 0
	PlayerReceives,  // 1
	EveryonePays,    // 2
	EveryoneReceives, // 3
	PrisonExitVoucher, // 4
	GoToStartPoint,    // 5
	GoToPrison         // 6
}
