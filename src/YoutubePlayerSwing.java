//************************************************************************
// File: YoutubePlayerSwing.java     
// 
// Author: EJ, Pranav, Maggie, and Jhon
//
// Class: YoutubePlayerSwing
// Dependencies: MainSceneSwing JavaFX YoutubeButtonListener.
//
// Description:
// Class that creates the path to run the youtube music player
//************************************************************************
import java.awt.*;
import javax.swing.*;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import java.awt.event.*;
import java.awt.Color;

public class YoutubePlayerSwing extends MainSceneSwing {

    public static JFrame frameTwo = new JFrame();
    public static JPanel topPanel = new JPanel();
    public static JPanel secondPanel = new JPanel();
    public static String songTitle;
    public static String artistName;

    public static void runSimulation() {//acts as main and creates the initial scene
        setUp();
    }

    public static void topPanel() {//adds application title to the screen
        topPanel.setLayout(new FlowLayout());
        JLabel title = new JLabel("Not-ify", JLabel.CENTER);
        title.setForeground(Color.BLUE);
        title.setFont(Title);
        topPanel.add(title);
    }

    public static void secondPanel() {//adds the title of the screen as well as some disclaimers
        secondPanel.setLayout(new FlowLayout());
        JLabel subtitle = new JLabel("YouTube Music Player", JLabel.CENTER);
        subtitle.setFont(Text);
        secondPanel.add(subtitle);
        JLabel disclaimer = new JLabel(
                "We may place ads before your music so that we can generate some revenue. Please note that we are starving students.",
                JLabel.CENTER);
        disclaimer.setFont(DisclaimerText);
        secondPanel.add(disclaimer);
        JLabel disclaimer2 = new JLabel(
                "If your lyrics do not come up, try running the program again and ensure that you type the words with no extra spaces or errors",
                JLabel.CENTER);
        disclaimer2.setFont(DisclaimerText);
        secondPanel.add(disclaimer2);

    }

    public static void setUp() {//creates initial screen that takes in user input
        //creates frame and sets layouts
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setTitle("Not-ify");
        frame.setLayout(new GridLayout(4, 1));

        //initializes the top half of the screen
        topPanel();
        secondPanel();

        //Sets up user input panel
        JPanel thirdPanel = new JPanel();
        secondPanel.setLayout(new GridLayout(4, 1));
        thirdPanel.setLayout(new GridLayout(4, 1));
        JTextArea artist = new JTextArea("Please enter the artist of the song: ");
        JTextArea artistAnswer = new JTextArea("");// **User enters artist name*/
        JTextArea song = new JTextArea("Please enter the song of your choice: ");
        JTextArea songAnswer = new JTextArea("");// **TODO Uncheck */

        artist.setEditable(false);
        artistAnswer.setEditable(true);
        song.setEditable(false);
        songAnswer.setEditable(true);

        //compiles the third panel
        thirdPanel.add(song);
        thirdPanel.add(songAnswer);
        thirdPanel.add(artist);
        thirdPanel.add(artistAnswer);

        //adds button that runs start
        JButton start = new JButton("Start");
        ActionListener startListener = new YoutubePlayerButtonListener(start, artistAnswer, songAnswer);
        start.setForeground(Color.GREEN);
        start.setFont(Subtitle);
        start.addActionListener(startListener);

        //adds all to the frame
        frame.add(topPanel);
        frame.add(secondPanel);
        frame.add(thirdPanel);
        frame.add(start);

        frame.setVisible(true);
    }

    public static void start(String artistName, String song, String lyrics, String url, String youtubeLink) {
        // Set up screen
        frameTwo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameTwo.setSize(new Dimension(WIDTH, HEIGHT));
        frameTwo.setTitle("Not-ify");
        frameTwo.setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();//sets up first panel
        leftPanel.setLayout(new GridLayout(2, 1));

        JPanel buttonPanel = new JPanel(new FlowLayout());//sets up button layout

        //adds a label that shows what song is currently being played
        JLabel nowPlaying = new JLabel("Currently Playing: " + song + " by " + artistName, JLabel.CENTER);
        nowPlaying.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        buttonPanel.add(nowPlaying);

        //adds button that redirects to home
        JButton reset = new JButton("Home");
        ActionListener resetListener = new DiagnosticTestButtonListener(reset);
        reset.addActionListener(resetListener);
        buttonPanel.add(reset);

        //adds scrollable lyrics
        JTextArea scrollLyrics = new JTextArea(lyrics);
        scrollLyrics.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        scrollLyrics.setEditable(false);
        JScrollPane scroll = new JScrollPane(scrollLyrics);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.getViewport().setView(scrollLyrics);

        leftPanel.add(buttonPanel);
        leftPanel.add(scroll);
        //creates the actual player panel to be put in the background of the frame that plays music
        JFXPanel youtubePlayer = new JFXPanel();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // Youtube panel
                WebEngine engine;//inits webengine used to look up yt video

                WebView view = new WebView();
                engine = view.getEngine();//the engine is initialized as what the user has set as their main engine

                engine.load(youtubeLink);//opens the link and begins playying

                youtubePlayer.setScene(new Scene(view));
            }
        });
        //adds everything to the frame
        frameTwo.add(leftPanel, BorderLayout.CENTER);
        frameTwo.add(youtubePlayer, BorderLayout.SOUTH);

        youtubePlayer.setVisible(false);//sends youtube video to the background
        frameTwo.setVisible(true);

    }

    public static void clear() {//clears all screens
        frame.removeAll();
        
        frameTwo.removeAll();
       
    }

}
