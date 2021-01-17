package EECS2311_Project;

public class Note {
    //Public for testing, change to private afterwards.
    //Each note has a measure, note number representing the 'tab' it is at (each '-' char is a note number)
    //string number represents the string on the guitar 0 being e, 1 being A and so on.
    //noteValue is the representation of the note in tab form, usually a combination of numbers, letters, and symbols.
    public int measure;
    public int noteNumber;
    public int stringNumber;
    public String noteValue;

    public Note(int measure, int noteNumber, int stringNumber, String noteValue) {
        this.measure = measure;
        this.noteNumber = noteNumber;
        this.stringNumber = stringNumber;
        this.noteValue = noteValue;
    }
}
