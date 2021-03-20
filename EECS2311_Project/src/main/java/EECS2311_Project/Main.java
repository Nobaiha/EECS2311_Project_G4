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
    static LinkedHashSet<Integer> repeatStarts = new LinkedHashSet<>();
    static LinkedHashSet<Integer> repeatEnds = new LinkedHashSet<>();
    static ArrayList<Integer> repeatAmout = new ArrayList<>();
    static ArrayList<Integer> topRepeatStarts = new ArrayList<>();
    static ArrayList<Integer> topRepeatEnds = new ArrayList<>();
    static ArrayList<Integer> topRepeatMeasuresStarts = new ArrayList<>();
    static ArrayList<Integer> topRepeatMeasuresEnds = new ArrayList<>();

    static String tabTitle = "";
    static String tabComposer = "";
    static String tabContents = "";


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
        Pattern pattern = Pattern.compile("^([eABCDEFGabcdfg|]||(?i)\\brepeat\\b)");
        //Need to check for alternatives such as CC HH, etc.
        Pattern drumPattern = Pattern.compile("^([CHSBRTF])");

        //Reads the txt file and parses each line into the arraylist if it is a note line.
        //Maybe need to edit later as sometimes there is information on top of the bars???
        try (Scanner sc = new Scanner(inputFile)) {
            while (sc.hasNextLine()) {
                String nextLine = sc.nextLine();
                System.out.println(nextLine);
                tabContents += nextLine + "\n";
                //removes all spaces.
                if (!nextLine.contains("|")) {
                    //System.out.println("linebreak");
                    testLines.add(" ");
                    drumTestLines.add(" ");
                }
                //String cleanedLine = nextLine.replaceAll("\\s", "");
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
        ArrayList<String[]> strArray = new ArrayList<>();
        //System.out.println("Printing notearray");
        for (String list : noteArray) {
            System.out.println(list);
            String[] tempArray = list.split("[-\\s]", -1);
            //System.out.println(Arrays.toString(tempArray));
            strArray.add(tempArray);
        }
        ArrayList<GuitarNote> guitarNoteArray = new ArrayList<>();

        int measureNum = 1;
        int noteNum = 1;
        int stringVal = 0;
        int repeatTimes = 0;
        int measureMem = 1;
        boolean repeat;
        boolean topRepeat = false;
        boolean repeatFlag = false;
        int repeatNum;
        int measureChars = 1;
        //Pattern pattern = Pattern.compile("^([eABCDEFGabcdfg])");
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher;

        //Iterates through the parsed string array and makes an array of note objects
        for (String[] array : strArray) {
            System.out.println(Arrays.toString(array));
            if (topRepeat) {
                topRepeatStarts.remove(topRepeatStarts.size() - 1);
            }
            topRepeat = false;
            repeat = false;
            repeatNum = 0;
            System.out.println(array.length);
            if (repeatTimes == 6) {
                repeatTimes = 0;
                stringVal = 0;
                measureMem = measureNum;

            }
            int counter = 0;
            //lowercases the repeat for checking
            for (int i = 0; i < array.length; i++) {
                array[i] = array[i].toLowerCase();
            }
            if (Arrays.asList(array).contains("repeat")) {
                topRepeat = true;
            }
            for (String str : array) {
                //System.out.println(str);
                if (topRepeatStarts.contains(measureChars)) {
                    repeatStarts.add(measureNum);
                    //topRepeatMeasuresStarts.add(measureNum);
                }
                if (topRepeatEnds.contains(measureChars)) {
                    repeatEnds.add(measureNum);
                    //topRepeatMeasuresEnds.add(measureNum);
                }
                //System.out.println(str);
                if (topRepeat && pattern.matcher(str).find()) {
                    repeatNum += str.length() + 1;
                    //System.out.println(str);
                    //System.out.println("repeat num is: " + repeatNum);
                    repeatFlag = true;
                    repeatAmout.add(Integer.parseInt(str.replaceAll("\\D+", "")));
                } else if (topRepeat && str.contains("repeat")) {
                    repeatNum += str.length() + 1;
                } else if (str.equals("||") && counter == 0) {
                    //do repeat.
                    //measuresElement.put(measureNum, noteNum);
                    measureNum = measureMem;
                    //change notenum to an array than contains repeat value and note number.
                    repeatStarts.add(measureNum);
                    measureNum++;
                    repeat = true;
                    noteNum = 1;
                } else if (str.equals("||")) {
                    measuresElement.put(measureNum, noteNum);
                    if (repeat) {
                        repeat = false;
                        repeatEnds.add(measureNum);
                    } else {
                        repeat = true;
                        repeatStarts.add(measureNum);
                    }
                    noteNum = 1;
                    measureNum++;
                } else if (str.length() >= 2 && str.endsWith("|") && Character.isDigit(str.charAt(0)) && stringVal == 1) {
                    repeat = false;
                    noteNum++;
                    measuresElement.put(measureNum, noteNum);
                    noteNum = 1;
                    repeatEnds.add(measureNum);
                    measureNum++;
                    repeatAmout.add(Character.getNumericValue(str.charAt(0)));
                }/*else if(str.startsWith("*") && str.endsWith("|")){
                    repeat = false;
                    noteNum++;
                    measuresElement.put(measureNum, noteNum);
                    noteNum = 1;
                    repeatEnds.add(measureNum);
                    measureNum++;
                }*/ else if (str.contains("|") && counter == 0) {
                    //System.out.println("New measure");
                    //stringVal = str.trim().toLowerCase().charAt(0);
                    if (topRepeat) {
                        //repeatNum++;
                        repeatNum = 1;
                        //System.out.println("1repeat num is: " + repeatNum);
                        topRepeatStarts.add(repeatNum);
                        repeat = true;
                    } else {
                        measureNum = measureMem;
                        noteNum = 1;
                        stringVal++;
                        repeatTimes++;
                    }
                } else if (str.contains("|")) {
                    //System.out.println("Case where | is first");
                    //Increase measure count if it encounters |
                    //Count end of measure here.
                    if (topRepeat && repeat) {
                        repeatNum += 2;
                        if (repeatFlag) {
                            topRepeatEnds.add(repeatNum);
                            repeatFlag = false;
                        } else {
                            topRepeatStarts.remove(topRepeatStarts.size() - 1);
                        }
                        topRepeatStarts.add(repeatNum);
                        //System.out.println("Inside str contains repeat num is: " + repeatNum);
                        //topRepeatEnds.add(repeatNum);
                        //topRepeatStarts.add(repeatNum);
                        //repeat = false;
                        //topRepeat = false;
                        counter = -1;
                    } else if (topRepeat) {
                        //repeatNum++;
                        //repeatNum = 1;
                        //System.out.println("2repeat num is: " + repeatNum);
                        topRepeatStarts.add(repeatNum);
                        repeatNum = 1;
                        repeat = true;
                    } else {
                        if (str.startsWith("*")) {
                            noteNum++;
                        }
                        measuresElement.put(measureNum, noteNum);
                        measureChars += noteNum + 1;
                        //System.out.println("measure char is: " + measureChars + " at measure " + measureNum);
                        noteNum = 1;
                        if (str.endsWith("*")) {
                            noteNum++;
                        }
                        measureNum++;
                    }
                    /*if (str.length() > 1) {
                        //Extract this to separate function? duplicate code in function below.
                        GuitarNote tempGuitarNote = new GuitarNote(measureNum, noteNum, stringVal, str.substring(1));
                        guitarNoteArray.add(tempGuitarNote);
                    }*/
                } else if (str.length() == 0) { // move this to bottom, add top repeat check inside.
                    //if blank, increase note count.
                    if (topRepeat) {
                        repeatNum++;
                    }
                    noteNum++;
                } else if (!topRepeat && stringVal != 0) {
                    //Otherwise create a new note with the string as the note value.
                    //splits each "block" into smaller individual notes, only checks for alphabet chars in between right now
                    //will update regex when encountering new patterns.
                    noteNum++;
                    for (String character : str.split("(?<=[PHph]|/|\\\\)")) {
                        //System.out.println(character);
                        GuitarNote tempGuitarNote = new GuitarNote(measureNum, noteNum, stringVal, character);
                        guitarNoteArray.add(tempGuitarNote);
                        noteNum += character.length();
                    }
                }
                /*if(topRepeat) {
                    System.out.println("Element was : " + str);
                    System.out.println("Repeat num is " + repeatNum);
                    System.out.println();
                }*/
                counter++;
            }
        }
        //Test print the array
        for (GuitarNote guitarNote : guitarNoteArray) {
            System.out.println("String: " + guitarNote.stringValue);
            System.out.println("Measure number: " + guitarNote.measure);
            System.out.println("Element number: " + guitarNote.noteNumber);
            System.out.println("Element value: " + guitarNote.noteValue);
            System.out.println();
        }
        System.out.println("Repeat starts at: " + repeatStarts.toString());
        System.out.println("Repeat ends at: " + repeatEnds.toString());
        System.out.println("Repeat amount: " + repeatAmout.toString());
        System.out.println();
        System.out.println("Top repeat starts at: " + topRepeatStarts.toString());
        System.out.println("Top repeat ends at: " + topRepeatEnds.toString());
        System.out.println("Top repeat amount: " + repeatAmout.toString());
        System.out.println("Top repeat offset: " + measureChars);
        System.out.println(measuresElement);
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
        for (DrumNote drumNote : drumNoteArray) {
            System.out.println("Part: " + drumNote.part);
            System.out.println("Measure number: " + drumNote.measure);
            System.out.println("Element number: " + drumNote.noteNumber);
            System.out.println("Element value: " + drumNote.noteValue);
            System.out.println();
        }
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
            if(repeatEnds.contains(i + 2)){
                directives.add("barline")
                        .attr("location", "right")
                        .add("bar-style")
                        .set("light-heavy").up()
                        .add("repeat")
                        .attr("direction","backward").up().up();
            }
            if (repeatStarts.contains(i + 1)) {
                int repeatTimes;
                try{
                    repeatTimes = repeatAmout.get(0);
                }catch (Exception e){
                    repeatTimes = 2;
                }
                directives.add("barline")
                        .attr("location", "left")
                        .add("bar-style")
                        .set("heavy-light").up()
                        .add("repeat")
                        .attr("direction","forward").up().up()
                        .add("direction")
                        .attr("placement","above")
                        .add("direction-type")
                        .add("words").attr("relative-x","256.17")
                        .attr("relative-y","16.01")
                        .set("Repeat " + repeatTimes + "times").up().up().up();
                repeatAmout.remove(0);
            }
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
                    if (guitarNote.grace) {
                        directives.add("grace").up();
                    }
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
                    if (guitarNote.harmonic) {
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
                    if (slurEnd && !guitarNote.chord) {
                        directives.add("slur")
                                .attr("number", 1)
                                .attr("type", "stop")
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
        	.add("work")
        	.add("work-title")
        	.set(tabTitle)
        	.up()//work-title
        	.up()//work
        	.add("identification")
        	.add("creator")
        	.attr("type", "composer")
        	.set(tabComposer)
        	.up()//creator
        	.up()//identification
        	.add("part-list")
        	.add("score-part")
        	.attr("id", "P1")
        	.add("part-name")
        	.set("Drumset") //change this to name of music
        	.up()//part-name


        	.add("score-instrument")
        	.attr("id", "P1-I36")
        	.add("instrument-name")
        	.set("Bass Drum 1")
        	.up()  
        	.up()

        	.add("score-instrument")
        	.attr("id", "P1-I39")
        	.add("instrument-name")
        	.set("Snare")
        	.up()  
        	.up()

        	.add("score-instrument")
        	.attr("id", "P1-I42")
        	.add("instrument-name")
        	.set("Low Floor Tom")
        	.up()  
        	.up()

        	.add("score-instrument")
        	.attr("id", "P1-I43")
        	.add("instrument-name")
        	.set("Closed Hi-Hat")
        	.up()  
        	.up()

//        	.add("score-instrument")
//        	.attr("id", "P1-I44")
//        	.add("instrument-name")
//        	.set("High Floor Tom")
//        	.up()  
//        	.up()

//        	.add("score-instrument")
//        	.attr("id", "P1-I45")
//        	.add("instrument-name")
//        	.set("Pedal Hi-Hat")
//        	.up()  
//        	.up()

        	.add("score-instrument")
        	.attr("id", "P1-I46")
        	.add("instrument-name")
        	.set("Low Tom")
        	.up()  
        	.up()

        	.add("score-instrument")
        	.attr("id", "P1-I47")
        	.add("instrument-name")
        	.set("Open Hi-Hat")
        	.up()  
        	.up()

        	.add("score-instrument")
        	.attr("id", "P1-I48")
        	.add("instrument-name")
        	.set("Low-Mid Tom")
        	.up()  
        	.up()

//        	.add("score-instrument")
//        	.attr("id", "P1-I49")
//        	.add("instrument-name")
//        	.set("Hi-Mid Tom")
//        	.up()  
//        	.up()

        	.add("score-instrument")
        	.attr("id", "P1-I50")
        	.add("instrument-name")
        	.set("Crash Cymbal 1")
        	.up()  
        	.up()

//        	.add("score-instrument")
//        	.attr("id", "P1-I51")
//        	.add("instrument-name")
//        	.set("High Tom")
//        	.up()  
//        	.up()

        	.add("score-instrument")
        	.attr("id", "P1-I52")
        	.add("instrument-name")
        	.set("Ride Cymbal 1")
        	.up()  
        	.up()

//        	.add("score-instrument")
//        	.attr("id", "P1-I53")
//        	.add("instrument-name")
//        	.set("Chinese Cymbal")
//        	.up()  
//        	.up()

//        	.add("score-instrument")
//        	.attr("id", "P1-I54")
//        	.add("instrument-name")
//        	.set("Ride Bell")
//        	.up()  
//        	.up()

//        	.add("score-instrument")
//        	.attr("id", "P1-I55")
//        	.add("instrument-name")
//        	.set("Tambourine")
//        	.up()  
//        	.up()

//        	.add("score-instrument")
//        	.attr("id", "P1-I56")
//        	.add("instrument-name")
//        	.set("Splash Cymbal")
//        	.up()  
//        	.up()

//        	.add("score-instrument")
//        	.attr("id", "P1-I57")
//        	.add("instrument-name")
//        	.set("Cowbell")
//        	.up()  
//        	.up()

//        	.add("score-instrument")
//        	.attr("id", "P1-I58")
//        	.add("instrument-name")
//        	.set("Crash Cymbal 2")
//        	.up()  
//        	.up()

//        	.add("score-instrument")
//        	.attr("id", "P1-I60")
//        	.add("instrument-name")
//        	.set("Ride Cymbal 2")
//        	.up()  
//        	.up()

//        	.add("score-instrument")
//        	.attr("id", "P1-I64")
//        	.add("instrument-name")
//        	.set("Open Hi Conga")
//        	.up()  
//        	.up()

//        	.add("score-instrument")
//        	.attr("id", "P1-I65")
//        	.add("instrument-name")
//        	.set("Low Conga")
//        	.up()  
//        	.up()

        	.up()//score-part
        	.up()//part-list

        	.add("part")
        	.attr("id","P1");

        for (int i = 0; i < 2; i++) {
            directives.add("measure")
                    .attr("number", i + 1)
                    .add("attributes")
                    .add("divisions")
                    .set("4")
                    .up()//divisions
                    .add("key")
                    .add("fifths")
                    .set(0)
                    .up()//fifths
                    .up()//key

                    .add("time")
                    .add("beats")
                    .set(4)
                    .up()//beat
                    .add("beat-type")
                    .set(4)
                    .up()//beat-type
                    .up()//time

                    .add("clef")
                    .add("sign")
                    .set("percussion")
                    .up()//sign
                    .add("line")
                    .set(2)
                    .up()//line
                    .up()//clef

                    .up();//attributes
            for (DrumNote drumNote : drumNoteArray) {
                if (drumNote.measure == i + 1) {
                    //process note here later
                    directives
                            .add("note")
                            .add("unpitched")
                            .add("display-step");
                            if (drumNote.part == "SD" || drumNote.part == "S") { //Snare
                            	directives.set("C");
                            } else if (drumNote.part.equalsIgnoreCase("BD") || drumNote.part.equalsIgnoreCase("B")) { //Bass
                            	directives.set("F");
                            } else if (drumNote.part.equalsIgnoreCase("HH") || drumNote.part.equalsIgnoreCase("H")) { //High hat
                            	directives.set("G");
                            } else if (drumNote.part.equalsIgnoreCase("RD") || drumNote.part.equalsIgnoreCase("R")) { //Ride
                            	directives.set("F");
                            } else if (drumNote.part.equalsIgnoreCase("CR") || drumNote.part.equalsIgnoreCase("C")) { //Crash
                            	directives.set("A");
                            } else if (drumNote.part.equalsIgnoreCase("ST") || drumNote.part.equalsIgnoreCase("T1")) { //High Tom
                            	directives.set("E");
                            } else if (drumNote.part.equalsIgnoreCase("MT") || drumNote.part.equalsIgnoreCase("T2")) { //Medium Tom
                            	directives.set("D");
                            } else if (drumNote.part.equalsIgnoreCase("FT") || drumNote.part.equalsIgnoreCase("T3")) { //Floor Tom
                            	directives.set("A");
                            } else {
                            	directives.set("F");
                            }

                            directives.up()//display-step
                            .add("display-octave");
                            if (drumNote.part.equalsIgnoreCase("SD") || drumNote.part.equalsIgnoreCase("S")) { //Snare
                            	directives.set(5);
                            } else if (drumNote.part.equalsIgnoreCase("BD") || drumNote.part.equalsIgnoreCase("B")) { //Bass
                            	directives.set(4);
                            } else if (drumNote.part.equalsIgnoreCase("HH") || drumNote.part.equalsIgnoreCase("H")) { //High hat
                            	directives.set(5);
                            } else if (drumNote.part.equalsIgnoreCase("RD") || drumNote.part.equalsIgnoreCase("R")) { //Ride
                            	directives.set(5);
                            } else if (drumNote.part.equalsIgnoreCase("CR") || drumNote.part.equalsIgnoreCase("C")) { //Crash
                            	directives.set(5);
                            } else if (drumNote.part.equalsIgnoreCase("ST") || drumNote.part.equalsIgnoreCase("T1")) { //High Tom
                            	directives.set(5);
                            } else if (drumNote.part.equalsIgnoreCase("MT") || drumNote.part.equalsIgnoreCase("T2")) { //Medium Tom
                            	directives.set(5);
                            } else if (drumNote.part.equalsIgnoreCase("FT") || drumNote.part.equalsIgnoreCase("T3")) { //Floor Tom
                            	directives.set(4);
                            } else {
                            	directives.set(4);
                            }
                            directives.up()//display-octave
                            .up()//unpitched

                            .add("duration")
                            .set(2)//TODO
                            .up()//duration

                            .add("instrument");
                            if (drumNote.part.equalsIgnoreCase("SD") || drumNote.part.equalsIgnoreCase("S")) { //Snare
                            	directives.attr("id", "P1-I39");
                            } else if (drumNote.part.equalsIgnoreCase("BD") || drumNote.part.equalsIgnoreCase("B")) { //Bass
                            	directives.attr("id", "P1-I36");
                            } else if (drumNote.part.equalsIgnoreCase("HH") || drumNote.part.equalsIgnoreCase("H")) { //High hat

                            	if (drumNote.noteValue == 'x') { //Closed High hat
                            		directives.attr("id", "P1-I43");
                            	} else if (drumNote.noteValue == 'o') { //Open High hat
                            		directives.attr("id", "P1-I47");
                            	} else {
                            		directives.attr("id", "P1-I43");
                            	}

                            } else if (drumNote.part.equalsIgnoreCase("RD") || drumNote.part.equalsIgnoreCase("R")) { //Ride
                            	directives.attr("id", "P1-I52");
                            } else if (drumNote.part.equalsIgnoreCase("CR") || drumNote.part.equalsIgnoreCase("C")) { //Crash
                            	directives.attr("id", "P1-I50");
                            } else if (drumNote.part.equalsIgnoreCase("ST") || drumNote.part.equalsIgnoreCase("T1")) { //High Tom
                            	directives.attr("id", "P1-I48");
                            } else if (drumNote.part.equalsIgnoreCase("MT") || drumNote.part.equalsIgnoreCase("T2")) { //Medium Tom
                            	directives.attr("id", "P1-I46");
                            } else if (drumNote.part.equalsIgnoreCase("FT") || drumNote.part.equalsIgnoreCase("T3")) { //Floor Tom
                            	directives.attr("id", "P1-I42");
                            } else {
                            	directives.attr("id", "P1-I36");
                            }

                            directives.up()//instrument

                            .add("voice")
                            .set(1)//TODO
                            .up()//voice

                            .add("type")
                            .set("eighth")//TODO
                            .up()//type

                            .add("stem")
                            .set("up")//TODO
                            .up()//stem

                            .add("notehead")
                            .set(drumNote.noteValue)
                            .up()//notehead

                            //if () {
                            		.add("beam")
                            		.attr("number", "1")
                            		//if () {
                            		.set("continue")
                            		//} else if() {
                            		//.set("begin")
                					//} else {
                            		//.set("end")
                            		.up()//beam
                            		//}
                            //}

                            .up();//note
                }
            }

            directives.up();//measure
        }
        directives.up()//part
        .up();//score-partwise

        String xml = null;
        try {
            xml = new Xembler(
                    directives
            ).xml();
        } catch (Exception e) {
            System.out.println("error");
        }

        System.out.println(xml);
        return xml;

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
            new Error("There was an error saving your file, please retry.", tabTitle, tabComposer, tabContents);
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
        tabContents = "";

        measuresElement = new HashMap<>();
        measures = new ArrayList<>();
        repeatStarts = new LinkedHashSet<>();
        repeatEnds = new LinkedHashSet<>();
        repeatAmout = new ArrayList<>();
        topRepeatStarts = new ArrayList<>();
        topRepeatEnds = new ArrayList<>();

        Object[] notes = fileParser(filePath);
        ArrayList<String> noteArray = (ArrayList<String>) notes[1];
        if (notes[0] == "guitar") {
            ArrayList<GuitarNote> guitarNoteArray = guitarNoteParser(noteArray);
            for (GuitarNote guitarNote : guitarNoteArray) {
                //System.out.println("setting music note");
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
                SaveFile saveFile = new SaveFile(tabTitle, tabComposer, tabContents, xml);
                saveFile.setVisible(true);
            } else {
                System.out.println("xml is null");
                new Error("Error parsing, please ensure tab is in correct format.", tabTitle, tabComposer, tabContents);
            }
        } else {
            //ArrayList<DrumNote> drumNoteArray = drumNoteParser(noteArray);
            System.out.println("not a guitar tab");
            Error error = new Error("Error parsing, please ensure tab is in correct format.", tabTitle, tabComposer, tabContents);

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