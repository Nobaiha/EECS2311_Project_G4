package EECS2311_Project;

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
    public GuitarNote(int measure, int noteNumber, char stringValue, String noteValue) {
        this.measure = measure;
        this.noteNumber = noteNumber;
        this.stringValue = stringValue;
        this.noteValue = noteValue;
    }
}
