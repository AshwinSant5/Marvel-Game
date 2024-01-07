import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * @author Ashwin
 * Date: November 12, 2022
 * Description: assigns each object with steps to move. Inherits from ImagePicture class
 * Method List: 
 * public MarvelHero(ImageIcon image, int stepsToMove) - Overloaded constructor to set desired image and steps to move
 * public int getStepsToMove() - Method to get the steps to move
 * public void setStepsToMove(int stepsToMove) - method to set the steps to move
 * public static void main(String[] args) - main method for self testing
 */
public class MarvelHero extends ImagePicture {
	/**
	 * Instance variables
	 */
	private int stepsToMove;

	/*
	 * Overloaded constructor to set desired image and steps to move
	 */
	public MarvelHero(ImageIcon image, int stepsToMove) {
		//calls the superclass from the parent constructor ImagePicture
		super(image);
		//set stepsToMove of the object to the desired/parsed stepsToMove 
		this.stepsToMove = stepsToMove;
	}//end of overloaded constructor


	/**
	 * Method to get the steps to move
	 */
	public int getStepsToMove() {
		return stepsToMove;
	}//end of getStepsToMove method


	/**
	 * method to set the steps to move
	 */
	public void setStepsToMove(int stepsToMove) {
		this.stepsToMove = stepsToMove;
	}//end of setStepsToMove method

	/**
	 * Main method for self testing
	 */
	public static void main(String[] args) {
		//create a new JFrame
		Frame f = new JFrame("Testing Only");
		//set the size of the frame
		f.setSize(400, 350);
		//set the frame to visible
		f.setVisible(true);
		//create a new MarvelHero object and set it to an image and the steps to move to 0
		MarvelHero m1 = new MarvelHero(new ImageIcon("ironman-runner.gif"), 0);
		//set the steps to move to the rolled die value
		m1.setStepsToMove(250);
		//add m1 to the frame
		f.add(m1);
		//repaint the frame
		f.setVisible(true);

		//wait
		JOptionPane.showMessageDialog(null,  "Wait");
		
		//loop until the steps moved by the MarvelHero object reaches its steps to move/supposed to move
		for (int i = 0; i <= m1.getStepsToMove(); i++) {
			//set the x-position of m1 to "i"
			m1.setxPos(i);
			//repaint m1
			m1.repaint();
			//put the program to sleep for 5 milliseconds
			try {
				Thread.sleep(5);
			}
			catch (Exception e) {

			}
		}//end of for loop
		//print the x-position of the MarvelHero and the steps to move to check of hero has moved its required steps
		System.out.println(m1.getxPos() + ", " + m1.getStepsToMove());
	}
}

