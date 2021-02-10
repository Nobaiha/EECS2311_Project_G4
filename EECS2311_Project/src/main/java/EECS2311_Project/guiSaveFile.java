package main.java.EECS2311_Project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class guiSaveFile extends JFrame {

    public File guiSaveFile(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("guitarTab.musicxml"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("musicxml file","musicxml"));
        int userSelection = fileChooser.showSaveDialog(guiSaveFile.this);
        File file = null;
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            setVisible(false);
        }
        return file;
    }
}
