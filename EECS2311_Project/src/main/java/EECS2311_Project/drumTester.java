package EECS2311_Project;

import java.util.ArrayList;

public class drumTester {
	
	public static void main (String[] args){
		
		String input = "MT|--x-x-x-o-x-x-f-|--------x-------|";
        ArrayList<String> testNoteArray = new ArrayList<>();
        testNoteArray.add(input);
        
        ArrayList<DrumNote> drumNotes = Main.drumNoteParser(testNoteArray);

        Main.drumXMLParser(drumNotes);
        
        
	}

}
