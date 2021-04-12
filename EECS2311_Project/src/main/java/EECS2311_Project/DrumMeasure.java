package EECS2311_Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Sorts notes based on the element's index number
 */
class noteSortDrum implements Comparator<DrumNote> {

	/**
	 * Compares two Drum Notes.
	 * 
	 * @param o1 first drum note.
	 * @param o2 second drum note.
	 * @return integer value indicating whether the compared notes are less than,
	 *         greater than or equal to each other
	 */
	public int compare(DrumNote o1, DrumNote o2) {
		return Integer.compare(o1.noteNumber, o2.noteNumber);
	}
}

/**
 * Deals with the drum measures in tablature.
 */
public class DrumMeasure {
	public int elementMax;
	public int measureNum;
	public ArrayList<DrumNote> drumNotes = new ArrayList<>();

	/**
	 * Constructor for a measure
	 * 
	 * @param elementMax last element in the measure
	 * @param measureNum measure number in the tablature
	 */
	public void Measure(int elementMax, int measureNum) {
		this.elementMax = elementMax;
		this.measureNum = measureNum;
	}

	/**
	 * Adds drum note to the array
	 * 
	 * @param drumNote referred note
	 */
	public void addGuitarNotes(DrumNote drumNote) {
		this.drumNotes.add(drumNote);
	}

	/**
	 * Formats the output of the measure
	 */
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("\nMeasure number: ").append(this.measureNum);
		res.append("\nMax elements: ").append(this.elementMax).append("\n");
		for (DrumNote drumNote : drumNotes) {
			res.append("\nElement number: ").append(drumNote.noteNumber);
			res.append("\nFret value: ").append(drumNote.noteValue);
			res.append("\nDuration: ").append(drumNote.duration);
			res.append("\nOctave: ").append(drumNote.octave).append("\n");
		}
		return res.toString();
	}

	/**
	 * Duration of each note determined based on the number of elements in the
	 * measurement
	 */
	public void processDuration() {
		ArrayList<Integer> samePositionNotes = new ArrayList<>();
		for (int i = 0; i < drumNotes.size(); i++) {
			int offset = 0;
			if (drumNotes.get(i).grace) {
				offset = 1;
			}
			if (i == drumNotes.size() - 1) {
				int thisDur = drumNotes.get(i).noteNumber;
				if (!samePositionNotes.isEmpty()) {
					for (int j = 0; j < samePositionNotes.size(); j++) {
						DrumNote temp = drumNotes.get(samePositionNotes.get(j));
						if (j != 0) {
							temp.setChord(true);
						}
						temp.setDuration(elementMax - temp.noteNumber - offset);
					}
					samePositionNotes.clear();
					drumNotes.get(i).setChord(true);
				}
				drumNotes.get(i).setDuration(elementMax - thisDur - offset);
			} else {
				if (drumNotes.get(i).noteNumber == drumNotes.get(i + 1).noteNumber) {
					samePositionNotes.add(i);
				} else {
					int nextDur = drumNotes.get(i + 1).noteNumber;
					if (!samePositionNotes.isEmpty()) {
						for (int j = 0; j < samePositionNotes.size(); j++) {
							DrumNote temp = drumNotes.get(samePositionNotes.get(j));
							if (j != 0) {
								temp.setChord(true);
							}
						}
						samePositionNotes.clear();
						drumNotes.get(i).setChord(true);
					}
					int thisDur = drumNotes.get(i).noteNumber;
				}
			}
		}
	}

	/**
	 * Sets the note type of the drum based on its note type
	 */
	public void setNoteType() {
		double maxDuration = 0;
		int chordValue = -1;
		for (DrumNote drumNote : drumNotes) {

			if (drumNote.chord && chordValue != drumNote.noteNumber) {
				chordValue = drumNote.noteNumber;

			} else if (!drumNote.chord) {
				maxDuration += drumNote.duration;
				chordValue = drumNote.noteNumber;

			}
		}

		for (DrumNote drumNote : drumNotes) {
			double noteType = drumNote.duration / maxDuration;

			if (noteType == 0.5) {
				drumNote.noteType = "half";
			} else if (noteType == 0.75) {
				drumNote.noteType = "half";
				drumNote.noteDot = true;
			} else if (noteType == 0.25) {
				drumNote.noteType = "quarter";
			} else if (noteType == 0.375) {
				drumNote.noteType = "quarter";
				drumNote.noteDot = true;
			} else if (noteType == 0.125) {
				drumNote.noteType = "eighth";
			} else if (noteType == 0.1875) {
				drumNote.noteType = "eighth";
				drumNote.noteDot = true;
			} else if (noteType == 0.0625) {
				drumNote.noteType = "16th";
			} else if (noteType == 0.003125) {
				drumNote.noteType = "32th";
			} else if (noteType == 1) {
				drumNote.noteType = "whole";
			}
		}
	}
}
