import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.Timer;

/**
 * 
 */

/**
 * @author Ashwin and Mihir
 * Date: November 13, 2022
 * Description: Class creates a new instance of RaceWindow. This object passes in the parameters of the two team names, the background choice, the array of character choices 
 * 				and number of laps that was previously chosen on the main menu (MarvelUniverse window object) and passed as parameters for the creation of 
 * 				the RaceWindow object. This object is a frame containing images of the user's chosen characters, each characters total points and steps rolled and a drawing 
 * 				panel upon which MarvelHero objects are added. Once the "start" button is clicked, the race would commence. The MarvelHero objects would follow the path around 
 * 				the borders of the drawing panel. The movement of the MarvelHero objects/the race can be paused at any given time and can be resumed from the current positions. 
 *				Race can also be started at any point in time. Once the MarvelHero objects move around the border of the drawing panel the number of times specified by the user's 
 *				number of laps choice, the race would come to a stop and a FinalScreen object is created containing the results of the race. Information such as the total points and 
 *				total steps per character and the character MarvelHero objects themselves would be passed as input parameters to creating this FinalScreen object. the FinalScreen object 
 *				can be closed at any time and would not end the program. The user can then restart the race again and all the data would reset (points, steps, etc.). Once that race is 
 *				finished, the same procedure of displaying the results of the race through creating a FinalSreen object is followed. This process can be repeated as many times as desired and 
 *				program can be ended by clicking the "x" on the top right corner.  
 * Method List: 
 *public RaceWindow(String name1, String name2, String backgroundChoice, String characterChoices[],int laps) - Creates a new instance of RaceWindow
 *																												passes in the team 2 name, team 2 name, background choice, 
 *																												array of character choices and number of laps
 *public void actionPerformed (ActionEvent e) - method for action events
 *public static void main(String[] args) -  Main method for GUI self testing
 */
public class RaceWindow extends JFrame implements ActionListener {

	/*
	 * private variables for window components
	 */
	private ImagePicture background, flag, flag2, characters[]; //image pictures objects for background image, flags images and character images
	private MarvelHero characters2[]; //marvel hero objects for the characters in the race 
	private TextPicture teamName1, teamName2, skips, pointsTitle[], pointsNum[], stepTitle[], stepCounter[]; //text picture objects for titles, points, steps and skip turns to display on the window
	private TextPicture playerNumbers[]; //text picture objects for the player numbers
	private JPanel drawingPanel; //panel for the drawing panel
	private JButton start2, restart; //start and restart buttons
	private JToggleButton pausePlay; //pause and play toggle button
	private Die steps1, steps2; //two dice to roll steps for each character
	private int width, height; //int variables for the width and height of the frame
	private int length = 4; //length of an array
	private int points[]; //array to store the points for each player/character
	private Timer timer; // animation timer
	private int count; //variable to keep track of how many times the timer fired
	private int xPos [], yPos [], xVel[], yVel[], totalSteps[]; //arrays for the x and y positions, the x and y velocities and total steps of each character in the race 
	private int totalStopped, once; //variables for the total number of characters stopped and variable to run a procedure once
	private int timesCrossed; //variable to keep track how many times the characters crossed the line
	private String skipTurnList; //string variable to store the players whose turns got skipped
	private int lapNum; //variable to store the number of laps to be completed 
	private int lapCount; //variable to keep track of the number of laps;

