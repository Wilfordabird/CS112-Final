//************************************************************************
// File: LyricAnalysisSwing.java   
// 
// Author: EJ, Pranav, Maggie, and Jhon
//
// Class: LyricAnalysisSwing
// Dependencies: MainSceneSwing
//
// Description:
// Creates the third path that the user can choose. Lets the user input a song
// name and outputs some predicted genres for it as well as recommended songs
//************************************************************************

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.util.Scanner;

public class LyricAnalyisSwing extends MainSceneSwing {
    // variables to count matching words
    public static int wordCountPop;
    public static int wordCountCountry;
    public static int wordCountRock;
    public static int wordCountRap;

    public static String genre;

    public static String[] wordCounts;

    //Song recommendations
    public static String popRecs;
    public static String rapRecs;
    public static String rockRecs;
    public static String countryRecs;
    public static String classicalRecs;

    // Frames and panels used to create display
    public static JFrame frameTwo = new JFrame();
    public static JPanel topPanel = new JPanel();
    public static JPanel secondPanel = new JPanel();
    public static String songTitle;
    public static String artistName;
    public static String lyrics = "";

    // runs this entire path
    public static void runSimulation() {
        setUp();
    }

    //Creates top panel 
    public static void topPanel() {
        topPanel.setLayout(new FlowLayout());
        JLabel title = new JLabel("Not-ify", JLabel.CENTER);
        title.setForeground(Color.BLUE);
        title.setFont(Title);
        topPanel.add(title);
    }

    // Creates second panel
    public static void secondPanel() {
        secondPanel.setLayout(new FlowLayout());
        JLabel subtitle = new JLabel("Lyric Analyzer", JLabel.CENTER);
        subtitle.setFont(Text);
        secondPanel.add(subtitle);
        
        JLabel disclaimer2 = new JLabel(
                "If your lyrics do not come up, try running the program again and ensure that you type the words with no extra spaces or errors",
                JLabel.CENTER);
        disclaimer2.setFont(DisclaimerText);
        secondPanel.add(disclaimer2);

    }

    // sets up the initial screen
    public static void setUp() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setTitle("Not-ify");
        frame.setLayout(new GridLayout(4, 1));

        topPanel();
        secondPanel();

        // Takes in user input
        JPanel thirdPanel = new JPanel();
        secondPanel.setLayout(new GridLayout(4, 1));
        thirdPanel.setLayout(new GridLayout(4, 1));
        JTextArea artist = new JTextArea("Please enter the artist of the song: ");
        JTextArea artistAnswer = new JTextArea();
        JTextArea song = new JTextArea("Please enter the song of your choice: ");
        JTextArea songAnswer = new JTextArea();

        artist.setEditable(false);
        artistAnswer.setEditable(true);
        song.setEditable(false);
        songAnswer.setEditable(true);

        thirdPanel.add(song);
        thirdPanel.add(songAnswer);
        thirdPanel.add(artist);
        thirdPanel.add(artistAnswer);

        JButton start = new JButton("Start");
        ActionListener startListener = new LyricAnalysisButtonListener(start, artistAnswer, songAnswer);
        start.setForeground(Color.GREEN);
        start.setFont(Subtitle);
        start.addActionListener(startListener);

        frame.add(topPanel);
        frame.add(secondPanel);
        frame.add(thirdPanel);
        frame.add(start);

