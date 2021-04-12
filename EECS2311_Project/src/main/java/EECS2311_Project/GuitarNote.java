package EECS2311_Project;

/**
 * A class that stores the data of the recognized guitar note. Eaxh note has a
 * measure, note number representing the 'tab' it is at(each '-' char is a note
 * number) string number represents the string on the guitar. Note value is the
 * representation of the note in tab form, a combination of numbers, letters,
 * and symbols.
 * 
 * @author Team 4 EECS2311 Winter 2021
 */
public class GuitarNote {

	public int measure;
	public int noteNumber;
	public int stringValue;
	public int octave;
	public String noteValue;
	public String musicNote;
	public int duration;
	public int modifier;
	public boolean pull;
	public boolean hammer;
	public boolean harmonic;
	public boolean chord;
	public boolean grace;
	public boolean bass = false;
	public String noteType;
	public boolean noteDot;

	/**
	 * A constructor for GuitarNote.
	 * 
	 * @param measure     in the tab
	 * @param noteNumber  integer value assigned to the note in the measure
	 * @param stringValue in the tablature
	 * @param noteValue   representation of note in tab form
	 */
	public GuitarNote(int measure, int noteNumber, int stringValue, String noteValue) {
		this.measure = measure;
		this.noteNumber = noteNumber;
		this.stringValue = stringValue;
		this.noteValue = noteValue;
		this.chord = false;
	}

	/**
	 * Sets the duration of the guitar note.
	 * 
	 * @param duration of the guitar note
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * Sets the chord of the guitar note.
	 * 
	 * @param chord of the drum note
	 */
	public void setChord(boolean chord) {
		this.chord = chord;
	}

	/**
	 * Assigns the note number and its attribute to a music note such as note value,
	 * note type,shift note, slides, bends, harmonics, hammer ons, pull offs, etc
	 */
	public void setMusicNote() {
		// convert the noteNumber here to musicNote.
		if (noteValue.startsWith("g")) {
			grace = true;
		}
		if (noteValue.contains("p")) {
			pull = true;
		} else if (noteValue.contains("h")) {
			hammer = true;
		} else if (noteValue.contains("(") || noteValue.contains("[") || noteValue.contains("<")) {
			harmonic = true;
			this.noteNumber += 2; // shift note value to end of harmonic value
		}
		// will be more like slides, bends... etc
		noteValue = noteValue.replaceAll("\\D+", ""); // takes only the digits after extracting modifiers
		int remainder = Integer.parseInt(this.noteValue) % 12;
		if (!bass) {
			if (this.stringValue == 1) {
				String[] notes = { "E", "F", "F#", "G", "G#", "A", "A#", "B", "C", "C#", "D", "D#", "E" };
				this.musicNote = notes[remainder];
				int octaveChange = Integer.parseInt(this.noteValue) / (12 + 8);
				this.octave = 4 + octaveChange;
				if (Integer.parseInt(this.noteValue) >= 8) {
					this.octave++;
				}
			} else if (this.stringValue == 2) {
				String[] notes = { "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B" };
				this.musicNote = notes[remainder];
				int octaveChange = Integer.parseInt(this.noteValue) / (12 + 1);
				this.octave = 3 + octaveChange;
				if (Integer.parseInt(this.noteValue) >= 1) {
					this.octave++;
				}
			} else if (this.stringValue == 3) {
				String[] notes = { "G", "G#", "A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G" };
				this.musicNote = notes[remainder];
				int octaveChange = Integer.parseInt(this.noteValue) / (12 + 5);
				this.octave = 3 + octaveChange;
				if (Integer.parseInt(this.noteValue) >= 5) {
					this.octave++;
				}
			} else if (this.stringValue == 4) {
				String[] notes = { "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B", "C", "C#", "D" };
				this.musicNote = notes[remainder];
				int octaveChange = Integer.parseInt(this.noteValue) / (12 + 10);
				this.octave = 3 + octaveChange;
				if (Integer.parseInt(this.noteValue) >= 10) {
					this.octave++;
				}
			} else if (this.stringValue == 5) {
				String[] notes = { "A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A" };
				this.musicNote = notes[remainder];
				int octaveChange = Integer.parseInt(this.noteValue) / (12 + 3);
				this.octave = 2 + octaveChange;
				if (Integer.parseInt(this.noteValue) >= 3) {
					this.octave++;
				}
			} else if (this.stringValue == 6) {
				String[] notes = { "E", "F", "F#", "G", "G#", "A", "A#", "B", "C", "C#", "D", "D#", "E" };
				this.musicNote = notes[remainder];
				int octaveChange = Integer.parseInt(this.noteValue) / (12 + 8);
				this.octave = 2 + octaveChange;
				if (Integer.parseInt(this.noteValue) >= 8) {
					this.octave++;
				}
			}
		} else {
			if (this.stringValue == 1) {
				String[] notes = { "G", "G#", "A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G" };
				this.musicNote = notes[remainder];
				int octaveChange = Integer.parseInt(this.noteValue) / (12 + 5);
				this.octave = 2 + octaveChange;
				if (Integer.parseInt(this.noteValue) >= 5) {
					this.octave++;
				}
			} else if (this.stringValue == 2) {
				String[] notes = { "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B", "C", "C#", "D" };
				this.musicNote = notes[remainder];
				int octaveChange = Integer.parseInt(this.noteValue) / (12 + 10);
				this.octave = 2 + octaveChange;
				if (Integer.parseInt(this.noteValue) >= 10) {
					this.octave++;
				}
			} else if (this.stringValue == 3) {
				String[] notes = { "A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A" };
				this.musicNote = notes[remainder];
				int octaveChange = Integer.parseInt(this.noteValue) / (12 + 3);
				this.octave = 3 + octaveChange;
				if (Integer.parseInt(this.noteValue) >= 3) {
					this.octave++;
				}
			} else if (this.stringValue == 4) {
				String[] notes = { "E", "F", "F#", "G", "G#", "A", "A#", "B", "C", "C#", "D", "D#", "E" };
				this.musicNote = notes[remainder];
				int octaveChange = Integer.parseInt(this.noteValue) / (12 + 8);
				this.octave = 3 + octaveChange;
				if (Integer.parseInt(this.noteValue) >= 8) {
					this.octave++;
				}
			}
		}
		if (this.musicNote.contains("#")) {
			this.musicNote = this.musicNote.substring(0, this.musicNote.length() - 1);
			this.modifier = 1;
		}
	}
}
