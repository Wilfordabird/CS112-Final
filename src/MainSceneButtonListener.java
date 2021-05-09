//************************************************************************
// File: MainSceneButtonListener.java      
// 
// Author: EJ, Pranav, Maggie, and Jhon
//
// Class: MainSceneButtonListener
// Dependencies: MainSceneSwing, ActionListener
//
// Description:
// Button listener for the buttons in main scene. Checks when to end the program
//************************************************************************

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class MainSceneButtonListener extends MainSceneSwing implements ActionListener {
    // Used to check which button is pressed
    private String buttonName;

    // constructor to get the name of the button
    public MainSceneButtonListener(JButton diagnosticTest) {
        buttonName = diagnosticTest.getText();
    }

    // provides information of what to do when a given button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (buttonName.equals("Diagnostic Test")) {
            diagnosticTestButtonCounter++;
            if (diagnosticTestButtonCounter == 2) { // if a path is pressed twice, end the program
                System.out.println(
                        "Error: You did not follow the instructions and tried to do one path twice. Please follow the instructions.");
                System.exit(1);
            }
            frame.setVisible(false);
            runDiagnosticTest();
        } else if (buttonName.equals("Import Music")) {
            importMusicButtonCounter++;
            if (importMusicButtonCounter == 2) { // if a path is pressed twice, end the program
                System.out.println(
                        "Error: You did not follow the instructions and tried to do one path twice. Please follow the instructions.");
                System.exit(1);
            }
            runImportMusic();
        } else {
            lyricAnalysisButtonCounter++;
            if (lyricAnalysisButtonCounter == 2) { // if a path is pressed twice, end the program
                System.out.println(
                        "Error: You did not follow the instructions and tried to do one path twice. Please follow the instructions.");
                System.exit(1);
            }
            runLyricAnalysis();
        }
    }
} // end of class MainSceneButtonListener
