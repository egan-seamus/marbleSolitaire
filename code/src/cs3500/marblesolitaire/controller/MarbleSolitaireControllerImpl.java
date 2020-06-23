package cs3500.marblesolitaire.controller;

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;


/**
 * A controller for the MarbleSolitaireModel.
 * Only has one public method, to play the game.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private final Readable input;
  private final Appendable output;
  private boolean gameIsRunning;
  private final Queue<Integer> moveList;

  /**
   * Constructs a new {@code MarbleSolitaireControllerImpl} object.
   *
   * @param rd the input to be used
   * @param ap the output to be used
   */
  public MarbleSolitaireControllerImpl(Readable rd, Appendable ap) throws IllegalArgumentException {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException("Cannot accept null I/O");
    } else {
      this.input = rd;
      this.output = ap;
      this.moveList = new LinkedList<Integer>();
    }
  }

  /**
   * Transmit a message to this implementation's output.
   * @param message   the message to be sent
   */
  private void transmit(String message) {
    try {
      this.output.append(message);
    } catch (Exception e) {
      throw new IllegalStateException("Unable to transmit output");
    }
  }


  /**
   * Handle the next argument.
   * If it is a valid integer, add it to the move list.
   * @param argument the next argument, interpreted as a string
   */
  private void handleArgument(String argument) {
    if (argument.equals("q") || argument.equals("Q")) {
      this.gameIsRunning = false;
      return;
    } else {
      try {
        int temp = Integer.parseInt(argument);
        if (temp < 1) {
          this.transmit("Unexpected negative input: " + argument + ". Please re-enter the value\n");
          return;
        }
        else {
          this.moveList.add(temp - 1);
        }

      } catch (Exception e) {
        this.transmit("Unexpected illegal input: " + argument + ". Please re-enter the value\n");
        return;
      }

    }

  }


  /**
   * Play the great game of marble solitaire with the given model.
   * @param model the implementation of the MSM that is being used.
   * @throws IllegalArgumentException if the given model is null.
   * @throws IllegalStateException if there is an error transmitting output or receiving input.
   */
  @Override
  public void playGame(MarbleSolitaireModel model)
          throws IllegalArgumentException, IllegalStateException {
    if (model == null) {
      throw new IllegalArgumentException("Cannot accept null game implementation");
    }
    this.gameIsRunning = true;
    Scanner scanner = new Scanner(this.input);

    this.transmit(model.getGameState() + "\n");
    this.transmit("Score: " + model.getScore() + "\n");

    while (scanner.hasNext()) {
      String nextArg = scanner.next();
      handleArgument(nextArg);
      if (!gameIsRunning) {
        this.transmit("Game quit!\n");
        this.transmit("State of game when quit:\n");
        break;
      }
      if (this.moveList.size() == 4) {
        try {
          model.move(this.moveList.remove(), this.moveList.remove(),
                  this.moveList.remove(), this.moveList.remove());
        } catch (Exception e) {
          this.transmit("Invalid move. Play again. Error: " + e.getMessage() + "\n");
        }
        this.moveList.clear();
      }

      if (model.isGameOver()) {
        this.transmit("Game over!\n");
        break;
      }

    }

    //if we run out of input before a quit or a game over
    //something has gone wrong
    if (!(model.isGameOver() || !gameIsRunning)) {
      throw new IllegalStateException("Unexpected end of input");
    }


    this.transmit(model.getGameState() + "\n");
    this.transmit("Score: " + model.getScore() + "\n");

  }

}
