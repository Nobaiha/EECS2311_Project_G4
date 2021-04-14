package EECS2311_Project;

/**
 * A class that stores the data of the recognized drum note.
 * 
 * @author Team 4 EECS2311 Winter 2021
 */
public class DrumNote {
	
        public int measure;
        public int noteNumber;
        public String part;
        public char noteValue;
        public int position;
        
        public int duration;
        
        /**
         * A constructor for DrumNote.
         * 
         * @param measure
         * @param noteNumber
         * @param part
         * @param noteValue
         */
        public DrumNote(int measure, int noteNumber, String part, char noteValue) {
            this.measure = measure;
            this.noteNumber = noteNumber;
            this.part = part;
            this.noteValue = noteValue;
        }
        
        /**
         * Sets the duration of the drum note.
         * 
         * @param duration
         */
        public void setDuration(int duration){
            this.duration = duration;
        }
        
        /**
         * Sets the position of the drum note.
         * 
         * @param position
         */
        public void setPosition(int position) {
            this.position = position;
        }
}
