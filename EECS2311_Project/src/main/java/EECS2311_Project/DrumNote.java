package EECS2311_Project;

/**
 * A class that stores the data of the recognized drum note.
 * 
 * @author Team 4 EECS2311 Winter 2021
 * 
 */

public class DrumNote {
        //Public for testing, change to private afterwards.
        //Each note has a measure, note number representing the 'tab' it is at (each '-' char is a note number)
        //string number represents the string on the guitar 0 being e, 1 being A and so on.
        //noteValue is the representation of the note in tab form, usually a combination of numbers, letters, and symbols.
        public int measure;
        public int noteNumber;
        public String part;
        public char noteValue;
        
        /**
         * A constructor for GuitarNote.
         * 
         * @param measure of note
         * @param number of note
         * @param part of note
         * @param value of note
         * 
         */
        public DrumNote(int measure, int noteNumber, String part, char noteValue) {
            this.measure = measure;
            this.noteNumber = noteNumber;
            this.part = part;
            this.noteValue = noteValue;
        }
}
