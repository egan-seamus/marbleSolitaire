import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import org.junit.Test;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

/**
 * Contains the tests for testing various inputs.
 */
public class TestInput {
  MarbleSolitaireModel theGame;
  MarbleSolitaireController theController;
  StringReader input;
  StringBuffer output;

  @Test
  //test a game played with a ton of moves that leads to a game over
  public void testMostlyDoneGame() {
    theGame = new MarbleSolitaireModelImpl();
    input = new StringReader("4 2 4 4 \n 6 3 4 3 \n 3 3 5 3 \n "
            + "6 5 6 3 \n 4 5 6 5 \n 7 5 5 5 \n 4 7 4 5 \n 4 4 4 6 \n 1 3 3 3 \n"
            + "1 5 1 3 \n 2 4 4 4 \n 2 5 4 5 \n 6 3 4 3 \n 4 3 2 3 \n 1 3 3 3 \n"
            + "3 2 3 4 \n 5 1 5 3 \n 3 1 5 1 \n 5 4 5 2 \n 5 1 5 3 \n 7 3 7 5 \n"
            + "4 5 6 5 \n 7 5 5 5 \n 3 7 3 5 \n 3 4 5 4 \n 5 6 3 6 \n 5 4 5 6 \n"
            + "5 7 5 5 \n 3 6 3 4");
    output = new StringBuffer();
    theController = new MarbleSolitaireControllerImpl(input, output);
    theController.playGame(theGame);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game over!\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "_ _ _ O _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ O _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 3\n", output.toString());


  }

  @Test
  //test a game played with a ton of moves that leads to a game over
  public void testGoofyInput() {
    theGame = new MarbleSolitaireModelImpl();
    input = new StringReader("4 2 4 4 \n 6 3 4 3 \n 3 3 5 3 boop beep \n "
            + "6 5 6 3 \n 4 5 6 5 \n 7 5 5 5 \n 4 7 4 5 \n 4 4 4 6 \n 1 3 3 3 \n"
            + "1 5 1 3 \n 2 4 4 4 \n 2 5 4 5 \n 6 3 4 3 \n 4 3 2 3 \n 1 3 3 3 \n"
            + "3 2 3 4 \n 5 1 5 3 \n 3 1 garbage 5 1 \n 5 4 5 2 \n 5 1 5 3 \n 7 3 7 5 \n"
            + "4 5 6 5 \n 7 5 5 5 \n 3 7 3 5 \n 3 4 5 4 \n 5 6 3 6 \n 5 4 5 6 \n"
            + "5 7 5 5 \n 3 6 3 4");
    output = new StringBuffer();
    theController = new MarbleSolitaireControllerImpl(input, output);
    theController.playGame(theGame);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Unexpected illegal input: boop. Please re-enter the value\n" +
            "Unexpected illegal input: beep. Please re-enter the value\n" +
            "Unexpected illegal input: garbage. Please re-enter the value\n" +
            "Game over!\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "_ _ _ O _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ O _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 3\n", output.toString());


  }

  @Test
  //test a game played with a ton of moves and a quit
  public void testQuit() {
    theGame = new MarbleSolitaireModelImpl();
    input = new StringReader("4 2 4 4 \n 6 3 4 3 \n 3 3 5 3 \n "
            + "6 5 6 3 \n 4 5 6 5 \n 7 5 5 5 \n 4 7 4 5 \n 4 4 4 6 \n 1 3 3 3 \n"
            + "1 5 1 3 \n 2 4 4 4 \n 2 5 4 5 \n 6 3 4 3 \n 4 3 2 3 \n 1 3 3 3 \n"
            + "3 2 3 4 \n 5 1 5 3 \n 3 1 5 1 \n 5 4 5 2 \n 5 1 5 3 \n 7 3 7 5 \n"
            + "4 5 6 5 \n 7 5 5 5 \n 3 7 3 5 \n 3 4 5 4 \n 5 6 3 6 \n 5 4 5 6 \n"
            + "5 7 5 5 \n q");
    output = new StringBuffer();
    theController = new MarbleSolitaireControllerImpl(input, output);
    theController.playGame(theGame);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "_ _ _ _ O O _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ O _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 4\n", output.toString());


  }

  @Test
  //test a game played with a ton of moves that leads to a game over, and then send a quit
  public void testQuitAfterGameOver() {
    theGame = new MarbleSolitaireModelImpl();
    input = new StringReader("4 2 4 4 \n 6 3 4 3 \n 3 3 5 3 \n "
            + "6 5 6 3 \n 4 5 6 5 \n 7 5 5 5 \n 4 7 4 5 \n 4 4 4 6 \n 1 3 3 3 \n"
            + "1 5 1 3 \n 2 4 4 4 \n 2 5 4 5 \n 6 3 4 3 \n 4 3 2 3 \n 1 3 3 3 \n"
            + "3 2 3 4 \n 5 1 5 3 \n 3 1 5 1 \n 5 4 5 2 \n 5 1 5 3 \n 7 3 7 5 \n"
            + "4 5 6 5 \n 7 5 5 5 \n 3 7 3 5 \n 3 4 5 4 \n 5 6 3 6 \n 5 4 5 6 \n"
            + "5 7 5 5 \n 3 6 3 4 \n q");
    output = new StringBuffer();
    theController = new MarbleSolitaireControllerImpl(input, output);
    theController.playGame(theGame);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game over!\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "_ _ _ O _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ O _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 3\n", output.toString());


  }

  @Test
  public void testBadMoveThenGoodMove() {
    theGame = new MarbleSolitaireModelImpl();
    input = new StringReader("4 2 4 4 \n 4 2 4 4 \n 6 3 4 3 \n q");
    output = new StringBuffer();
    theController = new MarbleSolitaireControllerImpl(input, output);
    theController.playGame(theGame);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move. Play again. Error: Illegal Move\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "O O _ O O O O\n" +
            "    _ O O\n" +
            "    O O O\n" +
            "Score: 30\n", output.toString());
  }

  @Test
  public void testBadMove() {
    theGame = new MarbleSolitaireModelImpl();
    input = new StringReader("4 2 4 4 \n 4 2 4 4 q");
    output = new StringBuffer();
    theController = new MarbleSolitaireControllerImpl(input, output);
    theController.playGame(theGame);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move. Play again. Error: Illegal Move\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n", output.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void testStopShort() {
    theGame = new MarbleSolitaireModelImpl();
    input = new StringReader("4 2 4");
    output = new StringBuffer();
    theController = new MarbleSolitaireControllerImpl(input, output);
    theController.playGame(theGame);
  }

  @Test(expected = IllegalStateException.class)
  public void testEmptyInput() {
    theGame = new MarbleSolitaireModelImpl();
    input = new StringReader("");
    output = new StringBuffer();
    theController = new MarbleSolitaireControllerImpl(input, output);
    theController.playGame(theGame);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullInput() {
    theGame = new MarbleSolitaireModelImpl();
    input = null;
    output = new StringBuffer();
    theController = new MarbleSolitaireControllerImpl(input, output);
    theController.playGame(theGame);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullInput2() {
    theGame = new MarbleSolitaireModelImpl();
    input = new StringReader("");
    output = null;
    theController = new MarbleSolitaireControllerImpl(input, output);
    theController.playGame(theGame);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullGame() {
    theGame = null;
    input = new StringReader("4 2 4 4 \n 6 3 4 3 \n 3 3 5 3 \n "
            + "6 5 6 3 \n 4 5 6 5 \n 7 5 5 5 \n 4 7 4 5 \n 4 4 4 6 \n 1 3 3 3 \n"
            + "1 5 1 3 \n 2 4 4 4 \n 2 5 4 5 \n 6 3 4 3 \n 4 3 2 3 \n 1 3 3 3 \n"
            + "3 2 3 4 \n 5 1 5 3 \n 3 1 5 1 \n 5 4 5 2 \n 5 1 5 3 \n 7 3 7 5 \n"
            + "4 5 6 5 \n 7 5 5 5 \n 3 7 3 5 \n 3 4 5 4 \n 5 6 3 6 \n 5 4 5 6 \n"
            + "5 7 5 5 \n 3 6 3 4");
    output = new StringBuffer();
    theController = new MarbleSolitaireControllerImpl(input, output);
    theController.playGame(theGame);
  }
}
