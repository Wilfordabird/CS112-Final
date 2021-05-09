//************************************************************************
// File: MainSceneSwing.java      
// 
// Author: EJ, Pranav, Maggie, and Jhon
//
// Class: MainSceneSwing
// Dependencies: Application
//
// Description:
// This is the main control class that runs our entire program
//************************************************************************

import java.awt.*;
import javax.swing.*;
import javafx.application.Application;
import javafx.stage.Stage;
import java.awt.event.*;

public class MainSceneSwing extends Application {

    // Create fonts to use for rest of program
    public static Font Title = new Font("Arial", Font.BOLD, 60);
    public static Font Subtitle = new Font("Arial", Font.PLAIN, 32);
    public static Font Text = new Font("Avenir", Font.ROMAN_BASELINE, 25);
    public static Font ButtonText = new Font("Avenir", Font.BOLD, 17);
    public static Font DisclaimerText = new Font("Avenir", Font.ITALIC, 13);

    // set size of window
    public static final int WIDTH = 800;
    public static final int HEIGHT = 500;

    // make a new frame and counters to check when to end the program
    public static JFrame frame = new JFrame();
    public static int diagnosticTestButtonCounter = 0;
    public static int importMusicButtonCounter = 0;
    public static int lyricAnalysisButtonCounter = 0;

    public static void main(String[] args) {
        completeRun();
    }

    // Method to run the entire program
    public static void completeRun() {
        JPanel centerPanel = new JPanel();
        init(frame, centerPanel);
        instructionAndButtons(frame, centerPanel);
        frame.setVisible(true);
    }

    // creates the initial screen
    public static void init(JFrame frame, JPanel centerPanel) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setTitle("Not-ify");
        frame.setLayout(new BorderLayout());
        centerPanel.setLayout(new GridLayout(3, 1));
        JLabel title = new JLabel("Not-ify", JLabel.CENTER);
        title.setForeground(Color.BLUE);
        title.setFont(Title);
        title.setVerticalAlignment(JLabel.CENTER);
        centerPanel.add(title);
    }

    // draws the instructions and makes the buttons
    public static void instructionAndButtons(JFrame frame, JPanel centerPanel) {
        JLabel instruction = new JLabel("Please select the path you would like to take: ", JLabel.CENTER);
        instruction.setFont(Text);
        instruction.setVerticalAlignment(JLabel.TOP);
        centerPanel.add(instruction);

        JLabel instruction2 = new JLabel(
                "Note: You may only click a given path one time. Please do not click a single path twice.",
                JLabel.CENTER);
        instruction2.setFont(DisclaimerText);
        instruction2.setVerticalAlignment(JLabel.TOP);
        centerPanel.add(instruction2);

        frame.add(centerPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new FlowLayout());
        JButton diagnosticTest = new JButton("Diagnostic Test");
        JButton importMusic = new JButton("Import Music");
        JButton lyricAnalysis = new JButton("Lyric Analysis");

        ActionListener diagnosticListener = new MainSceneButtonListener(diagnosticTest);
        ActionListener importListener = new MainSceneButtonListener(importMusic);
        ActionListener randomListener = new MainSceneButtonListener(lyricAnalysis);
        diagnosticTest.addActionListener(diagnosticListener);
        importMusic.addActionListener(importListener);
        lyricAnalysis.addActionListener(randomListener);

        southPanel.add(diagnosticTest);
        southPanel.add(importMusic);
        southPanel.add(lyricAnalysis);

        frame.add(southPanel, BorderLayout.SOUTH);
    }

    // Methods created to run each of the three paths from the button listener

    public static void runDiagnosticTest() {
        DiagnosticTestSwing.runSimulation();
    }

    public static void runImportMusic() {
        YoutubePlayerSwing.runSimulation();
    }

    public static void runLyricAnalysis() {
        LyricAnalyisSwing.runSimulation();
    }

    // required method from abstract class Application
    @Override
    public void start(Stage arg0) throws Exception {
    }
} //End of class MainSceneSwing
