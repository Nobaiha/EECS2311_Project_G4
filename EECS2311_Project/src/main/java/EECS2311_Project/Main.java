package EECS2311_Project;

import org.xembly.*;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;

/**
 * The main class. Takes all other classes and uses them.
 *
 * @author Team 4 EECS2311 Winter 2021
 */

public class Main {

    //Temp global to keep track of elements in measures.
    static HashMap<Integer, Integer> measuresElement = new HashMap<>();
    static ArrayList<Measure> measures = new ArrayList<>();

    static String tabTitle = "";
    static String tabComposer = "";


    /**
     * This checks to see if the input file is a ".txt" file.
     *
     * @param file is the name of the input file.
     * @return true if the file is of type ".txt". Fails if otherwise.
     */
    public static boolean fileChecker(String file) {
        int indexOfExt = file.lastIndexOf(".") + 1;
        return file.substring(indexOfExt).equals("txt");
    }

    /**
     * This reads the text file, extracts the infomation, and
     * places it in an ArrayList. It detects whether its a
     * drum or guitar tab.
     *
     * @param file is the name of the input file.
     * @return an ArrayList of the guitar/drum notes.
     * @throws FileNotFoundException throws FileNotFoundException if the file is not found.
     */
    public static Object[] fileParser(String file) throws FileNotFoundException {
        if (!fileChecker(file)) {
            throw new FileNotFoundException("Unsupported file type");
        }
        ArrayList<String> testLines = new ArrayList<>();
        ArrayList<String> drumTestLines = new ArrayList<>();

        //Might need to change the file path depending on system.
        File inputFile = new File(file);

        //Need to check for some others too, sometimes E is a D?
        //String tuning can be changed, needs to be accounted for
        Pattern pattern = Pattern.compile("^([eABCDEFGabcdfg])");
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
            //Send error not found here.
            e.printStackTrace();
        }
        if (testLines.size() > drumTestLines.size()) {
            return new Object[]{"guitar", testLines};
        } else {
            return new Object[]{"drum", drumTestLines};
        }
    }

    /**
     * This takes the information for the guitar tab ArrayList, picks out
     * the important information, and places them into variables.
     *
     * @param noteArray is the ArrayList with the converted ".txt" file.
     * @return an ArrayList of the guitar tab information.
     */
    public static ArrayList<GuitarNote> guitarNoteParser(ArrayList<String> noteArray) {
        //Creates array of notes
        ArrayList<String> strArray = new ArrayList<>();
        for (String list : noteArray) {
            //System.out.println(list);
            String[] tempArray = list.split("-", -1);
            //Converts to arraylist, prob a better way to do this.
            Collections.addAll(strArray, tempArray);
        }

        ArrayList<GuitarNote> guitarNoteArray = new ArrayList<>();

        int measureNum = 1;
        int noteNum = 1;
        int stringVal = 0;
        int repeatTimes = 0;
        int measureMem = 1;
        Pattern pattern = Pattern.compile("^([eABCDEFGabcdfg])");

        //Iterates through the parsed string array and makes an array of note objects
        for (String str : strArray) {
            //System.out.println(str);
            //checks for old regex expression which represents a note (e,a,b,d...)
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                //If it has gone through 6 iterations (6 strings) dont reset the measure count
                if (repeatTimes == 6) {
                    repeatTimes = 0;
                    stringVal = 0;
                    measureMem = measureNum;
                } else {
                    measureNum = measureMem;
                }
                //Sets string # to the index at which the char is located, 0 represents e 1 represents A... etc.
                noteNum = 1;
                //stringVal = str.trim().toLowerCase().charAt(0);
                stringVal++;
                repeatTimes++;
            } else if (str.contains("|")) {
                //Increase measure count if it encounters |
                //Count end of measure here.
                measuresElement.put(measureNum, noteNum);
                noteNum = 1;
                measureNum++;
                if (str.length() > 1) {
                    //Extract this to separate function? duplicate code in function below.
                    GuitarNote tempGuitarNote = new GuitarNote(measureNum, noteNum, stringVal, str.substring(1));
                    guitarNoteArray.add(tempGuitarNote);
                }
            } else if (str.length() == 0) {
                //if blank, increase note count.
                noteNum++;
            } else {
                //Otherwise create a new note with the string as the note value.
                //splits each "block" into smaller individual notes, only checks for alphabet chars in between right now
                //will update regex when encountering new patterns.
                noteNum++;
                for (String character : str.split("(?<=[a-z]|/|\\\\)")) {
                    GuitarNote tempGuitarNote = new GuitarNote(measureNum, noteNum, stringVal, character);
                    guitarNoteArray.add(tempGuitarNote);
                    noteNum += character.length();
                }
            }
        }
        //Test print the array
        /*for (GuitarNote guitarNote : guitarNoteArray) {
            System.out.println("String: " + guitarNote.stringValue);
            System.out.println("Measure number: " + guitarNote.measure);
            System.out.println("Element number: " + guitarNote.noteNumber);
            System.out.println("Element value: " + guitarNote.noteValue);
            System.out.println();
        }*/
        //System.out.println(measuresElement);
        measuresElement.forEach((k, v) -> measures.add(new Measure(v, k)));
        return guitarNoteArray;
    }

    /**
     * This takes the information for the drum tab ArrayList, picks out
     * the important information, and places them into variables.
     *
     * @param noteArray is the ArrayList with the converted ".txt" file.
     * @return an ArrayList of the drum tab information.
     */
    public static ArrayList<DrumNote> drumNoteParser(ArrayList<String> noteArray) {
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

    /**
     * Takes all the information gathered from the guitar tab
     * and makes an XML file with it.
     *
     * @param measures is an ArrayList of the guitar tab information.
     * @return an XML file.
     */
    public static String guitarXMLParser(ArrayList<Measure> measures) {
        boolean pulloff = false;
        boolean hammer = false;
        boolean slurEnd = false;
        //XML print attempt
        if (measures.size() == 0) {
            return null;
        }
        Directives directives = new Directives();
        directives
                .add("score-partwise")
                .attr("version", "3.0")
                .add("work")
                .add("work-title")
                .set(tabTitle)
                .up()
                .up()
                .add("identification")
                .add("creator")
                .attr("type", "composer")
                .set(tabComposer)
                .up()
                .up()
                .add("part-list")
                .add("score-part")
                .attr("id", "P1")
                .add("part-name")
                .set("Guitar") //change this to name of music
                .up()
                .up()
                .up()
                .add("part")
                .attr("id", "P1");
        for (int i = 0; i < measures.size(); i++) {
            directives.add("measure")
                    .attr("number", i + 1);
            //sets the first measure to include tab details and clef etc.
            if (i == 0) {
                directives
                        .add("attributes")
                        .add("divisions") //still no idea what divisions does?
                        .set("4") //the denominator in notes in terms of quarter notes. A duration of 4 will be one quarter note? A duration of 1 will be an 16th?
                        .up()
                        .add("time")
                        .add("beats")
                        .set(4) //change 4/4 to be based on user or input later
                        .up()
                        .add("beat-type")
                        .set(4)
                        .up()
                        .up()
                        .add("clef")
                        .add("sign")
                        .set("TAB") //to indicate it is a tab
                        .up()
                        .add("line")
                        .set(5) //sets tab to line 5
                        .up()
                        .up()
                        .add("staff-details")
                        .add("staff-lines")
                        .set(6)
                        .up()
                        .add("staff-tuning")
                        .attr("line", "1")
                        .add("tuning-step")
                        .set("E")
                        .up()
                        .add("tuning-octave")
                        .set(2)
                        .up()
                        .up()
                        .add("staff-tuning")
                        .attr("line", "2")
                        .add("tuning-step")
                        .set("A")
                        .up()
                        .add("tuning-octave")
                        .set(2)
                        .up()
                        .up()
                        .add("staff-tuning")
                        .attr("line", "3")
                        .add("tuning-step")
                        .set("D")
                        .up()
                        .add("tuning-octave")
                        .set(3)
                        .up()
                        .up()
                        .add("staff-tuning")
                        .attr("line", "4")
                        .add("tuning-step")
                        .set("G")
                        .up()
                        .add("tuning-octave")
                        .set(3)
                        .up()
                        .up()
                        .add("staff-tuning")
                        .attr("line", "5")
                        .add("tuning-step")
                        .set("B")
                        .up()
                        .add("tuning-octave")
                        .set(3)
                        .up()
                        .up()
                        .add("staff-tuning")
                        .attr("line", "6")
                        .add("tuning-step")
                        .set("E")
                        .up()
                        .add("tuning-octave")
                        .set(4)
                        .up()
                        .up()
                        .up()
                        .up();
            }
            //Add each note child node
            for (int j = 0; j < measures.get(i).guitarNotes.size(); j++) {
                GuitarNote guitarNote = measures.get(i).guitarNotes.get(j);
                if (guitarNote.measure == i + 1) {
                    //process note here later
                    directives
                            .add("note");
                    if (guitarNote.chord) {
                        directives.add("chord").up();
                    }
                    directives.add("pitch")
                            .add("step")
                            .set(guitarNote.musicNote).up();
                    if (guitarNote.modifier == 1) {
                        directives.add("alter").set(1).up();
                    }
                    directives
                            .add("octave")
                            .set(guitarNote.octave)
                            .up()
                            .up()
                            .add("duration")
                            .set(guitarNote.duration)
                            .up()
                            .add("notations")
                            .add("technical");
                    if(guitarNote.harmonic){
                        directives.add("harmonic")
                                .add("natural")
                                .up()
                                .up();
                    }
                    if (pulloff && !guitarNote.chord) {
                        directives.add("pull-off")
                                .attr("number", 1)
                                .attr("type", "stop")
                                .up();
                        slurEnd = true;
                        pulloff = false;
                    }
                    if (hammer && !guitarNote.chord) {
                        directives.add("hammer-on")
                                .attr("number", 1)
                                .attr("type", "stop")
                                .up();
                        slurEnd = true;
                        hammer = false;
                    }
                    if (guitarNote.hammer) {
                        directives.add("hammer-on")
                                .attr("number", 1)
                                .attr("type", "start")
                                .set("H")
                                .up();
                        hammer = true;
                    }
                    if (guitarNote.pull) {
                        directives.add("pull-off")
                                .attr("number", 1)
                                .attr("type", "start")
                                .set("P")
                                .up();
                        pulloff = true;
                    }
                    directives.add("string")
                            .set(guitarNote.stringValue)
                            .up()
                            .add("fret")
                            .set(guitarNote.noteValue)
                            .up()
                            .up();
                    if(slurEnd && !guitarNote.chord){
                        directives.add("slur")
                                .attr("number", 1)
                                .attr("type","stop")
                                .up();
                        slurEnd = false;
                    }
                    if (guitarNote.pull || guitarNote.hammer) {
                        directives.add("slur")
                                .attr("number", 1)
                                .attr("type", "start")
                                .attr("placement", "above")
                                .up();
                    }
                    directives.up()
                            .up();
                }
            }
            directives.up();
        }
        String xml = null;
        try {
            xml = new Xembler(
                    directives
            ).xml();
        } catch (Exception e) {
            System.out.println("error");
        }
        return xml;
        //System.out.println(xml);
    }

    /**
     * Takes all the information gathered from the drum tab
     * and makes an XML file with it.
     *
     * @param drumNoteArray is an ArrayList of the drum tab information.
     * @return an XML file.
     */
    public static String drumXMLParser(ArrayList<DrumNote> drumNoteArray) {
        if (drumNoteArray.size() == 0) {
            return null;
        }
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
            for (DrumNote drumNote : drumNoteArray) {
                if (drumNote.measure == i + 1) {
                    //process note here later
                    directives
                            .add("note")
                            .add("pitch")
                            .add("step")
                            .set(drumNote.noteValue)
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
        String xml = null;
        try {
            xml = new Xembler(
                    directives
            ).xml();
        } catch (Exception e) {
            System.out.println("error");
        }
        return xml;
        //System.out.println(xml);
    }

    /**
     * This saves the XML file that was created.
     *
     * @param file is the file that was input.
     * @param xml  is the XML file.
     */
    public static void saveFile(File file, String xml) {
        try {
            FileWriter xmlFile = new FileWriter(file);
            xmlFile.write(xml);
            xmlFile.close();
            //System.exit(0);
            Desktop.getDesktop().open(file);
        } catch (Exception e) {
            new Error("There was an error saving your file, please retry.", tabTitle, tabComposer);
        }
    }

    /*public static MusicNote drumToMusicNote(DrumNote drumNote) {
        if (drumNote.part.equals("C")) {
            if (drumNote.noteValue == 'X') {
                return new MusicNote("B");
            }
        }
        return null;
    }*/

    /**
     * 
     */
    public static void start(String filePath, String title, String composer) throws FileNotFoundException {
        tabTitle = title;
        tabComposer = composer;
        Object[] notes = fileParser(filePath);
        ArrayList<String> noteArray = (ArrayList<String>) notes[1];
        if (notes[0] == "guitar") {
            ArrayList<GuitarNote> guitarNoteArray = guitarNoteParser(noteArray);
            for (GuitarNote guitarNote : guitarNoteArray) {
                guitarNote.setMusicNote();
                /*System.out.println("String: " + guitarNote.stringValue);
                System.out.println("Measure: " + guitarNote.measure);
                System.out.println("Element number: " + guitarNote.noteNumber);
                System.out.println("Fret value: " + guitarNote.noteValue);
                System.out.println("Music note: " + guitarNote.musicNote);
                System.out.println("Octave: " + guitarNote.octave);
                System.out.println();*/
            }
            ArrayList<Measure> measureArrayList = new ArrayList<>();
            for (int i = 0; i < measuresElement.size(); i++) {
                Measure measure = new Measure(measuresElement.get(i + 1), i + 1);
                for (GuitarNote guitarNote : guitarNoteArray) {
                    if (guitarNote.measure == i + 1) {
                        measure.addGuitarNotes(guitarNote);
                    }
                }
                measureArrayList.add(measure);
            }
            for (Measure measure : measureArrayList) {
                measure.sortNotes();
                measure.processDuration();
                //System.out.println(measure);
            }
            String xml = guitarXMLParser(measureArrayList);
            if (xml != null) {
                //saveFile(xml);
                SaveFile saveFile = new SaveFile(xml);
                //saveFile.setXml(xml);
                saveFile.setVisible(true);
            } else {
                new Error("Error parsing, please ensure tab is in correct format.", tabTitle, tabComposer);
            }
        } else {
            //ArrayList<DrumNote> drumNoteArray = drumNoteParser(noteArray);
            Error error = new Error("Error parsing, please ensure tab is in correct format.", tabTitle, tabComposer);

        }
    }

    /**
     * This starts up the GUI.
     */
    public static void main(String[] args) {
        GuiWelcome welcomePage = new GuiWelcome();
        welcomePage.setVisible(true);
    }
}

