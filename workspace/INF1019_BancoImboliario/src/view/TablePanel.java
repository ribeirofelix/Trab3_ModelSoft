package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import model.Player;



public class TablePanel extends Panel {

	
	private static final long serialVersionUID = 1L;
	private ArrayList<Player> players ;

	public TablePanel (Dimension dimension, Container ct , ArrayList<Player> players){
		this.setSize(dimension);
		//this.setLayout(null);
		this.setVisible(true);
		this.players = players;
	}
	
	@Override
	public void paintComponents(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponents(g);
		this.paint(g);
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paint(g2d);
		
		// Draw Table Image 
		BufferedImage buffTableImage = null;
		try {
			 buffTableImage = ImageIO.read(new File("images//tabuleiro.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g2d.drawImage(buffTableImage,10,10,buffTableImage.getWidth(),buffTableImage.getHeight(),null);
		
		drawPivots(g2d);
		
	}
	
	private void drawPivots(Graphics2D g2d) {
		
		int initialPointerX = this.getHeight() / 11;
		int initialPointerY = this.getHeight() / 11 ;
		int WidHei = 5 ;
		String amarelo = "images//yellow_pin.png"; 
		String azul = "images//blue_pin.png";
		// Draw Table Image 
		BufferedImage buffTableImageYellow = null;
		BufferedImage buffTableImageAzul =  null;
		try {
			buffTableImageYellow = ImageIO.read(new File(amarelo));
			buffTableImageAzul = ImageIO.read(new File(azul));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g2d.drawImage(buffTableImageAzul,players.get(0).getPlayerPoint().x , players.get(0).getPlayerPoint().y , buffTableImageAzul.getWidth() , buffTableImageAzul.getHeight() , null );
		g2d.drawImage(buffTableImageYellow,players.get(1).getPlayerPoint().x , players.get(1).getPlayerPoint().y , buffTableImageYellow.getWidth() , buffTableImageYellow.getHeight() , null );
		
	}
	
	

	
}
