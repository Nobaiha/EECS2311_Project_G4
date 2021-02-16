/*
package EECS2311_Project;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.*;

public class MainTest {

public class MainTest {

    @Test
    public void fileParser() throws FileNotFoundException {
        //Patchanon
        String path = "src/main/java/EECS2311_Project/example.txt";
        Object[] guitarNotes = Main.fileParser(path);
        assertEquals("guitar", guitarNotes[0]);

        //Suha
        path = "src/main/java/EECS2311_Project/example2.txt";
        guitarNotes = Main.fileParser(path);
        //assertEquals("drum", guitarNotes[0]);

        //Rayta
        path = "src/main/java/EECS2311_Project/example2.exe";
        String finalPath = path;
        */
/*assertThrows(FileNotFoundException.class,() -> {
            Main.fileParser(finalPath);
        });*//*

    }

    @Test
    public void fileChecker() {
        //Patchanon
        String file = "notatxt.exe";
        assertFalse(Main.fileChecker(file));

        //Suha
        file = "valid.txt";
        //assertTrue(Main.fileChecker(file));
    }

    @Test
    public void guitarToMusicNote() {
        //Patchanon
        GuitarNote guitarNote = new GuitarNote(1, 1, 1, "2");
        //MusicNote musicNote = new MusicNote("F");
        //MusicNote testMusicNote = Main.guitarToMusicNote(guitarNote);
        //assertEquals(musicNote.note, testMusicNote.note);
    }

    @Test
    public void drumToMusicNote(){
        //Suha
        //Given a drumnote of part C and note value X, should return the musical note B
        DrumNote drumNote = new DrumNote(1,1,"C",'X');
        MusicNote musicNote = new MusicNote("B");
        MusicNote testMusicNote = Main.drumToMusicNote(drumNote);
        //assertEquals(musicNote.note, testMusicNote.note);
    }

    @Test
    public void guitarNoteParser() {
        //Rayta
        String input = "e|-----|-15p12--10p9--12p10--6p5--8p6------|---------|----|";
        ArrayList<String> testNoteArray = new ArrayList<String>();
        testNoteArray.add(input);
        ArrayList<GuitarNote> guitarNotes = Main.guitarNoteParser(testNoteArray);
        //assertEquals(10, guitarNotes.size());

        //Martin
        input = "e|---";
        testNoteArray.clear();
        testNoteArray.add(input);
        guitarNotes = Main.guitarNoteParser(testNoteArray);
        //assertEquals(0, guitarNotes.size());

    }

    @Test
    public void drumNoteParser() {
        //Rayta
        String input = "C |X---------------|----------------|X-------------------|X--X--X---------|";
        ArrayList<String> testNoteArray = new ArrayList<String>();
        testNoteArray.add(input);
        ArrayList<DrumNote> drumNotes = Main.drumNoteParser(testNoteArray);
        //assertEquals(5,drumNotes.size());

        //Richard
        input = "C |----";
        testNoteArray = new ArrayList<String>();
        testNoteArray.add(input);
        drumNotes = Main.drumNoteParser(testNoteArray);
        //assertEquals(drumNotes.size(), 0);
    }

    @Test
    public void guitarXMLParser() {
        //Martin
        ArrayList<GuitarNote> guitarNotes = new ArrayList<>();
        GuitarNote guitarNote = new GuitarNote(1,1,'e',"3");
        guitarNotes.add(guitarNote);
        //assertNotNull(Main.guitarXMLParser(guitarNotes));

        //Richard
        guitarNotes.clear();
        //assertNull(Main.guitarXMLParser(guitarNotes));
    }

    @Test
    public void drumXMLParser() {
        //Martin
        ArrayList<DrumNote> drumNotes = new ArrayList<>();
        //assertNull(Main.drumXMLParser(drumNotes));

        //Richard
        DrumNote drumNote = new DrumNote(1,1,"cc",'X');
        drumNotes.add(drumNote);
        //assertNotNull(Main.drumXMLParser(drumNotes));
    }
}*/
