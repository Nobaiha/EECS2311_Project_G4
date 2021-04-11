package EECS2311_Project;
 
/**
 * A class that stores the data of the recognized guitar note.
 * 
 * @author Team 4 EECS2311 Winter 2021
 */
public class GuitarNote {
    //Public for testing, change to private afterwards.
    //Each note has a measure, note number representing the 'tab' it is at (each '-' char is a note number)
    //string number represents the string on the guitar 0 being e, 1 being A and so on.
    //noteValue is the representation of the note in tab form, usually a combination of numbers, letters, and symbols.
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
   //overload const. with pull off1/2

    
    /**
     * A constructor for GuitarNote.
     * 
     * @param measure
     * @param noteNumber
     * @param stringValue
     * @param noteValue
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
     * @param duration
     */
    public void setDuration(int duration){
        this.duration = duration;
    }

    /**
     * Sets the chord of the guitar note.
     * 
     * @param chord
     */
    public void setChord(boolean chord) {
        this.chord = chord;
    }

    /**
     *TODO
     */
    public void setMusicNote(){
        //convert the noteNumber here to musicNote.
        if(noteValue.startsWith("g")){
            grace = true;
        }
        if(noteValue.contains("p")){
            pull = true;
        }else if(noteValue.contains("h")){
            hammer = true;
        }else if(noteValue.contains("(") || noteValue.contains("[") || noteValue.contains("<")){
            harmonic = true;
            this.noteNumber += 2; //shift note value to end of harmonic value
        }
        //will be more like slides, bends... etc
        noteValue = noteValue.replaceAll("\\D+",""); //takes only the digits after extracting modifiers
        int remainder = Integer.parseInt(this.noteValue) % 12;
        if(!bass) {
            if (this.stringValue == 1) {
                String[] notes = {"E", "F", "F#", "G", "G#", "A", "A#", "B", "C", "C#", "D", "D#", "E"};
                this.musicNote = notes[remainder];
                int octaveChange = Integer.parseInt(this.noteValue) / (12 + 8);
                this.octave = 4 + octaveChange;
                if (Integer.parseInt(this.noteValue) >= 8) {
                    this.octave++;
                }
            } else if (this.stringValue == 2) {
                String[] notes = {"B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
                this.musicNote = notes[remainder];
                int octaveChange = Integer.parseInt(this.noteValue) / (12 + 1);
                this.octave = 3 + octaveChange;
                if (Integer.parseInt(this.noteValue) >= 1) {
                    this.octave++;
                }
            } else if (this.stringValue == 3) {
                String[] notes = {"G", "G#", "A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G"};
                this.musicNote = notes[remainder];
                int octaveChange = Integer.parseInt(this.noteValue) / (12 + 5);
                this.octave = 3 + octaveChange;
                if (Integer.parseInt(this.noteValue) >= 5) {
                    this.octave++;
                }
            } else if (this.stringValue == 4) {
                String[] notes = {"D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B", "C", "C#", "D"};
                this.musicNote = notes[remainder];
                int octaveChange = Integer.parseInt(this.noteValue) / (12 + 10);
                this.octave = 3 + octaveChange;
                if (Integer.parseInt(this.noteValue) >= 10) {
                    this.octave++;
                }
            } else if (this.stringValue == 5) {
                String[] notes = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A"};
                this.musicNote = notes[remainder];
                int octaveChange = Integer.parseInt(this.noteValue) / (12 + 3);
                this.octave = 2 + octaveChange;
                if (Integer.parseInt(this.noteValue) >= 3) {
                    this.octave++;
                }
            } else if (this.stringValue == 6) {
                String[] notes = {"E", "F", "F#", "G", "G#", "A", "A#", "B", "C", "C#", "D", "D#", "E"};
                this.musicNote = notes[remainder];
                int octaveChange = Integer.parseInt(this.noteValue) / (12 + 8);
                this.octave = 2 + octaveChange;
                if (Integer.parseInt(this.noteValue) >= 8) {
                    this.octave++;
                }
            }
        }else{
            if(this.stringValue == 1){
                String[] notes = {"G","G#","A","A#","B","C","C#","D","D#","E","F","F#","G"};
                this.musicNote = notes[remainder];
                int octaveChange = Integer.parseInt(this.noteValue) / (12+5);
                this.octave = 2 + octaveChange;
                if(Integer.parseInt(this.noteValue) >= 5){
                    this.octave++;
                }
            }else if(this.stringValue == 2){
                String[] notes = {"D","D#","E","F","F#","G","G#","A","A#","B","C","C#","D"};
                this.musicNote = notes[remainder];
                int octaveChange = Integer.parseInt(this.noteValue) / (12+10);
                this.octave = 2 + octaveChange;
                if(Integer.parseInt(this.noteValue) >= 10){
                    this.octave++;
                }
            }else if(this.stringValue == 3){
                String[] notes = {"A","A#","B","C","C#","D","D#","E","F","F#","G","G#","A"};
                this.musicNote = notes[remainder];
                int octaveChange = Integer.parseInt(this.noteValue) / (12+3);
                this.octave = 3 + octaveChange;
                if(Integer.parseInt(this.noteValue) >= 3){
                    this.octave++;
                }
            }else if(this.stringValue == 4){
                String[] notes = {"E","F","F#","G","G#","A","A#","B","C","C#","D","D#","E"};
                this.musicNote = notes[remainder];
                int octaveChange = Integer.parseInt(this.noteValue) / (12+8);
                this.octave = 3 + octaveChange;
                if(Integer.parseInt(this.noteValue) >= 8){
                    this.octave++;
                }
            }
        }
        if(this.musicNote.contains("#")){
            this.musicNote = this.musicNote.substring(0,this.musicNote.length() - 1);
            this.modifier = 1;
        }
    }
}
