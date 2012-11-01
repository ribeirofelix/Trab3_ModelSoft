package com.monopoly.components;

import java.awt.Graphics;

import com.monopoly.exceptions.InvalidPlayersQuantityException;
import com.monopoly.model.Game;
import com.monopoly.model.Player;

@SuppressWarnings("serial")
public class BoardPanel extends ImagePanel
{
	public BoardPanel(String imagePath)
	{
		super(imagePath);
	}
	
	public void paintComponent (Graphics g)
	{
		// Sets size of image based on Width and Height of the Panel
		g.drawImage (_image, 0, 0, this.getWidth(), this.getHeight(), this);
		
		try
		{
			for (Player tempPlayer : Game.getInstance().getPlayers())
			{
				int playerPos = tempPlayer.getPiecePosition();
				int playerIndex = tempPlayer.getNumber() - 1;
				
				g.setColor(Player.getPieceColorByIndex(tempPlayer.getNumber()));
				
				if (playerPos >= 0 && playerPos < 10)
				{
					g.fillOval(125 + (playerPos * 80), 615 + (playerIndex * 10), 10, 10);
				}
				else if (playerPos >= 10 && playerPos < 20)
				{
					g.fillOval(823 + (playerIndex * 10), 570 - ((playerPos-10) * 60), 10, 10);
				}
				else if (playerPos >= 20 && playerPos < 30)
				{
					g.fillOval(765 - ((playerPos-20) * 75), 0 + (playerIndex * 10), 10, 10);
				}
				else if (playerPos >= 30 && playerPos < 40)
				{
					g.fillOval(0 + (playerIndex * 10), 90 + ((playerPos-30) * 60), 10, 10);
				}
			}
		}
		catch (InvalidPlayersQuantityException err)
		{
			err.printStackTrace();
		}		
	}
}
