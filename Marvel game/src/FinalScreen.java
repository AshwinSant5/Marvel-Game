import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * 
 */

/**
 * @author Ashwin
 * Date: November 14, 2022
 * Description: Class contains a new instance of FinalScreen to display a GUI for the podium based on the steps taken 
 * 				per character as well as a high scores list based on the high scores read from a file. class calls the 
 * 				HighScore.readFile method to read from the file. If the highest achieved points in the current game is 
 * 				greater than any of the high scores currently on the list, it would replace the lowest high score and would 
 * 				sort the list from greatest to least once again. The new high scores list could then be written to the same file 
 * 				so an updated high scores list can be accessed in future matches. the podium is also ogranized from 1-4 based on the 
 * 				number of steps taken (most steps - least steps). Class is called in the RaceWindow class after the race has concluded 
 * 				using data collected from the race and the main screen from MarvelUniverse and passes them in as parameters to create a new
 * 				FinalScreen object. 
 * Method List:
 *public FinalScreen(int points[], int totalSteps[], MarvelHero heros[]) throws IOException - Creates a new instance of FinalScreen
 *																							 passes in the points array and array of MarvelHero objects
 *public static void main(String[] args) throws IOException - main method for GUI self testing
 */
public class FinalScreen extends JFrame {
	/*
	 * private variables for window components
	 */

	//variables for the window width, height, array lengths, temporary variables, player number, high scores and temporary high scores
	private int width, height, len, temp, length, playerNum[], highScoreNumber[], highScoreNumberTemp[];
	//TextPicture objects for the position titles, points titles, high scores, high scores title standings and total steps title
	private TextPicture positionTitles[], pointsTitles[], highScores[], highScoreTitle, standings, stepsTotal[];
	//ImagePicture objects for the hero pictures and temporary pictures
	private ImagePicture heroPics[], heroTemp[], tempPic;
	//variable for the array of high scores in string format to display on the frame 
	private String highScoreNum[];