        frame.setVisible(true);
    }

    // Runs once the user presses start. Returns the lyrics, possible genres, and suggested songs
    public static void start(String artistName, String song, String lyrics, String geniusGenre) {
        JFrame frameTwo = new JFrame();
        frameTwo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameTwo.setSize(new Dimension(WIDTH, HEIGHT));
        frameTwo.setTitle("Not-ify");
        frameTwo.setLayout(new GridLayout(3, 1));

        JPanel topOfScreen = new JPanel();
        topOfScreen.setLayout(new FlowLayout());
        JLabel title = new JLabel("Not-ify", JLabel.CENTER);
        title.setForeground(Color.BLUE);
        title.setFont(Title);
        topOfScreen.setPreferredSize(new Dimension(WIDTH, 40));

        topOfScreen.add(title);

        JPanel secondComponent = new JPanel();
        secondComponent.setLayout(new FlowLayout());
        JLabel subtitle = new JLabel("Lyric Analyzer", JLabel.CENTER);
        subtitle.setFont(Text);
        secondComponent.add(subtitle);

        JPanel thirdPanel = new JPanel();
        secondPanel.setLayout(new GridLayout(4, 1));

        thirdPanel.setLayout(new GridLayout(1, 2));

        JPanel thirdRightPanel = new JPanel();
        // right side of third panel
        thirdRightPanel.setLayout(new FlowLayout());

        String genre = genre(lyrics);

        String genreAnal = "Our analysis predicts the genre to be: " + genre;
        String fakeGenreAnal = "Genius and their fake system considers this a " + geniusGenre + " song";

        JLabel GeniusGenreLabel = new JLabel(fakeGenreAnal, JLabel.CENTER);
        JLabel GenrePrediction = new JLabel(genreAnal, JLabel.CENTER);

        thirdRightPanel.add(GeniusGenreLabel);
        thirdRightPanel.add(GenrePrediction);

        JLabel Recommendation = new JLabel("We recommend that you listen to:", JLabel.CENTER);
        thirdRightPanel.add(Recommendation);

        JTextArea songrecs = new JTextArea(songRecs(genre));
        songrecs.setEditable(false);

        thirdRightPanel.add(songrecs);

        JButton home = new JButton("Home");
        ActionListener homeListener = new DiagnosticTestButtonListener(home);
        home.setFont(Subtitle);
        home.addActionListener(homeListener);

        thirdRightPanel.add(home);

        // left side of panel
        JTextArea scrollLyrics = new JTextArea(lyrics);
        scrollLyrics.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        scrollLyrics.setEditable(false);
        JScrollPane scroll = new JScrollPane(scrollLyrics);

        thirdPanel.add(scroll);
        thirdPanel.add(thirdRightPanel);

        frameTwo.add(topOfScreen);
        frameTwo.add(secondComponent);
        frameTwo.add(thirdPanel);

        frameTwo.setVisible(true);
    }

    // Our own genre analysis method. We scrape genre from Genius in Song.java
    public static String genre(String lyrics) {
        // counts how many times the most popular words of each genre are used in the
        // lyrics
        Scanner console = new Scanner(lyrics);
        String[] wordsInlyrics = lyrics.split(" ");

        // counting popular pop words used
        String[] wordsPop = { "love", "oh", "la", "got", "feel", "let", "down", "never", "yeah", "away" }; // most
                                                                                                           // popular
                                                                                                           // pop words
        for (int i = 0; i < wordsPop.length; i++) {
            for (int j = 0; j < wordsInlyrics.length; j++) {
                if (wordsInlyrics[j].equalsIgnoreCase(wordsPop[i]) || wordsInlyrics[j].contains(wordsPop[i])) {
                    wordCountPop++;
                }
            }
        }

        String[] wordsRap = { "got", "la", "caus", "ya", "down", "shit", "man", "let", "love", "fuck" }; // most popular
                                                                                                         // rap words
        for (int i = 0; i < wordsRap.length; i++) {
            for (int j = 0; j < wordsInlyrics.length; j++) {
                if (wordsInlyrics[j].equalsIgnoreCase(wordsRap[i]) || wordsInlyrics[j].contains(wordsRap[i])) {
                    wordCountRap++;
                }
            }
        }

        // // counting popular rock words used
        String[] wordsRock = { "love", "down", "oh", "never", "got", "feel", "let", "away", "yeah", "life" }; // most
                                                                                                              // popular
                                                                                                              // rock
                                                                                                              // words
        for (int i = 0; i < wordsRock.length; i++) {
            for (int j = 0; j < wordsInlyrics.length; j++) {
                if (wordsInlyrics[j].equalsIgnoreCase(wordsRock[i]) || wordsInlyrics[j].contains(wordsRock[i])) {
                    wordCountRock++;
                }
            }
        }

        // // counting popular country words used
        wordCountCountry = 0;
        String[] wordsCountry = { "yeah", "girl", "baby", "love", "little", "jeans", "trucks", "beer", "oh", "wanna" }; // most
                                                                                                                        // popular
                                                                                                                        // pop
                                                                                                                        // words
        for (int i = 0; i < wordsCountry.length; i++) {
            for (int j = 0; j < wordsInlyrics.length; j++) {
                if (wordsInlyrics[j].equalsIgnoreCase(wordsCountry[i]) || wordsInlyrics[j].contains(wordsCountry[i])) {
                    wordCountCountry++;
                }
            }
        }
        console.close();

        // creating an array with each of the wordCounts by genre
        int[] wordCounts = { wordCountPop, wordCountRap, wordCountRock, wordCountCountry };

        // looks through wordCounts array to see which genre's wordCount is the highest
        int temp = 0;
        int genreInt = 0;
        for (int i = 0; i < wordCounts.length; i++) {
            if (wordCounts[i] > temp) {
                temp = wordCounts[i];
                genreInt = i;
            }
        }

        int sum = 0;
        // the wordCount with the highest value is the genre returned
        for (int i = 0; i < wordCounts.length; i++) {
            sum += wordCounts[i];
        }

        if (sum == 0) {
            return "Classical";
        }

        if (genreInt == 0) {
            genre = "Pop";
            return genre;
        } else if (genreInt == 1) {
            genre = "Rap";
            return genre;
        } else if (genreInt == 2) {
            genre = "Rock";
            return genre;
        } else {
            genre = "Country";
            return genre;
        }
    }

    // Song recommendations created based on the genre that was returned
    public static String songRecs(String genre) {
        // Give us a rec based on the genre
        // lists of song recommendations based on genre
        popRecs = "La La Land\nMagic in the Hamptons\nCloser";
        rapRecs = "Lose Yourself\nSix Foot Seven Foot\nHomicide";
        rockRecs = "Livin on a Prayer\n Brown Eyed Girl\nSweet Home Alabama";
        countryRecs = "Body Like A Back Road\nTake Me Home Country Roads\nWagon Wheel";
        classicalRecs = "New World March\nNocturne In E Flat Major\nArabesque No. One";

        // returns a list of recommended songs in the same genre as the inputted song
        if (genre.equals("Pop")) {
            return popRecs;
        } else if (genre.equals("Rap")) {
            return rapRecs;
        } else if (genre.equals("Rock")) {
            return rockRecs;
        } else if (genre.equals("Country")) {
            return countryRecs;
        } else {
            return classicalRecs;
        }
    }
} // End of class LyricAnalysisSwing