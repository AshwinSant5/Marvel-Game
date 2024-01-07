import java.awt.*;

import javax.swing.*;

/**
 * 
 */

/**
 * @author Ashwin
 * Date: October 19, 2022
 * Description: Display text images. Inherits from Picture
 * Method List:
 * public TextPicture(String title, int x, int y) - Constructor
 * public void setTitle(String title) - method to set the title
 * public void setFont(Font font) - method to set the font
 * public void paint (Graphics g) - paint method
 * public static void main(String[] args) - main method used testing purposes
 * 
 *
 */
public class TextPicture extends Picture{

	//private (instance) data
	private String title;
	private Font font;
	
	/**
	 * Constructor
	 */
	public TextPicture(String title, int x, int y) {
		//call the parent constructor
		super();
		//set the title
		this.title = title;
		//set the font
		this.font = new Font ("Monospaced", Font.PLAIN, 24);
		//set the x-position
		this.setxPos(x);
		//set the y-position
		this.setyPos(y);
	}

	/**
	 * method to set the title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * method to set the font
	 */
	public void setFont(Font font) {
		this.font = font;
	}
	
	/**
	 * paint method
	 */
	public void paint (Graphics g) {
		//set the colour
		g.setColor(this.getColor());
		//set the font
		g.setFont(this.font);
		//draw the text at the specified x and y coordinates
		g.drawString(this.title, this.getxPos(), this.getyPos());
		
	}

	/*
	 * main method used testing purposes
	 */
	public static void main(String[] args) {
		//create a new JFrame
		JFrame frame = new JFrame ("testing");
		//set the size of the frame
		frame.setSize(400,400);

		//create a new TextPicture object
		TextPicture t = new TextPicture("title", 200,100);
		//it to the frame
		frame.add(t);

		//make the frame visible
		frame.setVisible(true);
		
		//JOptionPane to delay
		JOptionPane.showMessageDialog(null, "wait");
		
		//set the new text
		t.setTitle("Hello World");
		//set the new x-position
		t.setxPos(100);
		//set the new y-position
		t.setyPos(150);
		//set the new colour
		t.setColor(Color.BLUE);
		//set the new font
		t.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		
		//create another TextPicture object
		TextPicture t2 = new TextPicture("Computer Science", 50,300);
		
		//add the second TextPicture object to the frame
		frame.add(t2);	
		//repaint the frame and its components (set visible again)
		frame.setVisible(true);		
	}

}
