import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicArrowButton;

/**
 * 
 */

/**
 * @author Ashwin
 * Date: November 12, 2022
 * Description: Class creates a new instance of MarvelUniverse to display the main menu to the users. Main menu contains components for titles, 
 * 				team name inputs, character choices, background choices and choice for the number of laps. Once the "start" button is clicked, 
 * 				a new RaceWindow object is created and the MarvelUniverse window object would no longer be visible. Data that is collected from the 
 * 				main menu before "start" was clicked such as character choices, background choice, team names and number of laps is finilized after 
 * 				"start" has been clicked and such information is passed as parameters into the RaceWIndow object which is used by it to create the window 
 * 				containing the race. Main menu is the starting point of the entire program/game. 
 * Method List: 
 * public MarvelUniverse() - Creates a new instance of MarvelUniverse
 * public void actionPerformed (ActionEvent e) - method for action events
 * public static void main(String[] args) - main menu to create an object of the main menu and display it
 *
 */
public class MarvelUniverse extends JFrame implements ActionListener {
	/*
	 * private variables for window components
	 */
	private int width, height; //frame width and height
	private ImagePicture background; //background image
	private ImagePicture heros[][], backgrounds[]; //images array of hero picture arrays and background picture arrays
	private TextPicture team1, gameTitle, team2, team1Name, team2Name, bkg, lapTitle; //text for game title, team names, team 1 and 2 name inpit labels, background label and number of laps title
	private JTextField name1, name2; //text input fields for teams to input team names
	private BasicArrowButton left [], right []; //array of arrow buttons to scroll through characters
	private JComboBox characters [], backgroundOption; //array of combobox to display character choices and to display background choices
	private int length = 4; //length of an array
	private int num []; //number to determine if last character picture has been reached to make arrow disappear
	private String heroID []; //array to store user character choices
	private int num2; //number to determine if last background picture has been reached to make arrow disappear
	private JButton start; // button to continue to next window
	private JComboBox laps; //combo box for user to input number of desired laps

