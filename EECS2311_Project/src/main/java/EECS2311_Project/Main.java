package EECS2311_Project;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*System.out.println("New Version 17: Martin Edition 2");
		 * New change
		System.out.println("New Version Rayta");
		System.out.println("New Version Suha Siddiqui");
		System.out.println("Richard version");
		System.out.println("Rayta version 2");
		System.out.println("Suha version 2");
		System.out.println("Patchanon edit");*/
		//Rayta labtask
		//Suha labtask

		ArrayList<String> testLines = new ArrayList<>();

		//might need to change the file path depending on system.
		File inputFile = new File("src/main/java/EECS2311_Project/example.txt");

		Pattern pattern = Pattern.compile("^([eBGDAE])");

		//Reads the txt file and parses each line into the arraylist if it is a note line.
		//Maybe need to edit later as sometimes there is information on top of the bars???
		try (Scanner sc = new Scanner(inputFile)) {
			while (sc.hasNextLine()) {
				String nextLine = sc.nextLine();
				Matcher matcher = pattern.matcher(nextLine);
				if (matcher.find()) {
					testLines.add(nextLine);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		//Creates array of notes
		ArrayList<String> strArray = new ArrayList<>();
		for(String list : testLines){
			String[] tempArray = list.split("-",-1);
			//converts to arraylist, prob a better way to do this.
			Collections.addAll(strArray, tempArray);
		}

		ArrayList<Note> noteArray = new ArrayList<>();
		//constant arraylist to check string #
		List<Character> noteConst = Arrays.asList('e','B','G','D','A','D');

		int measureNum = 1;
		int noteNum = 1;
		int stringNum = 0;
		int repeatTimes = 0;
		int measureMem = 1;

		//Iterates through the parsed string array and makes an array of note objects
		for(String str : strArray){
			//checks for old regex expression which represents a note (e,a,b,d...)
			Matcher matcher = pattern.matcher(str);
			if (matcher.find()){
				//If it has gone through 6 iterations (6 strings) dont reset the measure count
				if(repeatTimes == 5){
					repeatTimes = 0;					
					measureMem = measureNum;
				}else {
					measureNum = measureMem;
				}
				//Sets string # to the index at which the char is located, 0 represents e 1 represents A... etc.
				noteNum = 1;
				stringNum = noteConst.indexOf(str.charAt(0));
				repeatTimes++;
			}else if (str.equals("|")){
				//Increase measure count if it encounters |
				noteNum = 1;
				measureNum++;
			}else if(str.length() == 0){
				//if blank, increase note count.
				noteNum++;
			}else{
				//Otherwise create a new note with the string as the note value.
				//splits each "block" into smaller individual notes, only checks for alphabet chars in between right now
				//will update regex when encountering new patterns.
				for(String character : str.split("(?<=[a-z])")){
					noteNum++;
					Note tempNote = new Note(measureNum, noteNum, stringNum, character);
					noteArray.add(tempNote);
				}
			}
		}

		//Test print the array
		for(Note note : noteArray){
			System.out.println("String number: " + note.stringNumber);
			System.out.println("Measure number: " + note.measure);
			System.out.println("Element number: " + note.noteNumber);
			System.out.println("Element value: " + note.noteValue);
			System.out.println();//print a line
		}

		//conflict here?


	}

}
