/*
Kevin Baron
1/30/13
Personality Test
*/

import java.io.*;//for PrintStream and File
import java.util.*;//for Arrays and Scanner

public class KWBaron_PersonalityTest {
	
	public static final int DIMENSIONS = 4;//for clarity, DO NOT CHANGE!!!
	
	//give an intro and process all the results of a given input file to a given output file.
	public static void main(String[] args) throws FileNotFoundException {
		giveIntro();
		processAll(new File(getInputName()), new File(getOutputName()));
	}//eo main
	
	public static void giveIntro() {
		System.out.println("This program processes a file of answers to the");
		System.out.println("Keirsey Temperament Sorter.  It converts the");
		System.out.println("various A and B answers for each person into");
		System.out.println("a sequence of B-percentages and then into a");
		System.out.println("four letter personality type.\n");
	}//eo giveIntro
	
	//prompt for and return an input file name.
	public static String getInputName() {
		System.out.print("input file name? ");
		return new Scanner(System.in).nextLine();
	}//eo getInputName
	
	//prompt for and return an output file name.
	public static String getOutputName() {
		System.out.print("output filename? ");
		return new Scanner(System.in).nextLine();
	}//eo getInputName
	
	//use a scanner to grab each line pair.
	//process each individual with a given PrintStream linked to the output file.
	public static void processAll(File input, File output) throws FileNotFoundException {
		Scanner scanIn = new Scanner(input);
		PrintStream printOut = new PrintStream(output);
		while (scanIn.hasNextLine()) {
			processIndividual(scanIn.nextLine(), scanIn.nextLine().toUpperCase(), printOut);
		}//eo while
	}//eo processAll
	
	//print the first part, the name and a colon.
	//get a 2D array that contains answer information sorted by dimension.
	//get and store an array that contains the percent of B answers for each dimension.
	//print the array containing the percentages of B answers and an equals sign.
	//use the stored percentages to determine and print the appropriate letter for each dimension.
	public static void processIndividual(String name, String answers, PrintStream printOut) {
		printOut.print(name + ": ");
		int[] percentBs = getPercentBs(getDimAnswers(answers));
		printOut.print(Arrays.toString(percentBs) + " = ");
		String[][] letters = {{"E", "I"}, {"S", "N"}, {"T", "F"}, {"J", "P"}};
		for (int i = 0; i < DIMENSIONS; i++) {
			if (percentBs[i] < 50) {
				printOut.print(letters[i][0]);//E, S, T, or F
			} else if (percentBs[i] > 50) {
				printOut.print(letters[i][1]);//I, N, F, or P
			} else {
				printOut.print("X");
			}//eo if else block
		}//eo for
		printOut.println();
	}//eo processIndividual
	
	//create a 2D array that can store two answer choices for each dimension.
	//fill the array by checking every answer in the 70-answer line.
	//return the array.
	public static int[][] getDimAnswers(String answers) {
		int[][] dimAnswers = new int[DIMENSIONS][2];
		for (int i = 0; i < 70; i++) {
			if (answers.charAt(i) != '-') {
				dimAnswers[(i % 7 + 1) / 2][answers.charAt(i) - 65]++;
			}//eo if
		}//eo for
		return dimAnswers;
	}//eo getDimAnswers
	
	//create an array that can store a percent for each dimension.
	//use the array of stored answers to fill each dimension with a percentage.
	//return the array.
	public static int[] getPercentBs(int[][] dimAnswers) {
		int[] percentBs = new int[DIMENSIONS];
		for (int i = 0; i < percentBs.length; i++) {
			percentBs[i] = (int) (100.0 * dimAnswers[i][1] / (dimAnswers[i][0] + dimAnswers[i][1]) + 0.5);
		}//eo for
		return percentBs;
	}//eo getPercentBs
	
}//eo KWBaron_PersonalityTest