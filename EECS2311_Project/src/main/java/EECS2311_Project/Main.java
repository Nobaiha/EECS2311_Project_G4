package EECS2311_Project;

import org.xembly.*;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;

/**
 * The main class including all related methods that will allow for correct file
 * input, reading text file uploaded by user, processes guitar tab, processes
 * drum tab, processes bass tab, converts the file to musicXML file based on the
 * instrument, saves the musicXML file, parses each note and inserts each note
 * into its appropriate measure.
 * 
 * @author Team 4 EECS2311 Winter 2021
 */
public class Main {

	// Music element related variable definition such as measure, time signature,
	// music title, composer and related key signature
	public static HashMap<Integer, Integer> measuresElement = new HashMap<>();
	public static ArrayList<Measure> measures = new ArrayList<>();
	public static LinkedHashSet<Integer> repeatStarts = new LinkedHashSet<>();
	public static LinkedHashSet<Integer> repeatEnds = new LinkedHashSet<>();
	public static ArrayList<Integer> repeatAmout = new ArrayList<>();
	public static ArrayList<Integer> topRepeatStarts = new ArrayList<>();
	public static ArrayList<Integer> topRepeatEnds = new ArrayList<>();


	static int timeSig1 = 4;
	static int timeSig2 = 4;

	public static int guitar = 0;

	static String tabTitle = "";
	static String tabComposer = "";
	static String tabContents = "";
	static int keySignature = 1;

	/**
	 * Checks if the input file is of <.txt> file format.
	 *
	 * @param file the name of the input file.
	 * @return true if the file is of type <.txt>, false if otherwise.
	 */
	public static boolean fileChecker(String file) {
		int indexOfExt = file.lastIndexOf(".") + 1;
		return file.substring(indexOfExt).equals("txt");
	}

