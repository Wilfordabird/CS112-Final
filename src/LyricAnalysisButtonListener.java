//************************************************************************
// File: LyricAnalysisButtonListener.java     
// 
// Author: EJ, Pranav, Maggie, and Jhon
//
// Class: LyricAnalysisButtonListener
// Dependencies: LyricAnalyisSwing, ActionLIstener
//
// Description:
// Creates the listener class for buttons in LyricAnalyisSwing
//************************************************************************

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class LyricAnalysisButtonListener extends LyricAnalyisSwing implements ActionListener {
    private String buttonName;//name of created button
    private JTextArea  artist; //inputted artist name
    private JTextArea  song; //inputted song name

    public LyricAnalysisButtonListener(JButton button,JTextArea artistAnswer, JTextArea songAnswer) {//constructs button and initializes variables
        buttonName = button.getText();
        artist = artistAnswer;
        song = songAnswer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {//deals with button pushes
        if (buttonName.equals("Start")) {//when start is pushed
            //it will grab user input
            artistName = artist.getText();
            songTitle = song.getText();

            //create a new song object
            Song currentlyPlaying = new Song(artistName, songTitle);
            //and run start method with given song info
            start(artistName,songTitle,currentlyPlaying.lyrics, currentlyPlaying.genre);
        }
        if (buttonName.equals("Home")) {//if home is pressed
            MainSceneSwing.completeRun();//it will run the home panel
            
        }
    }
} // end of class LyricAnalysisButtonListener
