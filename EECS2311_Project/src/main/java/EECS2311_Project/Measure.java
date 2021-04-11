package EECS2311_Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * A class that is used to sort notes based on their element number.
 * 
 * @author Team 4 EECS2311 Winter 2021
 */
class noteSort implements Comparator<GuitarNote> {

	/**
     * Compares two GuitarNotes.
     * 
     * @param o1
     * 				first guitar note.
     * @param o2
     * 				second guitar note.
     * @return TODO
     */
    @Override
    public int compare(GuitarNote o1, GuitarNote o2) {
        return Integer.compare(o1.noteNumber, o2.noteNumber);
    }
}

/**
 * TODO
 * 
 * @author Team 4 EECS2311 Winter 2021
 */
public class Measure {
    public int elementMax;
    public int measureNum;
    public ArrayList<GuitarNote> guitarNotes = new ArrayList<>();
    public ArrayList<DrumNote> drumNotes;

    /**
     * A constructor for Measure.
     * 
     * @param elementMax
     * @param measureNum
     */
    public Measure(int elementMax, int measureNum) {
        this.elementMax = elementMax;
        this.measureNum = measureNum;
    }

    /**
     * Adds a new guitar note.
     * 
     * @param guitarNote
     */
    public void addGuitarNotes(GuitarNote guitarNote) {
        this.guitarNotes.add(guitarNote);
    }

    /**
     * The toString method for Measure.
     */
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("\nMeasure number: ").append(this.measureNum);
        res.append("\nMax elements: ").append(this.elementMax).append("\n");
        for (GuitarNote guitarNote : guitarNotes) {
            res.append("\nString: ").append(guitarNote.stringValue);
            res.append("\nElement number: ").append(guitarNote.noteNumber);
            res.append("\nFret value: ").append(guitarNote.noteValue);
            res.append("\nMusic note: ").append(guitarNote.musicNote);
            res.append("\nDuration: ").append(guitarNote.duration);
            res.append("\nOctave: ").append(guitarNote.octave).append("\n");
        }
        return res.toString();
    }

    /**
     * Sorts the notes.
     */
    public void sortNotes() {
        guitarNotes.sort(new noteSort());
    }

    /**
     * Calculates the duration of the notes.
     */
    public void processDuration() {
        ArrayList<Integer> samePositionNotes = new ArrayList<>();
        for (int i = 0; i < guitarNotes.size(); i++) {
            int offset = 0;
            if(guitarNotes.get(i).grace){
                offset = 1;
            }
            if (i == guitarNotes.size() - 1) {
                int thisDur = guitarNotes.get(i).noteNumber;
                if (!samePositionNotes.isEmpty()) {
                    for (int j = 0; j < samePositionNotes.size(); j++) {
                        GuitarNote temp = guitarNotes.get(samePositionNotes.get(j));
                        if(j != 0) {
                            temp.setChord(true);
                        }
                        temp.setDuration(elementMax - temp.noteNumber - offset);
                    }
                    samePositionNotes.clear();
                    guitarNotes.get(i).setChord(true);
                }
                guitarNotes.get(i).setDuration(elementMax - thisDur - offset);
            } else {
                if (guitarNotes.get(i).noteNumber == guitarNotes.get(i + 1).noteNumber) {
                    samePositionNotes.add(i);
                } else {
                    int nextDur = guitarNotes.get(i + 1).noteNumber;
                    if (!samePositionNotes.isEmpty()) {
                        for (int j = 0; j < samePositionNotes.size(); j++) {
                            GuitarNote temp = guitarNotes.get(samePositionNotes.get(j));
                            if(j != 0) {
                                temp.setChord(true);
                            }
                            temp.setDuration(nextDur - temp.noteNumber - guitarNotes.get(i).noteValue.length() - offset);
                        }
                        samePositionNotes.clear();
                        guitarNotes.get(i).setChord(true);
                    }
                    int thisDur = guitarNotes.get(i).noteNumber;
                    guitarNotes.get(i).setDuration(nextDur - thisDur - guitarNotes.get(i).noteValue.length() - offset);
                }
            }
        }
    }

    /**
     * TODO
     */
    public void setNoteType(){
        double maxDuration = 0;
        int chordValue = -1;
        for(GuitarNote guitarNote : guitarNotes){
            /*System.out.println("String: " + guitarNote.stringValue);
            System.out.println("Measure number: " + guitarNote.measure);
            System.out.println("Element number: " + guitarNote.noteNumber);
            System.out.println("Element value: " + guitarNote.noteValue);
            System.out.println("Chord value: " + guitarNote.chord);
            System.out.println();*/
            if(guitarNote.chord && chordValue != guitarNote.noteNumber) {
                chordValue = guitarNote.noteNumber;
                //System.out.println(maxDuration);
            }else if(!guitarNote.chord){
                maxDuration += guitarNote.duration;
                chordValue = guitarNote.noteNumber;
                //System.out.println(maxDuration);
            }
        }
        for(GuitarNote guitarNote : guitarNotes){
            double noteType = guitarNote.duration / maxDuration;
            //System.out.println(noteType);
            if(noteType == 0.5){
                guitarNote.noteType = "half";
            }else if(noteType == 0.75){
                guitarNote.noteType = "half";
                guitarNote.noteDot = true;
            }else if(noteType == 0.25){
                guitarNote.noteType = "quarter";
            }else if(noteType == 0.375){
                guitarNote.noteType = "quarter";
                guitarNote.noteDot = true;
            }else if(noteType == 0.125){
                guitarNote.noteType = "eighth";
            }else if(noteType == 0.1875){
                guitarNote.noteType = "eighth";
                guitarNote.noteDot = true;
            }else if(noteType == 0.0625){
                guitarNote.noteType = "16th";
            }else if(noteType == 0.003125){
                guitarNote.noteType = "32th";
            }else if(noteType == 1){
                guitarNote.noteType = "whole";
            }
        }
    }
}

