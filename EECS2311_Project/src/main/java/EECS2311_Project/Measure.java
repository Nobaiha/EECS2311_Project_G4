package EECS2311_Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


//used to sort notes based on their element number.
class noteSort implements Comparator<GuitarNote> {

    @Override
    public int compare(GuitarNote o1, GuitarNote o2) {
        return Integer.compare(o1.noteNumber, o2.noteNumber);
    }
}

public class Measure {
    public int elementMax;
    public int measureNum;
    public ArrayList<GuitarNote> guitarNotes = new ArrayList<>();
    public ArrayList<DrumNote> drumNotes;

    public Measure(int elementMax, int measureNum) {
        this.elementMax = elementMax;
        this.measureNum = measureNum;
    }

    public void addGuitarNotes(GuitarNote guitarNote) {
        this.guitarNotes.add(guitarNote);
    }

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

    public void sortNotes() {
        guitarNotes.sort(new noteSort());
    }

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
}