	/**
	 * Creates a new instance of MarvelUniverse
	 */
	public MarvelUniverse() {
		super("Marvel Race");  // title for the frame

		// initialize the width of the frame
		width = 1000;
		height = 650;

		//initialize the num array to the variable length
		num = new int [length];
		//initialize each element of the num array to 1
		for (int i = 0; i < length; i++) {
			num[i] = 1;
		}//end of for

		//initialize variable to 1
		num2 = 1;

		//initialize heroID to length variable
		heroID = new String [length];

		//title for team 1 and team 2
		team1 = new TextPicture("Team 1", 10, 20);
		team1.setFont(new Font("Agency FB", Font.BOLD, 24)); //set the font
		team1.setColor(Color.BLACK); //set the text colour
		team1.setBounds(135, 40, 213, 40); //set the location and dimensions

		team2 = new TextPicture("Team 2", 10, 20);
		team2.setFont(new Font("Agency FB", Font.BOLD, 24)); //set the font
		team2.setColor(Color.BLACK); //set the text colour
		team2.setBounds(780, 40, 213, 40); //set the location and dimensions

		//game title
		gameTitle = new TextPicture ("Marvel Race", 10, 40);
		gameTitle.setFont(new Font("Agency FB", Font.BOLD, 40)); //set the font
		gameTitle.setColor(Color.BLACK); //set the text colour
		gameTitle.setBounds(400, 20, 213, 60); //set the location and dimensions

		//background choice title
		bkg = new TextPicture("Background", 10, 20); 
		bkg.setFont(new Font("Agency FB", Font.BOLD, 24)); //set the font
		bkg.setColor(Color.BLACK); //set the text colour
		bkg.setBounds(440, 100, 213, 40); //set the location and dimensions

		//label for team names
		team1Name = new TextPicture("Team Name", 10, 20);
		team1Name.setFont(new Font("Agency FB", Font.BOLD, 24)); //set the font
		team1Name.setColor(Color.BLACK); //set the text colour
		team1Name.setBounds(120, 90, 213, 40); //set the location and dimensions

		team2Name = new TextPicture("Team Name", 10, 20);
		team2Name.setFont(new Font("Agency FB", Font.BOLD, 24)); //set the font
		team2Name.setColor(Color.BLACK); //set the text colour
		team2Name.setBounds(765, 90, 213, 40); //set the location and dimensions

		lapTitle = new TextPicture("Laps", 10, 20);
		lapTitle.setFont(new Font("Agency FB", Font.BOLD, 24)); //set the text font
		lapTitle.setColor(Color.BLACK); //set the text colour
		lapTitle.setBounds(470, 450, 213, 40); //set the location and dimensions

		//Text fields for team name input
		name1 = new JTextField();
		name1.setBounds(70, 130, 213, 20); //set the location and dimensions
		name1.setHorizontalAlignment(JTextField.CENTER); //center typed text inside text field

		name2 = new JTextField();
		name2.setBounds(715, 130, 213, 20); //set the location and dimensions
		name2.setHorizontalAlignment(JTextField.CENTER); //center typed text inside text field

		//initialize left and right arrow arrays to length of 5
		left = new BasicArrowButton[5];
		right = new BasicArrowButton[5];

		//create the left and right arrow at each element of the left and right arrow arrays
		for (int i = 0; i < 5; i++) {
			left[i] = new BasicArrowButton(BasicArrowButton.WEST);
			right[i] = new BasicArrowButton(BasicArrowButton.EAST);
		}//end of for 

		//set the location and dimensions of each arrow
		left[0].setBounds(30, 280, 30, 30);
		right[0].setBounds(273, 280, 30, 30);
		left[1].setBounds(690, 280, 30, 30);
		right[1].setBounds(930, 280, 30, 30);
		left[2].setBounds(30, 500, 30, 30);
		right[2].setBounds(273, 500, 30, 30);
		left[3].setBounds(690, 500, 30, 30);
		right[3].setBounds(930, 500, 30, 30);
		left[4].setBounds(460, 400, 30, 30);
		right[4].setBounds(510, 400, 30, 30);

		//initially set all the left arrows to not visible
		for (int i = 0; i < 5; i++) {
			left[i].setVisible(false);
		}//end of for

		//create a string array with the character options
		String character[] = {"Deadpool", "Spiderman", "Iron Man", "Hulk"};

		//create an array of combo boxes
		characters = new JComboBox [length];
		//loop through each element of the array and create a combo box with the character options
		for (int i = 0; i < length; i++) {
			characters[i] = new JComboBox<String>(character);
			characters[i].setEnabled(false); //set the combo box to not enables (just for display)
		}

		//set the location and boundaries of the combo boxes
		characters[0].setBounds(70, 200, 213, 20);	
		characters[1].setBounds(715, 200, 213, 20);
		characters[2].setBounds(70, 410, 213, 20);
		characters[3].setBounds(715, 410, 213, 20);

		//initialize the length of an array to store arrays containing each characters image
		heros = new ImagePicture[length][4];

		//loop through each element of the array and create a new image picture for the image to be displayed
		for (int i = 0; i < length; i++) {
			heros[0][i] = new ImagePicture(new ImageIcon("hero" + (i+1) + ".gif")); //create the image picture for the image
			heros[0][i].setBounds(75, 225, 223, 223); //set the location and the boundaries
			heros[1][i] = new ImagePicture(new ImageIcon("hero" + (i+1) + ".gif"));
			heros[1][i].setBounds(735, 225, 223, 223); 
			heros[2][i] = new ImagePicture(new ImageIcon("hero" + (i+1) + ".gif"));
			heros[2][i].setBounds(75, 435, 223, 223); 
			heros[3][i] = new ImagePicture(new ImageIcon("hero" + (i+1) + ".gif"));
			heros[3][i].setBounds(725, 435, 223, 223); 
		}//end of for

		//loop through each element of each characters picture array and set all pictures beside the first one to not visible
		for (int i = 1; i < length; i++) {
			heros[0][i].setVisible(false);
			heros[1][i].setVisible(false);
			heros[2][i].setVisible(false);
			heros[3][i].setVisible(false);
		}//end of for 

		//initialize the length of an array to store background images
		backgrounds = new ImagePicture[length];

		//set the image for each element of the background array
		for (int i = 0; i < length; i++) {
			backgrounds[i] = new ImagePicture(new ImageIcon("bkg" + (i+1) + ".gif")); //create the image picture for the background image
			backgrounds[i].setBounds(350, 190, 300, 195); //set the location and the boundaries
		}//end of for

		//loop through each element of the background array and set all other backgrounds beside the first one to not visible
		for (int i = 1; i < length; i++) {
			backgrounds[i].setVisible(false);
		}//end of for

		String bkgr[] = {"Marvel", "Deadpool", "Captain America's Shield", "Hulk Activated"};

		backgroundOption = new JComboBox<String>(bkgr);
		backgroundOption.setBounds(395, 145, 213, 20);
		backgroundOption.setEnabled(false);

		//create a string array with the choices for the number of laps to be completed
		String lapNum[] = {"1", "2", "3", "4"};

		//create the combo box to display the number of lap choices
		laps = new JComboBox<String>(lapNum);
		laps.setBounds(395, 500, 213, 20); //set the location and boundaries

		//create the start button
		start = new JButton("Start");
		start.setBounds(430, 550, 140, 40); //set the location and boundaries

		//add each background image to the frame
		for (int i = 0; i < length; i++) {
			add(backgrounds[i]);
		}//end of for

		//add each character image array to the frame
		for (int i = 0; i < length; i++) {
			add(heros[0][i]);
			add(heros[1][i]);
			add(heros[2][i]);
			add(heros[3][i]);
		} //end of for

		//add team 1 and team 2 labels to frame
		add(team1);
		add(team2);

		//add team name labels, title, background and lap number labels to frame
		add(team1Name);
		add(team2Name);
		add(gameTitle);
		add(bkg);
		add(lapTitle);

		//add team name text input fields to frame
		add(name1);
		add(name2);

		//add the start button
		add(start);

		//add the scroll arrows to the frame
		for (int i = 0; i < 5; i++) {
			add(left[i]); //add the left arrow
			add(right[i]); //add the right arrow
		}//end of for

		//add drop down menus to the frame
		for (int i = 0; i < length; i++) {
			add(characters[i]);
		}//end of for 
		
		//add the background option drop down menu for displaying to the frame
		add(backgroundOption);
		//add the laps option drop down menu for diplaying to the frame
		add(laps);

		//add character drop down menus as listeners to this frame
		for (int i = 0; i < length; i++) {
			characters[i].addActionListener(this);
		}//end of for

		//add left and right arrows as listeners to this frame
		for (int i = 0; i < 5; i++) {
			left[i].addActionListener(this);
			right[i].addActionListener(this);
		}
		
		//add the laps drop down menu as a listener to this frame
		laps.addActionListener(this);
		//add the start button as a listener to this frame
		start.addActionListener(this);

		//create an image picture for the background image
		background = new ImagePicture(new ImageIcon("deadpool-background.gif"), 0, 0);
		//add the background to the frame
		add(background);

		setSize(width, height); //set the size of the frame
		setResizable(false); //make the window not resizable
		setLocationRelativeTo(null); //center the window to the center of the user's screen
		setDefaultCloseOperation(EXIT_ON_CLOSE); //allow user to close window and end program with the 'x' 
		setVisible(true); //make the window visible
	}//end of constructor 

