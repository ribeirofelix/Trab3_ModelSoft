package view;

import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class CardImagePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pathImageCard ;
	
	public CardImagePanel(JPanel contPanel , String pathImageCard ) {
		super();
		this.setBounds(contPanel.getHeight(), contPanel.getWidth() -50, 300, 270);
		this.setVisible(true);
		setPathImageCard(pathImageCard);
		contPanel.add(this);
	}
	
	
	public void setPathImageCard(String pathImageCard){
		this.pathImageCard = pathImageCard;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		if(pathImageCard != null){
			
			BufferedImage buffImagePivot = null ;
			try {				
				buffImagePivot = ImageIO.read(new File(pathImageCard ));				
			} catch (IOException e) {
				e.printStackTrace();				
			}
			
			g.drawImage(buffImagePivot, 0, 0, buffImagePivot.getWidth(), buffImagePivot.getHeight(), null);
			
			
		}
		
	}
	
	
	

}
