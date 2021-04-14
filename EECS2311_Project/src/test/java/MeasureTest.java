import EECS2311_Project.DrumNote;
import EECS2311_Project.GuitarNote;
import EECS2311_Project.Main;
import EECS2311_Project.Measure;
import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class MeasureTest {

    /**
     * Function sorts the notes inside a measure, ensures that all guitar notes are in order.
     * Covers 3 cases, where note 1 is greater than note 2, note 2 greater than note 1, and when they are equal
     */
    @Test
    public void sortNotes(){
        Measure measure = new Measure(3,1);
        GuitarNote guitarNote1 = new GuitarNote(1,1,1,"2");
        GuitarNote guitarNote2 = new GuitarNote(1,2,1,"2");
        measure.addGuitarNotes(guitarNote1);
        measure.addGuitarNotes(guitarNote2);
        measure.sortNotes();
        assertEquals(guitarNote1,measure.guitarNotes.get(0));
        assertEquals(guitarNote2,measure.guitarNotes.get(1));

        measure.guitarNotes.clear();
        measure.addGuitarNotes(guitarNote2);
        measure.addGuitarNotes(guitarNote1);
        measure.sortNotes();
        assertEquals(guitarNote1,measure.guitarNotes.get(0));
        assertEquals(guitarNote2,measure.guitarNotes.get(1));

        measure.guitarNotes.clear();
        guitarNote1 = new GuitarNote(1,1,1,"2");
        guitarNote2 = new GuitarNote(1,1,1,"2");
        measure.addGuitarNotes(guitarNote2);
        measure.addGuitarNotes(guitarNote1);
        measure.sortNotes();
        assertEquals(guitarNote2,measure.guitarNotes.get(0));
        assertEquals(guitarNote1,measure.guitarNotes.get(1));
    }

    /**
     * Function determines the note durations for usage in musicXML
     * Covers 2 cases, where the notes are not in a chord, the duration will be the difference in spacing
     * If the notes are in a chord, the durations will be the same and the chord modifier will be set to true.
     */
    @Test
    public void processDuration(){
        Measure measure = new Measure(5,1);
        GuitarNote guitarNote1 = new GuitarNote(1,1,1,"2");
        GuitarNote guitarNote2 = new GuitarNote(1,2,2,"2");
        measure.addGuitarNotes(guitarNote1);
        measure.addGuitarNotes(guitarNote2);
        measure.sortNotes();
        measure.processDuration();
        assertEquals(0,measure.guitarNotes.get(0).duration);
        assertEquals(3,measure.guitarNotes.get(1).duration);

        measure.guitarNotes.clear();
        guitarNote1 = new GuitarNote(1,1,1,"2");
        guitarNote2 = new GuitarNote(1,1,2,"5");
        GuitarNote guitarNote3 = new GuitarNote(1,2,3,"2");
        measure.addGuitarNotes(guitarNote1);
        measure.addGuitarNotes(guitarNote2);
        measure.addGuitarNotes(guitarNote3);
        measure.sortNotes();
        measure.processDuration();
        assertEquals(0,measure.guitarNotes.get(0).duration);
        assertEquals(0,measure.guitarNotes.get(1).duration);
        assertFalse(measure.guitarNotes.get(0).chord);
        assertTrue(measure.guitarNotes.get(1).chord);
        assertEquals(3,measure.guitarNotes.get(2).duration);

        measure.guitarNotes.clear();
        guitarNote1 = new GuitarNote(1,1,1,"2");
        guitarNote2 = new GuitarNote(1,1,2,"5");
        measure.addGuitarNotes(guitarNote1);
        measure.addGuitarNotes(guitarNote2);
        measure.addGuitarNotes(guitarNote3);
        measure.sortNotes();
        measure.processDuration();
        assertEquals(0,measure.guitarNotes.get(0).duration);
        assertEquals(0,measure.guitarNotes.get(1).duration);
        assertFalse(measure.guitarNotes.get(0).chord);
        assertTrue(measure.guitarNotes.get(1).chord);

    }

    @Test
    public void setNoteType(){
        Measure measure = new Measure(4,1);
        GuitarNote guitarNote1 = new GuitarNote(1,2,1,"2");
        GuitarNote guitarNote2 = new GuitarNote(1,4,2,"5");
        guitarNote1.setMusicNote();
        guitarNote2.setMusicNote();
        measure.addGuitarNotes(guitarNote1);
        measure.addGuitarNotes(guitarNote2);
        measure.sortNotes();
        measure.processDuration();
        measure.setNoteType();
        assertEquals("whole",measure.guitarNotes.get(0).noteType);

        measure = new Measure(4,1);
        guitarNote1 = new GuitarNote(1,2,1,"2");
        guitarNote2 = new GuitarNote(1,4,2,"5");
        guitarNote1.duration = 1;
        guitarNote2.duration = 7;
        measure.addGuitarNotes(guitarNote1);
        measure.addGuitarNotes(guitarNote2);
        measure.setNoteType();
        assertEquals("eighth",measure.guitarNotes.get(0).noteType);

        measure = new Measure(4,1);
        guitarNote1 = new GuitarNote(1,2,1,"2");
        guitarNote2 = new GuitarNote(1,4,2,"5");
        guitarNote1.duration = 1;
        guitarNote2.duration = 3;
        measure.addGuitarNotes(guitarNote1);
        measure.addGuitarNotes(guitarNote2);
        measure.setNoteType();
        assertEquals("quarter",measure.guitarNotes.get(0).noteType);

        measure = new Measure(4,1);
        guitarNote1 = new GuitarNote(1,2,1,"2");
        guitarNote2 = new GuitarNote(1,4,2,"5");
        guitarNote1.duration = 1;
        guitarNote2.duration = 1;
        measure.addGuitarNotes(guitarNote1);
        measure.addGuitarNotes(guitarNote2);
        measure.setNoteType();
        assertEquals("half",measure.guitarNotes.get(0).noteType);

        measure = new Measure(4,1);
        guitarNote1 = new GuitarNote(1,2,1,"2");
        guitarNote2 = new GuitarNote(1,4,2,"5");
        guitarNote1.duration = 3;
        guitarNote2.duration = 5;
        measure.addGuitarNotes(guitarNote1);
        measure.addGuitarNotes(guitarNote2);
        measure.setNoteType();
        assertEquals("quarter",measure.guitarNotes.get(0).noteType);
        assertTrue(measure.guitarNotes.get(0).noteDot);

        measure = new Measure(4,1);
        guitarNote1 = new GuitarNote(1,2,1,"2");
        guitarNote2 = new GuitarNote(1,4,2,"5");
        guitarNote1.duration = 3;
        guitarNote2.duration = 13;
        measure.addGuitarNotes(guitarNote1);
        measure.addGuitarNotes(guitarNote2);
        measure.setNoteType();
        assertEquals("eighth",measure.guitarNotes.get(0).noteType);
        assertTrue(measure.guitarNotes.get(0).noteDot);

        measure = new Measure(4,1);
        guitarNote1 = new GuitarNote(1,2,1,"2");
        guitarNote2 = new GuitarNote(1,4,2,"5");
        guitarNote1.duration = 1;
        guitarNote2.duration = 15;
        measure.addGuitarNotes(guitarNote1);
        measure.addGuitarNotes(guitarNote2);
        measure.setNoteType();
        assertEquals("16th",measure.guitarNotes.get(0).noteType);

    }
}
