package com.monopoly.components;

import java.awt.Graphics;

import com.monopoly.exceptions.InvalidPlayersQuantityException;
import com.monopoly.model.Game;
import com.monopoly.model.Player;

@SuppressWarnings("serial")
public class BoardPanel extends ImagePanel
{
	private int _height;
	private int _width;
	
	public BoardPanel(String imagePath, int width, int height)
	{
		super(imagePath);
		_height = height;
		_width = width;
	}
	
	@Override
	public void paintComponent (Graphics g)
	{
		// Sets size of image based on Width and Height of the Panel
		g.drawImage (_image, 0, 0, _width, _height, this);
		
		try
		{
			for (Player tempPlayer : Game.getInstance().getPlayers())
			{
				int playerPos   = tempPlayer.getPiecePosition();
				int playerIndex = tempPlayer.getPlayerNumber() - 1;
				
				g.setColor(Player.getPieceColorByIndex(tempPlayer.getPlayerNumber()));
				
				if (playerPos >= 0 && playerPos < 10)
				{
					g.fillOval(845 - ((playerPos) * 80), 615 + (playerIndex * 10), 10, 10);
				}
				else if (playerPos >= 10 && playerPos < 20)
				{
					g.fillOval(0 + (playerIndex * 10), 630 - ((playerPos - 10) * 60), 10, 10);
				}
				else if (playerPos >= 20 && playerPos < 30)
				{
					g.fillOval(50 + ((playerPos - 20) * 80), 0 + (playerIndex * 10), 10, 10);
				}
				else if (playerPos >= 30 && playerPos < 40)
				{
					g.fillOval(823 + (playerIndex * 10), 570 - ((39 - playerPos) * 60), 10, 10);
				}
			}
		}
		catch (InvalidPlayersQuantityException err)
		{
			err.printStackTrace();
		}		
	}
}
