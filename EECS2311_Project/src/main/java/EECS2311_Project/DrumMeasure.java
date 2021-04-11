package EECS2311_Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


//used to sort notes based on their element number.
class noteSortDrum implements Comparator<DrumNote> {

    @Override
    public int compare(DrumNote o1, DrumNote o2) {
        return Integer.compare(o1.noteNumber, o2.noteNumber);
    }
}

public class DrumMeasure {
    public int elementMax;
    public int measureNum;
    public ArrayList<DrumNote> drumNotes = new ArrayList<>();
    //public ArrayList<DrumNote> drumNotes;

    public void Measure(int elementMax, int measureNum) {
        this.elementMax = elementMax;
        this.measureNum = measureNum;
    }

    public void addGuitarNotes(DrumNote drumNote) {
        this.drumNotes.add(drumNote);
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("\nMeasure number: ").append(this.measureNum);
        res.append("\nMax elements: ").append(this.elementMax).append("\n");
        for (DrumNote drumNote : drumNotes) {
            res.append("\nElement number: ").append(drumNote.noteNumber);
            res.append("\nFret value: ").append(drumNote.noteValue);
            res.append("\nDuration: ").append(drumNote.duration);
            res.append("\nOctave: ").append(drumNote.octave).append("\n");
        }
        return res.toString();
    }

//    public void sortNotesDrum() {
//        drumNotes.sort(new noteSortDrum());
//    }

    public void processDuration() {
        ArrayList<Integer> samePositionNotes = new ArrayList<>();
        for (int i = 0; i < drumNotes.size(); i++) {
            int offset = 0;
            if(drumNotes.get(i).grace){
                offset = 1;
            }
            if (i == drumNotes.size() - 1) {
                int thisDur = drumNotes.get(i).noteNumber;
                if (!samePositionNotes.isEmpty()) {
                    for (int j = 0; j < samePositionNotes.size(); j++) {
                        DrumNote temp = drumNotes.get(samePositionNotes.get(j));
                        if(j != 0) {
                            temp.setChord(true);
                        }
                        temp.setDuration(elementMax - temp.noteNumber - offset);
                    }
                    samePositionNotes.clear();
                    drumNotes.get(i).setChord(true);
                }
                drumNotes.get(i).setDuration(elementMax - thisDur - offset);
            } else {
                if (drumNotes.get(i).noteNumber == drumNotes.get(i + 1).noteNumber) {
                    samePositionNotes.add(i);
                } else {
                    int nextDur = drumNotes.get(i + 1).noteNumber;
                    if (!samePositionNotes.isEmpty()) {
                        for (int j = 0; j < samePositionNotes.size(); j++) {
                            DrumNote temp = drumNotes.get(samePositionNotes.get(j));
                            if(j != 0) {
                                temp.setChord(true);
                            }
                            //temp.setDuration(nextDur - temp.noteNumber - drumNotes.get(i).noteValue.length() - offset);
                        }
                        samePositionNotes.clear();
                        drumNotes.get(i).setChord(true);
                    }
                    int thisDur = drumNotes.get(i).noteNumber;
                    //drumNotes.get(i).setDuration(nextDur - thisDur - drumNotes.get(i).noteValue.length() - offset);
                }
            }
        }
    }

    public void setNoteType(){
        double maxDuration = 0;
        int chordValue = -1;
        for(DrumNote drumNote : drumNotes){
            /*System.out.println("String: " + guitarNote.stringValue);
            System.out.println("Measure number: " + guitarNote.measure);
            System.out.println("Element number: " + guitarNote.noteNumber);
            System.out.println("Element value: " + guitarNote.noteValue);
            System.out.println("Chord value: " + guitarNote.chord);
            System.out.println();*/
            if(drumNote.chord && chordValue != drumNote.noteNumber) {
                chordValue = drumNote.noteNumber;
                //System.out.println(maxDuration);
            }else if(!drumNote.chord){
                maxDuration += drumNote.duration;
                chordValue = drumNote.noteNumber;
                //System.out.println(maxDuration);
            }
        }
        
    for(DrumNote drumNote : drumNotes){
            double noteType = drumNote.duration / maxDuration;
            //System.out.println(noteType);
            if(noteType == 0.5){
            	drumNote.noteType = "half";
            }else if(noteType == 0.75){
            	drumNote.noteType = "half";
            	drumNote.noteDot = true;
            }else if(noteType == 0.25){
            	drumNote.noteType = "quarter";
            }else if(noteType == 0.375){
            	drumNote.noteType = "quarter";
            	drumNote.noteDot = true;
            }else if(noteType == 0.125){
            	drumNote.noteType = "eighth";
            }else if(noteType == 0.1875){
            	drumNote.noteType = "eighth";
            	drumNote.noteDot = true;
            }else if(noteType == 0.0625){
            	drumNote.noteType = "16th";
            }else if(noteType == 0.003125){
            	drumNote.noteType = "32th";
            }else if(noteType == 1){
            	drumNote.noteType = "whole";
            }
        }
    }
}

