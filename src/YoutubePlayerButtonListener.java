//************************************************************************
// File: YoutubePlayerButtonListener.java     
// 
// Author: EJ, Pranav, Maggie, and Jhon
//
// Class: YoutubePlayerButtonListener
// Dependencies: YoutubePlayerSwing, ActionLIstener
//
// Description:
// Creates the listener class for buttons in YoutubePlayerSwing
//************************************************************************
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class YoutubePlayerButtonListener extends YoutubePlayerSwing implements ActionListener {
    //inits variables needed for listeners
    private String buttonName;//button name
    private JTextArea  artist;//user inputted artist
    private JTextArea  song; //user inputted song

    public YoutubePlayerButtonListener(JButton button, JTextArea artistAnswer, JTextArea songAnswer) {//constructor
        buttonName = button.getText();//grabs button name to be used later
        artist = artistAnswer;// sets artist name
        song = songAnswer; //sets song name
    }//end of YTButtonListener

    @Override
    public void actionPerformed(ActionEvent e) {//deals with button pushes
        if (buttonName.equals("Start")) {//if start is pressed it will:
            //grab neccesary user input
            artistName = artist.getText();
            songTitle = song.getText();
            //create a song object
            Song currentlyPlaying = new Song(artistName, songTitle);

            //run the start method with that new song information
            start(artistName,songTitle,currentlyPlaying.lyrics,currentlyPlaying.url, currentlyPlaying.youtubelink);
        }
        if (buttonName.equals("Home")){//if home button is pressed
            YoutubePlayerSwing.clear();//it will clear panel
            MainSceneSwing.completeRun();//and return home
        }
    }

}//end of class