	/**
	 * Creates a new instance of FinalScreen
	 * passes in the points array and array of MarvelHero objects
	 */
	public FinalScreen(int points[], int totalSteps[], MarvelHero heros[]) throws IOException {
		super("Results"); //set the title of the frame

		//initialize the dimensions of the frame
		width = 800; 
		height  = 600;

		//initialize the length value to the length of the points array
		length = points.length; 

		//initialize the length of the player num array to length
		playerNum = new int [length];
		//loop through each element of the playerNum array
		for (int i = 0; i < playerNum.length; i++) {
			//initialize the playerNum at element (i) to (i+1)
			playerNum[i] = (i+1);
		}//end of for 

		//create a new text picture object for the standings title
		standings = new TextPicture ("Podium - Steps", 10, 20); //set the text of the text picture
		standings.setFont(new Font("Agency FB", Font.BOLD, 25)); //set the font of the text 
		standings.setColor(Color.RED); //set the colour of the text 
		standings.setBounds(160, 20, 200, 50); //set the location and dimensions of the text picture object

		//initialize the length of the position titles array to length
		positionTitles = new TextPicture[points.length];
		//loop through each element of the positionTitles array
		for (int i = 0; i < positionTitles.length; i++) {
			positionTitles[i] = new TextPicture (Integer.toString(i+1), 10, 20); //set the text of the text picture to (i+1) 
			positionTitles[i].setFont(new Font("Agency FB", Font.BOLD, 24)); //set the font of the text
			positionTitles[i].setColor(Color.RED); //set the colour of the text
		}//end of for 

		//set the position and dimensions of each position title text picture 
		positionTitles[0].setBounds(30, 100, 100, 40);
		positionTitles[1].setBounds(30, 200, 100, 40);
		positionTitles[2].setBounds(30, 310, 100, 40);
		positionTitles[3].setBounds(30, 420, 100, 40);

		//initialize the length of the points titles array to length
		pointsTitles = new TextPicture[points.length];
		//loop through each element of the pointsTitles array
		for (int i = 0; i < pointsTitles.length; i++) {
			//set the text of the text picture to the player number and the corresponding points
			pointsTitles[i] = new TextPicture ("Player " + playerNum[i] + ": " + Integer.toString(points[i]) + " points", 10, 20);
			pointsTitles[i].setFont(new Font("Agency FB", Font.BOLD, 24)); //set the font of the text
			pointsTitles[i].setColor(Color.RED); //set the colour of the text
		}//end of for

		//set the position and dimensions of each points title text picture 
		pointsTitles[0].setBounds(200, 140, 200, 40);
		pointsTitles[1].setBounds(200, 240, 200, 40);
		pointsTitles[2].setBounds(200, 350, 200, 40);
		pointsTitles[3].setBounds(200, 460, 200, 40);

		//initialize the length of the steps total array to length
		stepsTotal = new TextPicture[points.length];
		//loop through each element of the stepsTotal array
		for (int i = 0; i < pointsTitles.length; i++) {
			//set the text of the text picture to the player number and the corresponding total steps
			stepsTotal[i] = new TextPicture ("Player " + playerNum[i] + ": " + Integer.toString(totalSteps[i]) + " total steps", 10, 20);
			stepsTotal[i].setFont(new Font("Agency FB", Font.BOLD, 24)); //set the font of the text
			stepsTotal[i].setColor(Color.RED); //set the colour of the text
		}//end of for

		//set the position and dimensions of each total steps title text picture 
		stepsTotal[0].setBounds(200, 100, 220, 40);
		stepsTotal[1].setBounds(200, 200, 220, 40);
		stepsTotal[2].setBounds(200, 310, 220, 40);
		stepsTotal[3].setBounds(200, 420, 220, 40);

		//initialize the length of the hero pics array to length
		heroPics = new ImagePicture[heros.length];
		//loop through each element of the heroPics array
		for (int i = 0; i < points.length; i++) {
			//create a new image picture of the same image from the passed in MarvelHero object at (i) 
			heroPics[i] = new ImagePicture(heros[i].getImage());
		}//end of for 

		/*
		 * bubble sorting from greatest to least 
		 */

		//initialize temporary variables for bubble sorting 
		len = stepsTotal.length;
		heroTemp = new ImagePicture[heroPics.length];
		//outer loop to go through each element
		for (int i = 0; i < len; i++) {
			//inner loop to perform comparisons and swapping 
			for (int j = 1; j < (len-i); j++) {
				//if the current number is less than the previous number, swap them
				if (totalSteps[j-1] < totalSteps[j]) {
					//swap the total steps
					temp = totalSteps[j-1];
					totalSteps[j-1] = totalSteps[j];
					totalSteps[j] = temp;
					
					//swap corresponding points
					temp = points[j-1];
					points[j-1] = points[j];
					points[j] = temp;

					//swap corresponding hero pics
					tempPic = heroPics[j-1];
					heroPics[j-1] = heroPics[j];
					heroPics[j] = tempPic;

					//swap corresponding player number
					temp = playerNum[j-1];
					playerNum[j-1] = playerNum[j];
					playerNum[j] = temp;

					//swap corresponding player number
					temp = playerNum[j-1];
					playerNum[j-1] = playerNum[j];
					playerNum[j] = temp;
				}//end of if
			}//end of inner loop
		}//end of outer loop

		//set a new array to the newly sorted array
		for (int i = 0; i < heroPics.length; i++) {
			heroTemp[i] = heroPics[i];
		}//end of for

		//set the location an boundaries of each hero image picture
		heroTemp[0].setBounds(100, 70, 80, 	100);
		heroTemp[1].setBounds(100, 170, 80, 100);
		heroTemp[2].setBounds(100, 280, 80, 100);
		heroTemp[3].setBounds(100, 390, 80, 100);

		//loop through each element of the arrays
		for (int i = 0; i < pointsTitles.length; i++) {
			//set the text to the newly bubble sorted player numbers and total steps to display positions based on greatest to least steps taken 
			stepsTotal[i].setTitle("Player " + playerNum[i] + ": " + Integer.toString(totalSteps[i]) + " total steps");
			//repaint the stepsTotal at element (i)
			stepsTotal[i].repaint();
			//set the text to the newly bubble sorted player numbers and points to display
			pointsTitles[i].setTitle("Player " + playerNum[i] + ": " + Integer.toString(points[i]) + " points");
			//repaint the pointsTitles at element (i)
			pointsTitles[i].repaint();
		}//end of for

		//initialize array for the high scores from the high scores saved to file
		highScoreNum = HighScores.readFile("highScoresSaved");

		//initialize the length of an array for the high scores in numerical values
		highScoreNumber = new int [highScoreNum.length];

		//loop through each element of the highScoreNumber array
		for (int i = 0; i < highScoreNum.length; i++) {
			//set the highScoreNumber at (i) to the numerical value of the highScoreNum at element (i)
			highScoreNumber[i] = Integer.parseInt(highScoreNum[i]);
		}//end of for

		//replace lowest points if points achieved in current match is higher 
		if (points[0] > highScoreNumber[highScoreNumber.length-1]) {
			highScoreNumber[highScoreNumber.length-1] = points[0];
		}//end of if

		//sort the high scores points from highest points to lowest 

		//initialize the length of the loop to the length of the highScoreNumber array
		len = highScoreNumber.length;
		//initialize variable to store temporary information
		highScoreNumberTemp = new int[highScoreNumber.length];
		//outer loop to go through each element
		for (int i = 0; i < len; i++) {
			//inner loop to perform comparisons and swapping 
			for (int j = 1; j < (len-i); j++) {
				//if the current number is less than the previous number, swap them
				if (highScoreNumber[j-1] < highScoreNumber[j]) {
					temp = highScoreNumber[j-1];
					highScoreNumber[j-1] = highScoreNumber[j];
					highScoreNumber[j] = temp;
				}//end of if
			}//end if inner for
		}//end of outer for

		//initialize the length of the highScores array 
		highScores = new TextPicture[highScoreNumber.length];
		//loop through each element of the highScores array
		for (int i = 0; i < highScoreNumber.length; i++) {
			//create a new text picture for highScores at (i) and set the text as the string value of highScoreNumber at (i)
			highScores[i] = new TextPicture(Integer.toString(highScoreNumber[i]), 10, 20);
			highScores[i].setFont(new Font("Agency FB", Font.BOLD, 24)); //set the font of the text
			highScores[i].setColor(Color.RED);	//set the colour of the text
		}//end of for

		//set the position and boundaries of each element of the highScores array
		highScores[0].setBounds(610, 160, 80, 50);
		highScores[1].setBounds(610, 210, 80, 50);
		highScores[2].setBounds(610, 260, 80, 50);
		highScores[3].setBounds(610, 310, 80, 50);
		highScores[4].setBounds(610, 360, 80, 50);

		//Convert all elements of the highScoreNumber array into string values
		for (int i = 0; i < highScoreNum.length; i++) {
			highScoreNum[i] = Integer.toString(highScoreNumber[i]);
		}//end of for

		//write the updated sorted high scores to the highScoresSaved file
		HighScores.writeFile(highScoreNum, "highScoresSaved");

		//create the text picture objects to display the high score title
		highScoreTitle = new TextPicture ("High Scores - Points", 10, 20);
		highScoreTitle.setFont(new Font("Agency FB", Font.BOLD, 24)); //set the font of the text
		highScoreTitle.setColor(Color.RED);	//set the colour of the text
		highScoreTitle.setBounds(540, 100, 200, 40); //set the position and dimensions of the high score title text picture

		//add each element of the arrays to the frame
		for (int i = 0; i < points.length; i++) {
			add(positionTitles[i]); //array of player positions
			add(pointsTitles[i]); //array of player points
			add(stepsTotal[i]); //array of total player steps
			add(heroTemp[i]); //array of player images pictures
		}//end of for 

		//loop through each element of the highScores array
		for (int i = 0; i < highScores.length; i++) {
			//add the highScores element at (i) to the frame
			add(highScores[i]);
		}//end of for

		//add the standings and high score title text pictures to the frame 
		add(standings);
		add(highScoreTitle);

		setSize(width, height); //set the size of the window
		setLayout(null); //set the window layout to null
		getContentPane().setBackground(new Color(255, 160, 106)); //set background color of the frame
		setResizable(false); //set the window to not resizable
		setLocationRelativeTo(null); //center the window on the user's screen
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); //allow user to close/dispose the window with the 'x' 
		setVisible(true); //set the window as visible
	}//end of constructor

	/**
	 * Main method for GUI self testing
	 */
	public static void main(String[] args) throws IOException {
		//create and initialize the length for an array of points, total steps and  MarvelHero objects
		int points [] = new int [4];
		int totalSteps [] = new int [4];
		MarvelHero heros [] = new MarvelHero[4];

		//initialize the points at each element of the points array
		points[0] = 20;
		points[1] = 40;
		points[2] = 30;
		points[3] = 10;

		//initialize the total steps at each element of the points array
		totalSteps[0] = 60;
		totalSteps[1] = 80;
		totalSteps[2] = 50;
		totalSteps[3] = 70;

		//initialize the MarvelHero object at each element of the points array
		heros[0] = new MarvelHero(new ImageIcon("deadpool-runner.gif"), 0);
		heros[1] = new MarvelHero(new ImageIcon("ironman-runner.gif"), 0);
		heros[2] = new MarvelHero(new ImageIcon("hulk-runner.gif"), 0);
		heros[3] = new MarvelHero(new ImageIcon("spiderman-runner.gif"), 0);

		//create a new object of the FinalScreen and pass in the initialized points and MarvelHero object arrays
		FinalScreen results = new FinalScreen(points, totalSteps, heros);
	}//end of self testing main method
}//end of public class FinalScreen
