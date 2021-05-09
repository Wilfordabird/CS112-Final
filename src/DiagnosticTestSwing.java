//************************************************************************
// File: DiagnosticTestSwing.java     
// 
// Author: EJ, Pranav, Maggie, and Jhon
//
// Class: DiagnosticTestSwing
// Dependencies: MainSceneSwing. *REQUIRES SONG FILES (in .wav) AND Song.txt TO RUN*
//
// Description:
// Class that creates the path to run the diagnostic test
//************************************************************************

import java.awt.*;
import javax.swing.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.awt.event.*;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DiagnosticTestSwing extends MainSceneSwing {
    public static String[][] songs; // 2D array of song file names and their genre
    public static int N; // number of songs in the file
    public static boolean isClicked, isClicked2 = false;
    public static int[] genresLiked = { 0, 0, 0, 0, 0 }; // Order: Pop, Country, Classical, Rock, Rap
    public static int songsLeft;

    // Creates the new fames and some panels that need to be used in this class
    public static JFrame frameTwo = new JFrame();
    public static JPanel topPanel = new JPanel();
    public static JPanel secondPanel = new JPanel();
    public static JButton like = new JButton("Like");
    public static JButton dislike = new JButton("Dislike");
    private static JLabel songsLeftLabel;
    public static MediaPlayer mediaPlayers[];
    public static Media hits[];

    // Method that runs the diagnostic test. Called in the main file
    public static void runSimulation() {
        readFile();
        setUp();
    }

    // Creates the top panel
    public static void topPanel() {
        topPanel.setLayout(new FlowLayout());
        JLabel title = new JLabel("Not-ify", JLabel.CENTER);
        title.setForeground(Color.BLUE);
        title.setFont(Title);
        topPanel.add(title);
    }

    // Creates the second panel
    public static void secondPanel() {
        secondPanel.setLayout(new FlowLayout());
        JLabel subtitle = new JLabel("Diagnostic Test", JLabel.CENTER);
        subtitle.setFont(Text);
        secondPanel.add(subtitle);
    }

    // creates the "like" button
    public static void like() {
        like.setText("Like");
        like.setBackground(Color.BLACK);
        like.setForeground(Color.GREEN);
    }

    // creates the "dislike" button
    public static void dislike() {
        dislike.setText("Dislike");
        dislike.setBackground(Color.BLACK);
        dislike.setForeground(Color.RED);
    }

    // Setup the inital screen with instructions and a start button
    public static void setUp() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setTitle("Not-ify");
        frame.setLayout(new GridLayout(4, 1));

        topPanel();
        secondPanel();

        // Write the instructions
        JPanel thirdPanel = new JPanel();
        secondPanel.setLayout(new GridLayout(4, 1));
        JTextArea instructions1 = new JTextArea(
                "Instructions: You will be played samples of various songs. You will have the option ");
        JTextArea instructions2 = new JTextArea(
                "to click \"like\" or \"dislike\" on each of the songs. You do not have to listen to ");
        JTextArea instructions3 = new JTextArea(
                "the entire song - simply press the button when you are ready. At the end of the test, ");
        JTextArea instructions4 = new JTextArea(
                "we will suggest one or a few genres that you might like. Press start to begin!");

        instructions1.setEditable(false);
        instructions2.setEditable(false);
        instructions3.setEditable(false);
        instructions4.setEditable(false);

        thirdPanel.add(instructions1);
        thirdPanel.add(instructions2);
        thirdPanel.add(instructions3);
        thirdPanel.add(instructions4);

        // Create "Start" button
        JButton start = new JButton("Start");
        ActionListener startListener = new DiagnosticTestButtonListener(start);
        start.setForeground(Color.GREEN);
        start.setFont(Subtitle);
        start.addActionListener(startListener);

        frame.add(topPanel);
        frame.add(secondPanel);
        frame.add(thirdPanel);
        frame.add(start);

        frame.setVisible(true);
    }

    // Used to read Songs.txt to get the genre and file names of the songs used in
    // the test
    public static void readFile() {
        Scanner console = null;
        try {
            File f = new File("Songs.txt");
            console = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file.");
            System.exit(1);
        }

        N = console.nextInt();
        songs = new String[N][2];
        songsLeft = N;

        for (int i = 0; i < N; i++) {
            songs[i][0] = console.next();
            songs[i][1] = console.next();
        }
    }

    // Creates the screen and starts playing music after "start" is pressed
    public static void start() {
        // Set up screen
        frameTwo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameTwo.setSize(new Dimension(WIDTH, HEIGHT));
        frameTwo.setTitle("Not-ify");
        frameTwo.setLayout(new GridLayout(5, 1));

        songsLeftLabel = new JLabel("Songs Left: " + songsLeft, JLabel.CENTER);

        JPanel options = new JPanel(new FlowLayout());
        like();
        dislike();
        options.add(like);
        options.add(dislike);
        
        // create and add action listeners to the "like" an "dislike" buttons
        ActionListener likeListener = new DiagnosticTestButtonListener(like);
        ActionListener dislikeListener = new DiagnosticTestButtonListener(dislike);

        like.addActionListener(likeListener);
        dislike.addActionListener(dislikeListener);

        frameTwo.add(topPanel);
        frameTwo.add(secondPanel);
        frameTwo.add(songsLeftLabel);
        frameTwo.add(options);
        frameTwo.setVisible(true);

        // Create Media and Media players for each of the songs that are being played
        hits = new Media[N];
        for (int i = 0; i < N; i++) {
            hits[i] = new Media(new File(songs[i][0]).toURI().toString());
        }

        mediaPlayers = new MediaPlayer[N];
        for (int i = 0; i < N; i++) {
            mediaPlayers[i] = new MediaPlayer(hits[i]);
        }
        // play music for the first song
        mediaPlayers[0].play();
    }

    // Once like or dislike is pressed, this increments the song that is being played
    public static void nextSong() {
        mediaPlayers[15 - songsLeft].play();
        songsLeftLabel.setText("Songs left: " + songsLeft);
    }

    // Run after songs to sum the likes and dislikes. Returns the genres that the user prefers
    public static String tally() {
        int max = 0;
        String songReturn = "";
        for (int i = 0; i < 5; i++) {
            if (genresLiked[i] > max) {
                max = genresLiked[i];
            }
        }

        for (int index = 0; index < 5; index++) {
            if (genresLiked[index] == max) {
                if (index == 0) {
                    songReturn += "Pop, ";
                } else if (index == 1) {
                    songReturn += "Country, ";
                } else if (index == 2) {
                    songReturn += "Classical, ";
                } else if (index == 3) {
                    songReturn += "Rock, ";
                } else if (index == 4) {
                    songReturn += "Rap, ";
                }
            }
        }
        String substring = songReturn.substring(0, songReturn.length() - 2);

        return substring;

    }

    // Used to display the results to the user and create a "home" button 
    public static void results() {
        like.setVisible(false);
        dislike.setVisible(false);
        songsLeftLabel.setText("Songs left: 0. You're done!");

        JPanel end = new JPanel(new FlowLayout());

        JLabel results = new JLabel("Based on our analysis, we think you would like the following genre(s): " + tally(),
                JLabel.CENTER);
        results.setAlignmentX(JTextArea.CENTER_ALIGNMENT);

        JButton home = new JButton("Home");
        ActionListener homeListener = new DiagnosticTestButtonListener(home);
        home.addActionListener(homeListener);

        end.add(results);
        end.add(home);

        frameTwo.add(end);
    }

    // run IF the song is liked to see which genre to add to
    public static void genreCounter() {
        int index = 15 - songsLeft;
        if (songs[index][1].equals("Pop")) {
            genresLiked[0]++;
        } else if (songs[index][1].equals("Country")) {
            genresLiked[1]++;
        } else if (songs[index][1].equals("Classical")) {
            genresLiked[2]++;
        } else if (songs[index][1].equals("Rock")) {
            genresLiked[3]++;
        } else if (songs[index][1].equals("Rap")) {
            genresLiked[4]++;
        }
    }
} // end of class DiagnosticTestSwing