	/**
	 * Reads the text file, extracts the information, and places it in an ArrayList.
	 *
	 * @param file the name of the input file.
	 * @return an ArrayList of the guitar/drum notes.
	 * @throws FileNotFoundException throws FileNotFoundException if the file is not found.
	 */
	public static Object[] fileParser(String file) throws FileNotFoundException {
		if (!fileChecker(file)) {
			throw new FileNotFoundException("Unsupported file type");
		}
		ArrayList<String> testLines = new ArrayList<>();
		ArrayList<String> drumTestLines = new ArrayList<>();

		File inputFile = new File(file);

		Pattern pattern = Pattern.compile("^([eABCDEFGabcdfg|]||(?i)\\brepeat\\b)");

		Pattern drumPattern = Pattern.compile("^([SDSBDBHHHTRDRCCCSTT1MTT2FTT3])");

		try (Scanner sc = new Scanner(inputFile)) {
			while (sc.hasNextLine()) {
				String nextLine = sc.nextLine();
				tabContents += nextLine + "\n";
				if (!nextLine.contains("|")) {
					testLines.add(" ");
					drumTestLines.add(" ");
				}
				Matcher matcher = pattern.matcher(nextLine);
				if (matcher.find() && guitar != 2) {
					testLines.add(nextLine);
				} else if (guitar == 2) {
					Matcher drumMatcher = drumPattern.matcher(nextLine);
					if (drumMatcher.find()) {
						drumTestLines.add(nextLine);
					}
				}
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		if (testLines.size() > drumTestLines.size() && guitar != 2) {
			return new Object[] { "guitar", testLines };
		} else {
			return new Object[] { "drum", drumTestLines };
		}
	}

	/**
	 * This takes the information for the guitar tablature ArrayList, processes it,
	 * and sorts it for converting.
	 *
	 * @param noteArray the ArrayList with the converted <.txt> file.
	 * @return an ArrayList of type GuitarNote with processed guitar tablature information.
	 */
	public static ArrayList<GuitarNote> guitarNoteParser(ArrayList<String> noteArray) {

		ArrayList<String[]> strArray = new ArrayList<>();

		for (String list : noteArray) {
			String[] tempArray = list.split("[-\\s]", -1);

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
		int repeatMax = 6;

		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher;

		if (guitar == 0) {
			repeatMax = 6;
		} else if (guitar == 1) {
			repeatMax = 4;
		}

		for (String[] array : strArray) {
			if (topRepeat) {
				topRepeatStarts.remove(topRepeatStarts.size() - 1);
			}
			topRepeat = false;
			repeat = false;
			repeatNum = 0;
			if (repeatTimes == repeatMax) {
				repeatTimes = 0;
				stringVal = 0;
				measureMem = measureNum;
			}
			int counter = 0;

			for (int i = 0; i < array.length; i++) {
				array[i] = array[i].toLowerCase();
			}
			if (Arrays.asList(array).contains("repeat")) {
				topRepeat = true;
			}
			for (String str : array) {
				if (topRepeatStarts.contains(measureChars)) {
					repeatStarts.add(measureNum);
				}
				if (topRepeatEnds.contains(measureChars)) {
					repeatEnds.add(measureNum);
				}
				if (topRepeat && pattern.matcher(str).find()) {
					repeatNum += str.length() + 1;
					repeatFlag = true;
					repeatAmout.add(Integer.parseInt(str.replaceAll("\\D+", "")));
				} else if (topRepeat && str.contains("repeat")) {
					repeatNum += str.length() + 1;
				} else if (str.equals("||") && counter == 0) {
					measureNum = measureMem;
					repeatStarts.add(measureNum + 1);
					measureNum++;
					repeat = true;
					noteNum = 1;
				} else if (str.equals("||")) {
					measuresElement.put(measureNum, noteNum);
					if (repeat) {
						repeat = false;
						repeatEnds.add(measureNum + 1);
					} else {
						repeat = true;
						repeatStarts.add(measureNum + 1);
					}
					noteNum = 1;
					measureNum++;
				} else if (str.length() >= 2 && str.endsWith("|") && Character.isDigit(str.charAt(0))
						&& stringVal == 1) {
					repeat = false;
					noteNum++;
					measuresElement.put(measureNum, noteNum);
					noteNum = 1;
					repeatEnds.add(measureNum + 1);
					measureNum++;
					repeatAmout.add(Character.getNumericValue(str.charAt(0)));
				} else if (str.contains("|") && counter == 0) {

					if (topRepeat) {

						repeatNum = 1;

						topRepeatStarts.add(repeatNum);
						repeat = true;
					} else {
						measureNum = measureMem;
						noteNum = 1;
						stringVal++;
						repeatTimes++;
					}
				} else if (str.contains("|")) {

					if (topRepeat && repeat) {
						repeatNum += 2;
						if (repeatFlag) {
							topRepeatEnds.add(repeatNum);
							repeatFlag = false;
						} else {
							topRepeatStarts.remove(topRepeatStarts.size() - 1);
						}
						topRepeatStarts.add(repeatNum);
						counter = -1;
					} else if (topRepeat) {
						topRepeatStarts.add(repeatNum);
						repeat = true;
					} else {
						if (str.startsWith("*")) {
							noteNum++;
						}
						measuresElement.put(measureNum, noteNum);
						measureChars += noteNum + 1;
						noteNum = 1;
						if (str.endsWith("*")) {
							noteNum++;
						}
						measureNum++;
					}

				} else if (str.length() == 0) {

					if (topRepeat) {
						repeatNum++;
					}
					noteNum++;
				} else if (!topRepeat && stringVal != 0) {

					noteNum++;
					for (String character : str.split("(?<=[PHph]|/|\\\\)")) {

						GuitarNote tempGuitarNote = new GuitarNote(measureNum, noteNum, stringVal, character);
						guitarNoteArray.add(tempGuitarNote);
						noteNum += character.length();
					}
				}

				counter++;
			}
		}
		measuresElement.forEach((k, v) -> measures.add(new Measure(v, k)));
		return guitarNoteArray;
	}

	/**
	 * This takes the information for the drum tablature ArrayList, processes it,
	 * and sorts it for converting.
	 *
	 * @param noteArray the ArrayList with the converted <.txt> file.
	 * @return an ArrayList of type DrumNote with processed drum tablature information.
	 */
	public static ArrayList<DrumNote> drumNoteParser(ArrayList<String> noteArray) {
        ArrayList<DrumNote> drumNoteArray = new ArrayList<>();
        ArrayList<String> strArray = new ArrayList<>();
        for (String list : noteArray) {
            String[] tempArray = list.split("-", -1);
            Collections.addAll(strArray, tempArray);
        }

        int measureNum = 1;
        int noteNum = 1;
        String part = "";
        String repeatedPart = "";
        int measureMem = 1;
        Pattern pattern = Pattern.compile("^([SDSBDBHHHTRDRCCCSTT1MTT2FTT3])");
        int wholePosition = 0;

        //Iterates through the parsed string array and makes an array of note objects

        for (String str : strArray) {
            //checks for old regex expression which represents a note
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
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
                        
                        tempDrumNote.setPosition(wholePosition);
                        
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
                        //tempDrumNote.setPosition(wholePosition);
                    }
                }
                
                //wholePosition = 0;
                
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
                    //tempDrumNote.setPosition(wholePosition);

                }
            }
        }
        return drumNoteArray;
    }


	/**
	 * Takes all the information gathered from the guitar tablature and converts it
	 * into a MusicXML file.
	 *
	 * @param measures an ArrayList of the guitar tablature information.
	 * @return a MusicXML file.
	 */
	public static String guitarXMLParser(ArrayList<Measure> measures) {
		boolean pulloff = false;
		boolean hammer = false;
		boolean slurEnd = false;

		String[] keyArray = new String[] { "C major", "G major", "D major", "A major", "E major", "B major", "F major",
				"B flat major", "E flat major", "A flat major", "D flat major", "G flat major", "C flat major" };
		int maxRepeats = Math.min(repeatStarts.size(), repeatEnds.size());
		int repeatCounter = 0;

		if (measures.size() == 0) {
			return null;
		}
		Directives directives = new Directives();
		directives.add("score-partwise").attr("version", "3.0").add("work").add("work-title").set(tabTitle).up().up()
				.add("identification").add("creator").attr("type", "composer").set(tabComposer).up().up()
				.add("part-list").add("score-part").attr("id", "P1").add("part-name");
		if (guitar == 0) {
			directives.set("Guitar");
		} else if (guitar == 1) {
			directives.set("Bass");
		}

		directives.up().up().up().add("part").attr("id", "P1");
		for (int i = 0; i < measures.size(); i++) {
			directives.add("measure").attr("number", i + 1);
			if (repeatCounter < maxRepeats * 2) {
				if (repeatEnds.contains(i + 2)) {
					directives.add("barline").attr("location", "right").add("bar-style").set("light-heavy").up()
							.add("repeat").attr("direction", "backward").up().up();
				}
				if (repeatStarts.contains(i + 1)) {
					int repeatTimes;
					try {
						repeatTimes = repeatAmout.get(0);
					} catch (Exception e) {
						repeatTimes = 2;
					}
					directives.add("barline").attr("location", "left").add("bar-style").set("heavy-light").up()
							.add("repeat").attr("direction", "forward").up().up().add("direction")
							.attr("placement", "above").add("direction-type").add("words").attr("relative-x", "256.17")
							.attr("relative-y", "16.01").set("Repeat " + repeatTimes + "times").up().up().up();
					repeatAmout.remove(0);
				}
				repeatCounter++;
			}

			if (i == 0) {
				directives.add("attributes").add("divisions").set("4").up().add("key").add("fifths").set(keySignature)
						.up().add("mode").set("major").up().up().add("time").add("beats").set(timeSig1).up()
						.add("beat-type").set(timeSig2).up().up().add("clef").add("sign").set("TAB").up().add("line")
						.set(5).up().up().add("staff-details").add("staff-lines");
				if (guitar == 0) {
					directives.set(6);
				} else if (guitar == 1) {
					directives.set(4);
				}
				directives.up();
				if (guitar == 0) {
					directives.add("staff-tuning").attr("line", "1").add("tuning-step").set("E").up()
							.add("tuning-octave").set(2).up().up().add("staff-tuning").attr("line", "2")
							.add("tuning-step").set("A").up().add("tuning-octave").set(2).up().up().add("staff-tuning")
							.attr("line", "3").add("tuning-step").set("D").up().add("tuning-octave").set(3).up().up()
							.add("staff-tuning").attr("line", "4").add("tuning-step").set("G").up().add("tuning-octave")
							.set(3).up().up().add("staff-tuning").attr("line", "5").add("tuning-step").set("B").up()
							.add("tuning-octave").set(3).up().up().add("staff-tuning").attr("line", "6")
							.add("tuning-step").set("E").up().add("tuning-octave").set(4);
				} else if (guitar == 1) {
					directives.add("staff-tuning").attr("line", "1").add("tuning-step").set("G").up()
							.add("tuning-octave").set(2).up().up().add("staff-tuning").attr("line", "2")
							.add("tuning-step").set("D").up().add("tuning-octave").set(2).up().up().add("staff-tuning")
							.attr("line", "3").add("tuning-step").set("A").up().add("tuning-octave").set(3).up().up()
							.add("staff-tuning").attr("line", "4").add("tuning-step").set("E").up().add("tuning-octave")
							.set(3);
				}
				directives.up().up().up().up();
			}

			for (int j = 0; j < measures.get(i).guitarNotes.size(); j++) {
				GuitarNote guitarNote = measures.get(i).guitarNotes.get(j);
				if (guitarNote.measure == i + 1) {

					directives.add("note");
					if (guitarNote.grace) {
						directives.add("grace").up();
					}
					if (guitarNote.chord) {
						directives.add("chord").up();
					}
					directives.add("pitch").add("step").set(guitarNote.musicNote).up();
					if (guitarNote.modifier == 1) {
						directives.add("alter").set(1).up();
					}
					directives.add("octave").set(guitarNote.octave).up().up().add("duration").set(guitarNote.duration)
							.up();
					if (guitarNote.noteType != null) {
						directives.add("type").set(guitarNote.noteType);
						if (guitarNote.noteDot) {
							directives.up().add("dot");
						}
						directives.up();
					}
					directives.add("notations").add("technical");
					if (guitarNote.harmonic) {
						directives.add("harmonic").add("natural").up().up();
					}
					if (pulloff && !guitarNote.chord) {
						directives.add("pull-off").attr("number", 1).attr("type", "stop").up();
						slurEnd = true;
						pulloff = false;
					}
					if (hammer && !guitarNote.chord) {
						directives.add("hammer-on").attr("number", 1).attr("type", "stop").up();
						slurEnd = true;
						hammer = false;
					}
					if (guitarNote.hammer) {
						directives.add("hammer-on").attr("number", 1).attr("type", "start").set("H").up();
						hammer = true;
					}
					if (guitarNote.pull) {
						directives.add("pull-off").attr("number", 1).attr("type", "start").set("P").up();
						pulloff = true;
					}
					directives.add("string").set(guitarNote.stringValue).up().add("fret").set(guitarNote.noteValue).up()
							.up();
					if (slurEnd && !guitarNote.chord) {
						directives.add("slur").attr("number", 1).attr("type", "stop").up();
						slurEnd = false;
					}
					if (guitarNote.pull || guitarNote.hammer) {
						directives.add("slur").attr("number", 1).attr("type", "start").attr("placement", "above").up();
					}
					directives.up().up();
				}
			}
			directives.up();
		}
		String xml = null;
		try {
			xml = new Xembler(directives).xml();
		} catch (Exception e) {
		}
		return xml;

	}

	/**
	 * Takes all the information gathered from the drum tablature and converts it
	 * into a MusicXML file.
	 *
	 * @param measures an ArrayList of the drum tablature information.
	 * @return a MusicXML file.
	 */
	public static String drumXMLParser(ArrayList<DrumNote> drumNoteArray) {
    	int voiceChange;
    	int doOnce = 0;
    	int lastNote = 0;
    	int maxMeasure = 0;
    	int backUpPoint = 0;
    	int currentMeasure;
    	
    	ArrayList<DrumNote> sortedDrumArray = new ArrayList<DrumNote>();
    	ArrayList<DrumNote> backUpDrumArray = new ArrayList<DrumNote>();
    	
    	int noteNumCounter = 0;
        while(sortedDrumArray.size() != drumNoteArray.size()) {
        	noteNumCounter++;
        	for (DrumNote drumNote : drumNoteArray) {
            	if (drumNote.noteNumber == noteNumCounter) {
            		sortedDrumArray.add(drumNote);
            	}     
            	if (drumNote.noteNumber == noteNumCounter && (drumNote.part.equalsIgnoreCase("BD") || drumNote.part.equalsIgnoreCase("B"))) {
            		backUpDrumArray.add(drumNote);
            	}
            	if (drumNote.measure > maxMeasure) {
            		maxMeasure = drumNote.measure;
            	}
            }
        }
        
        backUpPoint = maxMeasure/2;

    	if (drumNoteArray.size() == 0) {
            return null;
        }

        Directives directives = new Directives();
        directives
        	.add("score-partwise").attr("version", "3.0")
        	.add("work").add("work-title").set(tabTitle).up()//work-title
        	.up()//work
        	.add("identification").add("creator").attr("type", "composer").set(tabComposer).up()//creator
        	.up()//identification
        	.add("part-list").add("score-part").attr("id", "P1").add("part-name").set("Drumset") //change this to name of music
        	.up()//part-name
        	
        	.add("score-instrument").attr("id", "P1-I36").add("instrument-name").set("Bass Drum 1").up().up()
        	.add("score-instrument").attr("id", "P1-I39").add("instrument-name").set("Snare").up().up()
        	.add("score-instrument").attr("id", "P1-I42").add("instrument-name").set("Low Floor Tom").up().up()
        	.add("score-instrument").attr("id", "P1-I43").add("instrument-name").set("Closed Hi-Hat").up().up()
        	.add("score-instrument").attr("id", "P1-I46").add("instrument-name").set("Low Tom").up().up()
        	.add("score-instrument").attr("id", "P1-I47").add("instrument-name").set("Open Hi-Hat").up().up()
        	.add("score-instrument").attr("id", "P1-I48").add("instrument-name").set("Low-Mid Tom").up().up()
        	.add("score-instrument").attr("id", "P1-I50").add("instrument-name").set("Crash Cymbal 1").up().up()
        	.add("score-instrument").attr("id", "P1-I52").add("instrument-name").set("Ride Cymbal 1").up().up()
        	.up()//score-part
        	.up()//part-list
        	.add("part").attr("id","P1");
        for (int i = 0; i < maxMeasure; i++) {
            lastNote = 0;
            doOnce = 0;
            currentMeasure = i + 1;
            
        	directives.add("measure").attr("number", i + 1).add("attributes").add("divisions").set("4")
                    .up()//divisions
                    .add("key").add("fifths").set(0)
                    .up()//fifths
                    .up()//key

                    .add("time").add("beats").set(timeSig1).up()//beat
                    .add("beat-type").set(timeSig2)
                    .up()//beat-type
                    .up()//time

                    .add("clef").add("sign").set("percussion")
                    .up()//sign
                    .add("line").set(2)
                    .up()//line
                    .up()//clef
                    .up();//attributes
            for (DrumNote drumNote : sortedDrumArray) { 
                if (drumNote.measure == i + 1) {
                	if ((i+1) > backUpPoint && doOnce == 0) {
                		directives.add("backup")
                		.add("duration")
                		.set("16")
                		.up()//duration
                		.up();//backup
                		doOnce = 1;
            		}
                	
                	directives.add("note");
                	
                	if (drumNote.noteValue == 'f') {
                		directives.add("grace")
                		.up();
                	}    
//                	if (drumNote.noteNumber == lastNote) {
//                		directives.add("chord")
//                		.up();
//                	}   
                	directives.add("unpitched");
                			directives.add("display-step");
                            if (drumNote.part.equalsIgnoreCase("SD") || drumNote.part.equalsIgnoreCase("S")) { //Snare
                            	directives.set("C");
                            } else if (drumNote.part.equalsIgnoreCase("BD") || drumNote.part.equalsIgnoreCase("B")) { //Bass
                            	directives.set("F");
                            } else if (drumNote.part.equalsIgnoreCase("HH") || drumNote.part.equalsIgnoreCase("H") || drumNote.part.equalsIgnoreCase("HT")) { //High hat
                            	directives.set("G");
                            } else if (drumNote.part.equalsIgnoreCase("RD") || drumNote.part.equalsIgnoreCase("R")) { //Ride
                            	directives.set("F");
                            } else if (drumNote.part.equalsIgnoreCase("CC") || drumNote.part.equalsIgnoreCase("C")) { //Crash
                            	directives.set("A");
                            } else if (drumNote.part.equalsIgnoreCase("ST") || drumNote.part.equalsIgnoreCase("T1")) { //High Tom
                            	directives.set("E");
                            } else if (drumNote.part.equalsIgnoreCase("MT") || drumNote.part.equalsIgnoreCase("T2")) { //Medium Tom
                            	directives.set("D");
                            } else if (drumNote.part.equalsIgnoreCase("FT") || drumNote.part.equalsIgnoreCase("T3")) { //Floor Tom
                            	directives.set("A");
                            } else {
                            	Error error = new Error("Tablature incorrect format. No specified drum instrument! (Example: BD|--x-x-| is correct. |--x-x-| is not.)");
                            }

                            directives.up()//display-step
                            .add("display-octave");
                            if (drumNote.part.equalsIgnoreCase("SD") || drumNote.part.equalsIgnoreCase("S")) { //Snare
                            	directives.set(5);
                            } else if (drumNote.part.equalsIgnoreCase("BD") || drumNote.part.equalsIgnoreCase("B")) { //Bass
                            	directives.set(4);
                            } else if (drumNote.part.equalsIgnoreCase("HH") || drumNote.part.equalsIgnoreCase("H") || drumNote.part.equalsIgnoreCase("HT")) { //High hat
                            	directives.set(5);
                            } else if (drumNote.part.equalsIgnoreCase("RD") || drumNote.part.equalsIgnoreCase("R")) { //Ride
                            	directives.set(5);
                            } else if (drumNote.part.equalsIgnoreCase("CC") || drumNote.part.equalsIgnoreCase("C")) { //Crash
                            	directives.set(5);
                            } else if (drumNote.part.equalsIgnoreCase("ST") || drumNote.part.equalsIgnoreCase("T1")) { //High Tom
                            	directives.set(5);
                            } else if (drumNote.part.equalsIgnoreCase("MT") || drumNote.part.equalsIgnoreCase("T2")) { //Medium Tom
                            	directives.set(5);
                            } else if (drumNote.part.equalsIgnoreCase("FT") || drumNote.part.equalsIgnoreCase("T3")) { //Floor Tom
                            	directives.set(4);
                            } else { 
                            	Error error = new Error("Tablature incorrect format. No specified drum instrument! (Example: BD|--x-x-| is correct. |--x-x-| is not.)");
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
                            } else if (drumNote.part.equalsIgnoreCase("HH") || drumNote.part.equalsIgnoreCase("H") || drumNote.part.equalsIgnoreCase("HT")) { //High hat
                            	if (drumNote.noteValue == 'x') { //Closed High hat
                            		directives.attr("id", "P1-I43");
                            	} else if (drumNote.noteValue == 'o') { //Open High hat
                            		directives.attr("id", "P1-I47");
                            	} else {
                            		directives.attr("id", "P1-I43");
                            	}
                            } else if (drumNote.part.equalsIgnoreCase("RD") || drumNote.part.equalsIgnoreCase("R")) { //Ride
                            	directives.attr("id", "P1-I52");
                            } else if (drumNote.part.equalsIgnoreCase("CC") || drumNote.part.equalsIgnoreCase("C")) { //Crash
                            	directives.attr("id", "P1-I50");
                            } else if (drumNote.part.equalsIgnoreCase("ST") || drumNote.part.equalsIgnoreCase("T1")) { //High Tom
                            	directives.attr("id", "P1-I48");
                            } else if (drumNote.part.equalsIgnoreCase("MT") || drumNote.part.equalsIgnoreCase("T2")) { //Medium Tom
                            	directives.attr("id", "P1-I46");
                            } else if (drumNote.part.equalsIgnoreCase("FT") || drumNote.part.equalsIgnoreCase("T3")) { //Floor Tom
                            	directives.attr("id", "P1-I42");
                            } else {
                            	Error error = new Error("Tablature incorrect format. No specified drum instrument! (Example: BD|--x-x-| is correct. |--x-x-| is not.)");
                            }

                            directives.up();//instrument
                            if ((i+1) > backUpPoint) {
                            	directives.add("voice")
                                .set(2)
                                .up();//voice
                            } else {
                            	directives.add("voice")
                                .set(1)
                                .up();//voice
                            }
                            	
                            directives.add("type")
                            .set("eighth")//TODO
                            .up();//type
                            
                            directives.add("stem");
                            if ((i+1) > backUpPoint) {
                            	directives.set("down")
                            	.up();//Stem
                            } else {
                            	directives.set("up")
                            	.up();//Stem
                            }

                            if (drumNote.noteValue == 'o') {
                            	//Nothing
                            } else {
                            	directives.add("notehead")
                                .set(drumNote.noteValue)
                                .up();
                            }
                            directives.up();//note
                            lastNote = drumNote.noteNumber;
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
        }
        return xml;
    }

	/**
	 * This saves the MusicXML file that was created.
	 *
	 * @param file the file that was input.
	 * @param xml  the MusicXML file.
	 */
	public static void saveFile(File file, String xml) {
		try {
			FileWriter xmlFile = new FileWriter(file);
			xmlFile.write(xml);
			xmlFile.close();

			Desktop.getDesktop().open(file);
		} catch (Exception e) {
			new Error("There was an error saving your file, please retry.", tabTitle, tabComposer, tabContents);
		}
	}

	/**
	 * Identifies the instrument and parses each note to insert it into appropriate
	 * measures.
	 * 
	 * @param filePath location of where file is found on the desktop computer being
	 *                 used
	 * @param title    of the music piece uploaded
	 * @param composer of the music piece uploaded
	 * @throws FileNotFoundException exception thrown if the selected file not found
	 *                               in the desktop
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

				if (guitar == 1) {
					guitarNote.bass = true;
				}
				try {
					guitarNote.setMusicNote();
				} catch (Exception exception) {
					new Error("Error with at string " + guitarNote.stringValue + " at measure " + guitarNote.measure
							+ " please double check your tab. ", tabTitle, tabComposer, tabContents);
				}

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
				measure.setNoteType();

			}
			String xml = guitarXMLParser(measureArrayList);
			if (xml != null) {
				SaveFile saveFile = new SaveFile(tabTitle, tabComposer, tabContents, xml);
				saveFile.setVisible(true);
			} else {
				new Error("Error parsing, please ensure tab is in correct format.", tabTitle, tabComposer, tabContents);
			}
		} else if (notes[0] == "drum") {
			ArrayList<DrumNote> drumNotes = drumNoteParser(noteArray);
			String xml = drumXMLParser(drumNotes);
			if (xml != null) {
				SaveFile saveFile = new SaveFile(tabTitle, tabComposer, tabContents, xml);
				saveFile.setVisible(true);
			} else {
				new Error("Error parsing, please ensure tab is in correct format.", tabTitle, tabComposer, tabContents);
			}
		} else {
			Error error = new Error("Error parsing, please ensure tab is in correct format.", tabTitle, tabComposer,
					tabContents);

		}
	}

	/**
	 * Starts up the GUI.
	 */
	public static void main(String[] args) {
		GuiWelcome welcomePage = new GuiWelcome();
		welcomePage.setVisible(true);
	}
}
