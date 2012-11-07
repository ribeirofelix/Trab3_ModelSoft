package view;
import java.awt.Container;

import javax.swing.JButton;

public class MyButton extends JButton {
	
	private static final long serialVersionUID = 1L;

	public MyButton (String label, Container ct  ) {
		  super(label);
		  ct.add(this);
	  }

}
