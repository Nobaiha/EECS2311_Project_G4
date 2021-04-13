import EECS2311_Project.DrumNote;
import EECS2311_Project.GuitarNote;
import EECS2311_Project.Main;
import EECS2311_Project.Measure;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class GuitarNoteTest {
    /**
     * Converts the internal representation of guitar note into a music note
     * Covers tests for every note value from one octave.
     */
    @Test
    public void setMusicNote() {
        GuitarNote guitarNote = new GuitarNote(1, 1, 1, "2");
        guitarNote.setMusicNote();
        assertEquals( "F", guitarNote.musicNote);
        assertEquals(4,guitarNote.octave);
        assertEquals(1,guitarNote.modifier);

        guitarNote = new GuitarNote(1, 1, 1, "1");
        guitarNote.setMusicNote();
        assertEquals( "F", guitarNote.musicNote);
        assertEquals(4,guitarNote.octave);
        assertEquals(0,guitarNote.modifier);

        guitarNote = new GuitarNote(1, 1, 1, "3");
        guitarNote.setMusicNote();
        assertEquals( "G", guitarNote.musicNote);
        assertEquals(4,guitarNote.octave);
        assertEquals(0,guitarNote.modifier);

        guitarNote = new GuitarNote(1, 1, 1, "4");
        guitarNote.setMusicNote();
        assertEquals( "G", guitarNote.musicNote);
        assertEquals(4,guitarNote.octave);
        assertEquals(1,guitarNote.modifier);

        guitarNote = new GuitarNote(1, 1, 1, "5");
        guitarNote.setMusicNote();
        assertEquals( "A", guitarNote.musicNote);
        assertEquals(4,guitarNote.octave);
        assertEquals(0,guitarNote.modifier);

        guitarNote = new GuitarNote(1, 1, 1, "6");
        guitarNote.setMusicNote();
        assertEquals( "A", guitarNote.musicNote);
        assertEquals(4,guitarNote.octave);
        assertEquals(1,guitarNote.modifier);

        guitarNote = new GuitarNote(1, 1, 1, "7");
        guitarNote.setMusicNote();
        assertEquals( "B", guitarNote.musicNote);
        assertEquals(4,guitarNote.octave);
        assertEquals(0,guitarNote.modifier);

        guitarNote = new GuitarNote(1, 1, 1, "8");
        guitarNote.setMusicNote();
        assertEquals( "C", guitarNote.musicNote);
        assertEquals(5,guitarNote.octave);
        assertEquals(0,guitarNote.modifier);

        guitarNote = new GuitarNote(1, 1, 1, "9");
        guitarNote.setMusicNote();
        assertEquals( "C", guitarNote.musicNote);
        assertEquals(5,guitarNote.octave);
        assertEquals(1,guitarNote.modifier);

        guitarNote = new GuitarNote(1, 1, 1, "10");
        guitarNote.setMusicNote();
        assertEquals( "D", guitarNote.musicNote);
        assertEquals(5,guitarNote.octave);
        assertEquals(0,guitarNote.modifier);

        guitarNote = new GuitarNote(1, 1, 1, "11");
        guitarNote.setMusicNote();
        assertEquals( "D", guitarNote.musicNote);
        assertEquals(5,guitarNote.octave);
        assertEquals(1,guitarNote.modifier);

        guitarNote = new GuitarNote(1, 1, 1, "12");
        guitarNote.setMusicNote();
        assertEquals( "E", guitarNote.musicNote);
        assertEquals(5,guitarNote.octave);
        assertEquals(0,guitarNote.modifier);

        guitarNote = new GuitarNote(1,1,1, "g12");
        guitarNote.setMusicNote();
        assertTrue(guitarNote.grace);

        guitarNote = new GuitarNote(1,1,1, "12p");
        guitarNote.setMusicNote();
        assertTrue(guitarNote.pull);

        guitarNote = new GuitarNote(1,1,1, "12h");
        guitarNote.setMusicNote();
        assertTrue(guitarNote.hammer);

        guitarNote = new GuitarNote(1,1,1, "(12)");
        guitarNote.setMusicNote();
        assertTrue(guitarNote.harmonic);

        //STRING 2

        guitarNote = new GuitarNote(1, 1, 2, "2");
        guitarNote.setMusicNote();
        assertEquals( "C", guitarNote.musicNote);
        assertEquals(4,guitarNote.octave);
        assertEquals(1,guitarNote.modifier);

        guitarNote = new GuitarNote(1, 1, 3, "2");
        guitarNote.setMusicNote();
        assertEquals( "A", guitarNote.musicNote);
        assertEquals(3,guitarNote.octave);
        assertEquals(0,guitarNote.modifier);

        guitarNote = new GuitarNote(1, 1, 4, "2");
        guitarNote.setMusicNote();
        assertEquals( "E", guitarNote.musicNote);
        assertEquals(3,guitarNote.octave);
        assertEquals(0,guitarNote.modifier);

        guitarNote = new GuitarNote(1, 1, 5, "2");
        guitarNote.setMusicNote();
        assertEquals( "B", guitarNote.musicNote);
        assertEquals(2,guitarNote.octave);
        assertEquals(0,guitarNote.modifier);

        guitarNote = new GuitarNote(1, 1, 6, "2");
        guitarNote.setMusicNote();
        assertEquals( "F", guitarNote.musicNote);
        assertEquals(2,guitarNote.octave);
        assertEquals(1,guitarNote.modifier);

        guitarNote = new GuitarNote(1, 1, 1, "2");
        guitarNote.bass = true;
        guitarNote.setMusicNote();
        assertEquals( "A", guitarNote.musicNote);
        assertEquals(2,guitarNote.octave);
        assertEquals(0,guitarNote.modifier);

        guitarNote = new GuitarNote(1, 1, 2, "1");
        guitarNote.bass = true;
        guitarNote.setMusicNote();
        assertEquals( "D", guitarNote.musicNote);
        assertEquals(2,guitarNote.octave);
        assertEquals(1,guitarNote.modifier);

        guitarNote = new GuitarNote(1, 1, 3, "1");
        guitarNote.bass = true;
        guitarNote.setMusicNote();
        assertEquals( "A", guitarNote.musicNote);
        assertEquals(3,guitarNote.octave);
        assertEquals(1,guitarNote.modifier);

        guitarNote = new GuitarNote(1, 1, 4, "1");
        guitarNote.bass = true;
        guitarNote.setMusicNote();
        assertEquals( "F", guitarNote.musicNote);
        assertEquals(3,guitarNote.octave);
        assertEquals(0,guitarNote.modifier);


    }
}