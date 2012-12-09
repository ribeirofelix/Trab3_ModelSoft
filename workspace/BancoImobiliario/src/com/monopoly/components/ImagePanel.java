package com.monopoly.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel
{
	protected Image _image;
	private   JLabel _firstDiceImg;
	private   JLabel _secondDiceImg;
	
	public ImagePanel() { return; }
	
	public ImagePanel (String imagePath)
	{
		this(new ImageIcon(imagePath).getImage());
		
		// Adding labels to ImagePanel
		_firstDiceImg  = new JLabel();
		_secondDiceImg = new JLabel();
		this.add(_firstDiceImg);
		this.add(_secondDiceImg);
	}
	
	public ImagePanel (Image image)
	{
		_image = image;
		Dimension size = new Dimension(image.getWidth(null), image.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
	}
	
	public void paintComponent (Graphics g)
	{
		// Sets size of image based on Width and Height of the Panel
		g.drawImage (_image, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}
