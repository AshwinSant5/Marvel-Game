/**
 * 
 */

/**
 * @author Ashwin Santhosh
 * Date: October 5th, 2022
 * Description: Creates a die (6 faces or user specified) and rolls the die
 * Method List: 
 * public Die() - Default constructor (6 faces) 
 * public Die(int faces) - overloaded constructor (takes in desired faces)
 * public void rollDie() - method to roll die
 * public int getValue() - method to get value
 * public int getFaces() - method to get faces
 * public boolean compareTo(Die d) - method to compare die values
 * public static void main(String[] args) - self testing main method
 *
 */
public class Die {
	/**
	 * Attributes - Data
	 */
	private int faces; //faces of the die
	private int value; //value rolled by the die

	/**
	 * Default constructor (6 faces) 
	 */
	public Die() {
		//initializes the attributes or data
		this.faces = 6; //set the number of faces of the die to 6
		this.rollDie(); //roll the die
	}//end of default constructor 

	/**
	 * Overloaded constructor
	 *Takes in desired faces
	 */
	public Die(int faces) {
		this.faces = faces;//set the number of faces of the die to parsed faces
		this.rollDie(); //roll the die
	}//end of overloaded constructor 

	//method to roll die
	public void rollDie() {
		//random number based on faces
		this.value = (int)(Math.random()* this.faces+1);
	}//end of rollDie method

	/**
	 * Method to get value
	 */
	public int getValue() {
		//returns this objects value
		return this.value;
	}//end of getValue method

	/**
	 * @return the faces
	 */
	public int getFaces() {
		//return the number of faces 
		return faces;
	}//end of getFaces method

	/**
	 * Method to compare die values
	 */
	public boolean compareTo(Die d) {
		//returns true or false if both die values are the same 
		return (this.getValue() == d.getValue());
	}//end of compareTo method


	/**
	 * @param args
	 * self testing main method
	 */
	public static void main(String[] args) {
		//Create a Die object
		Die d1 = new Die();

		//display the initial value of die 
		System.out.println(d1.getValue());

		//Create a second die
		Die d2 = new Die();

		//display the initial value of die 
		System.out.println(d2.getValue());

		//roll the 2 dice in a loop 100 times
		for (int i = 0; i<100; i++) {
			//roll both dice
			d1.rollDie();
			d2.rollDie();
			//if both dice rolled the same value
			if(d1.getValue() == d2.getValue()) {
				//print the rolled value of both dice in the console window
				System.out.println(d1.getValue() + " " + d2.getValue());
				//print the number of rolls it took for both dice to roll the same value in the console window
				System.out.println("It took " + (i+1) + " rolls for the dice to be the same");
				//break out of the for loop
				break;
			}//end of if

		}//end of for

		//create a new die with 12 faces
		Die d3 = new Die(12);

		//display the initial value of die 
		System.out.println(d3.getValue());

		//create a second die with 12 faces
		Die d4 = new Die(12);

		//roll the 2 dice in a loop 100 times
		for (int i = 0; i<100; i++) {
			//roll the two dice with 12 faces
			d3.rollDie();
			d4.rollDie();
			//if both dice rolled the same value
			if(d3.getValue() == d4.getValue()) {
				//print the rolled value of both dice in the console window
				System.out.println(d3.getValue() + " " + d4.getValue());
				//print the number of rolls it took for both dice to roll the same value in the console window
				System.out.println("It took " + (i+1) + " rolls for the dice to be the same");
				//break out of the for loop
				break;
			}//end of if
		}//end of for loop

		//test the compareTo method

		//roll the two default dice
		d1.rollDie();
		d2.rollDie();

		//print whether both dice are equal or not (true or false)
		System.out.println(d2.compareTo(d1));
	}
}