	/**
	 * Creates a new instance of RaceWindow
	 * passes in the team 1 name, team 2 name, background choice, array of character choices and number of laps
	 */
	public RaceWindow(String name1, String name2, String backgroundChoice, String characterChoices[],int laps) {
		super("Race"); //set the title of the frame
		//initialize the width and height of the frame
		width = 1000;
		height = 650;

		//create two 12 sided dice
		steps1 = new Die(12);
		steps2 = new Die(12);

		count = 0; //initialize number of times timer fired to 0
		totalStopped = 0; //initialize number of characters stopped to 0
		once = 1; //initialize once to 1
		timesCrossed = 0; //initialize number of times the finish line was crossed to 0
		skipTurnList = "Turn Skipped: "; //initialize string to show the players whoe turn got skipped

		//set the number of laps to the laps passed into the constructor
		lapNum = laps; 

		//keeps track of the number of laps completed
		lapCount = 0;

		//initialize the length of the x and y positions and x and y velocities
		xPos = new int[characterChoices.length];
		yPos = new int[characterChoices.length];
		xVel = new int[characterChoices.length];
		yVel = new int[characterChoices.length];

		//initialize the values for each element of the x and y position and x and y velocity arrays
		for (int i = 0; i < xPos.length; i++) {
			xPos[i] = 890; //initialize the x position to 890
			yPos[i] = 10;//initialize the y position to 10
			xVel [i] = -4; //initialize the x velocity to -4
			yVel[i] = 0; //initialize the y velocity to 0
		}//end of for

		//initialize the length of the points array
		points = new int [characterChoices.length];

		//loop through each element of the points array and initialize the points to 0
		for (int i = 0; i < points.length; i++) {
			points[i] = 0;
		}//end of for

		//initialize the length of the total steps array
		totalSteps = new int [points.length];
		//loop through each element of the total steps array and initialize the total steps to 0
		for (int i = 0; i < points.length; i++) {
			totalSteps[i] = 0;
		}//end of for

		//create the drawing panel
		drawingPanel = new JPanel();
		//set the layout to null
		drawingPanel.setLayout(null);
		//set the location and size of the drawing panel
		drawingPanel.setBounds(10, 120, 970, 400);
		//set the colour and opacity of the drawing panel
		drawingPanel.setBackground(new Color(0,0,0,250));

		//create and set the position and size of the start button
		start2 = new JButton("Start");
		start2.setBounds(100, 550, 100, 40);

		//create and set the position and size of the pause and play toggle button
		pausePlay = new JToggleButton("Pause");
		pausePlay.setBounds(220, 550, 100, 40);
		pausePlay.setVisible(false); //set the button to not visible

		//create and set the location and size of the restart button
		restart = new JButton("Restart");
		restart.setBounds(340, 550, 100, 40);
		restart.setVisible(false); //set the button to not visible

		//create the text picture objects to display the team names for team 1 and team 2
		teamName1 = new TextPicture (name1, 10, 20); //set the text to the team 1 name passed into the constructor
		teamName1.setFont(new Font("Agency FB", Font.BOLD, 24)); //set the font
		teamName1.setColor(Color.RED); //set the text colour
		teamName1.setBounds(30, 20, 100, 40); //set the location and boundaries 

		teamName2 = new TextPicture (name2, 10, 20);
		teamName2.setFont(new Font("Agency FB", Font.BOLD, 24)); //set the text to the team 2 name passed into the constructor
		teamName2.setColor(Color.RED);
		teamName2.setBounds(530, 20, 100, 40);

		//create the text picture object to display the player's whose turn got skipped
		skips = new TextPicture("Skip turns appear here", 10, 20);
		skips.setFont(new Font("Agency FB", Font.BOLD, 24)); //set the font
		skips.setColor(Color.RED); //set the text colour
		skips.setBounds(550, 560, 300, 100); //set the location and boundaries 

		//initialize the length of the points title array
		pointsTitle = new TextPicture[points.length];
		//set the text, font and colour for each points title object
		for (int i = 0; i < pointsTitle.length; i++) {
			pointsTitle[i] = new TextPicture ("Points:", 10, 20);
			pointsTitle[i].setFont(new Font("Agency FB", Font.BOLD, 15));
			pointsTitle[i].setColor(Color.BLUE);
		}//end of for

		//set the location and dimensions of each points title object
		pointsTitle[0].setBounds(220, 10, 60, 20);
		pointsTitle[1].setBounds(710, 10, 60, 20);
		pointsTitle[2].setBounds(400, 10, 60, 20);
		pointsTitle[3].setBounds(890, 10, 60, 20);

		//initialize the length of the points number array to display the player points
		pointsNum = new TextPicture[length];
		//set the text, font and colour for each points number object
		for (int i = 0; i < pointsNum.length; i++) {
			pointsNum[i] = new TextPicture ("0", 10, 20); //initialize the total points for the label to 0
			pointsNum[i].setFont(new Font("Agency FB", Font.BOLD, 15));
			pointsNum[i].setColor(Color.BLUE);
		}//end of for

		//set the location and dimensions of each points number object
		pointsNum[0].setBounds(220, 30, 60, 20);
		pointsNum[1].setBounds(710, 30, 60, 20);
		pointsNum[2].setBounds(400, 30, 60, 20);
		pointsNum[3].setBounds(890, 30, 60, 20);

		//initialize the length of the steps title array
		stepTitle = new TextPicture[length];
		//set the text, font and colour for each steps title object
		for (int i = 0; i < stepTitle.length; i++) {
			stepTitle[i] = new TextPicture ("Steps:", 10, 25);
			stepTitle[i].setFont(new Font("Agency FB", Font.BOLD, 15));
			stepTitle[i].setColor(Color.BLUE);
		}//end of for

		//set the location and dimensions of each steps title object
		stepTitle[0].setBounds(220, 50, 60, 30);
		stepTitle[1].setBounds(710, 50, 60, 30);
		stepTitle[2].setBounds(400, 50, 60, 30);
		stepTitle[3].setBounds(890, 50, 60, 30);

		//initialize the length of the steps counter array to display the player steps rolled
		stepCounter = new TextPicture[length];
		//set the text, font and colour for each steps title object
		for (int i = 0; i < pointsNum.length; i++) {
			stepCounter[i] = new TextPicture ("0", 10, 20);//initialize the steps rolled to 0 for the label
			stepCounter[i].setFont(new Font("Agency FB", Font.BOLD, 15));
			stepCounter[i].setColor(Color.BLUE);
		}//end of for

		//set the location and dimensions of each steps counter object
		stepCounter[0].setBounds(220, 75, 60, 25);
		stepCounter[1].setBounds(710, 75, 60, 25);
		stepCounter[2].setBounds(400, 75, 60, 25);
		stepCounter[3].setBounds(890, 75, 60, 25);

		//initialize the length of the player numbers title array
		playerNumbers = new TextPicture[points.length];
		//set the text, font and colour for each player numbers title object
		for (int i = 0; i < pointsTitle.length; i++) {
			playerNumbers[i] = new TextPicture ("player " + (i+1), 10, 20);
			playerNumbers[i].setFont(new Font("Agency FB", Font.BOLD, 15));
			playerNumbers[i].setColor(Color.BLUE);
		}//end of for

		//set the location and dimensions of each player number title object
		playerNumbers[0].setBounds(130, 95, 60, 45);
		playerNumbers[1].setBounds(620, 95, 60, 45);
		playerNumbers[2].setBounds(310, 95, 60, 45);
		playerNumbers[3].setBounds(800, 95, 60, 45);

		//create an image picture object for two checkered flags and set their location and dimensions
		flag = new ImagePicture(new ImageIcon("flagImg.png"));
		flag.setBounds(806, 0, 40, 40);

		flag2 = new ImagePicture(new ImageIcon("flagImg.png"));
		flag2.setBounds(806, 100, 40, 40);

		//initialize the length of the characters array for the images for each player's character
		characters = new ImagePicture[points.length];
		//loop through each element of the characters array
		for (int i = 0; i < characters.length; i++) {
			//if the character choice at (i) passed into the constructor is deadpool
			if (characterChoices[i]=="Deadpool") {
				//create an image picture for the deadpool character at element (i) of the characters array
				characters[i] = new ImagePicture(new ImageIcon("deadpool-runner.gif"));
			}//end of if

			//if the character choice at (i) passed into the constructor is spiderman
			else if (characterChoices[i]=="Spiderman") {
				//create an image picture for the spiderman character at element (i) of the characters array
				characters[i] = new ImagePicture(new ImageIcon("spiderman-runner.gif"));
			}//end of if

			//if the character choice at (i) passed into the constructor is iron man
			else if (characterChoices[i]=="Iron Man") {
				//create an image picture for the iron man character at element (i) of the characters array
				characters[i] = new ImagePicture(new ImageIcon("ironman-runner.gif"));
			}//end of if

			//if the character choice at (i) passed into the constructor is hulk
			else if (characterChoices[i]=="Hulk") {
				//create an image picture for the hulk character at element (i) of the characters array
				characters[i] = new ImagePicture(new ImageIcon("hulk-runner.gif"));
			}//end of if
		}//end of for 

		//set the location and boundaries for each character's image/image picture object
		characters[0].setBounds(110, 10, 100, 90);
		characters[1].setBounds(600, 10, 100, 90);
		characters[2].setBounds(290, 10, 100, 90);
		characters[3].setBounds(780, 10, 100, 90);

		//initialize the length of the characters 2 array of MarvelHero objects
		characters2 = new MarvelHero[characters.length];

		//loop through each element of the characters2 array
		for (int i = 0; i < characters2.length; i++) {
			//if the character choice at (i) passed into the constructor is deadpool
			if (characterChoices[i]=="Deadpool") {
				//create a marvel hero for the deadpool character at element (i) of the characters2 array
				characters2[i] = new MarvelHero(new ImageIcon("deadpool-runner.gif"), 0);
			}//end of if 

			//if the character choice at (i) passed into the constructor is spiderman
			else if (characterChoices[i]=="Spiderman") {
				//create a marvel hero for the spiderman character at element (i) of the characters2 array
				characters2[i] = new MarvelHero(new ImageIcon("spiderman-runner.gif"), 0);
			}//end of if

			//if the character choice at (i) passed into the constructor is iron man
			else if (characterChoices[i]=="Iron Man") {
				//create a marvel hero for the iron man character at element (i) of the characters2 array
				characters2[i] = new MarvelHero(new ImageIcon("ironman-runner.gif"), 0);
			}//end of if

			//if the character choice at (i) passed into the constructor is hulk
			else if (characterChoices[i]=="Hulk") {
				//create a marvel hero for the hulk character at element (i) of the characters2 array
				characters2[i] = new MarvelHero(new ImageIcon("hulk-runner.gif"), 0);
			}//end of if

			characters2[i].setBounds(0, 0, drawingPanel.getWidth(), drawingPanel.getHeight()); //set the bounds for each marvel hero / characters2[i] object/character
			characters2[i].setxPos(xPos[i]); //set the x position of the marvel hero / characters2[i] object
			characters2[i].setyPos(yPos[i]);//set the y position of the marvel hero / characters2[i] object

		}//end of for

		//add the flag images to the drawing panel
		drawingPanel.add(flag2);

		//add each marvel hero object/character to the drawing panel and add the image pictures of each character to the frame 
		for (int i = 0; i < length; i++) {
			drawingPanel.add(characters2[i]);
			add(characters[i]);
		}//end of for 

		//add the two checkered flags image picture objects to the drawing panel
		drawingPanel.add(flag);

		//add the drawing panel to the frame
		add(drawingPanel);

		//add the points titles and the points counter text picture objects to the frame
		for (int i = 0; i < pointsTitle.length; i++) {
			add(pointsTitle[i]);
			add(pointsNum[i]);
		}//end of for 

		//add the steps titles, the steps counter and player number text picture objects to the frame
		for (int i = 0; i < stepTitle.length; i++) {
			add(stepTitle[i]);
			add(stepCounter[i]);
			add(playerNumbers[i]);
		}//end of for

		//add the start, pause and play and restart buttons to the frame
		add(start2);
		add(pausePlay);
		add(restart);

		//add the team names text picture object to the frame
		add(teamName1);
		add(teamName2);	
		//add the turns skipped text picture object label to the frame 
		add(skips);

		//set up timer 
		timer = new Timer (50, this);  //creates a timer and this class as the listener. set to fire every 20ms
		timer.setInitialDelay (10); //set the initial delay to 5 ms before it starts

		//add the start, pause and play and restart buttons as a listener on this frame
		start2.addActionListener(this);
		pausePlay.addActionListener(this);
		restart.addActionListener(this);

		//if the chosen background is marvel
		if (backgroundChoice.equalsIgnoreCase("Marvel")) {
			//create an image picture object and set the image to the marvel background
			background = new ImagePicture (new ImageIcon("marvel-background.gif"), 0, 0);
			//add the background image picture object to the frame
			add(background); //add the background to the frame
		}//end of if 

		//if the chosen background is deadpool
		else if (backgroundChoice.equalsIgnoreCase("Deadpool")) {
			//create an image picture object and set the image to the deadpool background
			background = new ImagePicture (new ImageIcon("deadpool-background.gif"), 0, 0);
			add(background); //add the background to the frame
		}//end of if 

		//if the chosen background is captain america's shield
		else if (backgroundChoice.equalsIgnoreCase("Captain America's Shield")) {
			//create an image picture object and set the image to the shield background
			background = new ImagePicture (new ImageIcon("shield-background.gif"), 0, 0);
			add(background); //add the background to the frame
		}//end of if 

		//if the chosen background is hulk activated
		else if (backgroundChoice.equalsIgnoreCase("Hulk Activated")) {
			//create an image picture object and set the image to the hulk activated background
			background = new ImagePicture (new ImageIcon("avenger-background.gif"), 0, 0);
			add(background); //add the background to the frame
		}//end of if

		setSize(width, height);//set the size of the window
		setResizable(false);//make the window not resizable
		setLocationRelativeTo(null);//center the window on the user's screen
		setDefaultCloseOperation(EXIT_ON_CLOSE); //allow user to close window and end program with the 'x' 
		setVisible(true); //set the window visible
	}//end of constructor 


