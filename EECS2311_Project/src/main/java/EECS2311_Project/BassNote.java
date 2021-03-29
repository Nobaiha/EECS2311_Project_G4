package EECS2311_Project;

public class BassNote extends GuitarNote{

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


    public BassNote(int measure, int noteNumber, int stringValue, String noteValue) {
        super(measure, noteNumber, stringValue, noteValue);
        this.measure = measure;
        this.noteNumber = noteNumber;
        this.stringValue = stringValue;
        this.noteValue = noteValue;
        this.chord = false;
    }

    @Override
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
        if(this.musicNote.contains("#")){
            this.musicNote = this.musicNote.substring(0,this.musicNote.length() - 1);
            this.modifier = 1;
        }
    }
}
