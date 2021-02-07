package EECS2311_Project;

import org.xembly.*;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.*;

/**
 * The main class.
 *
 * @author Team 4 EECS2311 Winter 2021
 */

public class Main {

    private static Object[] fileParser (String file){

        ArrayList<String> testLines = new ArrayList<>();
        ArrayList<String> drumTestLines = new ArrayList<>();

        //might need to change the file path depending on system.
        File inputFile = new File(file);

        //need to check for some others too, sometimes E is a D?
        //string tuning can be changed, needs to be accounted for
        Pattern pattern = Pattern.compile("^([eABCDEFG])");
        //Need to check for alternatives such as CC HH, etc.
        Pattern drumPattern = Pattern.compile("^([CHSBRTF])");

        //Reads the txt file and parses each line into the arraylist if it is a note line.
        //Maybe need to edit later as sometimes there is information on top of the bars???
        try (Scanner sc = new Scanner(inputFile)) {
            while (sc.hasNextLine()) {
                //removes all spaces.
                String nextLine = sc.nextLine().replaceAll("\\s", "");
                //System.out.println(nextLine);
                Matcher matcher = pattern.matcher(nextLine);
                if (matcher.find()) {
                    testLines.add(nextLine);
                } else {
                    Matcher drumMatcher = drumPattern.matcher(nextLine);
                    if (drumMatcher.find()) {
                        drumTestLines.add(nextLine);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (testLines.size() > drumTestLines.size()) {
            return new Object[]{"guitar", testLines};
        }else{
            return new Object[]{"drum", drumTestLines};
        }
    }

    private static ArrayList<GuitarNote> guitarNoteParser (ArrayList<String> noteArray){
        //Creates array of notes
        ArrayList<String> strArray = new ArrayList<>();
        for (String list : noteArray) {
            String[] tempArray = list.split("-", -1);
            //converts to arraylist, prob a better way to do this.
            Collections.addAll(strArray, tempArray);
        }

        ArrayList<GuitarNote> guitarNoteArray = new ArrayList<>();

        int measureNum = 1;
        int noteNum = 1;
        char stringVal = '.';
        int repeatTimes = 0;
        int measureMem = 1;
        Pattern pattern = Pattern.compile("^([eABCDEFG])");

        //Iterates through the parsed string array and makes an array of note objects
        for (String str : strArray) {
            //checks for old regex expression which represents a note (e,a,b,d...)
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                //If it has gone through 6 iterations (6 strings) dont reset the measure count
                if (repeatTimes == 5) {
                    repeatTimes = 0;
                    measureMem = measureNum;
                } else {
                    measureNum = measureMem;
                }
                //Sets string # to the index at which the char is located, 0 represents e 1 represents A... etc.
                noteNum = 1;
                stringVal = str.trim().toLowerCase().charAt(0);
                repeatTimes++;
            } else if (str.equals("|")) {
                //Increase measure count if it encounters |
                noteNum = 1;
                measureNum++;
            } else if (str.length() == 0) {
                //if blank, increase note count.
                noteNum++;
            } else {
                //Otherwise create a new note with the string as the note value.
                //splits each "block" into smaller individual notes, only checks for alphabet chars in between right now
                //will update regex when encountering new patterns.
                for (String character : str.split("(?<=[a-z])")) {
                    noteNum++;
                    GuitarNote tempGuitarNote = new GuitarNote(measureNum, noteNum, stringVal, character);
                    guitarNoteArray.add(tempGuitarNote);
                }
            }
        }
        return guitarNoteArray;

        //Test print the array
            /*for (GuitarNote guitarNote : guitarNoteArray) {
                System.out.println("String: " + guitarNote.stringValue);
                System.out.println("Measure number: " + guitarNote.measure);
                System.out.println("Element number: " + guitarNote.noteNumber);
                System.out.println("Element value: " + guitarNote.noteValue);
                System.out.println();
            }*/
    }

    private static ArrayList<DrumNote> drumNoteParser (ArrayList<String> noteArray){
        //drum test case
        ArrayList<DrumNote> drumNoteArray = new ArrayList<>();
        ArrayList<String> strArray = new ArrayList<>();
        for (String list : noteArray) {
            String[] tempArray = list.split("-", -1);
            //converts to arraylist, prob a better way to do this.
            Collections.addAll(strArray, tempArray);
        }

        int measureNum = 1;
        int noteNum = 1;
        String part = "";
        String repeatedPart = "";
        int measureMem = 1;
        Pattern pattern = Pattern.compile("^([CHSBRTF])");

        //Iterates through the parsed string array and makes an array of note objects
        for (String str : strArray) {
            //System.out.println((str));
            //checks for old regex expression which represents a note
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                //System.out.println((str));
                //checks to see if the first part of the line indicates a "string" i.e checks for a |
                int indexOfMeasure = str.indexOf("|");
                if (indexOfMeasure != -1) {
                    part = str.substring(0, indexOfMeasure);
                    //does once, to set the repeated char to the first string.
                    if (repeatedPart.equals("")) {
                        repeatedPart = part;
                    }
                    if (str.length() > indexOfMeasure + 1) {
                        //outlier where first note isnt a '-'
                        DrumNote tempDrumNote = new DrumNote(measureNum, noteNum, part, str.charAt(indexOfMeasure + 1));
                        drumNoteArray.add(tempDrumNote);
                    } else if (repeatedPart.equals(part)) {
                        //If it has gone through all possible parts dont reset the measure count
                        measureMem = measureNum;
                    } else {
                        measureNum = measureMem;
                    }
                    noteNum = 1;
                }
            } else if (str.contains("|")) {
                //Increase measure count if it encounters |
                noteNum = 1;
                measureNum++;
                if (str.length() > 1) {
                    //Extract this to separate function? duplicate code in function below.
                    for (char character : str.substring(1).toCharArray()) {
                        DrumNote tempDrumNote = new DrumNote(measureNum, noteNum, part, character);
                        drumNoteArray.add(tempDrumNote);
                    }
                }
            } else if (str.length() == 0) {
                //if blank, increase note count.
                noteNum++;
            } else {
                //Otherwise create a new note with the string as the note value.
                //splits each "block" into smaller individual notes, only checks for alphabet chars in between right now
                //will update regex when encountering new patterns.
                for (char character : str.toCharArray()) {
                    noteNum++;
                    DrumNote tempDrumNote = new DrumNote(measureNum, noteNum, part, character);
                    drumNoteArray.add(tempDrumNote);
                }
            }
        }

        //Test print the array.
        /*for (DrumNote drumNote : drumNoteArray) {
            System.out.println("Part: " + drumNote.part);
            System.out.println("Measure number: " + drumNote.measure);
            System.out.println("Element number: " + drumNote.noteNumber);
            System.out.println("Element value: " + drumNote.noteValue);
            System.out.println();
        }*/
        return drumNoteArray;
    }

    private static void guitarXMLParser (ArrayList<GuitarNote> guitarNoteArray){
        //XML print attempt
        Directives directives = new Directives();
        directives
                .add("score-partwise")
                .attr("version", "3.0")
                .add("part-list")
                .add("score-part")
                .attr("id", "P1")
                .add("part-name")
                .set("Music")
                .up()
                .up()
                .up()
                .add("part")
                .attr("id", "P1");
        for (int i = 0; i < 2; i++) {
            directives.add("measure")
                    .attr("number", i + 1)
                    .add("attributes")
                    .add("divisions")
                    .set("1")
                    .up()
                    .add("time")
                    .add("beats")
                    .set(4)
                    .up()
                    .add("beat-type")
                    .set(4)
                    .up()
                    .up()
                    .add("clef")
                    .add("sign")
                    .set("G")
                    .up()
                    .add("line")
                    .set(2)
                    .up()
                    .up()
                    .up();
            for (GuitarNote guitarNote : guitarNoteArray) {
                if (guitarNote.measure == i + 1) {
                    //process note here later
                    directives
                            .add("note")
                            .add("pitch")
                            .add("step")
                            .set(guitarNoteArray.get(i).noteValue)
                            .up()
                            .add("octave")
                            .set(4)
                            .up()
                            .up()
                            .add("duration")
                            .set(4)
                            .up()
                            .add("type")
                            .set("whole")
                            .up()
                            .up();
                }
            }
            directives.up();
        }
        String xml;
        try {
            xml = new Xembler(
                    directives
            ).xml();
            FileWriter xmlFile = new FileWriter("text.musicxml");
            xmlFile.write("");
            xmlFile.write(xml);
            xmlFile.close();
            System.out.println(xml);
        } catch (Exception e) {
            System.out.println("error");
        }
        //System.out.println(xml);
    }

    public static void main(String[] args) {
        Object[] notes = fileParser("src/main/java/EECS2311_Project/drumExample3.txt");
        ArrayList<String> noteArray = (ArrayList<String>)notes[1];
        if (notes[0] == "guitar") {
            ArrayList<GuitarNote> guitarNoteArray = guitarNoteParser(noteArray);
            guitarXMLParser(guitarNoteArray);
        } else {
            ArrayList<DrumNote> drumNoteArray = drumNoteParser(noteArray);
        }
    }
}
