//************************************************************************
// File: DiagnosticTestButtonListener.java     
// 
// Author: EJ, Pranav, Maggie, and Jhon
//
// Class: DiagnosticTestButtonListener
// Dependencies: DiagnosticTestSwing, ActionListener
//
// Description:
// Creates the listener class for buttons in DiagnosticTestSwing
//************************************************************************

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class DiagnosticTestButtonListener extends DiagnosticTestSwing implements ActionListener {
    // Name of the button
    private String buttonName;

    //Constructor gets name of the button
    public DiagnosticTestButtonListener(JButton button) {
        buttonName = button.getText();
    }

    // Paths for when each button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (buttonName.equals("Start")) { //Runs start() when start is pressed
            start();
        } else if (buttonName.equals("Like")) { // if song is liked, run genreCounter(), increment songsLeft, and run nextSong()
            if (songsLeft != 1) {
                genreCounter();
                mediaPlayers[15 - songsLeft].pause();
                songsLeft--;
                nextSong();
        
            } else { // If there are no songs left, run results
                results();
                mediaPlayers[14].pause();
            }


        } else if (buttonName.equals("Dislike")) { // if song is dislikedincrement songsLeft and run nextSong()
            if (songsLeft != 1) {
                mediaPlayers[15 - songsLeft].pause();
                songsLeft--;
                nextSong();
            } else { // If there are no songs left, run results
                results();
                mediaPlayers[14].pause();
            }
        } else if (buttonName.equals("Home")){ // Run the entire program again if "home" is pressed
            completeRun();
        }
    }

}//end of class DiagnosticTestButtonListene