	//method for action events
	public void actionPerformed (ActionEvent e){ 

		/*
		 * for the character options
		 */

		//loop through each element of the heroID and get user choice for each character 
		for (int i = 0; i < length; i++) {
			heroID[i] = String.valueOf(characters[i].getSelectedItem()); //convert selected item to a string value
		}//end of for 

		//if an event has occured
		if (e != null) {
			//loop through the arrays of characters 
			for (int i = 0; i < length; i++) {
				//if the right arrow for character [i] was clicked
				if (e.getSource()==right[i]) {
					//add 1 to the num variable
					num[i]++;
					//if the number of times the right arrow was clicked == 4 (reached last character)
					if (num[i] == 4) {
						right[i].setVisible(false); //set the right arrow to not visible
					}//end of if num == 4
					//if the number of times the right arrow was clicked > 1 (not reached first character)
					if (num[i] != 1) {
						left[i].setVisible(true); //set the left arrow to visible
					}//end of if num != 1
					characters[i].setSelectedIndex(num[i]-1); //set the drop down menu choice to the chosen character
				}//end of if right arrow was clicked

				//if the left arrow for character [i] was clicked
				if (e.getSource()==left[i]) {
					//subtract 1 to the num variable
					num[i]--;
					//if the number of times the left arrow was clicked == 1 (reached first character)
					if (num[i] == 1) {
						left[i].setVisible(false); //set the left arrow not visible
					} //end of if num == 1
					if (num[i] != 4) {
						right[i].setVisible(true); //set the right arrow to visible
					}//end of if num != 4
					characters[i].setSelectedIndex(num[i]-1); //set the drop down menu choice to the chosen character
				}//end of if left arrow was clicked

				//if the chosen character is deadpool
				if (heroID[i].equalsIgnoreCase("Deadpool")) {
					//set all other heros to not visible and set deadpool to visible
					heros[i][1].setVisible(false);
					heros[i][2].setVisible(false);
					heros[i][3].setVisible(false);
					heros[i][0].setVisible(true);
				}//end of if 
				
				//if the chosen character is ironman
				if (heroID[i].equalsIgnoreCase("Iron Man")) {
					//set all other heros to not visible and set ironman to visible
					heros[i][0].setVisible(false);
					heros[i][2].setVisible(false);
					heros[i][3].setVisible(false);
					heros[i][1].setVisible(true);
				}//end of if
				
				//if the chosen character is spiderman
				if (heroID[i].equalsIgnoreCase("Spiderman")) {
					//set all other heros to not visible and set spiderman to visible
					heros[i][0].setVisible(false);
					heros[i][1].setVisible(false);
					heros[i][3].setVisible(false);
					heros[i][2].setVisible(true);
				}//end of if
				
				//if the chosen character is hulk
				if (heroID[i].equalsIgnoreCase("Hulk")) {
					//set all other heros to not visible and set hulk to visible
					heros[i][0].setVisible(false);
					heros[i][1].setVisible(false);
					heros[i][2].setVisible(false);
					heros[i][3].setVisible(true);
				}//end of if
			}//end of for loop looping through each characters array
		}//end of if an event has occured

		
		/*
		 * for the background options
		 */
		//if the right arrow for the background options was clicked
		if (e.getSource()==right[4]) {
			//add 1 to the num2 variable
			num2++;
			//if the number of times the right arrow was clicked == 4 (reached last background option)
			if (num2 == 4) {
				right[4].setVisible(false); //set the right arrow for the background options to not visible
			}//end of if num2 == 4
			//if the number of times the right arrow was clicked > 1 (not reached first background option)
			if (num2 != 1) {
				left[4].setVisible(true); //set the left arrow for the background options to visible
			}//end of if num2 != 1
			backgroundOption.setSelectedIndex(num2-1); //set the drop down menu choice to the chosen background
		}//end if if right arrow was clicked for background options

		//if the left arrow for the background options was clicked
		if (e.getSource()==left[4]) {
			//subtract 1 from the num2 variable
			num2--;
			//if the number of times the left arrow was clicked == 1 (reached first background option)
			if (num2 == 1) {
				left[4].setVisible(false); //set the left arrow for the background options to not visible
			}//end of if num2 == 1
			//if the number of times the left arrow was clicked < 4 (not reached last background option)
			if (num2 != 4) {
				right[4].setVisible(true); //set the right arrow for the background options to visible
			}//end of if num2 != 4
			backgroundOption.setSelectedIndex(num2-1); //set the drop down menu choice to the chosen background
		}//end if if left arrow was clicked for background options

		//if the chosen background is marvel
		if (String.valueOf((backgroundOption.getSelectedItem())).equalsIgnoreCase("Marvel")) {
			//set all other backgrounds to not visible and set marvel background to visible
			backgrounds[1].setVisible(false);
			backgrounds[2].setVisible(false);
			backgrounds[3].setVisible(false);
			backgrounds[0].setVisible(true);
		}//end of if
		
		//if the chosen background is deadpool
		if (String.valueOf((backgroundOption.getSelectedItem())).equalsIgnoreCase("Deadpool")) {
			//set all other backgrounds to not visible and set deadpool background to visible
			backgrounds[0].setVisible(false);
			backgrounds[2].setVisible(false);
			backgrounds[3].setVisible(false);
			backgrounds[1].setVisible(true);
		}//end of if
		
		//if the chosen background is captain america's shield
		if (String.valueOf((backgroundOption.getSelectedItem())).equalsIgnoreCase("Captain America's Shield")) {
			//set all other backgrounds to not visible and set shield background to visible
			backgrounds[0].setVisible(false);
			backgrounds[1].setVisible(false);
			backgrounds[3].setVisible(false);
			backgrounds[2].setVisible(true);
		}//end of if 
		
		//if the chosen background is hulk activated
		if (String.valueOf((backgroundOption.getSelectedItem())).equalsIgnoreCase("Hulk Activated")) {
			//set all other backgrounds to not visible and set hulk activated background to visible
			backgrounds[0].setVisible(false);
			backgrounds[1].setVisible(false);
			backgrounds[2].setVisible(false);
			backgrounds[3].setVisible(true);
		}//end if if

		if (e.getSource()==start) { //if start button is clicked
			//get the team names from the text input fields
			String nameTeam1 = name1.getText();
			String nameTeam2 = name2.getText();
			
			//get the chosen background option from the background drop down menu
			String chosenBackground = String.valueOf((backgroundOption.getSelectedItem()));
			//get the chosen number of laps from the laps drop down menu
			int numLaps = Integer.parseInt(String.valueOf(laps.getSelectedItem()));

			//initialize a string array to the length of the length variable
			String chosenCharacters [] = new String [length];
			//loop through each element of the chosenCharacters array
			for (int i = 0; i < length; i++) {
				//set the chosen character to the selected character for each player (from the character's dropdown menu)
				chosenCharacters[i] = String.valueOf((characters[i].getSelectedItem()));
			}//end of for
			//set the main menu frame to not visible
			setVisible(false);

			//create an object of the race screen window with the parameters of all user inputed data and choices and displays the race window
			RaceWindow raceScreen = new RaceWindow(nameTeam1, nameTeam2, chosenBackground, chosenCharacters, numLaps);
		}//end if if start button is clicked
	}//end of method for action events

	/**
	 * main menu to create an object of the main menu and display it
	 */
	public static void main(String[] args) {
		//create an object of the main menu and displays it on user's screen
		MarvelUniverse game = new MarvelUniverse();
	}//end of main method
}//end of public class