	//method for action events
	public void actionPerformed (ActionEvent e){ 

		if (e.getSource()==start2) { //if the start button is clicked
			start2.setVisible(false); //set the start button to not visible
			pausePlay.setVisible(true); //set the pause and play toggle button to visible
			restart.setVisible(true); //set the restart button to visible

			timer.start(); //start the timer
		}//end of if start was clicked

		if (e.getSource()==pausePlay) { //if the pause-play toggle button was clicked
			if (pausePlay.isSelected()) { //if the button is in the selected state
				timer.stop();//stop the timer
				pausePlay.setText("Play");//set the button text to "play"
			}//end of if 
			else { //if the button is in the un-selected state
				timer.start(); //start the timer
				pausePlay.setText("Pause"); //set the button text to "pause"
			}//end of else
		}//end of if pause-play toggle button was clicked

		if (e.getSource() == restart) { //if the restart button was clicked
			pausePlay.setSelected(false);//set the pause-play toggle button to false
			pausePlay.setVisible(true); //make the pause play button visible
			pausePlay.setText("Pause");//set the pause-play button text to "pause"

			timer.restart();//restart the timer

			//reset counter variables
			count = 0;
			once = 1;
			totalStopped = 0;
			timesCrossed = 0;
			lapCount = 0;

			/*
			 * reset all the elements of the x and y position, character (marvel hero) object x and y positions and steps to move 
			 * and repaint the character2 objects back at the starting location
			 */
			for (int i = 0; i < xPos.length; i++) {
				xPos[i] = 890;
				yPos[i] = 10;
				characters2[i].setxPos(xPos[i]);
				characters2[i].setyPos(xPos[i]);
				characters2[i].setStepsToMove(0);
				characters2[i].repaint();
			}//end of for
		}//end of if restart button was clicked

		if (e.getSource()==timer) {//if the timer fired

			count ++;//add one to the count
			skipTurnList = "Turn Skipped: "; //reset the turns skipped text picture label

			//if the program hasn't been restarted (played for first time)
			if (once == 1) {
				//loop through each element of the arrays
				for (int i = 0; i < characters2.length; i++) {
					//set the step counter text picture to 0
					stepCounter[i].setTitle(Integer.toString(0));
					//repaint the step counter text picture object
					stepCounter[i].repaint();
					//set the steps to move for the character2 object at (i) to 4
					characters2[i].setStepsToMove(4);
					//set the points at (i) to 0
					points[i] = 0;
					//set the total steps at (i) to 0
					totalSteps[i] = 0;
					//set the point counter text picture to 0
					pointsNum[i].setTitle(Integer.toString(0));
					//repaint the point counter text picture object
					pointsNum[i].repaint();
				}//end of for 

				once++; //add one to the once variable
			}//end of if once == 1

			//if all character2 (marvel hero) objects have stopped (velocities 0)
			if (totalStopped == 4) {
				//put the program to sleep for 5 milliseconds
				try {
					Thread.sleep(250);
				}
				catch (Exception a) {

				}

				//reset the count variable
				count = 0;

				//loop through each element of the arrays
				for (int i = 0; i < characters2.length; i++) {
					//set the step and point counter text picture text colour to blue
					stepCounter[i].setColor(Color.BLUE);
					pointsNum[i].setColor(Color.BLUE);

					//roll the two twelve faced dice
					steps1.rollDie();
					steps2.rollDie();

					//set the step counter text picture object text to the sum of the two rolled dice
					stepCounter[i].setTitle(Integer.toString(steps1.getValue()+steps2.getValue()));
					//repaint the step counter object
					stepCounter[i].repaint();
					//set the steps for the character2 at (i) to the sum of the two rolled dice
					characters2[i].setStepsToMove(steps1.getValue()+steps2.getValue());

					//if the steps characters2 at (i) is supposed to move is not 4, 6 or 24 and the first character hasn't finished all laps
					if (characters2[i].getStepsToMove() != 4 && characters2[i].getStepsToMove() != 6 && characters2[i].getStepsToMove() != 24 && timesCrossed != ((timesCrossed*4)+1)) {
						//add the steps rolled to the total steps for characters2 at (i)
						totalSteps[i] = totalSteps[i] + (steps1.getValue()+steps2.getValue());
					}

					//while the first place person hasn't finished the required laps
					if (timesCrossed < (timesCrossed*4)+1) {
						//calculate the points based on the steps rolled and add it to the total points (points array at element (i)) for the character at (i)
						points[i] = points[i] + HighScores.calculatePoints(steps1.getValue()+steps2.getValue());
						//set the points counter text to the updated total points for the character at (i)
						pointsNum[i].setTitle(Integer.toString(points[i]));
						//repaint the points counter text picture
						pointsNum[i].repaint();
					}//end of if

					//if the character2 at (i)'s steps rolled is either 4, 6 or 24, skip their turn 
					if (characters2[i].getStepsToMove() == 4 || characters2[i].getStepsToMove() == 6|| characters2[i].getStepsToMove() == 24) {
						//add the character2 at (i) to the turns skipped text picture
						skipTurnList = skipTurnList + "Player " + (i+1) + ", ";

						//set the step and point counter text picture text colour to yellow
						stepCounter[i].setColor(Color.YELLOW);
						pointsNum[i].setColor(Color.YELLOW);
					}//end of if

					//set the text of the skips text picture to the updated list of players whose turns were skipped
					skips.setTitle(skipTurnList);
					//repaint the skips text picture object
					skips.repaint();

					//reset the total stopped characters counter to 0
					totalStopped = 0;
				}//end of for 
			}//end of if totalStopped == 4

			/*
			 * Set the direction of the velocity based on the position of each character/MarvelHero object
			 */

			//loop through each character2 object
			for (int i = 0; i < characters2.length; i++) {
				//if the character's position is at the top left corner of the drawing panel
				if (characters2[i].getxPos() == 10 && characters2[i].getyPos() == 10) {
					xVel[i] = 0; //set the x vel to 0
					yVel[i] = 4; //set the y vel to 4 (moves down)
				}//end of if
				//if the character's position is at the top right corner of the drawing panel
				else if (characters2[i].getxPos() == 890 && characters2[i].getyPos() == 10) {
					xVel[i] = -4; //set the x vel to -4 (moves left)
					yVel[i] = 0; //set the y vel to 0
				}//end of else if
				//if the character's position is at the bottom left corner of the drawing panel
				else if (characters2[i].getxPos() == 10 && characters2[i].getyPos() == (drawingPanel.getHeight()-characters2[i].getMyHeight()-10)) {
					xVel[i] = 4; //set the x vel to 4 (moves right)
					yVel[i] = 0; //set the y vel to 0
				}//end of else if 
				//if the character's position is at the bottom right corner of the drawing panel
				else if (characters2[i].getxPos() == 890 && characters2[i].getyPos() == (drawingPanel.getHeight()-characters2[i].getMyHeight()-10)) {
					xVel[i] = 0; //set the x vel to 0
					yVel[i] = -4; //set the y vel to -4 (moves up)
				}//end of else if 
				//if the character's position is along the top wall of the drawing panel
				else if (characters2[i].getyPos() == 10) {
					xVel[i] = -4; //set the x vel to -4 (moves left)
					yVel[i] = 0; //set the y vel to 0
				}//else if 
				//if the character's position is along the left wall of the drawing panel
				else if (characters2[i].getxPos() == 10) {
					xVel[i] = 0; //set the x vel to 0
					yVel[i] = 4; //set the yvel to 4 (moves down)
				}//end of else if 
				//if the character's position is along the right wall of the drawing panel
				else if (characters2[i].getxPos() == 890) {
					xVel[i] = 0; //set the x vel to 0
					yVel[i] = -4; //set the y vel to -4 (moves up)
				}//else if 
				//if the character's position is along the bottom wall of the drawing panel
				else if (characters2[i].getyPos() == (drawingPanel.getHeight()-characters2[i].getMyHeight()-10)) {
					xVel[i] = 4; //set the x vel to 4 (moves right)
					yVel[i] = 0; //set the y vel to 0
				}//end of else if
			}//end of for 

			//loop through each character2 object array element
			for (int i = 0; i < characters2.length; i++) {
				//if the position of the object is on the finish line 
				if (characters2[i].getxPos() == 806 && characters2[i].getyPos() == 10) {
					//add one to the number of times the finish line was crossed
					timesCrossed++;
				}//end of if
			}//end of for 

			//if the number of times the line was crossed by all characters was 2
			if (timesCrossed/4 == 2) {
				//set lap count to 1
				lapCount = 1;
			}//end of if
			//if the number of times the line was crossed by all characters was 3
			else if (timesCrossed/4 == 3) {
				//set lap count to 2
				lapCount = 2;
			}//end of else if
			//if the number of times the line was crossed by all characters was 4
			else if (timesCrossed/4 == 4) {
				//set lap count to 3
				lapCount = 3;
			}//end of else if
			//if the number of times the line was crossed by all characters was 5
			else if (timesCrossed/4 == 5) {
				//set lap count to 4
				lapCount = 4;
			}//end of else if 

			//if all characters have crossed the finish line after finishing the number of set laps
			if (lapCount == lapNum) {
				//stop the timer
				timer.stop();
				//set the pause-play toggle button to not visible
				pausePlay.setVisible(false);
				//set the steps to move for each character2 object to 0
				for (int i = 0; i < characters2.length; i++) {
					characters2[i].setStepsToMove(0);
				}//end of for 
				//display a new window with the race results and record of high scores from all matches
				try {
					//create a new FinalScreen object for window containing race results and all high scores from all matches played
					FinalScreen pointScreen = new FinalScreen(points, totalSteps, characters2);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}//end of if 

			//loop through each element of the characters2 object array
			for (int i = 0; i < characters2.length; i++) {
				//if the steps character2 at (i) rolled is 4, 6 or 24, skip their turn 
				if (characters2[i].getStepsToMove() == 4 || characters2[i].getStepsToMove() == 6|| characters2[i].getStepsToMove() == 24) {
					//set the velocities of the character2 at (i) to 0
					xVel[i] = 0;
					yVel[i] = 0;
				}//end of if
			}//end of for 

			//loop through each element of the characters2 object array
			for (int i = 0; i < characters2.length; i++) {
				//if the character2 object at (i) reaches the number of steps its supposed to move
				if (characters2[i].getStepsToMove() == count) {
					//set the character2 object's velocities to 0
					xVel[i] = 0;
					yVel[i] = 0;
					//add one to the number of character2 objects stopped stopped
					totalStopped++;
				}//end of if
				//if the character2 object at (i) hasn't reached the number of steps its supposed to move
				else {
					//add the velocity values to the x and y positions of the character2 object
					xPos[i] += xVel[i];
					yPos[i] += yVel[i];

					// change the position of the character2 object at characters2[i]
					characters2[i].setxPos(xPos[i]);
					characters2[i].setyPos(yPos[i]);

					characters2[i].repaint(); //repaint the character2 object at the updated location
				}
			}//end of for 
		}//end of if timer fired
	}//end of method for action events

	/**
	 * Main method for GUI self testing
	 */
	public static void main(String[] args) {
		//declare and initialize the length for an array to store the character choices
		String charactersChoose [] = new String[4];

		//initialize the character choices at each element of the array
		charactersChoose[0] = "Deadpool";
		charactersChoose[1] = "Iron Man";
		charactersChoose[2] = "Spiderman";
		charactersChoose[3] = "Hulk";

		/*
		 * create a new RaceWindow object and pass in testing information
		 * Passes in the team 1 name, team 2 name, background choice, the character choices array and laps to complete
		 */
		RaceWindow race = new RaceWindow("ashwin", "mihir", "Deadpool", charactersChoose, 1);
	}//end of self testing main method
}//end of public class RaceWindow
