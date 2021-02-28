import EECS2311_Project.GuitarNote;
import EECS2311_Project.Main;
import EECS2311_Project.Measure;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class MainTest {

    /**
     * Tests fileParser, which takes a file and determines if it is a guitar or drum tab.
     * Covers all 3 cases, a guitar tab is given, a drum tab is given, and the wrong file is given.
     * @throws FileNotFoundException if not given a txt file
     */
    @Test
    public void fileParser() throws FileNotFoundException {

        String path = "src/main/java/EECS2311_Project/tabs/example.txt";
        Object[] guitarNotes = Main.fileParser(path);
        assertEquals("guitar", guitarNotes[0]);

        path = "src/test/java/testTab/drumTest.txt";
        guitarNotes = Main.fileParser(path);
        assertEquals("drum", guitarNotes[0]);

        path = "src/main/java/EECS2311_Project/example2.exe";
        String finalPath = path;
        assertThrows(FileNotFoundException.class, () -> {
            Main.fileParser(finalPath);
        });
    }

    /**
     * Function checks if the file extension is a .txt
     * Covers 2 cases, where it is a txt and where it is not a txt.
     */
    @Test
    public void fileChecker() {
        String file = "notatxt.exe";
        assertFalse(Main.fileChecker(file));

        file = "valid.txt";
        assertTrue(Main.fileChecker(file));
    }

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
    }

    /**
     * Given a line of guitar notes, the function will store these notes in an array
     * Covers the case where a valid guitar line is given, and where an invalid one is given
     */
    @Test
    public void guitarNoteParser() {

        String input = "e|-----|-15p12--10p9--12p10--6p5--8p6------|---------|----|";
        ArrayList<String> testNoteArray = new ArrayList<>();
        testNoteArray.add(input);
        ArrayList<GuitarNote> guitarNotes = Main.guitarNoteParser(testNoteArray);
        assertEquals(10, guitarNotes.size());
        assertEquals("15p",guitarNotes.get(0).noteValue);

        input = "e|---";
        testNoteArray.clear();
        testNoteArray.add(input);
        guitarNotes = Main.guitarNoteParser(testNoteArray);
        assertEquals(0, guitarNotes.size());

    }

    /**
     * Given an array of measures, will write the xml output to a file.
     * Tests covers two cases, one where a valid input is given, and where a non-valid input is given.
     */
    @Test
    public void guitarXMLParser() {
        GuitarNote guitarNote = new GuitarNote(1, 1, 1, "2");
        guitarNote.setMusicNote();
        Measure measure = new Measure(1,1);
        measure.addGuitarNotes(guitarNote);
        ArrayList<Measure> measures = new ArrayList<>();
        measures.add(measure);
        assertNotNull(Main.guitarXMLParser(measures));


        measures.clear();
        assertNull(Main.guitarXMLParser(measures));
    }

    /**
     * Function to start the parser, will throw an exception if the wrong file is given.
     * Covers the case where an exception will be thrown
     */
    @Test
    public void start(){
        String path = "src/main/java/EECS2311_Project/example2.exe";
        assertThrows(FileNotFoundException.class, () -> {
            Main.start(path,"Test","Bach");
        });

    }

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
    }

}
