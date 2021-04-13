import EECS2311_Project.DrumNote;
import EECS2311_Project.GuiWelcome;
import EECS2311_Project.GuitarNote;
import EECS2311_Project.Main;
import EECS2311_Project.Measure;
import org.junit.jupiter.api.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

	/**
	 * Tests fileParser, which takes a file and determines if it is a guitar or drum
	 * tab. Covers all 3 cases, a guitar tab is given, a drum tab is given, and the
	 * wrong file is given.
	 * 
	 * @throws FileNotFoundException if not given a txt file
	 */
	@Test
	public void fileParser() throws FileNotFoundException {

		String path = "src/main/java/EECS2311_Project/tabs/example.txt";
		Object[] guitarNotes = Main.fileParser(path);
		assertEquals("guitar", guitarNotes[0]);

		path = "src/main/java/EECS2311_Project/example2.exe";
		String finalPath = path;
		assertThrows(FileNotFoundException.class, () -> {
			Main.fileParser(finalPath);
		});
	}

	/**
	 * Function checks if the file extension is a .txt Covers 2 cases, where it is a
	 * txt and where it is not a txt.
	 */
	@Test
	public void fileChecker() {
		String file = "notatxt.exe";
		assertFalse(Main.fileChecker(file));

		file = "valid.txt";
		assertTrue(Main.fileChecker(file));
	}

	/**
	 * Given a line of guitar notes, the function will store these notes in an array
	 * Covers the case where a valid guitar line is given, and where an invalid one
	 * is given
	 */
	@Test
	public void guitarNoteParser() {

		String topInput = "                   |---REPEAT-5X-----|";
		String bottomInput = "e|-----------0-----|-0---------------|--------5----------|";
		ArrayList<String> testNoteArray = new ArrayList<>();
		testNoteArray.add(topInput);
		testNoteArray.add(bottomInput);
		ArrayList<GuitarNote> guitarNotes = Main.guitarNoteParser(testNoteArray);
		assertTrue(Main.topRepeatStarts.size() != 0);
		assertTrue(Main.topRepeatEnds.size() != 0);
		assertTrue(Main.repeatAmout.size() != 0);

		topInput = "|-----------0-----||----------0--------4|";
		bottomInput = "|-------1-------1-||*---------1-------*||";
		testNoteArray.clear();
		testNoteArray = new ArrayList<>();
		testNoteArray.add(topInput);
		testNoteArray.add(bottomInput);
		guitarNotes = Main.guitarNoteParser(testNoteArray);
		assertTrue(Main.repeatStarts.size() != 0);
		assertTrue(Main.repeatEnds.size() != 0);
		assertTrue(Main.repeatAmout.size() != 0);

		String input = "e|-----|-15p12--10p9--12p10--6p5--8p6------|---------|----|";
		testNoteArray.clear();
		testNoteArray = new ArrayList<>();
		testNoteArray.add(input);
		guitarNotes = Main.guitarNoteParser(testNoteArray);
		assertEquals(10, guitarNotes.size());
		assertEquals("15p", guitarNotes.get(0).noteValue);

		input = "e|---";
		testNoteArray.clear();
		testNoteArray.add(input);
		guitarNotes = Main.guitarNoteParser(testNoteArray);
		assertEquals(0, guitarNotes.size());

	}

	/**
	 * Given a line of drum notes, the function will store these notes in an array
	 * Covers the case where a valid drum line is given, and where an invalid one is
	 * given
	 */
	@Test
	public void drumNoteParser() {

		String input = "HH|--x-x-x-x-x-x-x-|----------------|";
		ArrayList<String> testNoteArray = new ArrayList<>();
		testNoteArray.add(input);

		ArrayList<DrumNote> drumNotes = Main.drumNoteParser(testNoteArray);
		assertEquals(7, drumNotes.size());
		assertEquals('x', drumNotes.get(0).noteValue);

		input = "HH|---";
		testNoteArray.clear();
		testNoteArray.add(input);
		drumNotes = Main.drumNoteParser(testNoteArray);
		assertEquals(0, drumNotes.size());

	}

	/**
	 * Given an array of measures, will write the xml output to a file. Tests covers
	 * two cases, one where a valid input is given, and where a non-valid input is
	 * given.
	 */
	@Test
	public void guitarXMLParser() {
		GuitarNote guitarNote = new GuitarNote(1, 1, 1, "2");
		guitarNote.setMusicNote();
		guitarNote.harmonic = true;
		guitarNote.grace = true;
		guitarNote.chord = true;
		guitarNote.pull = true;
		guitarNote.hammer = true;
		Main.guitar = 1;
		Measure measure = new Measure(1, 1);
		measure.addGuitarNotes(guitarNote);
		ArrayList<Measure> measures = new ArrayList<>();
		measures.add(measure);
		assertNotNull(Main.guitarXMLParser(measures));
		Main.guitar = 0;
		assertNotNull(Main.guitarXMLParser(measures));
		measures.clear();
		assertNull(Main.guitarXMLParser(measures));

		String xml = null;
		String path = "src/main/java/EECS2311_Project/tabs/inlineRepeatExample.txt";
		try {
			Object[] notes = Main.fileParser(path);
			ArrayList<String> noteArray = (ArrayList<String>) notes[1];
			if (notes[0] == "guitar") {
				ArrayList<GuitarNote> guitarNoteArray = Main.guitarNoteParser(noteArray);
				for (GuitarNote guitarNote1 : guitarNoteArray) {
					// System.out.println("setting music note");
					if (Main.guitar == 1) {
						guitarNote1.bass = true;
					}
					try {
						guitarNote1.setMusicNote();
					} catch (Exception exception) {
						// do nothing
					}
				}
				ArrayList<Measure> measureArrayList = new ArrayList<>();
				for (int i = 0; i < Main.measuresElement.size(); i++) {
					Measure measure1 = new Measure(Main.measuresElement.get(i + 1), i + 1);
					for (GuitarNote guitarNote1 : guitarNoteArray) {
						if (guitarNote1.measure == i + 1) {
							measure1.addGuitarNotes(guitarNote1);
						}
					}
					measureArrayList.add(measure1);
				}
				for (Measure measure1 : measureArrayList) {
					measure1.sortNotes();
					measure1.processDuration();
					measure1.setNoteType();
					// System.out.println(measure);
				}
				xml = Main.guitarXMLParser(measureArrayList);
			}
		} catch (Exception e) {
			// do nothing
		}
		assertNotNull(xml);
	}

	/**
	 * Given an array of drumNotes, will write the xml output to a file. Tests
	 * covers two cases, one where a valid input is given, and where a non-valid
	 * input is given.
	 */
	@Test
	public void drumXMLParser() {
		String input = "BD|--x-x-x-o-x-x-x-|--------x-------|";
		ArrayList<String> testNoteArray = new ArrayList<>();
		testNoteArray.add(input);

		ArrayList<DrumNote> drumNotes = Main.drumNoteParser(testNoteArray);
		assertNotNull(Main.drumXMLParser(drumNotes));

		drumNotes.clear();
		assertNull(Main.drumXMLParser(drumNotes));
	}

	/**
	 * Function to start the parser, will throw an exception if the wrong file is
	 * given. Covers the case where an exception will be thrown
	 */
	@Test
	public void start() {
		String path = "src/main/java/EECS2311_Project/example2.exe";
		assertThrows(FileNotFoundException.class, () -> {
			Main.start(path, "Test", "Bach");
		});
	}

	/**
	 * Function to check if the robot is pressing in the correct area declared in
	 * the GuiWelcome class testing for bounds of the 'Start' button
	 * 
	 * @throws AWTException
	 */
	@Test
	public void buttonClick() throws AWTException {
		boolean result = false;
		Robot bot = new Robot();
		GuiWelcome gW = new GuiWelcome();
		int w = gW.getWidth();
		int h = gW.getHeight();

		bot.mouseMove(w, h);
		if (w == 358 && h == 575) {
			try {
				bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				Thread.sleep(250);
				bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				result = true;
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}

		assertEquals(true, result);
	}

}
