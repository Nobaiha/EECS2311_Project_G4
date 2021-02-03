package main.java.EECS2311_Project;

/**
 * A class that stores the data of the recognized guitar note.
 * 
 * @author Team 4 EECS2311 Winter 2021
 * 
 */

public class GuitarNote {
    //Public for testing, change to private afterwards.
    //Each note has a measure, note number representing the 'tab' it is at (each '-' char is a note number)
    //string number represents the string on the guitar 0 being e, 1 being A and so on.
    //noteValue is the representation of the note in tab form, usually a combination of numbers, letters, and symbols.
    public int measure;
    public int noteNumber;
    public char stringValue;
    public String noteValue;
    public int pullOff1;
    public int pullOff2;
   //overload const. with pull off1/2
    
    /**
     * A constructor for GuitarNote.
     * 
     * @param measure of note
     * @param number of note
     * @param string value of note
     * @param value of note
     * 
     */
    public GuitarNote(int measure, int noteNumber, char stringValue, String noteValue) {
        this.measure = measure;
        this.noteNumber = noteNumber;
        this.stringValue = stringValue;
        this.noteValue = noteValue;
    }
}
