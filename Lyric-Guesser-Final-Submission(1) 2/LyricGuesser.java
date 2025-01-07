import java.util.Arrays;
import java.util.Scanner;

/**
 * this class is used to store the nessacary variables and methods of lyrics, 
 * answers and song names or artists for a Lyric Guesser game. It allows the 
 * main program to run efficiently and protect information. 
 * @author Zara
 */

class LyricGuesser{
  Scanner console = new Scanner(System.in);// creating a console for the outGuess() method and its error checking
  private String[] answers;// creating 3 lists to store the answers to the lyric guesser, the lyrics that will be displayed (including the blank spots), and the song and artist to display at the end for anyone curious 
  private String[] lyrics;
  private String[] artist;
  private String guess; // variable to store the users guess 
  private static int points; // variable to store the number of points 
  private int lyricCount;  // variable to keep track of what lyric they are on (to know what lyric/index to display)
  private String username; // variable to store the users name 

  /**
   * Constructor method that accepts 3 lists containing the answers, list of 
   * lyrics and list of authors
   * @param ans : answers
   * @param l : lyrics
   * @param a : authors
   */
  public LyricGuesser(String[] answers, String[] lyrics, String[] artist){
    // assigning variables the default/given values
    this.answers = answers;
    this.lyrics = lyrics; 
    this.artist = artist; 
    points = 0; 
    lyricCount = 0;
  }

  /**
   * Method to print the proper texts to prompt the user for input/to chose a guess
   */
  public void outGuess(){
    System.out.println("Your Guess: "); // asking the user to enter their guess 
    while(!console.hasNextLine()){ // if the user doesn't enter a string, it will output an error message
      console.nextLine(); // flushing out the console to prevent an infinite loop
      System.out.println("Not a valid answer, please try again"); 
      System.out.println("Your Guess: "); 
    }
    
    guess = console.nextLine();
    // assigning the guess variable to what the user entered

  }

  /**
   * Method that checks if the guess the user gave is right
   * @param g : user's guess
   * @return true if guess was right, false if it was wrong
   */
  public boolean isRight(String g){ // this method is used to check if the users guess is correct 
    String c = ""; // creating a variable that's an empty string which is used as the replacement for any spaces in the guess
    if(answers[lyricCount].equalsIgnoreCase(guess.replaceAll(" ", c))){ // if the answer at the certain lyric that is being displayed equals the users guess without any spaces and ignoring the possible capitals, return true (meaning they guessed correctly)
      return true;
    }
    // otherwise return false
    return false;
  }

  /**
   * method to get the user's guess
   * @return String : user's guess
   */
  public String getGuess(){
    return guess;
  }

  /**
   * Method to return a certain lyric at a specific index (since they are 
   * stored in an array)
   * @param n : position in the array to search 
   * @return String : the lyrics in that position
   */
  public String getLyrics(int n){
    if(n >= 0){ // checking if the number entered is valid (because there is no such this as an index of -1)
      return lyrics[n]; // returning the specified lyric
    }
    // if the value passed through in the paramater isn't valid, the method will return "Error"
     return "Error"; 
  }


  /**
   * overload method returns the lyrics but without the index being specified,
   * it instead returns the lyric at the current lyricCount
   * @return String : Lyrics at the position
   */
  public String getLyrics(){
    return lyrics[lyricCount]; // returning the specified lyric
  }

  /**
   * Method to return the value of points 
   * @return int : the points
   */
  public static int getPoints(){
    return points; 
  }

  /**
   * Method to return the song and artist at a specified index which is 
   * passed through as int n in the parameters
   * @param n : position to get the song from
   * @return String : the song at that position
   */
  public String getSong(int n){
    if(n >= 0){ // checking if the paramater is a valid number to prevent any errors
      return artist[n]; 
    } 
    // if the number is not valid the program would return "Error"
    return "Error"; 
  }

  /**
   * this method allows the variable guess to be set to any value
   * @param g String: string to set guess to
   */
  public void setGuess(String g){
    guess = g; 
  }

  /**
   * Method to allow points to be set to any value
   * @param n int : value to set the points to
   */
  public static void setPoints(int n){
    if(n >= 0){ // the variable 'points' should not be less than 0. this if statements checks if n is a valid number before assigning 'points' to it
      points = n;  
    }
  }
  
  /**
   * Method to increase the variable points by 1, it is useful to
   * use when the user guesses the right answer 
   */
  public static void increasePoints(){
    points++; // increase points by 1 
  }

  /**
   * Method to set the variable lyricCount to a value.
   * @param m int: value to set lyricsCount to
   */
  public void setLyricCount(int n){
    if(n < 0 || n > 6){ // checking if the value is less than 0 or greater than 6 it cannot be over 6 or less than 0 because there are maximum 6 lyrics for each category
      System.out.println("Error");
    }
    else{ // if it meets the nessacary conditions, lyricCount will be set to the value of n
      lyricCount = n;
    }
  }

  /**
   * Method to increase the value of lyricCount
   * @return true if the count is within the range of lyrics, false if it is not
   */
  public boolean increaseLyricCount(){
    if(lyricCount <= this.getListLength("lyrics")){ // checking that the current value of lyricCount does not exceed the maximum amount of lyrics 
      lyricCount++; 
    } 
    else {
      return false; //past the last lyrics
    }

    return true;
  }

  /**
   * Method to get the value of lyricCount
   * @return 
   */
  public int getLyricCount(){
    return lyricCount; 
  }

  // this method is used to return the length of a specified array
  /**
   * Method to return the length of a specified array 
   * @param name String : array to return length of 
   * @return length of the array 
   */
  public int getListLength(String name){
    // if "lyrics" is passed through as a parameter, return the length of the lyrics array
    if(name.equalsIgnoreCase("lyrics")){
      return lyrics.length; 
    }
    // if "answers" is passed through as a parameter, return the length of the answers array
    if(name.equalsIgnoreCase("answers")){
      return answers.length;
    }
    // if "song" (or "songs") is passed through as a parameter, return the length of the songs array
    if(name.equalsIgnoreCase
      ("song") || name.equalsIgnoreCase
      ("songs") ){
      return artist.length;
    }

    // if none of the 3 possible array names were passed through, return -1. 
    return -1;
  }

  /**
   * Method to return the answer at a specified index
   * @param n int : index to return 
   * @return String : answer at the index
   */
  public String getAnswer(int n){
    if(n >= 0){ // error checking if the parameter value is valid 
      return answers[n];
    }
    // if the value isn't valid, return "Error"
    return "Error"; 
  }


  // end of class
}