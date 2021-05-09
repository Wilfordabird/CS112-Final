
//************************************************************************
// File: Song.java
// 
// Author: EJ, Pranav, Maggie, Jhon
//
// Class: Song
// Dependencies: JavaFX 
//
// Description:
// This is a file that creates a song object and proceeds to scrape data,
// find urls, and picks a genre for that song. It does this through a variety
// of things but most is using jsoup libraries. 
//************************************************************************
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Song {
    public String artist;// artist of song
    public String song; // song name
    public String url;// genius url
    public String lyrics;// lyrics that are scraped from genius
    public String youtubelink;// link to the youtube video
    public String videoTitle; // **TODO
    public String genre;// the genius genre for the song inputted

    public Song(String artistName, String songName) {// creates the object and initializes it
        this.artist = artistName;
        this.song = songName;
        this.url = getUrl(artistName, songName);
        this.lyrics = lyrics(url);
        // geniusSearch(artistName, songName);
        this.genre = genreFinder(url);
        this.youtubelink = youtubeSearch(artist, song);
    }

    public static String getUrl(String artistName, String songName) {// uses the basic genius url format to create a
                                                                     // working url
        String domain = "https://genius.com/";
        String url = domain + artistName.replaceAll(" ", "-") + "-";
        url += songName.replaceAll(" ", "-");
        url += "-lyrics";

        return url;
    }

    public static String lyrics(String url) { // uses url to use jsoup and scrape genius for their lyrics data

        String lyrics = "";
        try {
            final Document document = Jsoup.connect(url).get();

            Elements lyricsDiv = document.select(".lyrics");
            lyrics = Jsoup.clean(lyricsDiv.html(), Whitelist.none().addTags("br")).trim();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print(
                    "There was an error scraping the lyrics from the internet. Please check your spelling and try again.");
        }
        lyrics = lyrics.replaceAll("<br>", "");
        return lyrics;
    }

    public String youtubeSearch(String artistName, String songName) { // creates a search query for a song and then
                                                                      // scrapes the youtube html to find the video id
                                                                      // for the first result
        String yt = "https://www.youtube.com/watch?v=";
        try {
            // this creates the search url
            String query = artistName + " " + songName;
            String reformatedString = query.replaceAll(" ", "+");
            String search = "https://www.youtube.com/results?search_query=" + reformatedString;

            // creates the document containing the html
            Document doc = Jsoup.connect(search).get();
            PrintWriter out = new PrintWriter("Document.txt");
            out.println(doc);

            Scanner console;
            String substring = null;
            // scrapes the html for words and returns the videoId
            console = new Scanner(new File("Document.txt"));
            while (console.hasNext()) {
                String test = console.next();
                if (test.contains("\"videoId\":\"")) {
                    int index = test.indexOf("\"videoId\":\"");
                    substring = test.substring(index + 11, index + 22);
                    continue;
                }
            }
            out.close();
            console.close();
            yt += substring;

        } catch (Exception ex) {// handles the problems
            ex.printStackTrace();
            System.out.print("There was an error scraping yhe youtube page. Please try again.");
            System.exit(1);
        }
        return yt;
    }

    public static String genreFinder(String url) {// downloads genius metadata and scrapes it for the genre
        String substring = "error";
        try {
            Document doc = Jsoup.connect(url).get();// connects to genius

            PrintWriter out = new PrintWriter("GenreHTML.txt");
            out.println(doc);// creates a txt doc with genius html

            Scanner console = new Scanner(new File("GenreHTML.txt"));// scans it for proper words and returns the genre
                                                                     // found
            while (console.hasNext()) {
                String test = console.next();
                if (test.contains("tag:")) {
                    int index = test.indexOf("tag:");
                    int i = index;
                    int indexEndString = index + 4;
                    while (test.charAt(i) >= 'a' && test.charAt(i) <= 'z') {
                        i++;
                        indexEndString++;
                    }
                    substring = test.substring(index + 4, indexEndString);
                    continue;
                }
            }
            out.close();
            console.close();
        } catch (Exception ex) {// handles errors
            ex.printStackTrace();
            System.out.print("There was an error scraping metadata from Genius. Please try again.");
            System.exit(1);
        }
        // returns the properly formatted genre based on genius scraping

        if (substring.equalsIgnoreCase("cou")) {
            substring = "Country";
        }
        if (substring.equalsIgnoreCase("r-b")) {
            substring = "R and B";
        }
        if (substring.equalsIgnoreCase("non")) {
            substring = "Classical";
        }
        if (substring.equalsIgnoreCase("rap")) {
            substring = "Rap";
        }
        if (substring.equalsIgnoreCase("pop")) {
            substring = "Pop";
        }
        if (substring.equalsIgnoreCase("roc")) {
            substring = "Rock";
        }

        return substring;
        // Return Pop, Rap, Cou(country), Non(classical), r-b (R and B)
    }

}// end of Song
