import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 
 */

/**
 * @author Mihir
 * Date: November 12, 2022
 * Description: Class contains methods to calculate total points based on steps taken, method to read from a file and method to 
 * 				write to a file. 
 * Method List:
 * public static int calculatePoints(int step) - method to calculate points based on steps passed in
 * public static String [] readFile (String fileName) throws IOException - method to read from file by passing in file name
 *public static void writeFile (String scores[], String writeFile) throws IOException - method to write to a file by passing in array of data and file name to write to
 *public static void main(String[] args) throws IOException - Self testing main method
 */
public class HighScores{

	//method to calculate points based on steps passed in
	public static int calculatePoints(int step) {
		//return the appropriate points based on the steps passed in
		switch (step) {
		case 2: {//if 2 steps
			return 1; //return 1 point
		}//end of case
		case 3: {//if 3 steps
			return 2; //return 2 points
		}//end of case
		case 5: {//if 5 steps
			return 3; //return 3 points
		}//end of case
		//if 7,8,9,10,11,12, 13, 14, 15,16,17,18,19,20,21,22 or 23 steps 
		case 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23: { 
			return (step-3); //return (steps-3) points
		}//end of case
		//if 4, 6 or 24 steps
		default: {
			return 0; //return 0 points
		}//end of default
		}//end of switch
	}//end of calculatePoints method

	//method to read from file by passing in file name
	public static String [] readFile (String fileName) throws IOException {
		//open a file to read
		BufferedReader reader = new BufferedReader(new FileReader(fileName));

		//set the size on the data set to the variable "length"
		int length = Integer.parseInt(reader.readLine());

		//create array of strings to read the rest of the file with the number of elements set to length
		String score[] = new String[length];

		//load each phrase into the string array
		for(int i = 0; i < length; i++) {
			//read the data and assign it to the score [i] in the string array
			score[i] = reader.readLine();
		}//end of for
		//close the file
		reader.close();

		//return the array
		return score;
	}//end of readFile method
	
	//method to write to a file by passing in array of data and file name to write to
	public static void writeFile (String scores[], String writeFile) throws IOException {
		//open a new file to write to (name being fileName specified by user passed into method)
		FileWriter fileW = new FileWriter(writeFile);
		PrintWriter output = new PrintWriter(fileW);

		//print the length of the data/array in the file
		output.println(scores.length);
		
		//loop through each element of the passed in array
		for (int i = 0; i < scores.length; i++) {
			//write the value at element (i) of the scores array into the file
			output.println(scores[i]);
		}//end of for

		fileW.close(); //write to the file and close the file
	}//end of writeFile method

	/**
	 * Self testing main method
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//variable for steps to test calculating its points
		int steps1 = 4, steps2 = 12, steps3 = 7;
		
		//call calculatePoints method and return the points and display in the console window
		System.out.println(calculatePoints(steps1));
		System.out.println(calculatePoints(steps2));
		System.out.println(calculatePoints(steps3));
		
		//array to store values read from file 
		String fileValues [] = new String[5];
		//call readFile method and assign the returned array to fileValues
		fileValues = readFile("highScoresSaved");
		
		//display the read values from the file stored in the fileValues array in the console window
		for (int i = 0; i < fileValues.length; i++) {
			System.out.println(fileValues[i]);
		}//end of for
		
		//call writeFile method to test writing to a new file
		writeFile(fileValues, "highScoresSavedText");
	}//end of self testing main method
}//end of public class HighScores
