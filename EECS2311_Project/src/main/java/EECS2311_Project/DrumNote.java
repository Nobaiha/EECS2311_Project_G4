package EECS2311_Project;

/**
 * A class that stores the data of the recognized drum note. Each note has a
 * measure, note number representing the 'tab' it is at(each '-' char is a note
 * number), string number represents the string. The note value is the
 * representation of the note in tab form, usually a combination of numbers,
 * letters and symbols.
 * 
 * @author Team 4 EECS2311 Winter 2021
 */
public class DrumNote {
	// Public for testing, change to private afterwards.

	public int measure;
	public int noteNumber;
	public String part;
	public char noteValue;
	public boolean backUp;

	public int duration;
	public boolean chord;
	public String noteType;
	public boolean noteDot;
	public int octave;
	public boolean grace;

	/**
	 * A constructor for DrumNote.
	 * 
	 * @param measure in the tab 
	 * @param noteNumber integer value assigned to the note in the measure
	 * @param part in the tablature
	 * @param noteValue representation of note in tab form 
	 */
	public DrumNote(int measure, int noteNumber, String part, char noteValue) {
		this.measure = measure;
		this.noteNumber = noteNumber;
		this.part = part;
		this.noteValue = noteValue;
		this.chord = false;

		if (this.part.equalsIgnoreCase("BD") || this.part.equalsIgnoreCase("B")) {
			this.backUp = true;
		} else {
			this.backUp = false;
		}
	}

	/**
	 * Sets the duration of the drum note.
	 * 
	 * @param duration of the drum note
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * Sets the chord of the drum note.
	 * 
	 * @param chord of the drum note
	 */
	public void setChord(boolean chord) {
		this.chord = chord;
	}
}
