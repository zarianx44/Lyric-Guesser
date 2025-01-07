/**
* @author: Zara Chaudhry 
* @version 1.0
* 
* GUI class: creates UI for the lyric guesser game 
* using different components.Includes file reading, action listners 
* and key listeners 
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


class GUI extends JFrame implements ActionListener, KeyListener {

  // initializing variables in the class to allow them to be used in the methods 
  JTextField text, textArea;
  JFrame instructionsFrame;
  JPanel topPanel, contentPane, inputPanel, selectionPanel, promptPanel, utilPanel, thanksPanel, instructionsPanel;
  JLabel welcomeDisplay, promptLabel, pointsLabel, gameComplete, instructions;
  JButton buttonConfirm, button2000, button2010, buttonZaras, nextButton, instructionsButton;
  LyricGuesser guessingGame;
  String username;

  /**
  * Cosntructor method for GUI classs, initializes all components
  */
  public GUI() {
    instructionsFrame = new JFrame("instructions");  // creating a frame to pop up when the user wants to see how to play the game
    
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // making the default action for when the 'x' button is pressed on the GUI to stop running the program
    this.setSize(700, 300); // setting the size of the frame
    this.setTitle("Lyric Guesser"); // setting the window title
    instructionsFrame.setSize(700,200); // setting the size of the window that will pop up for instructions

    instructionsPanel = new JPanel();  // creating a panel to hold the text that will be displayed as instructions 
    instructions = new JLabel("<html>"+ "welcome to lyric guesser." + "<br />" + "to play this game you must select a genre category to start the game." + "<br />"+ "a lyric from the specified genre will appear and you must guess the missing word!" + "<br /> " + "goodluck and have fun!" + "<br />" + "</html>"); // this line includes the instructions on how to play the game (source: https://stackoverflow.com/questions/10478420/jlabel-doesnt-show-a-long-paragraph)
    instructions.setAlignmentX(Component.CENTER_ALIGNMENT); // centering the text

    instructionsPanel.add(instructions); // adding the text to the panel, which is then added to the frame. this allows it to show up when declared visible
    instructionsFrame.add(instructionsPanel); 

    // initializing more JPanels
    contentPane = new JPanel();
    inputPanel = new JPanel();
    promptPanel = new JPanel();
    this.setContentPane(contentPane);  // setting the contentPane of the main frame to contentPane

    // applying a box layout to different panels for a good formatting of the program
    promptPanel.setLayout(new BoxLayout(promptPanel, BoxLayout.Y_AXIS));
    inputPanel.setLayout(new FlowLayout());

    // creating a label to display the lyrics and alligning it in the center 
    promptLabel = new JLabel("");
    promptLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    promptPanel.add(promptLabel); //adding the label to the correct panel so it is in the proper position 

    // creating more panels
    selectionPanel = new JPanel();
    utilPanel = new JPanel();
    thanksPanel = new JPanel(); 
    topPanel = new JPanel();
    topPanel.setBackground(Color.lightGray); // setting the background color of a panel to light gray to let the game have a nice neutral colour

    welcomeDisplay = new JLabel("welcome to lyric guesser! choose a username:"); // 
    welcomeDisplay.setAlignmentX(Component.CENTER_ALIGNMENT);
    inputPanel.add(welcomeDisplay);

    text = new JTextField(7);
    text.setBounds(100, 100, 10, 10);
    text.setAlignmentX(Component.CENTER_ALIGNMENT);
    inputPanel.add(text);

    buttonConfirm = new JButton("confirm");
    buttonConfirm.addActionListener(this);
    buttonConfirm.setAlignmentX(Component.CENTER_ALIGNMENT);
    inputPanel.add(buttonConfirm);

    button2000 = new JButton("2000s throwbacks");
    button2000.setBounds(500, 150, 70, 40);
    button2000.addActionListener(this);
    selectionPanel.add(button2000);

    button2010 = new JButton("2010 hits");
    button2010.setBounds(50, 150, 30, 40);
    button2010.addActionListener(this);
    selectionPanel.add(button2010);

    buttonZaras = new JButton("zara's picks");
    buttonZaras.setBounds(50, 150, 30, 40);
    buttonZaras.addActionListener(this);
    selectionPanel.add(buttonZaras);

    instructionsButton = new JButton("how to play");
    instructionsButton.addActionListener(this);
    instructionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    selectionPanel.add(instructionsButton);

    //creating text areas for the user to input their guess
    textArea = new JTextField(50);
    textArea.addKeyListener(this); // adding a key listener to the text area to be able to detect when the user presses enter to submit a guess 
    textArea.setEditable(false); // this doesn't allow the user to change their guess / press / input anything 
    promptPanel.add(textArea);

    nextButton = new JButton("next");
    nextButton.setBounds(50, 150, 30, 40);
    nextButton.addActionListener(this);
    nextButton.setEnabled(false); // doesn't allow the user to press the button (source: https://stackoverflow.com/questions/1625855/how-to-disable-javax-swing-jbutton-in-java)
    utilPanel.add(nextButton);

    pointsLabel = new JLabel("points: 0");
    utilPanel.add(pointsLabel);

    gameComplete = new JLabel("thank you for playing lyric guesser! feel free to continue playing with the other genres.");
    gameComplete.setForeground(Color.green); 
    thanksPanel.add(gameComplete); 
  
  
    contentPane.add(inputPanel);
    contentPane.add(selectionPanel);
    contentPane.add(promptPanel);
    contentPane.add(utilPanel);
    contentPane.add(thanksPanel); 

    selectionPanel.setVisible(false);
    promptPanel.setVisible(false);
    utilPanel.setVisible(false);
    thanksPanel.setVisible(false);
    this.setVisible(true);

  }

  /**
  * Implement actionPerformed method inherited from ActionListner interface
  * Define action to be taken when actionEvents from button are activated 
  *
  * @param: ActionEvent event : event passed by button 
  */
  public void actionPerformed(ActionEvent event) {
    Object src = event.getSource();
    
    // Confirm button was pressed
    if (src == buttonConfirm) {
      username = text.getText();
      
      try {
        // initialize the file writer
        FileWriter fw = new FileWriter("players.txt", true);
        PrintWriter fileOut = new PrintWriter(fw);
        
        // add the username to the players file
        fileOut.println(username);
        fileOut.close();
        
        //make components that werer hidden visible - game starts
        text.setVisible(false);
        buttonConfirm.setVisible(false);

        welcomeDisplay.setText("welcome to lyric guesser, " + username + "! select a genre:");

        selectionPanel.setVisible(true);
        promptPanel.setVisible(true);
        utilPanel.setVisible(true);
        
      } 
      catch (IOException e) {
        System.out.println("Error: " + e);
      }
    }

    // 2000s button was pressed
    else if (src == button2000) {
      thanksPanel.setVisible(false);
      
      button2000.setBackground(Color.pink);
      button2000.setForeground(Color.white);

      // create new lyricsGuesser object to start the game with the 2000s genre
      guessingGame = new LyricGuesser(readAnswers("2000s"), readLyrics("2000s"), readArtist("2000s"));

      // reset the Field and buttons when a new game starts
      resetGame();
      
      // print out the first prompt for the new game
      promptLabel.setText(guessingGame.getLyrics(0));

      // enable the text area so the user can type
      textArea.setEditable(true);
      
    }

    // 2010s button was pressed
    else if (src == button2010) {
      thanksPanel.setVisible(false);
      
      button2010.setBackground(Color.pink);
      button2010.setForeground(Color.white);

      // create new lyricsGuesser object to start the game with the 2010s genre
      guessingGame = new LyricGuesser(readAnswers("2010s"), readLyrics("2010s"), readArtist("2010s"));

      // reset the Field and buttons when a new game starts
      resetGame();
      
      // print out the first prompt for the new game
      promptLabel.setText(guessingGame.getLyrics(0));

      // enable the text area so the user can type
      textArea.setEditable(true);
    }

    // Zara's button was pressed
    else if (src == buttonZaras) {
      thanksPanel.setVisible(false); 
      
      buttonZaras.setBackground(Color.pink);
      buttonZaras.setForeground(Color.white);
      
      // create new lyricsGuesser object to start the game with the zara genre
      guessingGame = new LyricGuesser(readAnswers("Zaras"), readLyrics("Zaras"), readArtist("Zaras"));


      // reset the Field and buttons when a new game starts
      resetGame();
      
      // print out the first prompt for the new game
      promptLabel.setText(guessingGame.getLyrics(0));

      // enable the text area so the user can type
      textArea.setEditable(true);
    }

    // next button was pressed
    else if (src == nextButton) {
      
      // check if we have answered the last question 
      if(guessingGame.getLyricCount() == 9){
        // finsihed
        thanksPanel.setVisible(true);
        nextButton.setEnabled(false);
      }
        
      // display the next prompt
      else{
        guessingGame.increaseLyricCount();
        promptLabel.setText(guessingGame.getLyrics());
        // clear and enable the text area
        textArea.setEditable(true);
        textArea.setText("");  
      }
      
      // disable the next button till the user has submitted an asnwer
      nextButton.setEnabled(false);
      
    }

    // instructions button was pressed
    else if (src == instructionsButton){
      instructionsFrame.setVisible(true); 
    }
  }

  /**
   * Method inherited from KeyListener. Executes when key event happens
   * @param e : KeyEvent
   */
  public void keyReleased(KeyEvent e) {
      // enter key is pressed
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      // enable the next button, and remove focus from the text area so the user
      // cant keep editing it after it is disabled
      nextButton.setEnabled(true);
      nextButton.requestFocus();  
    }
  }

  /**
   * Method inherited from KeyListener. Do Nothing
   * @param e : KeyEvent 
   */
  public void keyTyped(KeyEvent e) {
    // Do nothing
  }
  

  /**
   * Method inherited from KeyListener. Executes whenever key event happens.
   * Enter key pressed handles answer
   * @param e : KeyEvent 
   */
  public void keyPressed(KeyEvent e) {
    // enter key is pressed
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      // take the user input 
      guessingGame.setGuess(textArea.getText());
      
      // check if it is right
      if(guessingGame.isRight(guessingGame.getGuess())){
        
          // if it is correct, display correct, and increase points
          promptLabel.setText("correct! answer: " + guessingGame.getAnswer(guessingGame.getLyricCount()) + ", song: " + guessingGame.getSong(guessingGame.getLyricCount()));
        
          guessingGame.increasePoints(); 
        
          pointsLabel.setText("points: " + guessingGame.getPoints());
        
        }
        else{
          // if the guess is wrong 
          promptLabel.setText("incorrect, answer: " + guessingGame.getAnswer(guessingGame.getLyricCount()) + ", song: " + guessingGame.getSong(guessingGame.getLyricCount()));
          
        }
      // make the text area not editable after having submitted an answer
      textArea.setEditable(false);
    }
  }

  /**
   * Method to read the Lyrics from the text file
   * @param genre : Genre that user selected 
   * @return String[] array of Lyrics taken from file
   */
  public static String[] readLyrics(String genre) {
    
    String lyrics[] = new String[10];
    int endLyricIndex = 0;
    FileReader fr;

    try {
      // read from 2000s file 
      if (genre.equalsIgnoreCase("2000s")) {
        fr = new FileReader("2000sLyrics.txt");
      } 
      // read from 2010s file 
      else if (genre.equalsIgnoreCase("2010s")) {
        fr = new FileReader("2010sLyrics.txt");
      } 
      // read from zara's file
      else if (genre.equalsIgnoreCase("Zaras")) {
        fr = new FileReader("ZarasChoices.txt");
      } 
        
      else {
        System.out.println("File not specified");
        return lyrics;
      }

      BufferedReader br = new BufferedReader(fr);
      String line = br.readLine();

      // for loop and read all lines 
      for (int i = 0; i < lyrics.length; i++) {
        endLyricIndex = line.indexOf(":");

        // get just the lyrics
        lyrics[i] = line.substring(0, endLyricIndex);

        line = br.readLine();
      }
      
      br.close();

      return lyrics;
      
    } 
    catch (IOException e) {
      System.out.println("Error in lyric file reading");
      return lyrics;
    }
  }

  /**
   * Method to read the Answers from the text file
   * @param genre : Genre that user selected 
   * @return String[] array of answers taken from file
   */
  public static String[] readAnswers(String genre) {
    String answers[] = new String[10];
    int endLyricIndex = 0;
    int startArtistIndex = 0;
    FileReader fr;

    try {
      // read from 2000s file 
      if (genre.equalsIgnoreCase("2000s")) {
        fr = new FileReader("2000sLyrics.txt");
      } 
      // read from 2010s file 
      else if (genre.equalsIgnoreCase("2010s")) {
        fr = new FileReader("2010sLyrics.txt");
      } 
      // read from zara's file
      else if (genre.equalsIgnoreCase("Zaras")) {
        fr = new FileReader("ZarasChoices.txt");
      } 
      else {
        System.out.println("File not specified");
        return answers;
      }
      
      BufferedReader br = new BufferedReader(fr);
      String line = br.readLine();

      // for loop and read each line
      for (int i = 0; i < answers.length; i++) {
        endLyricIndex = line.indexOf(":");
        startArtistIndex = line.lastIndexOf(":");
        
        // take just the answer
        answers[i] = line.substring(endLyricIndex + 1, startArtistIndex);
        line = br.readLine();

      }
      
      br.close();
      return answers;
      
    } 
    catch (IOException e) {
      System.out.println("Error in answers file reading.");
      return answers;
    }
  }

  /**
   * Method to read the Artists from the text file
   * @param genre : Genre that user selected 
   * @return String[] array of Artists taken from file
   */
  public static String[] readArtist(String genre) {
    String artist[] = new String[10];
    int startArtistIndex = 0;
    FileReader fr;

    try {
      // read from 2000sLyrics file 
      if (genre.equalsIgnoreCase("2000s")) {
        fr = new FileReader("2000sLyrics.txt");
      } 
      // read from 2010sLyrics file 
      else if (genre.equalsIgnoreCase("2010s")) {
        fr = new FileReader("2010sLyrics.txt");
      } 
      // read from ZarasChoices file
      else if (genre.equalsIgnoreCase("Zaras")) {
        fr = new FileReader("ZarasChoices.txt");
      } 
      else {
        System.out.println("File not specified");
        return artist;
      }

      BufferedReader br = new BufferedReader(fr);
      String line = br.readLine();

      // for loop and read each line
      for (int i = 0; i < artist.length; i++) {
        startArtistIndex = line.lastIndexOf(":");
        // take just the artist 
        artist[i] = line.substring(startArtistIndex + 1);
        line = br.readLine();
      }

      br.close();
      return artist;
      
    } 
    catch (IOException e) {
      System.out.println("Error in artist file reading: " + e);
      return artist;
    }
  }
  /**
  * Implement actionPerformed method inherited from ActionListner interface
  * Define action to be taken when actionEvents from button are activated 
  *
  * @param: ActionEvent event : event passed by button 
  */
  /**
   * Method to reset the TextField and scores when a new genre is selected
   */
  public void resetGame() {
    textArea.setText(""); // removes the text in the jtextfield, (source: https://docs.oracle.com/javase/8/docs/api/javax/swing/JTextField.html)
    pointsLabel.setText("points: 0");
    
  }
}