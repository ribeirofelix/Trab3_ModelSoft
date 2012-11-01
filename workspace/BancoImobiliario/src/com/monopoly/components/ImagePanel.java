package com.monopoly.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel
{
	protected Image _image;
	
	public ImagePanel (String imagePath)
	{
		this(new ImageIcon(imagePath).getImage());
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
